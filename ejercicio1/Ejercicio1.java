package ejercicio1;
import java.util.*;
import java.util.stream.Collectors;

// 1. Realiza una aplicación que muestre las propiedades del usuario en el sistema.
public class Ejercicio1 {

	public static void main(String[] args) {
		
				Properties propiedades = System.getProperties();
				List<Object> keys = Collections.list(propiedades.keys())
						.stream()
						.filter(x -> ((String)x).substring(0,4).equals("user"))
						.collect(Collectors.toList());

				keys.forEach(k -> {
					System.out.println(k + "=" + propiedades.get(k));
				});
	}

}
