package ru.myhw.task5;

import ru.myhw.task5.exampleclasses.Dancing;
import ru.myhw.task5.exampleclasses.Doctor;

public class Main {
    public static void main(String[] args) {
        Dancing d = TimeMeasureProxy.create(new Doctor("Igor", 1));
        d.dance();
    }
}
