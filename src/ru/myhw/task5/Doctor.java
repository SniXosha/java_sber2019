package ru.myhw.task5;

class Doctor extends Person {
    private final int degree;

    Doctor(String name, Integer degree) {
        super(name);
        this.degree = degree;
    }
}