package ejercicio4;

public class Persona {
	
	private String nombre;
	private int edad;

	public Persona(String nombre, int edad){
		this.nombre = nombre;
		this.edad = edad;
	}
	public void setNombre(String nombre){ this.nombre = nombre; }
	public void setEdad(int edad){ this.edad = edad; }

	public String getNombre(){ return nombre; }
	public int getEdad(){ return edad; }

	public String toString(){ return "Nombre: " + nombre + "\tEdad: " + edad; }

}
