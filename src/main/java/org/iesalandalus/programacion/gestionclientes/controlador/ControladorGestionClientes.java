package org.iesalandalus.programacion.gestionclientes.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.IModeloGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.vista.IVistaGestionClientes;


public class ControladorGestionClientes implements IControladorGestionClientes {
	
	private IVistaGestionClientes vista;
	private IModeloGestionClientes modelo;
	
	public ControladorGestionClientes(IModeloGestionClientes modelo, IVistaGestionClientes vista) {
		this.modelo = modelo;
		this.vista = vista;
		vista.setControlador(this);
	}

	@Override
	public void comenzar() {
		modelo.comenzar();
		vista.comenzar();
	}
	
	@Override
	public void salir() {
		modelo.terminar();
		System.out.println("Hasta luego Lucas!!!");
	}
	
	@Override
	public void insertarCliente(Cliente cliente) throws OperationNotSupportedException {
		modelo.insertarCliente(cliente);
	}
	
	@Override
	public void borrarCliente(Cliente cliente) throws OperationNotSupportedException {
		modelo.borrarCliente(cliente);
	}
	
	@Override
	public Cliente buscarCliente(Cliente cliente) {
		return modelo.buscarCliente(cliente);
	}
	
	@Override
	public List<String> listarClientes() {
		return modelo.representarClientes();
	}
	
	@Override
	public List<Cliente> getClientes() {
		return modelo.getClientes();
	}
	
}
