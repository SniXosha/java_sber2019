package ru.myhw.task6.serializer;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getIndent(int indent) {
        return String.join("", Collections.nCopies(indent, " "));
    }

    public static Map<String, Object> getDeclaredFields(Object obj) {
        HashMap<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Impossible accessibility error");
            }
        }
        return map;
    }

    public static String shiftBlockPartially(String s, int indent) {
        return String.join("\n" + getIndent(indent), s.split("\n"));
    }

    public static String shiftBlock(String s, int indent) {
        return getIndent(indent) + shiftBlockPartially(s, indent);
    }
}
