package org.iesalandalus.programacion.gestionclientes.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;

public class Clientes {
	
	private static final String NOMBRE_FICHERO_CLIENTES = "ficheros/clientes.dat";
		
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
	
	public void leer() {
		File ficheroAulas = new File(NOMBRE_FICHERO_CLIENTES);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
			Cliente cliente = null;
			do {
				cliente = (Cliente) entrada.readObject();
				insertar(cliente);
			} while (cliente != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo abrir el fihero de clientes.");
		} catch (EOFException e) {
			System.out.println("Fichero clientes leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void escribir() {
		File ficheroAulas = new File(NOMBRE_FICHERO_CLIENTES);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))){
			for (Cliente cliente : coleccionClientes)
				salida.writeObject(cliente);
			System.out.println("Fichero clientes escrito satisfactoriamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de clientes");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
}
