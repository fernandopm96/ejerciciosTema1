/* 7. Completa el código para el programa de ejemplo de gestión de animales visto en los apuntes, 
haciendo uso del patrón MVC.*/

package ejercicio7;


public class Ejercicio7 {

	public static void main(String[] args) {
		
		Model model; View view; Controller controller;
		view = new View();
		model = new Model();
		view.colocaComponentes();
		controller = new Controller(view, model);
		controller.setEvents();	
		
	}
}
