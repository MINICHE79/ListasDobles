package LinkedList;

import java.util.Iterator;

import node.node;

/*
METODOS QUE FALTAN
removebefore
removeafter
Falta un printer con iterator
*/

//Constructores

public class LinkedListD<T> implements Iterable {
	private node<T> start = null, end = null;
	
	public LinkedListD(){
		start = new node<T>();
		start.setIndex(-1);
		end = new node<T>();
		end.setIndex(-1);
	}
	
	public LinkedListD(T value){
		this();
		start.setNext(new node<T>(value));
//		start.getNext().setIndex(0);
		end.setBack(start.getNext());
		Reindex();
	}
	
	//Metodos de agregar
	
	public void add(T value){
		node<T> tmp = end.getBack();
		end.setBack(new node<T>(value));
		if (tmp == null){
			start.setNext(end.getBack());
		} 
		else{
			end.getBack().setBack(tmp);
			tmp.setNext(end.getBack());
		}
		Reindex();
	}
	
	public void addstart(T value){
		node<T> tmp = start.getNext();
		start.setNext(new node<T>(value));
		if (tmp == null){
//			start.getNext().setIndex(0);
			end.setBack(start.getNext());
		} 
		else{
			start.getNext().setNext(tmp);
			tmp.setBack(start.getNext());
		}
		Reindex();
	}
	
	public void addend(T value){
		node<T> tmp = end.getBack();
		end.setBack(new node<T>(value));
		if (tmp == null){
//			end.getBack().setIndex(0);
			start.setNext(end.getBack());
		} 
		else{
			end.getBack().setBack(tmp);
			tmp.setNext(end.getBack());
		}
		Reindex();
	}
	
