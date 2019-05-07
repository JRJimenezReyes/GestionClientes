package org.iesalandalus.programacion.gestionclientes.modelo.ficheros;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.IModeloGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.ficheros.dao.Clientes;

public class ModeloGestionClientes implements IModeloGestionClientes {

	private Clientes clientes;
	
	public ModeloGestionClientes() {
		clientes = new Clientes();
	}
	
	@Override
	public void comenzar() {
		clientes.leer();
	}
	
	@Override
	public void terminar() {
		clientes.escribir();
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
