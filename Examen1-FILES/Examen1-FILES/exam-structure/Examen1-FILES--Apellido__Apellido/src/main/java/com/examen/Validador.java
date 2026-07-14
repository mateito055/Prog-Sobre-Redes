package com.examen;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Proporciona metodos para validar datos ingresados por el usuario
 * a traves de la consola.
 * <p>
 * La validacion minima requerida es que el dato ingresado no este vacio.
 * No se permite el uso de la clase Scanner.
 */
public class Validador {

    /**
     * Lee un texto ingresado por el usuario y valida que no sea vacio.
     * Si el usuario ingresa una cadena vacia, se muestra un mensaje de error
     * y se solicita nuevamente el ingreso.
     * <p>
     * Utiliza BufferedReader (NO Scanner).
     *
     * @param reader  BufferedReader conectado a System.in
     * @param mensaje mensaje a mostrar al usuario
     * @return el texto ingresado (garantizado no vacio)
     */
    public static String leerNoVacio(BufferedReader reader, String mensaje) {
        // COMPLETAR: pedir ingreso, validar que no este vacio,
        // repetir hasta obtener un valor valido
        String entrada = "";
        boolean valido = false;

        // Códigos ANSI para mantener la estética colorida del examen
        String ROJO = "\u001B[31m";
        String RESET = "\u001B[0m";

        while (!valido) {
            try {
                // Imprime el mensaje descriptivo para guiar al usuario
                System.out.print(mensaje);
                
                // Lee la línea de la consola usando BufferedReader
                String lineaLeida = reader.readLine();
                
                if (lineaLeida != null) {
                    entrada = lineaLeida.trim(); // Quita espacios en blanco al inicio y final
                }

                // Validación: verifica si quedó vacía después del recorte de espacios
                if (entrada.isEmpty()) {
                    System.out.println(ROJO + "Error: El dato no puede estar vacío. Inténtelo de nuevo." + RESET);
                } else {
                    valido = true; // Salimos del bucle si el dato es correcto
                }
                
            } catch (IOException ex) {
                // Registra el error en crash.log de forma silenciosa sin romper el flujo del menú
                LogManager.registrarError("Fallo crítico en la lectura de consola del Validador", ex);
                System.out.println(ROJO + "Error al leer de consola. Inténtelo nuevamente." + RESET);
            }
        }
        
        return entrada;
    }
}
