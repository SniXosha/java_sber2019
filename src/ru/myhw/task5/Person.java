package ru.myhw.task5;

class Person implements Dancing {

    private final String name;

    Person(String name) {
        this.name = name;
    }

    public void dance() throws InterruptedException {
        Thread.sleep(1000);
    }
}