package org.iesalandalus.programacion.gestionclientes.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dao.Clientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;

public class ModeloGestionClientes {

	private Clientes clientes;
	
	public ModeloGestionClientes() {
		clientes = new Clientes();
	}
	
	public void insertarCliente(Cliente cliente) throws OperationNotSupportedException {
		clientes.insertar(cliente);
	}
	
	public void borrarCliente(Cliente cliente) throws OperationNotSupportedException {
		clientes.borrar(cliente);
	}
	
	public Cliente buscarCliente(Cliente cliente) {
		return clientes.buscar(cliente);
	}
	
	public List<String> representarClientes() {
		return clientes.representar();
	}
}
