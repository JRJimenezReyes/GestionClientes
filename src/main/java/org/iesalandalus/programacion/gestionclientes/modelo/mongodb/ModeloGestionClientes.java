package org.iesalandalus.programacion.gestionclientes.modelo.mongodb;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.IModeloGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.mongodb.dao.Clientes;
import org.iesalandalus.programacion.gestionclientes.modelo.mongodb.utilidades.MongoDB;

public class ModeloGestionClientes implements IModeloGestionClientes {

	private Clientes clientes;
	
	public ModeloGestionClientes() {
		clientes = new Clientes();
	}
	
	@Override
	public void comenzar() {
		MongoDB.establecerConexion();
	}
	
	@Override
	public void terminar() {
		MongoDB.cerrarCliente();
	}
	
	@Override
	public void insertarCliente(Cliente cliente) throws OperationNotSupportedException {
		clientes.insertar(cliente);
	}
	
	@Override
	public void borrarCliente(Cliente cliente) throws OperationNotSupportedException {
		clientes.borrar(cliente);
	}
	
	@Override
	public Cliente buscarCliente(Cliente cliente) {
		return clientes.buscar(cliente);
	}
	
	@Override
	public List<String> representarClientes() {
		return clientes.representar();
	}
	
	@Override
	public List<Cliente> getClientes() {
		return clientes.getClientes();
	}
	
}
