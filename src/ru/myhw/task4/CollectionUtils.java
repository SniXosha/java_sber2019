package ru.myhw.task4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? super T> source, T element) {
        int i = 0;
        for (Object obj : source) {
            if (obj.equals(element)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <T> List<T> limit(List<T> source, int size) {
        int i = 0;
        List<T> newArray = CollectionUtils.<T>newArrayList();
        for (T element : source) {
            if (i >= size)
                break;
            newArray.add(element);
            i++;
        }
        return newArray;
    }

    public static <T> void add(List<? super T> source, T element) {
        source.add(element);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        c2.forEach(removeFrom::remove);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

    public static <L, T extends Comparable<? super L>> List<L> range(List<L> list, T min, T max) {
        return range(list, element -> min.compareTo(element) <= 0 && max.compareTo(element) >= 0);
    }

    public static <L, T extends L> List<L> range(List<L> list, T min, T max, Comparator<? super L> comparator) {
        return range(list, element -> comparator.compare(min, element) <= 0 && comparator.compare(max, element) >= 0);
    }

    public static <T> List<T> range(List<T> list, Predicate<T> predicate) {
        List<T> newArray = CollectionUtils.newArrayList();
        list.stream().filter(predicate).forEachOrdered(newArray::add);
        return newArray;
    }
}