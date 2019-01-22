package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ClienteTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final DatosPersonales DATOS_PERSONALES = 
			new DatosPersonales("José Ramón", "Jiménez Reyes", "11223344X", LocalDate.of(1973, 6, 7));
	private static final DatosPersonales OTROS_DATOS_PERSONALES =
			new DatosPersonales("Paco", "Jones", "22334455X", LocalDate.of(1973, 6, 7));
	private static final DatosContacto DATOS_CONTACTO =
			new DatosContacto("950112233", "prueba@iesalandalus.org", 
					new DireccionPostal("Finca Santa Isabel s/n", "Almería", "04008"));

	@Test
	public void constructorValidoTest() {
		try {
			Cliente cliente = new Cliente(DATOS_PERSONALES, DATOS_CONTACTO);
			assertEquals(DATOS_PERSONALES, cliente.getDatosPersonales());
			assertEquals(DATOS_CONTACTO, cliente.getDatosContacto());
			assertFalse(DATOS_PERSONALES == cliente.getDatosPersonales());
			assertFalse(DATOS_CONTACTO == cliente.getDatosContacto());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructoDatosPersonalesNoValidoTest() {
		Cliente cliente = null;
		try {
			cliente = new Cliente(null, DATOS_CONTACTO);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(cliente);
			assertEquals("Los datos personales no pueden ser nulos.", e.getMessage());
		}
	}
	
	@Test
	public void constructoDatosContactoNoValidoTest() {
		Cliente cliente = null;
		try {
			cliente = new Cliente(DATOS_PERSONALES, null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(cliente);
			assertEquals("Los datos de contacto no pueden ser nulos.", e.getMessage());
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		try {
			Cliente cliente1 = new Cliente(DATOS_PERSONALES, DATOS_CONTACTO);
			Cliente cliente2 = new Cliente(cliente1);
			assertEquals(DATOS_PERSONALES, cliente2.getDatosPersonales());
			assertEquals(DATOS_CONTACTO, cliente2.getDatosContacto());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Cliente cliente = null;
		try {
			cliente = new Cliente(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se puede copiar un cliente nulo.", e.getMessage());
			assertNull(cliente);
		}
	}
	
	@Test
	public void equalTest() {
		Cliente cliente1 = new Cliente(DATOS_PERSONALES, DATOS_CONTACTO);
		Cliente cliente2 = new Cliente(cliente1);
		Cliente cliente3 = new Cliente(OTROS_DATOS_PERSONALES, DATOS_CONTACTO);
		assertNotEquals(cliente1, null);
		assertNotEquals(cliente1, "");
		assertEquals(cliente1, cliente1);
		assertEquals(cliente1, cliente2);
		assertNotEquals(cliente1, cliente3);
	}
	
	@Test
	public void hashCodeTest() {
		Cliente cliente1 = new Cliente(DATOS_PERSONALES, DATOS_CONTACTO);
		Cliente cliente2 = new Cliente(cliente1);
		Cliente cliente3 = new Cliente(OTROS_DATOS_PERSONALES, DATOS_CONTACTO);
		assertEquals(cliente1.hashCode(), cliente1.hashCode());
		assertEquals(cliente1.hashCode(), cliente2.hashCode());
		assertNotEquals(cliente1.hashCode(), cliente3.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String clienteStr = "Cliente [datos personales=[nombre=José Ramón, apellidos=Jiménez Reyes, dni=11223344X, fecha nacimiento=07/06/1973], "
				+ "datos de contacto=[teléfono=950112233, correo=prueba@iesalandalus.org, dirección postal="
				+ "[dirección=Finca Santa Isabel s/n, localidad=Almería, código postal=04008]]]";
		Cliente cliente = new Cliente(DATOS_PERSONALES, DATOS_CONTACTO);
		assertEquals(clienteStr, cliente.toString());
	}

}
