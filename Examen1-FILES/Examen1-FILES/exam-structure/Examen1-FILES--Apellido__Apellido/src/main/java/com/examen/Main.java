package com.examen;

import java.io.File;
import com.examen.Estandarizador;
import com.examen.LogManager;
import GenericFiles.managerFile;

/**
 * Punto de entrada principal del programa.
 * <p>
 * Orquesta la ejecucion:
 * 1. Inicializa LogManager
 * 2. Crea un Estandarizador y estandariza juegos.dat
 * 3. Crea un GestorPartidas
 * 4. Inicia el menu interactivo
 */


public class Main {

    public static void main(String[] args) {

    File archivo = new File("juegos.dat");
	Estandarizador e = new Estandarizador();
	try {
		e.estandarizar(archivo);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	/*
        // COMPLETAR: inicializar LogManager
        // COMPLETAR: crear Estandarizador y estandarizar "juegos.dat"
        // COMPLETAR: crear GestorPartidas y Menu
        // COMPLETAR: iniciar el menu
    	.Inicializar("");
    */

	
	
    }
}
