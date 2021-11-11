package ejercicio7;

import java.awt.*;

import javax.swing.*;

public class View extends JFrame {
	
	protected JPanel panel1, panel2;
	protected JTextField tfNombre, tfRaza, tfCaracteristicas, tfPeso;
	protected JLabel jlNombre, jlRaza, jlCaracteristicas, jlPeso;
	protected JButton bGuardar, bNuevo, bModificar, bEliminar, bPrimero, bUltimo, bAnterior, bSiguiente;
		
	public void colocaComponentes() {
		this.setTitle("Ejercicio 7 - Animales");
		this.setLayout(new GridLayout(0,1));
		panel1 = new JPanel(new GridLayout(4,2));
		this.add(panel1);
		jlNombre = new JLabel("Nombre: ");
		jlRaza = new JLabel("Raza: ");
		jlCaracteristicas = new JLabel("Caracteristicas: ");
		jlPeso = new JLabel("Peso: ");
		tfNombre = new JTextField();
		tfRaza = new JTextField();
		tfCaracteristicas = new JTextField();
		tfPeso = new JTextField();
		panel1.add(jlNombre); panel1.add(tfNombre);
		panel1.add(jlRaza); panel1.add(tfRaza);
		panel1.add(jlCaracteristicas); panel1.add(tfCaracteristicas);
		panel1.add(jlPeso); panel1.add(tfPeso);
		panel2 = new JPanel(new GridLayout(2,4));
		bGuardar = new JButton("Guardar");
		bNuevo = new JButton("Nuevo");
		bModificar = new JButton("Modificar");
		bEliminar = new JButton("Eliminar");
		bPrimero = new JButton("|<");
		bAnterior = new JButton("<");
		bSiguiente = new JButton(">");
		bUltimo = new JButton(">|");
		panel2.add(bGuardar); panel2.add(bNuevo);
		panel2.add(bModificar); panel2.add(bEliminar);
		panel2.add(bPrimero); panel2.add(bAnterior);
		panel2.add(bSiguiente); panel2.add(bUltimo);
		this.add(panel2);
		this.setSize(400, 200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
