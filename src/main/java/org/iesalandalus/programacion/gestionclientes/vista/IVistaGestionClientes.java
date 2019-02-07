package org.iesalandalus.programacion.gestionclientes.vista;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;

public interface IVistaGestionClientes {

	void setControlador(IControladorGestionClientes controlador);

	void comenzar();

	void salir();

	void insertarCliente();

	void borrarCliente();

	void buscarCliente();

	void listarClientes();

}