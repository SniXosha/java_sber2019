package ru.myhw.task8.concurrent;

import java.util.concurrent.Callable;

public class Task<T> {

    private volatile boolean ready;
    private volatile boolean callAssigned;
    private final Callable<? extends T> callable;
    private T result;
    private TaskException taskException;

    public Task(Callable<? extends T> callable) {
        this.ready = false;
        this.callAssigned = false;
        this.result = null;
        this.taskException = null;
        this.callable = callable;
    }

    public T get() throws TaskException {
        boolean doCall = tryTakeCall();

        if (doCall) {
            call();
            synchronized (this) {
                notifyAll();
            }
        }

        return getResult();
    }

    private boolean tryTakeCall() {
        boolean doCall = false;
        if (!callAssigned) {
            synchronized (this) {
                if (!callAssigned) {
                    doCall = callAssigned = true;
                }
            }
        }
        return doCall;
    }

    private T getResult() throws TaskException {
        while (!ready) {
            sleep();
        }

        if (taskException != null) throw taskException;
        return result;
    }

    private void call() {
        try {
            result = callable.call();
        } catch (Exception e) {
            taskException = new TaskException("Exception in callable", e);
        } finally {
            ready = true;
        }
    }

    private synchronized void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
