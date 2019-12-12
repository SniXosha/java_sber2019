package ru.myhw.task8;

import java.util.concurrent.Callable;

class CalcSum implements Callable<Integer> {

    private final long n;
    private final boolean throwException;

    CalcSum(long n, boolean throwException) {
        this.n = n;
        this.throwException = throwException;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (long i = 0; i < n; i++) {
             result += i;
        }
        if (throwException) throw new Exception("something went wrong");
        return result;
    }
}
