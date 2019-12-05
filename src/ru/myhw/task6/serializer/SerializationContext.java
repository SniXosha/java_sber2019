package ru.myhw.task6.serializer;

import java.util.ArrayList;

public class SerializationContext {

    private ArrayList<Integer> indents;
    private Boolean isPrimitiveArray;
    private Boolean isLast;

    public SerializationContext() {
        indents = new ArrayList<>();
        indents.add(0);
        isPrimitiveArray = false;
    }

    public int getIndent() {
        return indents.get(indents.size() - 1);
    }

    public void deleteIndent() {
        indents.remove(indents.size() - 1);
    }

    public void addIndent(int indent) {
        indents.add(getIndent() + indent);
    }

    public void setIsPrimitiveArray(Boolean isPrimitiveArray) {
        this.isPrimitiveArray = isPrimitiveArray;
    }

    public Boolean isPrimitiveArray() {
        return isPrimitiveArray;
    }

    public Boolean isLast() {
        return isLast;
    }

    public void setIsLast(Boolean last) {
        isLast = last;
    }
}
