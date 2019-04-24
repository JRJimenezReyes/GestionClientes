package org.iesalandalus.programacion.gestionclientes.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;

public interface IModeloGestionClientes {

	void insertarCliente(Cliente cliente) throws OperationNotSupportedException;

	void borrarCliente(Cliente cliente) throws OperationNotSupportedException;

	Cliente buscarCliente(Cliente cliente);

	List<String> representarClientes();
	
	List<Cliente> getClientes();
	
	void leerClientes();
	
	void escribirClientes();

}