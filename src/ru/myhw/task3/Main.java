package ru.myhw.task3;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CountMap<Integer> map = createMap();

        System.out.println(map.getCount(5));
        System.out.println(map.getCount(6));
        System.out.println(map.getCount(10));

        CountMap<Integer> map2 = createMap();

        map.addAll(map2);
        System.out.println(map.getCount(5));
        System.out.println(map.getCount(6));
        System.out.println(map.getCount(10));

        Map<Integer, Integer> asMap = (Map<Integer, Integer>) map.toMap();
        System.out.println(asMap);
    }

    private static CountMap<Integer> createMap() {
        CountMap<Integer> map = new CountHashMap<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        return map;
    }
}
