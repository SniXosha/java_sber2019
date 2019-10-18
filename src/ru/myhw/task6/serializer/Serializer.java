package ru.myhw.task6.serializer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Serializer {

    private static JSONArray serializeCollection(Collection c) throws IllegalAccessException {
        JSONArray json = new JSONArray();
        for (Object o : c) {
            JSONObject child = serialize(o);
            json.put(child);
        }
        return json;
    }

    public static JSONObject serialize(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        JSONObject json = new JSONObject();
        while (clazz != Object.class) {
            serializeDeclared(json, clazz, obj);
            clazz = clazz.getSuperclass();
        }
        return json;
    }

    private static boolean isSimple(Class<?> type) {
        return type.isPrimitive() || type.isAssignableFrom(String.class);
    }

    private static JSONObject serializeDeclared(JSONObject json, Class<?> clazz, Object obj) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            String name = field.getName();
            if (isSimple(type)) {
                json.put(name, field.get(obj));
                continue;
            }
            if (type.isAssignableFrom(Collection.class)) {
                json.put(name, serializeCollection((Collection) field.get(obj)));
                continue;
            }
            json.put(name, serialize(field.get(obj)));
        }
        return json;
    }

    public static void serializeIntoFile(Object obj, String filepath) throws IllegalAccessException, FileNotFoundException {
        JSONObject json = serialize(obj);
        PrintWriter out = new PrintWriter(filepath);
        out.println(json.toString());
        out.close();
    }
}
