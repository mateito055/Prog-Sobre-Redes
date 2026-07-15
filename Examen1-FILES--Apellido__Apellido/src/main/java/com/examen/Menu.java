package com.examen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Menu principal de la aplicacion.
 * Presenta opciones al usuario y ejecuta las acciones correspondientes.
 * <p>
 * NO se permite usar la clase Scanner. Se utiliza BufferedReader + InputStreamReader.
 * <p>
 * Opciones:
 * 1 - Mostrar todas las partidas
 * 2 - Eliminar 1 partida elegida por el usuario
 * 3 - Ver mes con mayor cantidad de victorias
 * 4 - Salir (guarda datos y cierra)
 */
public class Menu {

    private GestorPartidas gestor;
    private BufferedReader reader;

    /**
     * Crea el menu asociado a un gestor de partidas.
     * Inicializa el BufferedReader para leer desde la consola.
     *
     * @param gestor el gestor de partidas
     */
    public Menu(GestorPartidas gestor) {
        //gestor.cargar("juegos.csv")
    	
    	this.gestor = gestor;
    	this.reader = new BufferedReader(new InputStreamReader(System.in));
    	this.gestor.cargar("juegos.csv");
    	
    }

    /**
     * Inicia el bucle principal del menu.
     * Muestra las opciones, solicita la eleccion al usuario y ejecuta la accion.
     * El bucle se repite hasta que el usuario elija la opcion 4 (Salir).
     */
    public void iniciar() {
        // COMPLETAR: bucle que muestre menu, lea opcion, ejecute accion
        // Usar Validador.leerNoVacio() para leer la opcion
        // Opcion 1: gestor.mostrarTodos()
        // Opcion 2: gestor.eliminar()
        // Opcion 3: mostrar resultado de gestor.mesMasVictorias()
        // Opcion 4: gestor.guardar("juegos.csv") y salir
    	
    	Boolean salir = false;
    	while (!salir) {
    		System.out.println("\n\u001B[35m====== MENU HISTORIAL LOL ======\u001B[0m");
            System.out.println("\u001B[36m1 -\u001B[0m Mostrar todas las partidas");
            System.out.println("\u001B[36m2 -\u001B[0m Eliminar 1 partida elegida por el usuario");
            System.out.println("\u001B[36m3 -\u001B[0m Ver mes con mayor cantidad de victorias");
            System.out.println("\u001B[36m4 -\u001B[0m Ver KDA General de la cuenta");
            System.out.println("\u001B[36m5 -\u001B[0m Salir (guarda datos y cierra)");
            System.out.println("\u001B[35m================================\u001B[0m");

            String opcion = Validador.leerNoVacio(reader, "Seleccione una opción: ");
            
            switch (opcion) {
            case "1":
            	gestor.mostrarTodos();
            	break;
            case "2":
            	menuEliminar();
            	break;
            case "3":
            	String mes = gestor.mesMasVictorias();
            	System.out.println("\u001B[32mEl mes con más victorias es: " + mes + "\u001B[0m");
            	break;
            case "4":
            	double kda = gestor.calcularKDAGeneral();
            	System.out.printf("\u001B[32mEl KDA general acumulado es: %.2f\u001B[0m\n", kda);
            	break;
            case "5":
            	System.out.println("\u001B[33mGuardando datos de forma segura y saliendo...\u001B[0m");
                gestor.guardar("juegos_encriptado.csv");
                salir = true;
                break;
            default:
                System.out.println("\u001B[31mOpción incorrecta. Intente de nuevo.\u001B[0m");	
            }
            
    	}
    }
    
    private void menuEliminar() {
    	System.out.println("\n\u001B[34m--- Eliminar Partida ---\u001B[0m");
    	try {
    		int index = Integer.parseInt(Validador.leerNoVacio(reader, "Ingrese el Nº de partida a eliminar: "));
    		gestor.eliminar(index);
    	} catch (NumberFormatException e) {
    		System.out.println("\u001B[31mError: Debe ingresar un número válido de índice.\u001B[0m");
    	}
    }
    
}
