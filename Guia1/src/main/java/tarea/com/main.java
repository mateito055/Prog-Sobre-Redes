package tarea.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class main {

	public void TP1_Ej1() {
		   try {
		       ArrayList<String> lista = new ArrayList<>();


		       for(int i=0;i<5;i++){
		           Utils.getOut().println("Nombre:");
		           lista.add(Utils.getLector().readLine());
		       }


		       Utils.getOut().println(lista);
		       Utils.getOut().println("Cantidad: " + lista.size());
		       Utils.getOut().println("Primero: " + lista.get(0));
		       Utils.getOut().println("Ultimo: " + lista.get(lista.size()-1));


		       for(String n: lista){
		           Utils.getOut().println(n.toUpperCase());
		       }


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej2(ArrayList<String> lista) {
		   try {
		       Utils.getOut().println("Buscar:");
		       String b = Utils.getLector().readLine();


		       if(lista.contains(b)){
		           Utils.getOut().println("Existe en: " + lista.indexOf(b));
		       }else{
		           Utils.getOut().println("No existe");
		       }


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej3(ArrayList<String> lista) {
		   lista.set(2, "Nuevo");
		   lista.remove("Nombre");


		   Utils.getOut().println(lista);
		}

	public void TP1_Ej4(ArrayList<String> lista) {


		   for(int i=0;i<lista.size();i++){
		       Utils.getOut().println("\u001B[32m" + lista.get(i) + "\u001B[0m");
		   }


		   for(String s: lista){
		       Utils.getOut().println("\u001B[34m" + s + "\u001B[0m");
		   }


		   Iterator<String> it = lista.iterator();
		   while(it.hasNext()){
		       Utils.getOut().println("\u001B[35m" + it.next() + "\u001B[0m");
		   }
		}

	public void TP1_Ej5() {
		   try {
		       Utils.getOut().println("Frase:");
		       String f = Utils.getLector().readLine();


		       String[] p = f.split(" ");
		       ArrayList<String> lista = new ArrayList<>();


		       for(String s:p) lista.add(s);


		       Utils.getOut().println("Cantidad: " + lista.size());


		       String larga = "";
		       for(String s: lista){
		           if(s.length()>larga.length()) larga=s;
		       }
		       Utils.getOut().println("Mas larga: " + larga);


		       Utils.getOut().println("Letra:");
		       String l = Utils.getLector().readLine();


		       int c=0;
		       for(String s: lista){
		           if(s.contains(l)) c++;
		       }


		       Utils.getOut().println("Contienen: " + c);


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej6(ArrayList<String> lista) {


		   for(int i=0;i<lista.size();i++){
		       String s = lista.get(i).toLowerCase().trim();
		       s = s.replaceAll("[aeiou]", "*");
		       lista.set(i, s);
		   }


		   Utils.getOut().println(lista);
		}

	public void TP1_Ej7() {
		   try {
		       HashMap<String,String> map = new HashMap<>();


		       for(int i=0;i<5;i++){
		           Utils.getOut().println("Esp:");
		           String esp = Utils.getLector().readLine();


		           Utils.getOut().println("Ing:");
		           String ing = Utils.getLector().readLine();


		           map.put(esp, ing);
		       }


		       Utils.getOut().println(map.entrySet());
		       Utils.getOut().println(map.keySet());
		       Utils.getOut().println(map.values());


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej8(HashMap<String,String> map) {
		   try {
		       String p = Utils.getLector().readLine();


		       if(map.containsKey(p)){
		           Utils.getOut().println(map.get(p));
		       }else{
		           Utils.getOut().println("Error");
		       }


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej9(HashMap<String,String> map) {
		   try {
		       String f = Utils.getLector().readLine();
		       String[] p = f.split(" ");


		       for(String s: p){
		           if(map.containsKey(s))
		               Utils.getOut().print(map.get(s)+" ");
		           else
		               Utils.getOut().print("[???] ");
		       }


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej10() {
		   try {
		       String f = Utils.getLector().readLine();
		       String[] p = f.split(" ");


		       HashMap<String,Integer> map = new HashMap<>();


		       for(String s: p){
		           if(map.containsKey(s))
		               map.put(s, map.get(s)+1);
		           else
		               map.put(s,1);
		       }


		       Utils.getOut().println(map);


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej11() {
		   try {
		       ArrayList<Integer> lista = new ArrayList<>();


		       for(int i=0;i<5;i++){
		           lista.add(Integer.parseInt(Utils.getLector().readLine()));
		       }


		       HashSet<Integer> set = new HashSet<>(lista);


		       Utils.getOut().println(lista);
		       Utils.getOut().println(set);


		   } catch(IOException e){ e.printStackTrace(); }
		}

	public void TP1_Ej12() {
		   try {
		       ArrayList<String> nombres = new ArrayList<>();
		       HashMap<String,Integer> notas = new HashMap<>();


		       
		       for(int i=0;i<3;i++){
		           String n = Utils.getLector().readLine();
		           int nota = Integer.parseInt(Utils.getLector().readLine());


		           nombres.add(n);
		           notas.put(n, nota);
		       }


		       
		       for(String n: nombres){
		           int nota = notas.get(n);


		           if(nota>=6)
		               Utils.getOut().println("\u001B[32m"+n+" "+nota+"\u001B[0m");
		           else
		               Utils.getOut().println("\u001B[31m"+n+" "+nota+"\u001B[0m");
		       }
		       int suma=0;
		       for(int v: notas.values()) suma+=v;


		       Utils.getOut().println("Promedio: " + (suma/notas.size()));


		   } catch(IOException e){ e.printStackTrace(); }
		}

}
