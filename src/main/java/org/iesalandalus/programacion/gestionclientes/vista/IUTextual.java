package org.iesalandalus.programacion.gestionclientes.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.ModeloGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;


public class IUTextual {
	
	private static final String ERROR = "ERROR: ";
	
	private ModeloGestionClientes modelo;

	public IUTextual() {
		modelo = new ModeloGestionClientes();
		Opcion.setVista(this);
	}

	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		System.out.println("Hasta luego Lucas!!!");
	}
	
	public void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			Cliente cliente = Consola.leerCliente();
			modelo.insertarCliente(cliente);
			System.out.println("Cliente insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			Cliente cliente = Consola.leerDniCliente();
			modelo.borrarCliente(cliente);
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
			cliente = modelo.buscarCliente(cliente);
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
		String[] clientes = modelo.representarClientes();
		if (clientes.length > 0) {
			for (String cliente : clientes) {
				System.out.println(cliente);
			}
		} else {
			System.out.println("No hay clientes que listar.");
		}
	}
	
}
