package com.example.threadsclase2_8;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Long> {
    private int number;
    public FactorialCalculator(int number) {
        this.number = number;
    }
    @Override
    public Long call() throws Exception {
        return calculateFactorial(number);
    }

    private long calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
}
