package org.iesalandalus.programacion.gestionclientes;

import org.iesalandalus.programacion.gestionclientes.vista.IUTextual;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de clientes");
		IUTextual vista = new IUTextual();
		vista.comenzar();
	}

}
