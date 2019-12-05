package ru.myhw.task6.serializer.formats;

import ru.myhw.task6.serializer.SerializationContext;

import static ru.myhw.task6.serializer.Utils.fillIndent;

public class XMLFormat implements SerializationFormat {
    private final int indent;

    public XMLFormat(int indent) {
        this.indent = indent;
    }

    @Override
    public void serializePrimitive(StringBuilder builder, SerializationContext context, Object value) {
        if (value != null)
            builder.append(value.toString());
    }

    @Override
    public void serializeBeforeKeyValue(StringBuilder builder, SerializationContext context, Object key) {
        fillIndent(builder, context.getIndent());
        writeStartTag(builder, key);
    }

    @Override
    public void serializeAfterKeyValue(StringBuilder builder, SerializationContext context, Object key) {
        writeEndTag(builder, key);
        builder.append("\n");
    }

    @Override
    public void serializeBeforeCollectionElement(StringBuilder builder, SerializationContext context) {
        serializeBeforeKeyValue(builder, context, "element");
    }

    @Override
    public void serializeAfterCollectionElement(StringBuilder builder, SerializationContext context) {
        serializeAfterKeyValue(builder, context, "element");
    }

    @Override
    public void init(StringBuilder builder, SerializationContext context, Object object) {
        writeStartTag(builder, object.getClass().getName());
    }

    @Override
    public void finish(StringBuilder builder, SerializationContext context, Object object) {
        writeEndTag(builder, object.getClass().getName());
    }

    @Override
    public void initMap(StringBuilder builder, SerializationContext context) {
        initBlock(builder, context);
    }

    @Override
    public void finishMap(StringBuilder builder, SerializationContext context) {
        finishBlock(builder, context);
    }

    @Override
    public void initCollection(StringBuilder builder, SerializationContext context) {
        initBlock(builder, context);
    }

    @Override
    public void finishCollection(StringBuilder builder, SerializationContext context) {
        finishBlock(builder, context);
    }

    private void initBlock(StringBuilder builder, SerializationContext context) {
        builder.append("\n");
        context.addIndent(this.indent);
    }

    private void finishBlock(StringBuilder builder, SerializationContext context) {
        context.deleteIndent();
        fillIndent(builder, context.getIndent());
    }

    private void writeStartTag(StringBuilder builder, Object key) {
        builder.append("<").append(key.toString()).append(">");
    }

    private void writeEndTag(StringBuilder builder, Object key) {
        builder.append("</").append(key.toString()).append(">");
    }
}
