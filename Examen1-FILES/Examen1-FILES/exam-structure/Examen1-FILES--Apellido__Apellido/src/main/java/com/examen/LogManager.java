package com.examen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import GenericFiles.managerFile;

public class LogManager {

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

            fw = new FileWriter(f, !SobreEscribir); 
            pw = new PrintWriter(fw);

            pw.println(msg);
            pw.flush();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }

    public static void registrarError(String mensaje, Exception e) {
        File f = new File("crash.log");
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f, true);
            pw = new PrintWriter(fw);

            pw.println("=== REGISTRO DE ERROR ===");
            pw.println("Mensaje: " + mensaje);
            pw.println("Fecha/Hora: " + new java.util.Date());
            pw.println("Stack Trace:");
            e.printStackTrace(pw); 
            pw.println("=========================\n");
            pw.flush();

        } catch (IOException ex) {
            Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
        } finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }

    public static void registrarInfo(String mensaje) {
        File f = new File("crash.log");
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f, true);
            pw = new PrintWriter(fw);

            pw.println("[" + new java.util.Date() + "] INFO: " + mensaje);
            pw.flush();

        } catch (IOException ex) {
            Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
        } finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                Logger.getLogger(managerFile.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
