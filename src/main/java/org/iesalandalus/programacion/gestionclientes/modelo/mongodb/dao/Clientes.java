package org.iesalandalus.programacion.gestionclientes.modelo.mongodb.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Clientes {
	
	private static final String COLECCION = "clientes";
		
	private MongoCollection<Document> coleccionClientes;
	
	public Clientes() {
		coleccionClientes = MongoDB.getBD().getCollection(COLECCION);
	}
	
	public List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<>();
		for (Document documentoCliente : coleccionClientes.find()) {
			clientes.add(MongoDB.obtenerClienteDesdeDocumento(documentoCliente));
		}
		return clientes;
	}
	
	public int getNumClientes() {
		return (int)coleccionClientes.countDocuments();
	}
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede insertar un cliente nulo.");
		}
		if (buscar(cliente) != null) {
			throw new OperationNotSupportedException("El cliente ya existe.");
		} else {
			coleccionClientes.insertOne(MongoDB.obtenerDocumentoDesdeCliente(cliente));
		}
	}
	
	public Cliente buscar(Cliente cliente) { 
		Document documentoCliente = coleccionClientes.find().filter(eq(MongoDB.DATOS_PERSONALES_DNI, cliente.getDatosPersonales().getDni())).first();
		return MongoDB.obtenerClienteDesdeDocumento(documentoCliente);
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede borrar un cliente nulo.");
		}
		if (buscar(cliente) != null) {
			coleccionClientes.deleteOne(eq(MongoDB.DATOS_PERSONALES_DNI, cliente.getDatosPersonales().getDni()));
		} else {
			throw new OperationNotSupportedException("El cliente a borrar no existe.");
		} 
	}
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Cliente cliente : getClientes()) {
			representacion.add(cliente.toString());
		}
		return representacion;
	}
	
}
