package org.iesalandalus.programacion.gestionclientes.vista.iutextual;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.vista.IVistaGestionClientes;


public class VistaGestionClientes implements IVistaGestionClientes {
	
	private static final String ERROR = "ERROR: ";
	
	private IControladorGestionClientes controlador;

	public VistaGestionClientes() {
		Opcion.setVista(this);
	}
	
	@Override
	public void setControlador(IControladorGestionClientes controlador) {
		this.controlador = controlador;
	}

	@Override
	public void comenzar() {
		Consola.mostrarCabecera("Programa para la gestión de clientes");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		controlador.salir();
	}
	
	public void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			Cliente cliente = Consola.leerCliente();
			controlador.insertarCliente(cliente);
			System.out.println("Cliente insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			Cliente cliente = Consola.leerDniCliente();
			controlador.borrarCliente(cliente);
			System.out.println("Cliente borrado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void buscarCliente() {
		Consola.mostrarCabecera("Buscar cliente");
		Cliente cliente = null;
		try {
			cliente = Consola.leerDniCliente();
			cliente = controlador.buscarCliente(cliente);
			if (cliente != null) {
				System.out.println("El cliente buscado es: " + cliente);
			} else {
				System.out.println("No existe ningún cliente con dicho DNI.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void listarClientes() {
		Consola.mostrarCabecera("Listar clientes");
		List<String> clientes = controlador.listarClientes();
		if (!clientes.isEmpty()) {
			for (String cliente : clientes) {
				System.out.println(cliente);
			}
		} else {
			System.out.println("No hay clientes que listar.");
		}
	}
	
}
