package com.examen;

public class Partida {
    private String fecha;
    private String resultado; 
    private boolean primeraSangre;
    private boolean primeraTorreta;
    private int asesinatos;
    private int muertes;
    private int asistencias;

    public Partida(String fecha, String resultado, boolean primeraSangre, boolean primeraTorreta, int asesinatos, int muertes, int asistencias) {
        this.fecha = fecha;
        this.resultado = resultado;
        this.primeraSangre = primeraSangre;
        this.primeraTorreta = primeraTorreta;
        this.asesinatos = asesinatos;
        this.muertes = muertes;
        this.asistencias = asistencias;
    }

    public String getFecha() { return fecha; }
    public String getResultado() { return resultado; }
    public boolean isPrimeraSangre() { return primeraSangre; }
    public boolean isPrimeraTorreta() { return primeraTorreta; }
    public int getAsesinatos() { return asesinatos; }
    public int getMuertes() { return muertes; }
    public int getAsistencias() { return asistencias; }

    public double calcularKDA() {
        if (this.muertes == 0) {
            return (this.asesinatos + this.asistencias); 
        }
        return (double) (this.asesinatos + this.asistencias) / this.muertes;
    }

    @Override
    public String toString() {
        return fecha + ";" + resultado + ";" + primeraSangre + ";" + primeraTorreta + ";" + asesinatos + ";" + muertes + ";" + asistencias;
    }
}

