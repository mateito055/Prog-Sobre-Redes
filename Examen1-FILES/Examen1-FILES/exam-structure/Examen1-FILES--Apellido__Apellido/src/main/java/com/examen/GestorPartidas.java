package com.examen;



import java.util.ArrayList;
import java.util.List;

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
    }

    /**
     * Muestra todas las partidas cargadas en consola de forma ordenada.
     * Si no hay partidas, muestra un mensaje indicandolo.
     */
    public void mostrarTodos() {
        // COMPLETAR: mostrar datos en consola en formato tabular
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
        return "SIN DATOS";
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
