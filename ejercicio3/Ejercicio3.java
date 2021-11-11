/*3. Basándote en los apuntes y ejemplos de

    http://chuwiki.chuidiang.org/index.php?title=Leer_y_modificar_fichero_de_propiedades_en_java

crea un fichero de propiedades con el siguiente contenido:

#Fichero de configuración de la aplicación X

version=1.2.3
lanzamiento=11/08/2021
standalone=yes
port=5858

Posteriormente el programa cargará estas propiedades, las modiicará y actualizará a fichero modificando la fecha y la versión.
*/
package ejercicio3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Ejercicio3 {
	
	public static void main(String[] args) throws IOException {

		Properties propiedades = new Properties();
		propiedades.setProperty("version", "1.2.3");
		propiedades.setProperty("lanzamiento","11/08/2021");
		propiedades.setProperty("standalone","yes");
		propiedades.setProperty("port","5858");

		propiedades.store(new FileOutputStream("./propiedades.prop"), "Propiedades ejercicio 3");
		
		propiedades.load(new FileInputStream("./propiedades.prop"));
		propiedades.setProperty("version", "1.2.3.4");
		propiedades.setProperty("lanzamiento", "17/09/2021");
		propiedades.store(new FileOutputStream("./propiedades.prop"), "Propiedades ejercicio 3");

		// Opcionalmente muestro las propiedades
		propiedades.list(System.out);
	}
}
