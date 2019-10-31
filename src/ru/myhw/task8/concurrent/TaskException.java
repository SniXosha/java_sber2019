package ru.myhw.task8.concurrent;

class TaskException extends Exception {
    TaskException(String errorMessage, Throwable callableException) {
        super(errorMessage, callableException);
    }
}
