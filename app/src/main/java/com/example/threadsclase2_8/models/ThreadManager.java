package com.example.threadsclase2_8.models;

public class ThreadManager {
    private volatile boolean stopThreads = false;

    public void stopAllThreads() {
        stopThreads = true;
    }
}
