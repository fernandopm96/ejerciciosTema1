
/*6. Realiza una aplicación que, mediante RandomAccessFile, genere un fichero con datos de empleados de una 
 empresa. La posición ocupada por cada empleado en el fichero vendrá determinada por las 3 últimas cifras 
 de su DNI. Podrán haber sinónimos, resuélvelo con un área de excedentes al final del fichero.*/


/* Los empleados irán desde la posición 0 hasta la 999 * TAM_FICHERO. He establecido el tamaño del empleado en una cifra 
redondeada de 100B, con algo de margen entre empleado y empleado(String de 9(dni), String de 20(nombre) y double(salario)).
Registraré los empleados en un ArrayList. Después, los ordenaré en función de las tres últimas cifras del dni. 
A continuación, comprobaré si hay repeticiones y en caso afirmativo, las introduciré en un segundo ArrayList al cuál llamo
banquillo, y borraré del ArrayList 'empleados' las repeticiones. 

Para la escritura, recorro 'empleados'(ordenado y sin repeticiones) y me posicionaré en el fichero en base
a las tres últimas cifras de los empleados que hayan en el ArrayList. Una vez haya escrito el array principal, recorreré
el segundo ArrayList, 'banquillo', y a partir de la posición 1000 * TAM_EMPLEADO( = 100), escribiré secuencialmente las 
repeticiones de 'banquillo'.

Por último, realizaré la lectura de la misma forma que la escritura. Volveré a recorrer 'empleados', posicionándome con 
las tres últimas cifras de cada empleado, y la lectura del banquillo será secuencial, posicionándome de nuevo en 
1000 * TAM_FICHERO. */

