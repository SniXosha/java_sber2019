package ru.myhw.task6.serializer;

import ru.myhw.task6.serializer.formats.SerializationFormat;

import java.io.Serializable;
import java.util.*;

import static ru.myhw.task6.serializer.Utils.getDeclaredFields;

public class RecursiveSerializer implements Serializer {

    private final SerializationFormat format;
    private final StringBuilder builder;
    private final SerializationContext context;

    public RecursiveSerializer(SerializationFormat format) {
        this.format = format;
        this.builder = new StringBuilder();
        this.context = new SerializationContext();
    }

    @Override
    public String serialize(Object obj) {
        format.init(builder, context, obj);
        serializeDeclaredFields(obj);
        format.finish(builder, context, obj);
        return builder.toString();
    }

    private void serializeDeclaredFields(Object obj) {
        Map<String, Object> declaredFields = getDeclaredFields(obj);
        serializeMap(declaredFields);
    }

    private void serializeObject(Object obj) {
        if (obj == null) {
            format.serializePrimitive(builder, context, null);
            return;
        }
        Class<?> clazz = obj.getClass();
        if (Collection.class.isAssignableFrom(clazz)) {
            serializeCollection((Collection<?>) obj);
        } else if (Map.class.isAssignableFrom(clazz)) {
            serializeMap((Map<?, ?>) obj);
        } else if (isSimple(clazz)) {
            format.serializePrimitive(builder, context, obj);
        } else {
            serializeDeclaredFields(obj);
        }
    }

    private void serializeCollection(Collection<?> collection) {
        boolean isPrimitive = collection.stream().allMatch(e -> isSimple(e.getClass()));
        context.setIsPrimitiveArray(isPrimitive);

        format.initCollection(builder, context);
        serializeCollectionEntries(collection);
        format.finishCollection(builder, context);
    }

    private void serializeCollectionEntries(Collection<?> collection) {
        int i = 0;
        int size = collection.size();
        for (Object obj : collection) {
            format.serializeBeforeCollectionElement(builder, context);
            serializeObject(obj);

            context.setIsLast(i == size - 1);
            format.serializeAfterCollectionElement(builder, context);
            i++;
        }
    }

    private void serializeMap(Map<?, ?> map) {
        format.initMap(builder, context);
        serializeMapEntries(map);
        format.finishMap(builder, context);
    }

    private void serializeMapEntries(Map<?, ?> map) {
        int i = 0;
        int size = map.size();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            format.serializeBeforeKeyValue(builder, context, entry.getKey().toString());
            serializeObject(entry.getValue());

            context.setIsLast(i == size - 1);
            format.serializeAfterKeyValue(builder, context, entry.getKey().toString());
            i++;
        }
    }

    private boolean isSimple(Class<?> type) {
        return type.isPrimitive() || Serializable.class.isAssignableFrom(type);
    }
}
