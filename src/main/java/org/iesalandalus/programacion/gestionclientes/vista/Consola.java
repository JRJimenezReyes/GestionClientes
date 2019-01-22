package org.iesalandalus.programacion.gestionclientes.vista;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContacto;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonales;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostal;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	
	private static final DireccionPostal DIRECCION_POSTAL_FICTICIA = new DireccionPostal("C/ 1", "1", "11111");
	private static final DatosContacto DATOS_CONTACTO_FICTICIOS = new DatosContacto("950111111", "1@1.es", DIRECCION_POSTAL_FICTICIA);
		
	private Consola() {
		//Evito que se cree el constructor por defecto
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("Gestión de clientes");
		for (Opcion opcion: Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Cliente leerCliente() {
		System.out.print("Introduce el nombre: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce los apellidos: ");
		String apellidos = Entrada.cadena();
		System.out.print("Introduce el dni: ");
		String dni = Entrada.cadena();
		System.out.print("Introduce el teléfono: ");
		String telefono = Entrada.cadena();
		System.out.print("Introduce la fecha de nacimiento: ");
		String fechaNacimiento = Entrada.cadena();
		System.out.print("Introduce el correo: ");
		String correo = Entrada.cadena();
		System.out.print("Introduce la dirección: ");
		String direccion = Entrada.cadena();
		System.out.print("Introduce la localidad: ");
		String localidad = Entrada.cadena();
		System.out.print("Introduce el código postal: ");
		String codigoPostal = Entrada.cadena();
		DatosPersonales datosPersonales = new DatosPersonales(nombre, apellidos, dni, fechaNacimiento);
		DireccionPostal direccionPostal = new DireccionPostal(direccion, localidad, codigoPostal);
		DatosContacto datosContacto = new DatosContacto(telefono, correo, direccionPostal);
		return new Cliente(datosPersonales, datosContacto);
	}
	
	public static Cliente leerDniCliente() {
		String dni;
		do {
			System.out.print("Introduce el dni: ");
			dni = Entrada.cadena();
		} while (dni.trim().equals(""));
		DatosPersonales datosPersonalesDni = new DatosPersonales("Cliente", "1", dni, "01/01/1999");
		return new Cliente(datosPersonalesDni, DATOS_CONTACTO_FICTICIOS);
	}
	
}
