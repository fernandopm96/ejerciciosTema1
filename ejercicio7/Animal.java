package ejercicio7;

public class Animal {
	
	public Animal() {
		super();
	}
	
	public Animal(String nombre, String raza, String caracteristicas, double peso) {
		super();
		this.nombre = nombre;
		this.raza = raza;
		this.caracteristicas = caracteristicas;
		this.peso = peso;
	}
	
	private String nombre;
	private String raza;
	private String caracteristicas;
	private double peso;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Animal [nombre=" + nombre + ", raza=" + raza + ", caracteristicas=" + caracteristicas + ", peso=" + peso
				+ "]";
	}
	
}
