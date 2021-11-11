package ejercicio6;

import java.io.Serializable;

public class Empleado implements Serializable {
	
	private String dni;
	private String nombre;
	private double salario;
	
	public Empleado(String dni) {
		this.dni = dni;
	}
	public Empleado(String dni, String nombre, double salario) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.salario = salario;
	}
	public String getDni() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nombre=" + nombre + ", salario=" + salario + "]";
	}

}
