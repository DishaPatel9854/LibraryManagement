package com.library.interfaces;

public interface ProgressSubject {
    void registerObserver(ProgressObserver observer);
    void removeObserver(ProgressObserver observer);
    void notifyObservers(String message);
}
