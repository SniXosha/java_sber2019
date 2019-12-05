package ru.myhw.task6.serializer.formats;

import ru.myhw.task6.serializer.SerializationContext;

public interface SerializationFormat {
    void serializePrimitive(StringBuilder builder, SerializationContext context, Object value);

    void serializeBeforeKeyValue(StringBuilder builder, SerializationContext context, Object key);

    void serializeAfterKeyValue(StringBuilder builder, SerializationContext context, Object key);

    void serializeBeforeCollectionElement(StringBuilder builder, SerializationContext context);

    void serializeAfterCollectionElement(StringBuilder builder, SerializationContext context);

    void init(StringBuilder builder, SerializationContext context, Object object);

    void finish(StringBuilder builder, SerializationContext context, Object object);

    void initMap(StringBuilder builder, SerializationContext context);

    void finishMap(StringBuilder builder, SerializationContext context);

    void initCollection(StringBuilder builder, SerializationContext context);

    void finishCollection(StringBuilder builder, SerializationContext context);
}
