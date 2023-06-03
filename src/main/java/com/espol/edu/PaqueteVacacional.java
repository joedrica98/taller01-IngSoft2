// Copyright (C) 2020
// All rights reserved

package com.espol.edu;

import java.util.HashMap;
import java.util.Map;

public class PaqueteVacacional {
    private int costo_base = 1000;
    private int umbral_descuento_grupo1 = 4;
    private int umbral_descuento_grupo2 = 10;
    private int tamano_max_grupo = 80;
    private double descuento_grupo1 = 0.10;
    private double descuento_grupo2 = 0.20;
    private int duracion_penalizacion = 7;
    private int duracion_promocional = 30;
    private int cantidad_pasajeros_promocional = 2;
    private int tarifa_penalizacion = 200;
    private int descuento_promocional = 200;
    private Map<String, Integer> costo_lugares_turisticos;

    /**
     * Constructor que inicializa el mapa costo_lugares_turisticos con los costos
     * adicionales de los lugares turísticos.
     */
    public PaqueteVacacional() {
        costo_lugares_turisticos = new HashMap<String, Integer>();
        costo_lugares_turisticos.put("París", 500);
        costo_lugares_turisticos.put("Nueva York", 600);
    }

    /**
     * Método que valida las entradas de un paquete vacacional.
     * @param destino El destino del viaje.
     * @param viajeros El número de viajeros.
     * @param duracion La duración del viaje en días.
     * @return true si las entradas son válidas, false de lo contrario.
     */
    private boolean validarEntradas(String destino, int viajeros, int duracion) {
        if (destino == null || destino.isEmpty() || viajeros <= 0 || viajeros > tamano_max_grupo || duracion <= 0) {
            System.err.println("Datos de entrada inválidos.");
            return false;
        }
        return true;
    }

    /**
     * Método que calcula el costo de un paquete vacacional.
     * @param destino El destino del viaje.
     * @param viajeros El número de viajeros.
     * @param duracion La duración del viaje en días.
     * @return El costo total del paquete vacacional o -1 si los datos de entrada son inválidos.
     */
    public int calcularCostoPaquete(String destino, int viajeros, int duracion) {
        if (!validarEntradas(destino, viajeros, duracion)) {
            return -1;
        }

        int costo = costo_base;

        // Agregar costo adicional si el destino es un lugar turístico popular
        if (costo_lugares_turisticos.containsKey(destino)) {
            costo += costo_lugares_turisticos.get(destino);
        }

        costo *= viajeros;

        // Aplicar descuento por grupo
        if (viajeros > umbral_descuento_grupo1 && viajeros <= umbral_descuento_grupo2) {
            costo -= costo * descuento_grupo1;
        } else if (viajeros > umbral_descuento_grupo2) {
            costo -= costo * descuento_grupo2;
        }

        // Aplicar penalización o descuento según la duración del viaje
        if (duracion < duracion_penalizacion) {
            costo += tarifa_penalizacion;
        } else if (duracion > duracion_promocional || viajeros == cantidad_pasajeros_promocional) {
            costo -= descuento_promocional;
        }

        return costo;
    }
}