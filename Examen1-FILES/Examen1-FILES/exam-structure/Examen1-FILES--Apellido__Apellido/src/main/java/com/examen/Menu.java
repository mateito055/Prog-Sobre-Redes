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
    }
}
