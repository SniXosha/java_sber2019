package ru.myhw.task8.concurrent;

import java.util.concurrent.Callable;

public class Task<T> {

    private volatile boolean ready;
    private final Callable<? extends T> callable;
    private volatile T result;
    private volatile TaskException taskException;

    public Task(Callable<? extends T> callable) {
        this.ready = false;
        this.result = null;
        this.taskException = null;
        this.callable = callable;
    }

    public T get() throws TaskException {
        if (!ready) {
            synchronized (this) {
                if (!ready) {
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        this.taskException = new TaskException("Exception in callable", e);
                    }
                    ready = true;
                    if (taskException != null) throw this.taskException;
                }
            }
        }
        if (taskException == null) {
            return result;
        } else {
            throw taskException;
        }
    }
}
