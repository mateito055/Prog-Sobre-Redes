package com.examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
    public void estandarizar(String rutaOriginal) {
        // COMPLETAR: leer archivo original, reemplazar + por " ; ",
        // escribir nuevo archivo .csv, eliminar el .dat original,
        // capturar errores con LogManager
    		
    		File archivoOriginal = new File(rutaOriginal);
    		if(!archivoOriginal.exists()){
    			return; // Si no existe el original, no hacemos nada (ya se estandarizó en otra ejecución)
    		}
    	try {
    		
    		// Leer y estandarizar en memoria reemplazando '+' por ';'
    		
    		StringBuilder contenidoEstandarizado = new StringBuilder();
    		try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))){
				String linea;
				boolean esCabecera = true;
				while((linea = br.readLine()) != null) {
					if (esCabecera) {
						// reemplazar cabecera: '+' por ';'
						contenidoEstandarizado.append(linea.replace('+', ';')).append("\n");
						esCabecera = false;
					} else {
						contenidoEstandarizado.append(linea.replace('+', ';')).append("\n");
					}
					
				}
			}
    		
    		// Generar y guardar la clave AES
    		
    		SecretKey clave = generarClaveAES();
    		guardarClave(clave, "AES.key");
    		
    		
    		// Encriptar el contenido usando Cipher
    		
    		String datosEncriptados = encriptar(contenidoEstandarizado.toString(), clave);
    		
    		// Guardar en juegos_encriptado.csv;
    		
    		try(PrintWriter pw = new PrintWriter (new FileWriter("juegos_encriptado.csv"))){
    			
    			pw.print(datosEncriptados);
    			
    		}
    		
    		// Eliminar el archivo original
    		
    		Files.delete(archivoOriginal.toPath());
    		
    		LogManager.registrarInfo("Estandarización y encriptación inicial completadas con éxito.");
    		
    }catch (Exception e) {
    	LogManager.registrarError("Error durante el proceso de estandarización", e);
    }
	
    		
    		
    		
    		
    		
    		
    	
        }
    	   	
    	
    
    
    /**
     * Genera una nueva clave simétrica AES de 256 bits.
     * Este método solo debe llamarse la primera vez que se ejecuta el programa.
     */
    public static SecretKey generarClaveAES() {
		try{
			KeyGenerator keyGen = KeyGenerator.getInstance("AES"); 
			keyGen.init(256); // genera clave simétrica AES de 256 bits
			return keyGen.generateKey();
		}catch (Exception e){
			LogManager.registrarError("No se pudo generar la clave AES", e);
			return null;
		}
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
            try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))){
            	pw.print(claveEnTexto);
            }
            
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
            
            try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
                textoLeido = lector.readLine();
            }
            
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
    	try {
    		if (datos == null || clave == null) {
    			return null;
    		}
    		Cipher cipher = Cipher.getInstance("AES");
    		cipher.init(Cipher.ENCRYPT_MODE, clave);
    		byte[] bytesEncriptados = cipher.doFinal(datos.getBytes("UTF-8"));
    		return Base64.getEncoder().encodeToString(bytesEncriptados);
    	}catch (Exception e) {
    		LogManager.registrarError("Error al encriptar datos mediante AES", e);
    		return "";
    	}
		
    	
    }
    
    /**
     * Desencripta un texto en Base64 para recuperar los datos originales.
     * @param datosEncriptados El texto encriptado (leído del archivo).
     * @param clave La clave AES.
     * @return El texto plano original (contenido del CSV).
     */
    public static String desencriptar(String datosEncriptados, SecretKey clave) {
		try {
			if (datosEncriptados == null || clave == null) {
				return null;
			}
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, clave);
			byte[] bytesDecodificados = Base64.getDecoder().decode(datosEncriptados.trim());
			byte[] bytesDesencriptados = cipher.doFinal(bytesDecodificados);
			return new String(bytesDesencriptados, "UTF-8");
		} catch (Exception e) {
			LogManager.registrarError("Error al desencriptar datos mediante AES", e);
			return null;
		}
    }
    
}
