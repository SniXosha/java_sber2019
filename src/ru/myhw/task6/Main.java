package ru.myhw.task6;

import ru.myhw.task6.exampleclasses.Doctor;
import ru.myhw.task6.exampleclasses.Hand;
import ru.myhw.task6.serializer.Serializer;
import ru.myhw.task6.serializer.formats.JSONFormat;
import ru.myhw.task6.serializer.ObjectSerializer;
import ru.myhw.task6.serializer.formats.XMLFormat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Doctor doctor = createComplexDoctorObject();

        Serializer jsonRecursiveSerializer = new ObjectSerializer(new JSONFormat(4));
        System.out.println(jsonRecursiveSerializer.serialize(doctor));

        Serializer xmlRecursiveSerializer = new ObjectSerializer(new XMLFormat(4));
        System.out.println(xmlRecursiveSerializer.serialize(doctor));
    }

    private static Doctor createComplexDoctorObject() {
        List<Hand> hands = Arrays.asList(new Hand("left"), new Hand("right"));
        Doctor doctor3 = new Doctor("Richard", 3, null, null, null, hands);
        Doctor doctor2 = new Doctor("John", 2, null, Arrays.asList("1", "2", "3"), Map.of(doctor3, doctor3), hands);
        return new Doctor("Alex", 4, doctor2, Arrays.asList(1, 2, 3), null, hands);
    }
}
