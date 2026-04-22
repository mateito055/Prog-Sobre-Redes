package tarea.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ej1 {
	private static BufferedReader lector;
	private static PrintWriter out;

	
	public void Guia1_Ej1(){
		lector = new BufferedReader( new InputStreamReader(System.in) );
		out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(System.out) ) ,true );
	
out.println("ingrese el nombre de la organizacion: ");	
try {
		String aux = lector.readLine();
		String PalabrasMayus = aux.toUpperCase();
		char letra1 = PalabrasMayus.charAt(0);
		int espacio = PalabrasMayus.indexOf(" ");
		String palabras = PalabrasMayus.substring(espacio +1);
		char letra2 = palabras.charAt(0);
		int espacio2 = palabras.indexOf(" ");
		String palabras2 = palabras.substring(espacio2 +1);
		char letra3 = palabras2.charAt(0);
		
		
		out.println(letra1);
		out.println(letra2);
		out.println(letra3);
		
	
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}

	public void Guia1_Ej2() {
		   Utils.getOut().println("Ingrese palabra:");
		   try {
		       String p = Utils.getLector().readLine();
		       String inv = new StringBuilder(p).reverse().toString();


		       if(p.equals(inv))
		           Utils.getOut().println("Es palindromo");
		       else
		           Utils.getOut().println("No es palindromo");


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej3() {
		   Utils.getOut().println("Ingrese texto:");
		   try {
		       String t = Utils.getLector().readLine().toLowerCase();


		       int c = 0;
		       c += t.length() - t.replace("a","").length();
		       c += t.length() - t.replace("e","").length();
		       c += t.length() - t.replace("i","").length();
		       c += t.length() - t.replace("o","").length();
		       c += t.length() - t.replace("u","").length();


		       Utils.getOut().println(c);
		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej4() {
		   Utils.getOut().println("Texto:");
		   try {
		       String t = Utils.getLector().readLine();


		       Utils.getOut().println("Buscar:");
		       String b = Utils.getLector().readLine();


		       Utils.getOut().println("Reemplazo:");
		       String r = Utils.getLector().readLine();


		       Utils.getOut().println(t.replace(b, r));


		   } catch (IOException e) { e.printStackTrace(); }
		}
	
	public void Guia1_Ej5() {
		   Utils.getOut().println("Email:");
		   try {
		       String e = Utils.getLector().readLine();


		       String u = e.substring(0, e.indexOf("@"));
		       u = u.substring(0,1).toUpperCase() + u.substring(1).toLowerCase();


		       Utils.getOut().println("Bienvenido " + u);


		   } catch (IOException ex) { ex.printStackTrace(); }
		}

	public void Guia1_Ej6() {
		   Utils.getOut().println("Frase:");
		   try {
		       String t = Utils.getLector().readLine();
		       t = t.trim().replaceAll(" +", " ");


		       Utils.getOut().println(t);


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej7() {
		   Utils.getOut().println("Password:");
		   try {
		       String p = Utils.getLector().readLine();


		       boolean l = p.length() >= 8;
		       boolean n = p.matches(".*\\d.*");
		       boolean c = p.toLowerCase().contains("clave");


		       if(l && n && !c)
		           Utils.getOut().println("SEGURO");
		       else
		           Utils.getOut().println("VULNERABLE");


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej8() {
		   Utils.getOut().println("Ruta:");
		   try {
		       String r = Utils.getLector().readLine();
		       String ext = r.substring(r.lastIndexOf(".")+1);


		       Utils.getOut().println(ext);


		   } catch (IOException e) { e.printStackTrace(); }
		}
	
	public void Guia1_Ej9() {
		try {
			Utils.getOut().println("Ingrese una frase de 3 palabras");
			String linea= Utils.getLector().readLine();
			String textoFinal= "";
			
			for(int i = 0; i<=linea.length(); i++) {
				if (i%2 == 0 && linea.charAt(i) == ' ') {
					
				textoFinal = textoFinal.concat(linea.substring(i,1).toLowerCase()) ;
				} else if (i%2 == 1 && linea.charAt(i) == ' '){
				textoFinal = textoFinal.concat(linea.substring(i,1).toUpperCase()) ;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Guia1_Ej10() {
		   Utils.getOut().println("Texto:");
		   try {
		       String t = Utils.getLector().readLine();


		       Utils.getOut().println("Buscar:");
		       String b = Utils.getLector().readLine();


		       int ini = t.indexOf(b);
		       int fin = ini + b.length();


		       Utils.getOut().println("Inicio: " + ini);
		       Utils.getOut().println("Fin: " + fin);


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej11() {
		try {
		Utils.getOut().println("Nombre Producto: ");
		String producto =Utils.getLector().readLine();
		
		Utils.getOut().println("Cantidad: ");
		Integer cant = Integer.parseInt(Utils.getLector().readLine());
		
		Utils.getOut().println("Precio: ");
		float precio = Float.parseFloat(Utils.getLector().readLine());
		
		
		StringBuilder st = new StringBuilder();
		st.append("Producto: ").append(producto);
		st.append(" Cantidad: ").append(cant);
		st.append(" Precio Unitario: ").append(precio);
		st.append(" Precio Total: ").append(cant * precio);
		Utils.getOut().println(st.toString());
		
		}catch(IOException ex) {
			
		}
		
	}

	public void Guia1_Ej12() {
		   try {
		       String n = Utils.getLector().readLine();


		       StringBuilder sb = new StringBuilder(n);
		       sb.insert(0, "Dr./Dra. ");


		       Utils.getOut().println(sb.toString());


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej13() {
		   try {
		       String t = Utils.getLector().readLine();
		       StringBuilder sb = new StringBuilder(t);


		       int i = sb.indexOf("error");


		       if(i != -1)
		           sb.delete(i, i + 6);


		       Utils.getOut().println(sb.toString());


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej14() {
		   try {
		       String p = Utils.getLector().readLine();


		       StringBuilder sb = new StringBuilder(p);
		       sb.insert(0, "<b>");
		       sb.append("</b>");


		       Utils.getOut().println(sb.toString());


		   } catch (IOException e) { e.printStackTrace(); }
		}

	public void Guia1_Ej15() {
		   try {
		       String p1 = Utils.getLector().readLine();
		       String p2 = Utils.getLector().readLine();
		       String p3 = Utils.getLector().readLine();


		       StringBuilder sb = new StringBuilder();
		       sb.append(p1).append("-").append(p2).append("-").append(p3);


		       int i = sb.indexOf(p2);


		       if(i != -1)
		           sb.delete(i - 1, i + p2.length() + 1);


		       Utils.getOut().println(sb.toString());


		   } catch (IOException e) { e.printStackTrace(); }
		}

}
