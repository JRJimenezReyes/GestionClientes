package org.iesalandalus.programacion.gestionclientes.modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;


public class Clientes {
		
	private List<Cliente> coleccionClientes;
	
	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}
	
	public Clientes(Clientes clientes) {
		setClientes(clientes);
	}
	
	private void setClientes(Clientes clientes) {
		if (clientes == null) {
			throw new IllegalArgumentException("No se pueden copiar clientes nulos.");
		}
		coleccionClientes = copiaProfundaClientes(clientes.coleccionClientes);
	}
	
	private List<Cliente> copiaProfundaClientes(List<Cliente> clientes) {
		List<Cliente> otrosClientes = new ArrayList<>();
		for (Cliente cliente: clientes) {
			otrosClientes.add(new Cliente(cliente));
		}
		return otrosClientes;
	}
	
	public List<Cliente> getClientes() {
		return copiaProfundaClientes(coleccionClientes);
	}
	
	public int getNumClientes() {
		return coleccionClientes.size();
	}
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede insertar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("El cliente ya existe.");
		} else {
			coleccionClientes.add(new Cliente(cliente));
		}
	}
	
	public Cliente buscar(Cliente cliente) { 
		int indice = coleccionClientes.indexOf(cliente);
		if (indice != -1) {
			return new Cliente(coleccionClientes.get(indice));
		} else {
			return null;
		}
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede borrar un cliente nulo.");
		}
		if (!coleccionClientes.remove(cliente)) {
			throw new OperationNotSupportedException("El cliente a borrar no existe.");
		}
	}
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Cliente cliente : coleccionClientes) {
			representacion.add(cliente.toString());
		}
		return representacion;
	}
}
