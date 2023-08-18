package com.example.threadsclase2_8.models;

public class Singleton {
    public boolean winner = false;
    private static Singleton instance;
    private Singleton() {
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
