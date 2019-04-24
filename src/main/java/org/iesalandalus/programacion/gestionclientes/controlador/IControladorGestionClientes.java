package org.iesalandalus.programacion.gestionclientes.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;

public interface IControladorGestionClientes {

	void comenzar();

	void salir();

	void insertarCliente(Cliente cliente) throws OperationNotSupportedException;

	void borrarCliente(Cliente cliente) throws OperationNotSupportedException;

	Cliente buscarCliente(Cliente cliente);

	List<String> listarClientes();
	
	List<Cliente> getClientes();

}