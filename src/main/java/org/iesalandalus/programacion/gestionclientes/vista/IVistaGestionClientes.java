package org.iesalandalus.programacion.gestionclientes.vista;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;

public interface IVistaGestionClientes {

	void setControlador(IControladorGestionClientes controlador);

	void comenzar();

}