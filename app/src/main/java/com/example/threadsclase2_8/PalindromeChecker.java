package com.example.threadsclase2_8;

import android.util.Log;

import java.util.concurrent.Callable;

public class PalindromeChecker implements Callable<String> {
    private String cadena;

    public PalindromeChecker(String cadena) {
        this.cadena = cadena;
    }

    @Override
    public String call() {
        String resultado;
        if (esPalindromoRecursivo(cadena)) {
            resultado = cadena + " es un palíndromo.";
        } else {
            resultado = cadena + " no es un palíndromo.";
        }
        return resultado;
    }

    private boolean esPalindromoRecursivo(String cadena) {
        cadena = cadena.toLowerCase().replace(" ", "");
        return esPalindromoRecursivoAux(cadena, 0, cadena.length() - 1);
    }

    private boolean esPalindromoRecursivoAux(String cadena, int inicio, int fin) {
        if (inicio >= fin) {
            return true; // Caso base: cuando los índices se cruzan, la cadena es un palíndromo
        }
        if (cadena.charAt(inicio) != cadena.charAt(fin)) {
            return false; // Si los caracteres en los índices no coinciden, no es un palíndromo
        }

        // Llamada recursiva, avanzando los índices hacia el centro
        return esPalindromoRecursivoAux(cadena, inicio + 1, fin - 1);
    }

}

