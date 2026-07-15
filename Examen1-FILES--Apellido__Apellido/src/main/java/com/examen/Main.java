package com.examen;

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
        // COMPLETAR: inicializar LogManager
    	LogManager.inicializar();
        
    	// COMPLETAR: crear Estandarizador y estandarizar "juegos.dat"
    	Estandarizador est = new Estandarizador();
    	est.estandarizar("juegos.dat");
    	
        // COMPLETAR: crear GestorPartidas y Menu
    	GestorPartidas gestor = new GestorPartidas();
    	gestor.cargar("juegos_encriptado.csv");
    	Menu menu = new Menu(gestor);
    	
        // COMPLETAR: iniciar el menu
    	menu.iniciar();
    	
    	
    }
}
