/*5. Realiza la aplicación que lea y muestre los datos del fichero XML creado por la anterior aplicación.*/

package ejercicio5;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio5 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		// Suponemos que el xml está en el mismo directorio
		Document documento = builder.parse(new File("./personas.xml"));
		
		NodeList personas = documento.getElementsByTagName("persona");
		
		System.out.println("LECTURA XML");
		System.out.println("---------------------");
		for(int i=0; i < personas.getLength(); i++) {
			
			Node persona = personas.item(i);
			Element elemento = (Element)persona;
			String nombre = elemento.getElementsByTagName("nombre")
					.item(0).getChildNodes().item(0).getNodeValue();
			String edad = elemento.getElementsByTagName("edad")
					.item(0).getChildNodes().item(0).getNodeValue();
			System.out.println("Nombre: " + nombre + "\tEdad: " + edad);
			System.out.println("---------------------");
		}
	}

}
