package ru.myhw.task6;

import ru.myhw.task6.serializer.Serializer;

import java.io.FileNotFoundException;

class Person {

    private final String name;

    Person(String name) {
        this.name = name;
    }

    public void dance() throws InterruptedException {
        Thread.sleep(1000);
    }
}

class Doctor extends Person {
    private final int degree;

    Doctor(String name, Integer degree) {
        super(name);
        this.degree = degree;
    }
}

public class Main {

    public static void main(String[] args) throws IllegalAccessException, FileNotFoundException {
        Doctor d = new Doctor("kek", 1);
        Serializer.serializeIntoFile(d, "/tmp/kek.txt");
    }
}
