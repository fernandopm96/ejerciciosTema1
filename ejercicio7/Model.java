package ejercicio7;

import java.util.ArrayList;

public class Model {
	
	protected static ArrayList<Animal> animales;
	protected static int indice = 0;
	
	public Model() {
		animales = new ArrayList<Animal>();
	}
	
	/* Actualizará la posición del elemento a mostrar en función del botón presionado, modificando el atributo estático
	'posición'. */
	public void posicion(String posicion) {
		switch(posicion) {
			case "|<" : indice = 0; break;
			
			case "<" : if(indice > 0) {
					indice--;
				} 
				break;	
			
			case ">" : 
				if(indice < animales.size() - 1) {
					indice++;
				}
				break;
			
			case ">|" : indice = animales.size()-1; 
		}
	}

	public void guardar(Animal animal) {
		animales.add(animal);
	}
	
	public void eliminar() {
		animales.remove(indice);
	}
	
	// Comprueba que el parámetro coincide con algún nombre de los animales del ArrayList.
	public int animalExiste(String nombre) {
		int index = -1;
		for(Animal a:animales) {
			if(a.getNombre().equals(nombre))
				index = animales.indexOf(a);
		}
		return index;
	}
	
	// Funciones encargadas de devolver el valor del atributo correspondiente al animal según el valor del índice. 
	public String nombre() {
		return animales.get(indice).getNombre();
	}
	public String caracteristicas() {
		return animales.get(indice).getCaracteristicas();
	}
	public String raza() {
		return animales.get(indice).getRaza();
	}
	public double peso() {
		return animales.get(indice).getPeso();
	}
	
	
}
