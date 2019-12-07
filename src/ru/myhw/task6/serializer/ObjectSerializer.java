package ru.myhw.task6.serializer;

import ru.myhw.task6.serializer.formats.SerializationFormat;

import java.io.Serializable;
import java.util.*;

import static ru.myhw.task6.serializer.Utils.getDeclaredFields;

public class ObjectSerializer implements Serializer {

    private final SerializationFormat format;

    public ObjectSerializer(SerializationFormat format) {
        this.format = format;
    }

    @Override
    public String serialize(Object obj) {
        return format.finishSerialization(obj, serializeDeclaredFields(obj));
    }

    private String serializeDeclaredFields(Object obj) {
        return serializeMap(getDeclaredFields(obj));
    }

    private String serializeObject(Object obj) {
        if (obj == null) {
            return format.serializePrimitive(null);
        }
        Class<?> clazz = obj.getClass();
        if (Collection.class.isAssignableFrom(clazz)) {
            return serializeCollection((Collection<?>) obj);
        } else if (Map.class.isAssignableFrom(clazz)) {
            return serializeMap((Map<?, ?>) obj);
        } else if (isSimple(clazz)) {
            return format.serializePrimitive(obj);
        } else {
            return serializeDeclaredFields(obj);
        }
    }

    private String serializeCollection(Collection<?> collection) {
        List<String> serializedElements = new ArrayList<>();
        collection.forEach(v -> serializedElements.add(serializeObject(v)));
        return format.combineList(serializedElements);
    }

    private String serializeMap(Map<?, ?> map) {
        Map<String, String> serializedElements = new HashMap<>();
        map.forEach((k, v) -> serializedElements.put(k.toString(), serializeObject(v)));
        return format.combineMap(serializedElements);
    }

    private boolean isSimple(Class<?> type) {
        return type.isPrimitive() || Serializable.class.isAssignableFrom(type);
    }
}
