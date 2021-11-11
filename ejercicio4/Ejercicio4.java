/*4. Realiza la aplicación que genere un fichero XML con datos de personas como en el ejercicio 1. */
package ejercicio4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio4 {
	
	public static void main(String args[]) throws ParserConfigurationException, TransformerException, FileNotFoundException {
		
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Scanner ent = new Scanner(System.in);
		String nombre, edad = "", respuesta = "s";
		
		// Introducción de empleados por consola.
		System.out.println("******* REGISTRO *******\n");
		while(respuesta.equals("s")) {
			System.out.println("Introduce el nombre: ");
			nombre = ent.nextLine();
			while(!validaEdad(edad)) {
				System.out.println("Introduce la edad: ");
				edad = ent.nextLine();
			}			
			Persona p = new Persona(nombre, Integer.parseInt(edad));
			personas.add(p);
			System.out.println("¿Quieres introducir una nueva persona?");
			System.out.println("s -> si\nCualquier otra letra para salir\n");
			respuesta = ent.nextLine();
			edad = "";
		}
		
		// Proceso de creación del 'Document'
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation dom = builder.getDOMImplementation();
		Document documento = dom.createDocument(null, "xml", null);
		Element raiz = documento.createElement("personas");
		documento.getDocumentElement().appendChild(raiz);
		Element nodoPersona = null, nodoDatos = null;
		Text texto = null;
		
		// Creación de nodos
		for(Persona p:personas) {
			// Se crea el nodo persona
			nodoPersona = documento.createElement("persona");
			raiz.appendChild(nodoPersona);
			// nodo nombre dentro de persona
			nodoDatos = documento.createElement("nombre");
			nodoPersona.appendChild(nodoDatos);
			texto = documento.createTextNode(p.getNombre());
			nodoDatos.appendChild(texto);
			// nodo edad dentro de persona
			nodoDatos = documento.createElement("edad");
			nodoPersona.appendChild(nodoDatos);
			texto = documento.createTextNode(Integer.valueOf(p.getEdad()).toString());
			nodoDatos.appendChild(texto);	
		}
		
		// Creación del archivo en el mismo directorio
		Source source = new DOMSource(documento);
		Result resultado = new StreamResult(new File("./personas.xml"));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, resultado);
	
		System.out.println("XML creado.");
	}
	
	// Método que comprueba que la edad sea un valor válido(entero positivo y menor a 150).
	public static boolean validaEdad(String respuesta) {
		try {
			int edad = Integer.parseInt(respuesta);
			if(edad < 0 || edad > 150)
				return false;
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
