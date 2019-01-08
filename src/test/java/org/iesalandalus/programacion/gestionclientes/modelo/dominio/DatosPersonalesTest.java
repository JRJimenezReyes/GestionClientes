package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatosPersonalesTest {

	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final String NOMBRE = "José Ramón";
	private static final String APELLIDOS = "Jiménez Reyes";
	private static final String DNI = "11223344X";
	
	@Test
	public void constructorValidoTest() {
		try {
			DatosPersonales datosPersonales = new DatosPersonales(NOMBRE, APELLIDOS, DNI);
			assertEquals(NOMBRE, datosPersonales.getNombre());
			assertEquals(APELLIDOS, datosPersonales.getApellidos());
			assertEquals(DNI, datosPersonales.getDni());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorNombreNoValidoTest() {
		DatosPersonales datosPersonales = null;
		try {
			datosPersonales = new DatosPersonales(null, APELLIDOS, DNI);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("El nombre no puede ser nulo.", e.getMessage());
		}
		try {
			datosPersonales = new DatosPersonales("", APELLIDOS, DNI);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("El nombre no puede estar vacío.", e.getMessage());
		}
		try {
			datosPersonales = new DatosPersonales("   ", APELLIDOS, DNI);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("El nombre no puede estar vacío.", e.getMessage());
		}
	}
	
	@Test
	public void constructorApellidosNoValidoTest() {
		DatosPersonales datosPersonales = null;
		try {
			datosPersonales = new DatosPersonales(NOMBRE, null, DNI);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("Los apellidos no pueden ser nulos.", e.getMessage());
		}
		try {
			datosPersonales = new DatosPersonales(NOMBRE, "", DNI);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("Los apellidos no pueden estar vacíos.", e.getMessage());
		}
		try {
			datosPersonales = new DatosPersonales(NOMBRE, "   ", DNI);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("Los apellidos no pueden estar vacíos.", e.getMessage());
		}
	}
	
	@Test
	public void constructorDniNoValidoTest() {
		DatosPersonales datosPersonales = null;
		try {
			datosPersonales = new DatosPersonales(NOMBRE, APELLIDOS, null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("El dni no puede ser nulo.", e.getMessage());
		}
		try {
			datosPersonales = new DatosPersonales(NOMBRE, APELLIDOS, "11223344");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("El formato del dni no es correcto.", e.getMessage());
		}
		try {
			datosPersonales = new DatosPersonales(NOMBRE, APELLIDOS, "11223344xx");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosPersonales);
			assertEquals("El formato del dni no es correcto.", e.getMessage());
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		try {
			DatosPersonales datosPersonales1 = new DatosPersonales(NOMBRE, APELLIDOS, DNI);
			DatosPersonales datosPersonales2 = new DatosPersonales(datosPersonales1);
			assertEquals(NOMBRE, datosPersonales2.getNombre());
			assertEquals(APELLIDOS, datosPersonales2.getApellidos());
			assertEquals(DNI, datosPersonales2.getDni());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		DatosPersonales datosPersonales = null;
		try {
			datosPersonales = new DatosPersonales(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se pueden copiar unos datos personales nulos.", e.getMessage());
			assertNull(datosPersonales);
		}
	}
	
	@Test
	public void equalTest() {
		DatosPersonales datosPersonales1 = new DatosPersonales(NOMBRE, APELLIDOS, DNI);
		DatosPersonales datosPersonales2 = new DatosPersonales(datosPersonales1);
		DatosPersonales datosPersonales3 = new DatosPersonales(NOMBRE, APELLIDOS, "22334455X");
		assertNotEquals(datosPersonales1, null);
		assertNotEquals(datosPersonales1, "");
		assertEquals(datosPersonales1, datosPersonales1);
		assertEquals(datosPersonales1, datosPersonales2);
		assertNotEquals(datosPersonales1, datosPersonales3);
	}
	
	@Test
	public void hashCodeTest() {
		DatosPersonales datosPersonales1 = new DatosPersonales(NOMBRE, APELLIDOS, DNI);
		DatosPersonales datosPersonales2 = new DatosPersonales(datosPersonales1);
		DatosPersonales datosPersonales3 = new DatosPersonales(NOMBRE, APELLIDOS, "22334455X");
		assertEquals(datosPersonales1.hashCode(), datosPersonales1.hashCode());
		assertEquals(datosPersonales1.hashCode(), datosPersonales2.hashCode());
		assertNotEquals(datosPersonales1.hashCode(), datosPersonales3.hashCode());
	}
	
	@Test
	public void toStringTest() {
		DatosPersonales datosPersonales = new DatosPersonales(NOMBRE, APELLIDOS, DNI);
		assertEquals("[nombre=José Ramón, apellidos=Jiménez Reyes, dni=11223344X]", datosPersonales.toString());
	}

}
