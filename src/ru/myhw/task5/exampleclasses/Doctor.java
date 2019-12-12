package ru.myhw.task5.exampleclasses;

public class Doctor extends Person {
    private final int degree;

    public Doctor(String name, Integer degree) {
        super(name);
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }
}