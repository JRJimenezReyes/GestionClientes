package org.iesalandalus.programacion.gestionclientes.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;


public class Clientes {
	
	private static final int MAX_CLIENTES = 20;
	
	private Cliente[] coleccionClientes;
	private int numClientes;
	
	public Clientes() {
		coleccionClientes = new Cliente[MAX_CLIENTES];
		numClientes = 0;
	}
	
	public Clientes(Clientes clientes) {
		setClientes(clientes);
	}
	
	private void setClientes(Clientes clientes) {
		if (clientes == null) {
			throw new IllegalArgumentException("No se pueden copiar clientes nulos.");
		}
		coleccionClientes = copiaProfundaClientes(clientes.coleccionClientes);
		numClientes = clientes.numClientes;
	}
	
	private Cliente[] copiaProfundaClientes(Cliente[] clientes) {
		Cliente[] otrosClientes = new Cliente[clientes.length];
		for (int i = 0; i < clientes.length && clientes[i] != null; i++) {
			otrosClientes[i] = new Cliente(clientes[i]);
		}
		return otrosClientes;
	}
	
	public Cliente[] getClientes() {
		return copiaProfundaClientes(coleccionClientes);
	}
	
	public int getNumClientes() {
		return numClientes;
	}
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede insertar un cliente nulo.");
		}
		int indice = buscarIndiceCliente(cliente);
		if (!indiceNoSuperaTamano(indice)) {
			coleccionClientes[indice] = cliente;
			numClientes++;
		} else {
			if (indiceNoSuperaCapacidad(indice)) {
				throw new OperationNotSupportedException("El cliente ya existe.");
			} else {
				throw new OperationNotSupportedException("No se aceptan más clientes.");
			}		}
	}
	
	private int buscarIndiceCliente(Cliente cliente) {
		int indice = 0;
		boolean clienteEncontrado = false;
		while (indiceNoSuperaTamano(indice) && !clienteEncontrado) {
			if (coleccionClientes[indice].equals(cliente)) {
				clienteEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		return indice < numClientes;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) {
		return indice < MAX_CLIENTES;
	}
	
	public Cliente buscar(Cliente cliente) {
		int indice = 0;
		indice = buscarIndiceCliente(cliente);
		if (indiceNoSuperaTamano(indice)) {
			return coleccionClientes[indice];
		} else {
			return null;
		}
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede borrar un cliente nulo.");
		}
		int indice = buscarIndiceCliente(cliente);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
		else {
			throw new OperationNotSupportedException("El cliente a borrar no existe.");
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < numClientes - 1; i++) {
			coleccionClientes[i] = coleccionClientes[i+1];
		}
		coleccionClientes[numClientes] = null;
		numClientes--;
	}
	
	public String[] representar() {
		String[] representacion = new String[numClientes];
		for (int i = 0; indiceNoSuperaTamano(i); i++) {
			representacion[i] = coleccionClientes[i].toString();
		}
		return representacion;
	}
}
