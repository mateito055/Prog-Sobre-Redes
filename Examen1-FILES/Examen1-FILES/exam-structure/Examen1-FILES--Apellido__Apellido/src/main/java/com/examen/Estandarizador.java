package com.examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Base64;
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
     * @param f archivo juegos.dat a procesar
     */
    public void estandarizar(File f) {
        // COMPLETAR: leer archivo original, reemplazar + por " ; ",
        // escribir nuevo archivo .csv, eliminar el .dat original,
        // capturar errores con LogManager
        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder texto = new StringBuilder();
        PrintWriter pw = null;

        if (f == null || !f.exists()) {
            return;
        }

        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                // Reemplaza el caracter '+' por el formato limpio " ; " exigido
                // Se usa \\+ porque el signo + es un operando especial en Expresiones Regulares
                String lineaLimpia = linea.replaceAll("\\+", " ; ");
                texto.append(lineaLimpia).append("\n");
            }

            // Cerramos canales de lectura antes de proceder al borrado
            br.close();
            fr.close();

            // Definimos el archivo destino csv en la misma carpeta
            File archivoCsv = new File(f.getParent(), "juegos.csv");
            
            // Escribimos el nuevo contenido estandarizado
            pw = new PrintWriter(new FileWriter(archivoCsv, false));
            pw.print(texto.toString());
            pw.flush();
            pw.close();

            // Una vez creado con éxito, eliminamos el .dat original
            f.delete();

        } catch (IOException ex) {
            // Silencioso por consola: delega de forma directa a crash.log
            LogManager.registrarError("Fallo en el proceso de estandarización del archivo .dat", ex);
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
                if (pw != null) pw.close();
            } catch (IOException ex) {
                LogManager.registrarError("Error al cerrar flujos en estandarizar", ex);
            }
        }
    }

    /**
     * Genera una nueva clave simétrica AES de 256 bits.
     * Este método solo debe llamarse la primera vez que se ejecuta el programa.
     */
    public static SecretKey generarClaveAES() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // Configurado a 256 bits como pide la documentación
            return keyGen.generateKey();
        } catch (Exception ex) {
            LogManager.registrarError("Error al generar la clave simétrica AES de 256 bits", ex);
            return null;
        }
    }
    
    /**
     * Convierte la clave AES a texto (Base64) y la guarda en un archivo.
     * @param clave La clave generada que se desea guardar.
     * @param rutaArchivo Ruta donde se guardará (ej: "clave.key").
     */
    public static void guardarClave(SecretKey clave, String rutaArchivo) {
        PrintWriter pw = null;
        try {
            // 1. Obtenemos los bytes de la clave y los pasamos a texto Base64
            String claveEnTexto = Base64.getEncoder().encodeToString(clave.getEncoded());
            
            // 2. Guardamos ese texto en el archivo indicado
            pw = new PrintWriter(new FileWriter(rutaArchivo, false));
            pw.print(claveEnTexto);
            pw.flush();
        } catch (Exception ex) {
            LogManager.registrarError("Error al guardar la clave en el archivo: " + rutaArchivo, ex);
        } finally {
            if (pw != null) pw.close();
        }
    }

    /**
     * Lee el archivo de texto y reconstruye la clave AES para poder usarla.
     * @param rutaArchivo Ruta del archivo donde está guardada la clave (ej: "clave.key").
     * @return El objeto SecretKey reconstruido, o null si falla.
     */
    public static SecretKey recuperarClave(String rutaArchivo) {
        try {
            File f = new File(rutaArchivo);
            if (!f.exists()) return null;

            // 1. Leemos el texto completo (Base64) desde el archivo
            String textoLeido = new String(Files.readAllBytes(f.toPath())).trim();
            
            // 2. Decodificamos el texto para recuperar los bytes originales
            byte[] bytesClave = Base64.getDecoder().decode(textoLeido);
            
            // 3. Reconstruimos y retornamos la llave AES
            return new SecretKeySpec(bytesClave, 0, bytesClave.length, "AES");
        } catch (Exception ex) {
            LogManager.registrarError("Error al recuperar la clave del archivo: " + rutaArchivo, ex);
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
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] bytesEncriptados = cipher.doFinal(datos.getBytes());
            return Base64.getEncoder().encodeToString(bytesEncriptados);
        } catch (Exception ex) {
            LogManager.registrarError("Error interno durante el proceso de cifrado AES", ex);
            return null;
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
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] bytesCifrados = Base64.getDecoder().decode(datosEncriptados);
            byte[] bytesDescifrados = cipher.doFinal(bytesCifrados);
            return new String(bytesDescifrados);
        } catch (Exception ex) {
            LogManager.registrarError("Error interno durante el proceso de descifrado AES", ex);
            return null;
        }
    }
}
