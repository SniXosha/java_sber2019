package ru.myhw.task6;

import ru.myhw.task6.serializer.formats.JSONFormat;
import ru.myhw.task6.serializer.RecursiveSerializer;
import ru.myhw.task6.serializer.formats.XMLFormat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Hand {
    private final String type;

    Hand(String type) {
        this.type = type;
    }
}

class Doctor {

    private final String name;
    private final int degree;
    private final Doctor boss;
    private final List<?> list;
    private final Map<?, ?> map;
    private final List<Hand> hands;


    Doctor(String name, Integer degree, Doctor colleague, List<?> list, Map<?, ?> map, List<Hand> hands) {
        this.name = name;
        this.degree = degree;
        this.boss = colleague;
        this.list = list;
        this.map = map;
        this.hands = hands;
    }
}

public class Main {

    public static void main(String[] args) {
        List<Hand> hands = Arrays.asList(new Hand("left"), new Hand("right"));
        Doctor doctor3 = new Doctor("Richard", 3, null, null, null, hands);
        Doctor doctor2 = new Doctor("John", 2, null, Arrays.asList("1", "2", "3"), Map.of(doctor3, doctor3), hands);
        Doctor doctor = new Doctor("Alex", 4, doctor2, Arrays.asList(1, 2, 3), null, hands);

        RecursiveSerializer jsonRecursiveSerializer = new RecursiveSerializer(new JSONFormat(4));
        System.out.println(jsonRecursiveSerializer.serialize(doctor));

        RecursiveSerializer xmlRecursiveSerializer = new RecursiveSerializer(new XMLFormat(4));
        System.out.println(xmlRecursiveSerializer.serialize(doctor));
    }
}
