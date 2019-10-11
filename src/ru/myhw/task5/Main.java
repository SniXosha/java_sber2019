package ru.myhw.task5;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InterruptedException {
        Dancing d = TimeMeasureProxy.create(new Doctor("Kek", 1));
        d.dance();
    }
}
