package ru.myhw.task5.exampleclasses;

class Person implements Dancing {

    private final String name;

    Person(String name) {
        this.name = name;
    }

    public void dance() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't dance anymore");
        }
    }

    public String getName() {
        return name;
    }
}