package com.examen;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

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
    	
    	this.partidas.clear();
    	File archivo = new File(rutaCsv);
    	if (!archivo.exists()) {
    		return;
    	}
    	
    	try {
    		
    		// Leer el contenido encriptado por completo
    		
    		StringBuilder contenidoEncriptado = new StringBuilder();
    		try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
    			String linea;
    			while ((linea = br.readLine()) != null) {
    				contenidoEncriptado.append(linea).append("\n");
    			}
    		}
    		
    		// Recuperar la clave AES
    		
    		SecretKey clave = Estandarizador.recuperarClave("AES.key");
    		
    		if (clave == null) {
    			throw new Exception("No se pudo recuperar la clave AES.");
    		}
    		
    		// Desencriptar el contenido
    		
    		String contenidoPlano = Estandarizador.desencriptar(contenidoEncriptado.toString().trim(), clave);
    		
    		if (contenidoPlano == null) {
    			throw new Exception("Error al desencriptar el archivo CSV.");
    		}
    		
    		// Parsear el CSV plano linea por linea
    		
    		String[] lineas = contenidoPlano.split("\n");
    		boolean esCabecera = true;
    		for (String linea : lineas) {
    			if (linea.trim().isEmpty()) {
    				continue;
    			}
    			if (esCabecera) {
    				esCabecera = false; // Omitir la primera linea de cabecera
    				continue;
    			}
    			String[] campos = linea.split(";");
    			if (campos.length < 7) {
    				continue;
    			}
    			
    			String fecha = campos[0].trim();
    			Boolean gano = campos[1].trim().equals("1");
    			Boolean primerTorreta = campos[2].trim().equals("1");
    			Boolean primeraSangre = campos[3].trim().equals("1");
    			int asesinatos = Integer.parseInt(campos[4].trim());
    			int muertes = Integer.parseInt(campos[5].trim());
    			int asistencias = Integer.parseInt(campos[5].trim());
    			
    			Partida p = new Partida(fecha, gano, primerTorreta, primeraSangre, asesinatos, muertes, asistencias);
    			this.partidas.add(p);
    		}

    		
    	} catch (Exception e) {
    		LogManager.registrarError("Error al cargar partidas desde archivo CSV encriptado", e);
    	}
    }

    /**
     * Muestra todas las partidas cargadas en consola de forma ordenada.
     * Si no hay partidas, muestra un mensaje indicandolo.
     */
    public void mostrarTodos() {
        // COMPLETAR: mostrar datos en consola en formato tabular
    	
    	if (partidas.isEmpty()) {
    		System.out.println("\u001B[33mNo hay partidas registradas en el sistema.\u001B[0m");
    		return;
    	}
    	
    	// Cabecera de tabla de datos
    	
    	System.out.printf("\u001B[34m%-5s | %-10s | %-10s | %-12s | %-12s | %-6s | %-6s | %-9s | %-6s\u001B[0m\n", 
                "Nº", "Fecha", "Resultado", "1ª Sangre", "1ª Torreta", "Kills", "Deaths", "Assists", "KDA");
    	System.out.print("---------------------------------------------------------------------------------------------------");
    	
    	for (int i = 0; i < partidas.size(); i++) {
    		Partida p = partidas.get(i);
    		
    		// Alternancia de colores en las filas (Verde y Blanco)
    		String colorFila = (i % 2 == 0) ? "\u001B[32m":"\u001B[37m";
    		
    		double kdaIndividual = p.getMuertes() == 0 ? (p.getAsesinatos() + p.getAsistencias()) :
    			(double) (p.getAsesinatos() + p.getAsesinatos() / p.getMuertes());
    		String kdaStr = p.getMuertes() == 0 ? "Perfect" : String.format("%.2f", kdaIndividual);
    		
    		System.out.printf(colorFila + "%-5d | %-10s | %-10s | %-12s | %-12s | %-6d | %-6d | %-9d | %-6s\u001B[0m\n",
    				i,
    				p.getFecha(),
    				(p.isGano() ? "Victoria" : "Derrota"),
    				(p.isPrimeraSangre() ? "Si" : "No"),
    				(p.isPrimeraSangre() ? "Si" : "No"),
    				p.getAsesinatos(),
    				p.getMuertes(),
    				p.getAsistencias(),
    				kdaStr);
    		
    		
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
        	if(p.isGano()) {
        		String[] partesFecha = p.getFecha().split("/");
        		if (partesFecha.length == 2) {
        			String mes = partesFecha[1].trim();
        			victoriasPorMes.put(mes, victoriasPorMes.getOrDefault(mes, 0));
        		}
        		
        	}
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
     * Calcula el KDA general acumulado de todas las partidas en memoria (Punto C).
     * Evita la división por cero si las muertes totales son 0.
     */
    public double calcularKDAGeneral() {
        if (partidas.isEmpty()) {
            return 0.0;
        }

        int totalKills = 0;
        int totalDeaths = 0;
        int totalAssists = 0;

        for (Partida p : partidas) {
            totalKills += p.getAsesinatos();
            totalDeaths += p.getMuertes();
            totalAssists += p.getAsistencias();
        }

        if (totalDeaths == 0) {
            // Manejo matemático: Kills + Assists acumulados
            return totalKills + totalAssists;
        }

        return (double) (totalKills + totalAssists) / totalDeaths;
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
    	
    	try {
    		StringBuilder sb = new StringBuilder();
    		// Encabezado exacto
    		
    		sb.append("date ; hasWon ; isFirstTower ; isFirstBlood ; kills ; deaths ; assists\n");
    		
    		for (Partida p : partidas) {
    			// p.toString() devuelve: fecha + " ; " + (gano ? 1 : 0) + ...
    			sb.append(p.toString()).append("/n");
    		}
    		
    		// Recuperar la clave AES
    		
    		SecretKey clave = Estandarizador.recuperarClave("AES.key");
    		if (clave == null) {
    			throw new Exception("No se encontró la clave AES para guardar.");
    		}
    		
    		// Encriptar toda el CSV
    		
    		String contenidoEncriptado = Estandarizador.encriptar(sb.toString(), clave);
    		
    		// Escribir el archivo
    		
    		try (PrintWriter pw = new PrintWriter(new FileWriter(rutaCsv))){
    			pw.print(contenidoEncriptado);
    		}
    		
    		LogManager.registrarInfo("Partidas encriptadas y guardadas con éxito en: "+ rutaCsv);
    		
    	} catch (Exception e) {
    		LogManager.registrarError("Error al guardar las partidas en el archivo.", e);
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
    	
    	if (indice < 0 || indice >= partidas.size()) {
    		System.out.println("\u001B[31mError: El índice ingresado no es válido.\u001B[0m");
            return;
    	}
    	partidas.remove(indice);
    	System.out.println("\u001B[32mPartida eliminada correctamente de la lista en memoria.\u001B[0m");	
    	
    }
    
    public void agregar(Partida p) {
        this.partidas.add(p);
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
