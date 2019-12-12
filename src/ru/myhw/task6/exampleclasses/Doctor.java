package ru.myhw.task6.exampleclasses;

import java.util.List;
import java.util.Map;

public class Doctor {

    private final String name;
    private final int degree;
    private final Doctor boss;
    private final List<?> list;
    private final Map<?, ?> map;
    private final List<Hand> hands;


    public Doctor(String name, Integer degree, Doctor colleague, List<?> list, Map<?, ?> map, List<Hand> hands) {
        this.name = name;
        this.degree = degree;
        this.boss = colleague;
        this.list = list;
        this.map = map;
        this.hands = hands;
    }
}
