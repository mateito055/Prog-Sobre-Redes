package com.examen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import GenericFiles.managerFile;

/**
 * Sistema de registro de errores que escribe en el archivo crash.log.
 * <p>
 * Ningun error debe mostrarse por consola, todos deben registrarse aqui.
 * Utiliza FileWriter y PrintWriter para escribir en el archivo.
 */
public class LogManager {

    /**
     * Inicializa el sistema de log.
     * Crea o verifica que el archivo crash.log existe y esta listo para escribir.
     * Este metodo debe llamarse una unica vez al iniciar el programa.
     */
	public static void Inicializar(File f, String msg, boolean SobreEscribir) {
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException ex) {
					Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
				}
			}

			fw = new FileWriter(f); // <- canal de cominicacion / archivo
			pw = new PrintWriter(fw, !SobreEscribir);

			pw.println(msg);

			pw.flush();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
		} finally {
			try {
				if (pw == null)
					pw.close();
				if (fw == null)
					fw.close();
			} catch (IOException ex) {
				Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
			}
		}
	}
        // COMPLETAR: crear el archivo crash.log si no existe
    }

    /**
     * Registra un error en crash.log.
     * Escribe la fecha/hora, el mensaje de contexto y el stack trace de la excepcion.
     *
     * @param mensaje descripcion del contexto donde ocurrio el error
     * @param e       la excepcion capturada
     */
    public static void registrarError(String mensaje, Exception e) {
        // COMPLETAR: escribir mensaje y stack trace en crash.log
        // Usar FileWriter y PrintWriter
    }

    /**
     * Registra un mensaje informativo en crash.log.
     *
     * @param mensaje mensaje informativo a registrar
     */
    public static void registrarInfo(String mensaje) {
        // COMPLETAR: escribir mensaje informativo en crash.log
    }
}