package ejercicio6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Ejercicio6 {
	
	private static final int TAM_EMPLEADO = 100;  

	static RandomAccessFile raf;
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		ArrayList<Empleado> banquillo = new ArrayList<Empleado>();
		raf = new RandomAccessFile("alumnos.dat", "rw");
		
		empleados = ingresarDatos(empleados);
	
		// Ordenación del ArrayList en función de las tres últimas cifras del dni. 
		if(empleados.size() > 1) {

			Collections.sort(empleados, new Comparator<Empleado>() {			
				@Override
				public int compare(Empleado e1, Empleado e2) {
					return Integer.valueOf(posicion(e1.getDni())).compareTo(Integer.valueOf(posicion(e2.getDni())));
				}
			});
		}
		
		banquillo = banquillo(empleados);
		
		// Comprobación de que hay empleados que registrar.
		if(!empleados.isEmpty()) {
			escritura(empleados);
			if(!banquillo.isEmpty()) {
				escrituraBanquillo(banquillo);
			}
		} else {
			System.out.println("No hay empleados registrados. ");
		}
		// Comprobación de que hay empleados registrados.
		if(!empleados.isEmpty())
			lectura(empleados);
		if(!banquillo.isEmpty())
			lecturaBanquillo(banquillo);
		
		System.exit(0);			
	}
	
	// Función para pedir datos por teclado. 
	public static ArrayList<Empleado> ingresarDatos(ArrayList<Empleado> empleados) throws NumberFormatException {
		Scanner ent = new Scanner(System.in);
		String nombre = "", dni = "", salarioS = "", respuesta = "s";
		StringBuffer sbNombre = null;
		double salario;
		System.out.println("------- REGISTAR EMPLEADOS -------");
		System.out.println("\ns para introducir nuevo empleado. \tCualquier otra tecla para salir");
		respuesta = ent.nextLine();
		while(respuesta.equals("s")) {
			// DNI
			System.out.println("Introduce el dni: ");
			dni = ent.nextLine();
			while(!validaDni(dni)) {
				System.out.println("El valor no es válido. Introduce el dni: ");
				dni = ent.nextLine();
			}
			// NOMBRE
			System.out.println("Introduce el nombre: ");
			nombre = ent.nextLine();
			sbNombre = new StringBuffer(nombre);
			sbNombre.setLength(20);
			//SALARIO
			System.out.println("Introduce el salario");
			salarioS = ent.nextLine();
			while(!validaSalario(salarioS)) {
				System.out.println("El valor no es válido. Introduce el salario: ");
				salarioS = ent.nextLine();
			}
			salario = Double.valueOf(salarioS);
			Empleado empleado = new Empleado(dni, sbNombre.toString(), salario);
			empleados.add(empleado);
			System.out.println("¿Quieres introducir un nuevo empleado? \ns para introducir nuevo empleado. \tCualquier otra tecla para salir");
			respuesta = ent.nextLine();
		}
		return empleados;
		
	}
	
	// Función que validará el salario, estableciéndolo como válido siempre que sea un número positivo
	private static boolean validaSalario(String salario) {
		char[] cifras = salario.toCharArray();
	
		for(int i = 0 ; i < cifras.length ; i++) {
			if(cifras[0] < 49 || cifras[0] > 57) {
				return false;
			}
		}		
		if(Double.valueOf(salario) < 0)
			return false;
		return true;
	}
	
	/* Validación del dni. Se comprobará que el parámetro tenga una longitud de 9 digitos, que los ocho primeros 
	 sean números y que el último sea una letra. */
	private static boolean validaDni(String dni) {
	
		if(dni.length() != 9) {
			return false;
		} 
		
		char[] cifras = dni.toCharArray();
		
		for(byte i = 0; i < 8 ; i++) {
			if(cifras[i] < 48 || cifras[i] > 57) {
				return false;
			}
		}
		
		if((cifras[8] >= 65 && cifras[8] <= 90) || (cifras[8] >= 97 && cifras[8] <= 122)) {		
			return true;
		}
		return false;
	}

	/* Función encargadas de devolver un ArrayList con las repeticiones del arrayList recibido como parámetro.
	 Recorro el ArrayList de 'empleadosOrdenados' y saco el dni(las 3 últimas cifras) de la posición 'i' junto con el
	 de la siguiente posición. En caso de que coincida, borraré el objeto Empleado correspondiente a la siguiente 
	 posición(i+1) en 'empleadosOrdenados' para después añadirlo a 'banquillo'. */	
	public static ArrayList<Empleado> banquillo(ArrayList<Empleado> empleados){
		
		ArrayList<Empleado> banquillo = new ArrayList<Empleado>();
		
		for(int i = 0 ; i < empleados.size()-2 ; i++) {
			String dni1 = Integer.valueOf(posicion(empleados.get(i).getDni())).toString();
			String dni2 = Integer.valueOf(posicion(empleados.get(i+1).getDni())).toString();
			while(dni1.equals(dni2)) {
				Empleado e = empleados.get(i+1);
				banquillo.add(e);
				empleados.remove(i + 1);
				dni2 = Integer.valueOf(posicion(empleados.get(i+1).getDni())).toString();
			}
		}
		return banquillo;
	}
	
	/* Función encargada de escribir en el fichero según el orden establecido por las tres últimas cifras del dni. */	
	public static void escritura(ArrayList<Empleado> empleados) throws IOException {
		
		raf.seek(0);	
		for(Empleado e:empleados) {
			raf.seek(posicion(e.getDni()) * TAM_EMPLEADO);
			raf.writeUTF(e.getDni());
			raf.writeUTF(e.getNombre());
			raf.writeDouble(e.getSalario());			
		}
	}
	
	/* Función que gestionará la escritura de las repeticiones en la parte final del fichero
	(a partir de la posicion 1000 * TAM_EMPLEADO)*/	
	public static void escrituraBanquillo(ArrayList<Empleado> banquillo) throws IOException {
		if(banquillo.isEmpty()) {
			return;
		}
		int puntero = 1000 * TAM_EMPLEADO; 
		raf.seek(puntero);
		for(Empleado e:banquillo) {
			raf.seek(puntero);
			raf.writeUTF(e.getDni());
			raf.writeUTF(e.getNombre());
			raf.writeDouble(e.getSalario());
			puntero += TAM_EMPLEADO;
		}
	}
	
	// Lee el archivo posicionándose según el dni de los empleados(ordenados) del array 'empleados' pasado como parámetro. */
	public static void lectura(ArrayList<Empleado> empleados) throws IOException {
		
		System.out.println("\n**************************************");
		System.out.println("EMPLEADOS:");
		System.out.println("**************************************");
		for(Empleado e:empleados) {
			
			raf.seek(posicion(e.getDni()) * TAM_EMPLEADO);
			System.out.println("DNI: \t" + raf.readUTF());
			System.out.println("NOMBRE: \t" + raf.readUTF());
			System.out.println("SALARIO: \t " + raf.readDouble());
			System.out.println("-----------------------------------------");
		}	
	}
	
	// Lectura secuencial de los empleados repetidos, que comienza en la posición -> 1000 * TAM_EMPLEADO	
	public static void lecturaBanquillo(ArrayList<Empleado> banquillo) throws IOException {
		if(banquillo.isEmpty()) {
			return;
		}
		System.out.println("\n**************************************");
		System.out.println("BANQUILLO:");
		System.out.println("**************************************");
		int puntero = 1000 * TAM_EMPLEADO;
		raf.seek(puntero);
		while(puntero < raf.length()) {
			raf.seek(puntero);
			System.out.println("DNI: \t" + raf.readUTF());
			System.out.println("NOMBRE: \t" + raf.readUTF());
			System.out.println("SALARIO: \t " + raf.readDouble());
			System.out.println("-----------------------------------------");
			puntero += TAM_EMPLEADO;
		}
	}
	
	/* Función que devolverá las tres últimas cifras numéricas del dni que reciba como parámetro. */
	public static int posicion(String dni) {		
		int posicion = Integer.valueOf(dni.substring(5, 8));
		return posicion;
	}
		
}
