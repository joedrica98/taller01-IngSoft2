// Copyright (C) 2020
// All rights reserved

package com.espol.edu;

/**
 * Clase principal para ejecutar la aplicación PaqueteVacacional.
 * @author YourName
 */
public class Main {

    /**
     * El método main para iniciar la aplicación.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        PaqueteVacacional paqueteVacacional = new PaqueteVacacional();
        System.out.println(paqueteVacacional.calcularCostoPaquete("París", 5, 10));
    }
}