	public boolean addbefore(T value, T newvalue){
		node<T> finder = search(value);
		node<T> nodo = new node<T>(newvalue);
		if(finder != null){
			nodo.setNext(finder);
			nodo.setBack(finder.getBack());
			finder.getBack().setNext(nodo);
			finder.setBack(nodo);
			Reindex();
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean addafter(T value, T newvalue){
		node<T> finder = search(value);
		node<T> nodo = new node<T>(newvalue);
		if(finder != null){
			nodo.setNext(finder.getNext());
			finder.setNext(nodo);
			nodo.setBack(finder);
			nodo.getNext().setBack(nodo);
			Reindex();
			return true;
		}
		else{
			return false;
		}
	}
	
	//Metodos Get (adquirir)
	
	public void getfirst(){
		node<T> tmp = start.getNext();
		System.out.println(tmp.getValue());

	}
	
	public void getlast(){
		node<T> tmp = end.getBack();
		System.out.println(tmp.getValue());
	}
	
	public void indexof(T value){
		node<T> finder = search(value);
		if(finder != null)
			System.out.println(finder.getIndex());
		else
			System.out.println("Valor no encontrado");
	}
	
	//Metodos de Busqueda
	
	public node<T> search (T value){
		return search (value, start, end); 
	}
	private node<T> search (T value, node<T> start, node<T> end){
		if (start.getNext() == null || end.getBack() == null){
			return null;
		}
		else if(start.getNext().getValue().equals(value)){
			return start.getNext();
		}
		else if(end.getBack().getValue().equals(value)){
			return end.getBack();
		}
		else if(start.getNext().equals(end) || start.equals(end)){
			return null;
		}
		return search(value, start.getNext(), end.getBack());
	}
	
	//Metodos para Eliminar o Reemplazar
	
	public boolean Remove(T value){
		node <T> tmp = search(value);
		if(tmp!=null){
			if(tmp.getNext() != null)
				tmp.getNext().setBack(tmp.getBack());
			else
				end.setBack(tmp.getBack());
			if(tmp.getBack() != null)
				tmp.getBack().setNext(tmp.getNext());
			else
				start.setNext(tmp.getNext());
			Reindex();
			return true;
		}
		return false;
	}
	
	//CLEAR
	
	public void clear(){
		while(start.getNext() != null){
			node<T> tmp = start;
			while (tmp.getNext().getNext()!=null) {
				tmp = tmp.getNext();
			}
			tmp.setNext(null);
		}
		while(end.getBack() != null) {
		
			node<T> tmp = end;
			while (tmp.getBack().getBack()!=null) {
				tmp = tmp.getBack();
			}
			tmp.setBack(null);
		}
		System.gc();
	}
	
	//REPLACE
	
	public boolean Replace(T value, T newvalue){
		node<T> finder = search(value);
		if(finder != null){
			finder.setValue(newvalue);
			return true;
		}
		else
			return false;
	}
	//REMOVEFIRST
	
	public boolean removefirst (){
		if(!IsEmpty()) {
			if(start.getNext().getNext() == null) {
				start.setNext(null);
				end.setBack(null);
				Reindex();
				return true;
			}
			else {
				node<T> tmp = start.getNext();
				tmp.getNext().setBack(null);
				start.setNext(tmp.getNext());
				Reindex();
				return true;
			}
		} return false;

	}
	//REMOVELAST
	
	public boolean removelast(){
		if(!IsEmpty()) {
			if(end.getBack().getBack() == null) {
				start.setNext(null);
				end.setBack(null);
				Reindex();
				return true;
			}
			else {
				node<T> tmp = end.getBack();
				tmp.getBack().setNext(null);
				end.setBack(tmp.getBack());
				Reindex();
				return true;
			} 	
		} return false;
	}
	//REMOVEBEFORE
	
	public boolean RemoveBefore(T value){
		node <T> tmp = search(value).getBack();
		if(tmp!=null){
			if(tmp.getNext() != null)
				tmp.getNext().setBack(tmp.getBack());
			else
				end.setBack(tmp.getBack());
			if(tmp.getBack() != null)
				tmp.getBack().setNext(tmp.getNext());
			else
				start.setNext(tmp.getNext());
			Reindex();
			return true;
		}
		return false;
	}
	
	//REMOVEAFTER
	
	public boolean RemoveAfter(T value){
		node <T> tmp = search(value).getNext();
		if(tmp!=null){
			if(tmp.getNext() != null)
				tmp.getNext().setBack(tmp.getBack());
			else
				end.setBack(tmp.getBack());
			if(tmp.getBack() != null)
				tmp.getBack().setNext(tmp.getNext());
			else
				start.setNext(tmp.getNext());
			Reindex();
			return true;
		}
		return false;
	}
	
	
	//Reindex
	
	public void Reindex(){
		node<T> tmp = start;
		int i = 0;
		while (tmp.getNext()!=null) {
			tmp = tmp.getNext();
			tmp.setIndex(i);
			i++;
		}
	}
	
	//Metodos para imprimir
	
	//SIZE
	
	public void size(){
		node<T> tmp = start;
		int i = 0;
		while (tmp.getNext()!=null) {
			tmp = tmp.getNext();
			i++;
		}
		System.out.println("Tamaño de la lista : " + i);
	}
	
	//ISEMPTY
	
	public boolean IsEmpty(){
		node<T> tmp = start;
		if(tmp.getNext() == null){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	public void printer() {
		
		
	}
	
	public void pronter() {
		node<T> tmp = start;
		while (tmp.getNext()!=null) {
			tmp = tmp.getNext();
			System.out.println(tmp.getValue()  + "   " + tmp.getIndex());
		}
	}
	
	public void prenter() {
		node<T> tmp = end;
		while (tmp.getBack()!=null) {
			tmp = tmp.getBack();
			System.out.println(tmp.getValue() + "   " + tmp.getIndex());
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			node<T> tmp = start;
			@Override
			public boolean hasNext() {
				tmp = tmp.getNext();
				return (tmp != null)?true:false;
			}
			@Override
			public T next() {
				return tmp.getValue();
			}
		};
	}

}
