package com.espol.edu;

import java.util.HashMap;
import java.util.Map;

public class PaqueteVacacional {
    private final int costoBase = 1000;
    private final int umbralDescuentoGrupo1 = 4;
    private final int umbralDescuentoGrupo2 = 10;
    private final int tamanoMaxGrupo = 80;
    private final double descuentoGrupo1 = 0.10;
    private final double descuentoGrupo2 = 0.20;
    private final int duracionPenalizacion = 7;
    private final int duracionPromocional = 30;
    private final int cantidadPasajerosPromocional = 2;
    private final int tarifaPenalizacion = 200;
    private final int descuentoPromocional = 200;
    private final Map<String, Integer> costoLugaresTuristicos;

    /**
     * Constructor que inicializa el mapa costoLugaresTuristicos con los costos
     * adicionales de los lugares turísticos.
     */
    public PaqueteVacacional() {
        costoLugaresTuristicos = new HashMap<String, Integer>();
        costoLugaresTuristicos.put("París", 500);
        costoLugaresTuristicos.put("Nueva York", 600);
    }

    /**
     * Método que valida las entradas de un paquete vacacional.
     *
     * @param destino   El destino del viaje.
     * @param viajeros  El número de viajeros.
     * @param duracion  La duración del viaje en días.
     * @return true si las entradas son válidas, false de lo contrario.
     */
    private boolean validarEntradas(String destino, int viajeros, int duracion) {
        if (destino == null || destino.isEmpty() || viajeros <= 0 || viajeros > tamanoMaxGrupo || duracion <= 0) {
            System.err.println("Datos de entrada inválidos.");
            return false;
        }
        return true;
    }

    /**
     * Método que calcula el costo de un paquete vacacional.
     *
     * @param destino   El destino del viaje.
     * @param viajeros  El número de viajeros.
     * @param duracion  La duración del viaje en días.
     * @return El costo total del paquete vacacional o -1 si los datos de entrada son inválidos.
     */
    public int calcularCostoPaquete(String destino, int viajeros, int duracion) {
        if (!validarEntradas(destino, viajeros, duracion)) {
            return -1;
        }

        int costo = costoBase;

        // Agregar costo adicional si el destino es un lugar turístico popular
        if (costoLugaresTuristicos.containsKey(destino)) {
            costo += costoLugaresTuristicos.get(destino);
        }

        costo *= viajeros;

        // Aplicar descuento por grupo
        if (viajeros > umbralDescuentoGrupo1 && viajeros <= umbralDescuentoGrupo2) {
            costo -= costo * descuentoGrupo1;
        } else if (viajeros > umbralDescuentoGrupo2) {
            costo -= costo * descuentoGrupo2;
        }

        // Aplicar penalización o descuento según la duración del viaje
        if (duracion < duracionPenalizacion) {
            costo += tarifaPenalizacion;
        } else if (duracion > duracionPromocional || viajeros == cantidadPasajerosPromocional) {
            costo -= descuentoPromocional;
        }

        return costo;
    }
}
