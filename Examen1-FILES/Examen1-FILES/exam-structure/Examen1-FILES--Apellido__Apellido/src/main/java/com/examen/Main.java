package com.examen;

import java.io.File;
import com.examen.Estandarizador;
import com.examen.LogManager;
import GenericFiles.managerFile;

/**
 * Punto de entrada principal del programa.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Inicializar LogManager de forma segura
        File archivoLog = new File("crash.log");
        LogManager.Inicializar(archivoLog, "=== INICIO DE LA APLICACIÓN ===", true);

        // 2. Crear Estandarizador y estandarizar "juegos.dat"
        File archivoDatos = new File("juegos.dat");
        Estandarizador estandarizador = new Estandarizador();
        
        try {
            if (archivoDatos.exists()) {
                estandarizador.estandarizar(archivoDatos);
                LogManager.registrarInfo("El archivo juegos.dat fue detectado y estandarizado con éxito.");
            } else {
                LogManager.registrarInfo("No se detectó juegos.dat inicial. Se omitió la estandarización.");
            }
        } catch (Exception ex) {
            LogManager.registrarError("Fallo inesperado en la fase de inicialización y estandarización del Main.", ex);
        }

        // 3. Crear GestorPartidas y cargar los datos limpios
        GestorPartidas gestor = new GestorPartidas();
        gestor.cargar("juegos.csv");

        // 4. Iniciar el menú interactivo pasándole el gestor al constructor
        Menu menuInteractivos = new Menu(gestor);
        
        // CORRECCIÓN AQUÍ: Se llama a iniciar() según pide tu esqueleto de Menu
        menuInteractivos.iniciar();
    }
}
