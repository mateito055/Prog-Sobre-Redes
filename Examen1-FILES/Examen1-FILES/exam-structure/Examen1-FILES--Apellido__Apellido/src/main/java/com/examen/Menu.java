package com.examen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
        // Asignamos el gestor recibido por parámetro
        this.gestor = gestor;
        // Inicializamos el lector por consola nativo como lo pide la consigna
        this.reader = new BufferedReader(new InputStreamReader(System.in));
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

        // Códigos de escape ANSI para la interfaz de consola amigable y colorida
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String AMARILLO = "\u001B[33m";
        String VERDE = "\u001B[32m";
        String BLANCO = "\u001B[37m";
        String MAGENTA = "\u001B[35m";
        String ROJO = "\u001B[31m";

        String opcion = "";

        while (!opcion.equals("4")) {
            System.out.println("\n" + CYAN + "=======================================" + RESET);
            System.out.println(MAGENTA + "      LOL MATCH ANALYZER - MENU" + RESET);
            System.out.println(CYAN + "=======================================" + RESET);
            System.out.println(AMARILLO + " 1. " + BLANCO + "Mostrar todas las partidas");
            System.out.println(AMARILLO + " 2. " + BLANCO + "Eliminar una partida por ID");
            System.out.println(AMARILLO + " 3. " + BLANCO + "Ver mes con mayor cantidad de victorias");
            System.out.println(AMARILLO + " 4. " + BLANCO + "Guardar y Salir");
            System.out.println(CYAN + "=======================================" + RESET);

            // Invocamos el método del Validador pasando nuestro reader configurado
            opcion = Validador.leerNoVacio(reader, "Seleccione una opción (1-4): ");

            switch (opcion) {
                case "1":
                    gestor.mostrarTodos();
                    break;

                case "2":
                    if (gestor.cantidadPartidas() == 0) {
                        System.out.println(ROJO + "No hay partidas en memoria para eliminar." + RESET);
                    } else {
                        // Mostramos el historial para que el usuario visualice los IDs válidos
                        gestor.mostrarTodos();
                        String indiceTexto = Validador.leerNoVacio(reader, "Ingrese el ID de la partida a eliminar: ");
                        try {
                            int indice = Integer.parseInt(indiceTexto);
                            gestor.eliminar(indice);
                        } catch (NumberFormatException ex) {
                            System.out.println(ROJO + "Error: El ID ingresado debe ser un número entero." + RESET);
                            LogManager.registrarError("Entrada inválida al intentar parsear ID de eliminación", ex);
                        }
                    }
                    break;

                case "3":
                    String mejorMes = gestor.mesMasVictorias();
                    if (mejorMes.equals("SIN DATOS")) {
                        System.out.println(ROJO + "\nResultado: No se encontraron registros de victorias." + RESET);
                    } else {
                        System.out.println(VERDE + "\n[!] El mes con mayor número de victorias es el: " + AMARILLO + mejorMes + RESET);
                    }
                    break;

                case "4":
                    System.out.println(CYAN + "Guardando partidas de forma segura en 'juegos.csv'..." + RESET);
                    gestor.guardar("juegos.csv");
                    System.out.println(VERDE + "¡Datos guardados con éxito! Finalizando aplicación." + RESET);
                    break;

                default:
                    System.out.println(ROJO + "Opción incorrecta. Ingrese un número del 1 al 4." + RESET);
                    break;
            }
        }
    }
}
