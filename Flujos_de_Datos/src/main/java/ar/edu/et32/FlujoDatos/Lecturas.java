package ar.edu.et32.FlujoDatos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lecturas {
	/* System.out.println("hola");
	Scanner sc; 
	PROHIBIDOS
	
	BUFFER almacenamiento interno para despues usarse
	
	Readers y Writers clases preprocesadoras de informacion de escritura y lectura
	
	Todos los S.O tienen canales de comunicacion estandar, para comunicar los programas con el S.O, 
	todos estos lo tienen por defecto y estos son:
	
	SALIDA -> OUT
	System.out.println("hola");
	
	ENTRADA -> IN
	Scanner sc = new Scanner (System.in);
	
	ERRORES -> ERR
	try{
		// codigo con posible error
	}catch(tipoError){
		//como resuelve
		er.printStackTrace
		Logger
	}
	Se usa para atrapar errores, se encarga del error y que el programa no deje de funcionar
	
	*/
	
	private BufferedReader lector;  
	
	public Lecturas() {
		// Buffered(Almacenado)   ->   Reader(algo q una)  ->      Consola(Canal)       
		lector = new BufferedReader(new InputStreamReader(System.in));
		
	}

	public BufferedReader getLector() {
		return lector;
	}
	
	
}
