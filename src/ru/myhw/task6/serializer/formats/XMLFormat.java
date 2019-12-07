package ru.myhw.task6.serializer.formats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.myhw.task6.serializer.Utils.*;

public class XMLFormat implements SerializationFormat {
    private final int indent;

    public XMLFormat(int indent) {
        this.indent = indent;
    }

    @Override
    public String combineMap(Map<String, String> keyToElement) {
        ArrayList<String> mapElements = new ArrayList<>();
        keyToElement.forEach((k, v) -> mapElements.add(combineKeyValue(k, v)));
        return String.join("\n", mapElements);
    }

    @Override
    public String combineList(List<String> elements) {
        return elements.stream()
                .map(e -> combineKeyValue("element", e))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String serializePrimitive(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    @Override
    public String finishSerialization(Object obj, String body) {
        return combineKeyValue(obj.getClass().getName(), body);
    }

    private String combineKeyValue(String k, String v) {
        String body;
        if (v.contains("<")) {
            body = "\n" + shiftBlock(v, this.indent) + "\n";
        } else {
            body = v;
        }
        return getStartTag(k) + body + getEndTag(k);
    }

    private String getStartTag(Object key) {
        return "<" + key.toString() + ">";
    }

    private String getEndTag(Object key) {
        return "</" + key.toString() + ">";
    }
}
