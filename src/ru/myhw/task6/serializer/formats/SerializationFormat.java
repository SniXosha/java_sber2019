package ru.myhw.task6.serializer.formats;

import java.util.List;
import java.util.Map;

public interface SerializationFormat {
    String combineMap(Map<String, String> keyToElement);

    String combineList(List<String> elements);

    String serializePrimitive(Object obj);

    String finishSerialization(Object obj, String body);
}
