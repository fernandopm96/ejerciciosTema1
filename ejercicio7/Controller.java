package ejercicio7;

import javax.swing.JOptionPane;

public class Controller {

	protected View view;
	protected Model model;
	
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	
	// Función encargada de asignar eventos a los componentes de la vista
	public void setEvents() {
		
		view.bNuevo.addActionListener(x -> { limpiar(); });
		view.bGuardar.addActionListener(x -> { nuevo(); });
		view.bModificar.addActionListener(x -> { modificar(); });
		view.bEliminar.addActionListener(x -> { eliminar(); }); 
		view.bPrimero.addActionListener(x -> { posicion(view.bPrimero.getText()); });
		view.bUltimo.addActionListener(x -> { posicion(view.bUltimo.getText()); });
		view.bAnterior.addActionListener(x -> { posicion(view.bAnterior.getText()); });
		view.bSiguiente.addActionListener(x -> { posicion(view.bSiguiente.getText()); });
		
	}
	
	/* Llama al modelo para actualizar la posición, y después rellena los valores de los TextField con los datos que 
	devuelven las llamadas a las funciones del modelo. */
	private void posicion(String posicion) {
		if(model.animales.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Aún no hay animales registrados. ");
			return;
		}
		model.posicion(posicion);
		view.tfNombre.setText(model.nombre());
		view.tfRaza.setText(model.raza());
		view.tfCaracteristicas.setText(model.caracteristicas());
		view.tfPeso.setText(Double.toString(model.peso()));
	}
	
	// Deja en blanco los JTextField
	private void limpiar() {
		view.tfNombre.setText("");
		view.tfRaza.setText("");
		view.tfCaracteristicas.setText("");
		view.tfPeso.setText("");
	}
	
	/* Obtiene los valores de los JTextField y comprueba que no estén sin rellenar, para después crear un objeto Animal 
	y llamar al modelo para registrarlo en el ArrayList. */
	private void nuevo() {
		String nombre, raza, caracteristicas;
		double peso;
		
		if(view.tfNombre.getText().isEmpty() || view.tfRaza.getText().isEmpty() || 
				view.tfCaracteristicas.getText().isEmpty() || view.tfPeso.getText().isEmpty()) {
			JOptionPane.showMessageDialog(view, "Debes rellenar todos los campos.");
		} else {
			if(isNumber(view.tfPeso.getText())) {
				nombre = view.tfNombre.getText();
				raza = view.tfRaza.getText();
				caracteristicas = view.tfCaracteristicas.getText();
				peso = Double.valueOf(view.tfPeso.getText());
				Animal animal = new Animal(nombre, raza, caracteristicas, peso);
				model.guardar(animal);
				JOptionPane.showMessageDialog(view, "Registro realizado correctamente.");
			} else {
				JOptionPane.showMessageDialog(view, "'Peso' debe ser un valor numérico.");
			}
		}
	
		limpiar();
	}
	
	/* Pide un nombre de animal y comprueba si está registrado. Si lo encuentra, los TextField se rellenan con los datos de
	 ese animal y posteriormente se borra. El usuario modificará los datos que quiera y guardará los cambios. */
	private void modificar() {
		if(model.animales.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Aún no hay animales registrados. ");
			return;
		}
		String respuesta = JOptionPane.showInputDialog(view, "Introduce el nombre del animal");
		int busqueda = model.animalExiste(respuesta);
		if(busqueda != -1) {
			JOptionPane.showMessageDialog(view, "Modifica los datos y guarda los cambios.");
			model.indice = busqueda;
			view.tfNombre.setText(model.nombre());
			view.tfRaza.setText(model.raza());
			view.tfCaracteristicas.setText(model.caracteristicas());
			view.tfPeso.setText(Double.toString(model.peso()));
			model.eliminar();
		} else {
			JOptionPane.showMessageDialog(view, "El animal no existe");
		}
	}
	/* Se lanza un mensaje de advertencia antes de eliminar. En caso afirmativo, la función 'showConfirmDialog' devolverá
	un 0, y se borrará el animal. */
	private void eliminar() {
		if(model.animales.isEmpty()) {
			JOptionPane.showMessageDialog(view, "Aún no hay animales registrados. ");
			return;
		}
		int resultado = JOptionPane.showConfirmDialog(view,
				"¿Quieres eliminar el animal " + 
				model.animales.get(model.indice).getNombre() + "?" );
		
		switch(resultado) {
			case 0: model.eliminar();
					JOptionPane.showMessageDialog(view, "Animal eliminado"); 
					limpiar();
					break;
			default: break;
		}
		
	}	
	
	// Función que comprueba si un String contiene un valor numérico. 
	public boolean isNumber(String numero) {
		boolean isNumber;
		try {
			Double.parseDouble(numero);
			isNumber = true;
		} catch(NumberFormatException e) {
			isNumber = false;
		}
		return isNumber;
	}
	
	
}
