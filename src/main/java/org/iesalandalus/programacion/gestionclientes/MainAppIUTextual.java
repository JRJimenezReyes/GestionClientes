package org.iesalandalus.programacion.gestionclientes;

import org.iesalandalus.programacion.gestionclientes.controlador.ControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.IModeloGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.ModeloGestionClientes;
import org.iesalandalus.programacion.gestionclientes.vista.IVistaGestionClientes;
import org.iesalandalus.programacion.gestionclientes.vista.iutextual.VistaGestionClientes;

public class MainAppIUTextual {

	public static void main(String[] args) {
		IModeloGestionClientes modelo = new ModeloGestionClientes();
		IVistaGestionClientes vista = new VistaGestionClientes();
		IControladorGestionClientes controlador = new ControladorGestionClientes(modelo, vista);
		controlador.comenzar();
	}

}
