package com.examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestiona la coleccion de partidas cargadas desde el archivo CSV estandarizado.
 * Provee metodos para cargar, mostrar, analizar y guardar los datos.
 */
public class GestorPartidas {

    private ArrayList<Partida> partidas;

    /**
     * Crea un gestor vacio. Inicializa la lista de partidas.
     */
    public GestorPartidas() {
        this.partidas = new ArrayList<>();
    }

    /**
     * Carga los datos desde el archivo CSV estandarizado.
     * Lee cada linea, la parsea y crea objetos Partida.
     * La primera linea (encabezados) debe ignorarse.
     * <p>
     * Formato esperado: dd/MM ; 1 ; 0 ; 1 ; 4 ; 7 ; 5
     * <p>
     * Los errores deben registrarse en crash.log sin mostrar en consola.
     *
     * @param rutaCsv ruta del archivo CSV a cargar
     */
    public void cargar(String rutaCsv) {
        // COMPLETAR: leer archivo CSV, ignorar primera linea,
        // separar por " ; ", convertir tipos, crear Partida,
        // agregar a la lista
        File archivo = new File(rutaCsv);
        if (!archivo.exists()) {
            return;
        }

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine(); // Ignorar la primera línea de encabezados

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                // Separar los campos usando exactamente " ; " como indica la consigna
                String[] campos = linea.split(" ; ");

                if (campos.length >= 7) {
                    String fecha = campos[0].trim();
                    // Conversión de tipos basada en el formato estándar de tu enunciado
                    // (Resultado/Victoria, Primera Sangre, Primera Torreta, K, D, A)
                    String resultado = campos[1].trim().equals("1") ? "Victoria" : "Derrota";
                    boolean primeraSangre = campos[2].trim().equals("1");
                    boolean primeraTorreta = campos[3].trim().equals("1");
                    int asesinatos = Integer.parseInt(campos[4].trim());
                    int muertes = Integer.parseInt(campos[5].trim());
                    int asistencias = Integer.parseInt(campos[6].trim());

                    // Instanciar la clase Partida del paquete FlujoDatos
                    Partida nuevaPartida = new Partida(fecha, resultado, primeraSangre, primeraTorreta, asesinatos, muertes, asistencias);
                    partidas.add(nuevaPartida);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            // Manejo de errores silencioso delegando a LogManager
            LogManager.registrarError("Error al cargar o parsear el archivo CSV: " + rutaCsv, ex);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ex) {
                LogManager.registrarError("Error al cerrar flujos en cargar CSV", ex);
            }
        }
    }

    /**
     * Muestra todas las partidas cargadas en consola de forma ordenada.
     * Si no hay partidas, muestra un mensaje indicandolo.
     */
    public void mostrarTodos() {
        // COMPLETAR: mostrar datos en consola en formato tabular
        if (partidas.isEmpty()) {
            System.out.println("\u001B[31mNo hay partidas cargadas en el sistema.\u001B[0m");
            return;
        }

        // Códigos de escape ANSI para cumplir con el menú prolijo y colorido
        String CYAN = "\u001B[36m";
        String VERDE = "\u001B[32m";
        String BLANCO = "\u001B[37m";
        String RESET = "\u001B[0m";

        System.out.printf("\n" + CYAN + "%-5s %-10s %-10s %-10s %-10s %-5s %-5s %-5s\n" + RESET, 
                "ID", "FECHA", "RESULT", "1ST BLOOD", "1ST TURRET", "K", "D", "A");
        System.out.println("---------------------------------------------------------------------");

        boolean alternarColor = false;
        for (int i = 0; i < partidas.size(); i++) {
            Partida p = partidas.get(i);
            String colorFila = alternarColor ? VERDE : BLANCO;
            
            System.out.printf(colorFila + "[%-3d] %-10s %-10s %-10b %-10b %-5d %-5d %-5d\n" + RESET,
                    i, p.getFecha(), p.getResultado(), p.isPrimeraSangre(), p.isPrimeraTorreta(),
                    p.getAsesinatos(), p.getMuertes(), p.getAsistencias());
            
            alternarColor = !alternarColor;
        }
    }

    /**
     * Analiza las partidas y determina en que mes hubo mas victorias.
     * La fecha tiene formato dd/MM (el mes esta despues de la barra).
     * <p>
     * En caso de empate, devuelve cualquiera de los meses.
     *
     * @return String con numero de mes (dos digitos, ej: "04").
     *         Si no hay partidas, retorna "SIN DATOS".
     */
    public String mesMasVictorias() {
        // COMPLETAR: contar victorias por mes, devolver el mes con mas
        if (partidas.isEmpty()) {
            return "SIN DATOS";
        }

        Map<String, Integer> victoriasPorMes = new HashMap<>();

        for (Partida p : partidas) {
            // Evaluamos si el resultado es una victoria (valor "1" o "Victoria")
            if ("Victoria".equalsIgnoreCase(p.getResultado()) || "1".equals(p.getResultado())) {
                String fecha = p.getFecha(); // Formato dd/MM
                if (fecha != null && fecha.contains("/")) {
                    String[] partes = fecha.split("/");
                    if (partes.length == 2) {
                        String mes = partes[1].trim(); // Extrae los dos dígitos del mes
                        victoriasPorMes.put(mes, victoriasPorMes.getOrDefault(mes, 0) + 1);
                    }
                }
            }
        }

        if (victoriasPorMes.isEmpty()) {
            return "SIN DATOS";
        }

        String mejorMes = "SIN DATOS";
        int maxVictorias = -1;

        for (Map.Entry<String, Integer> entry : victoriasPorMes.entrySet()) {
            if (entry.getValue() > maxVictorias) {
                maxVictorias = entry.getValue();
                mejorMes = entry.getKey();
            }
        }

        return mejorMes;
    }

    /**
     * Guarda todas las partidas en el archivo CSV.
     * Primero escribe la linea de encabezados y luego cada partida.
     * Si el archivo ya existe, se sobrescribe.
     *
     * @param rutaCsv ruta del archivo CSV donde guardar
     */
    public void guardar(String rutaCsv) {
        // COMPLETAR: escribir encabezados y cada partida al archivo
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(rutaCsv, false)); // false para sobrescribir
            
            // Escribimos la cabecera exacta requerida
            pw.println("Fecha ; Resultado ; Primera Sangre ; Primera Torreta ; Kills ; Deaths ; Assists");
            
            for (Partida p : partidas) {
                // Volvemos a transformar las propiedades al formato primitivo numérico del CSV estandarizado
                String resNum = "Victoria".equalsIgnoreCase(p.getResultado()) ? "1" : "0";
                String fbNum = p.isPrimeraSangre() ? "1" : "0";
                String ftNum = p.isPrimeraTorreta() ? "1" : "0";

                pw.printf("%s ; %s ; %s ; %s ; %d ; %d ; %d\n",
                        p.getFecha(), resNum, fbNum, ftNum,
                        p.getAsesinatos(), p.getMuertes(), p.getAsistencias());
            }
            pw.flush();
        } catch (IOException ex) {
            LogManager.registrarError("Error al guardar partidas en el archivo CSV: " + rutaCsv, ex);
        } finally {
            if (pw != null) pw.close();
        }
    }

    /**
     * Elimina una partida de la lista segun el indice indicado.
     * Valida que el indice sea valido antes de eliminar.
     * Si el indice es invalido, muestra un mensaje de error.
     *
     * @param indice posicion de la partida a eliminar (base 0)
     */
    public void eliminar(int indice) {
        // COMPLETAR: validar indice, eliminar de la lista, mostrar mensaje
        if (indice >= 0 && indice < partidas.size()) {
            partidas.remove(indice);
            System.out.println("\u001B[32mPartida con ID [" + indice + "] eliminada correctamente de la memoria.\u001B[0m");
        } else {
            System.out.println("\u001B[31mError: El índice [" + indice + "] no es válido. Rango actual: 0 a " + (partidas.size() - 1) + ".\u001B[0m");
        }
    }

    /**
     * @return cantidad de partidas cargadas
     */
    public int cantidadPartidas() {
        return partidas.size();
    }

    /**
     * @return la lista interna de partidas
     */
    public List<Partida> getPartidas() {
        return partidas;
    }
}

