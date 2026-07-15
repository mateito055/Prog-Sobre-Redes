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
    	while(true) {
    		try {
    			System.out.print(mensaje);
        		System.out.flush();
				entrada = reader.readLine();
				
				if(entrada != null && !entrada.trim().isEmpty()) {
					return entrada.trim();
				}
				System.out.println("\u001B[31mError: El campo no puede estar vacío.\u001B[0m");
					
			} catch (IOException e) {
				LogManager.registrarError("Error al validar entrada del usuario", e);
			}
    	}
    	
        
    }
}
