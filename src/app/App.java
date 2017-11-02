package app;



import java.util.Iterator;

import LinkedList.LinkedListD;

public class App {

	public static void main(String[] args) {
		LinkedListD<String> names     = new LinkedListD<String>();
		names.addstart("adios");
		names.addstart("hola");
		names.addend("Carcel");
		names.addstart("lolis");
		names.add("Pepe");
		names.addafter("hola", "Jose");
		names.addbefore("hola", "Perro");
		
//		names.clear();
//		names.Remove("Perro");
//		names.removefirst();
//		names.removelast();
//		names.RemoveAfter("adios");
//		names.RemoveBefore("Pepe");
		
		
		Iterator<String> iterator = names.iterator();
		for (Iterator<String> i = iterator; i.hasNext();) {
			System.out.println(iterator.next());
		}
		
		names.pronter();
//		names.prenter();
//		names.indexof("Jose");
//		names.getfirst();
//		names.getlast();

	}

}
