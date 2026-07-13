package com.examen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import GenericFiles.managerFile;

/**
 * Se encarga de leer el archivo original juegos.dat y reestructurarlo
 * a un formato estandarizado CSV con separador " ; ".
 * Luego elimina el archivo original.
 * <p>
 * El archivo original usa '+' como separador.
 * Ejemplo de linea original: 20/04+1+0+1+4+7+5
 * Ejemplo de linea estandarizada: 20/04 ; 1 ; 0 ; 1 ; 4 ; 7 ; 5
 */
public class Estandarizador {
    /**
     * Lee el archivo original (juegos.dat), reemplaza el caracter '+'
     * por el separador " ; ", guarda el resultado como juegos.csv
     * y elimina el archivo original juegos.dat.
     * <p>
     * Si ocurre cualquier error, se debe capturar la excepcion y
     * registrarla en crash.log mediante LogManager.
     * El error NO debe mostrarse por consola.
     *
     * @param rutaOriginal ruta completa del archivo juegos.dat a procesar
     */
	
	
	
	
    public void estandarizar(File f) {
        // COMPLETAR: leer archivo original, reemplazar + por " ; ",
        // escribir nuevo archivo .csv, eliminar el .dat original,
        // capturar errores con LogManager
    	FileReader fr = null;
		BufferedReader br = null;
		String texto = "";
		FileOutputStream fos = null;
		PrintStream fs = null;

		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String linea = "";
			while ((linea = br.readLine()) != null) {
				texto = texto.concat(linea.concat(String.valueOf('\n')));
				// en vez de mostrarlo pueden o gruarlo en variable
				// como tambien en un array o llamar directo a una
				// funcion que la use
			}

		} catch (IOException ex) {
			Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException ex) {
				Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
			}
		}

		texto = texto.replaceAll("+", ";");	;
		
		try {
			if (!f.exists()) {
				try {
					f.delete();
					f.createNewFile();
				} catch (IOException ex) {
					Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
				}
			}

			fos = new FileOutputStream(f, false);
			fs = new PrintStream(fos);

			fs.println(texto);

			fs.flush();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
		} finally {
			try {
				if (fs != null)
					fs.close();
				if (fos != null)
					fos.close();
			} catch (IOException ex) {
				Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
			}
		}

	}
	
    	
    	
    
    
    /**
     * Genera una nueva clave simétrica AES de 256 bits.
     * Este método solo debe llamarse la primera vez que se ejecuta el programa.
     */
    
    public static SecretKey generarClaveAES() {
		return null;
    }
    
    /**
     * Convierte la clave AES a texto (Base64) y la guarda en un archivo.
     * @param clave La clave generada que se desea guardar.
     * @param rutaArchivo Ruta donde se guardará (ej: "clave.key").
     */
    public static void guardarClave(SecretKey clave, String rutaArchivo) {
        try {
            // 1. Obtenemos los bytes de la clave y los pasamos a texto Base64
            String claveEnTexto = Base64.getEncoder().encodeToString(clave.getEncoded());
            
            // 2. Guardamos ese texto en el archivo indicado
            
        } catch (Exception ex) {
        	Logger.getLogger(Estandarizador.class.getName()).log(Level.WARNING, "Error al guardar la clave en el archivo", ex);
        }
    }

    /**
     * Lee el archivo de texto y reconstruye la clave AES para poder usarla.
     * @param rutaArchivo Ruta del archivo donde está guardada la clave (ej: "clave.key").
     * @return El objeto SecretKey reconstruido, o null si falla.
     */
    public static SecretKey recuperarClave(String rutaArchivo) {
        try {
            // 1. Leemos el texto completo (Base64) desde el archivo
            String textoLeido="" ; //Guardar la key en la variable
            
            // 2. Decodificamos el texto para recuperar los bytes originales
            byte[] bytesClave = Base64.getDecoder().decode(textoLeido);
            
            // 3. Reconstruimos y retornamos la llave AES
            return new SecretKeySpec(bytesClave, 0, bytesClave.length, "AES");
        } catch (Exception ex) {
        	Logger.getLogger(Estandarizador.class.getName()).log(Level.WARNING, "Error al recuperar la clave del archivo", ex);
        }
        return null;
    }
    
    
    /**
     * Encripta un texto (ej: el contenido del CSV) usando la clave proporcionada.
     * @param datos El texto plano a encriptar.
     * @param clave La clave AES.
     * @return El texto encriptado convertido a formato Base64 para guardarlo seguro.
     */
    public static String encriptar(String datos, SecretKey clave) {
		return datos;
    }
    
    /**
     * Desencripta un texto en Base64 para recuperar los datos originales.
     * @param datosEncriptados El texto encriptado (leído del archivo).
     * @param clave La clave AES.
     * @return El texto plano original (contenido del CSV).
     */
    public static String desencriptar(String datosEncriptados, SecretKey clave) {
		return datosEncriptados;
    }
    
}
