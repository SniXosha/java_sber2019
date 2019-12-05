package ru.myhw.task6.serializer.formats;

import ru.myhw.task6.serializer.SerializationContext;

import static ru.myhw.task6.serializer.Utils.fillIndent;

public class JSONFormat implements SerializationFormat {

    private final int indent;

    public JSONFormat(int indent) {
        this.indent = indent;
    }

    @Override
    public void serializePrimitive(StringBuilder builder, SerializationContext context, Object value) {
        builder.append(getPrimitiveString(value));
    }

    @Override
    public void serializeBeforeKeyValue(StringBuilder builder, SerializationContext context, Object key) {
        fillIndent(builder, context.getIndent());
        String jsonKey = "\"" + key + "\": ";
        builder.append(jsonKey);
        context.addIndent(jsonKey.length());
    }

    @Override
    public void serializeAfterKeyValue(StringBuilder builder, SerializationContext context, Object key) {
        if (!context.isLast()) {
            builder.append(",");
        }
        builder.append("\n");
        context.deleteIndent();
    }

    @Override
    public void serializeBeforeCollectionElement(StringBuilder builder, SerializationContext context) {
        if (!context.isPrimitiveArray()) {
            fillIndent(builder, context.getIndent());
        }
    }

    @Override
    public void serializeAfterCollectionElement(StringBuilder builder, SerializationContext context) {
        if (!context.isLast()) {
            builder.append(",");
            if (context.isPrimitiveArray()) {
                builder.append(" ");
            }
        }
        if (!context.isPrimitiveArray()) {
            builder.append("\n");
        }
    }

    @Override
    public void init(StringBuilder builder, SerializationContext context, Object object) {
    }

    @Override
    public void finish(StringBuilder builder, SerializationContext context, Object object) {
    }

    @Override
    public void initMap(StringBuilder builder, SerializationContext context) {
        builder.append("{\n");
        context.addIndent(this.indent);
    }

    @Override
    public void finishMap(StringBuilder builder, SerializationContext context) {
        context.deleteIndent();
        fillIndent(builder, context.getIndent());
        builder.append("}");
    }

    @Override
    public void initCollection(StringBuilder builder, SerializationContext context) {
        builder.append("[");
        if (!context.isPrimitiveArray()) {
            builder.append("\n");
            context.addIndent(this.indent);
        }
    }

    @Override
    public void finishCollection(StringBuilder builder, SerializationContext context) {
        if (!context.isPrimitiveArray()) {
            context.deleteIndent();
            fillIndent(builder, context.getIndent());
        }
        builder.append("]");
    }

    private String getPrimitiveString(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            String s = obj.toString();
            if (obj instanceof Number || obj instanceof Boolean) {
                return s;
            } else {
                return "\"" + s + "\"";
            }
        }
    }
}
