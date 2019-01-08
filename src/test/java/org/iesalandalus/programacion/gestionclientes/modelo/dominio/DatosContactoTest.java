package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatosContactoTest {

	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final String TELEFONO = "950112233";
	private static final String CORREO = "prueba@iesalandalus.org";
	private static final DireccionPostal DIRECCION_POSTAL = new DireccionPostal("Finca Santa Isabel s/n", "Almería", "04008");

	@Test
	public void constructorValidoTest() {
		try {
			DatosContacto datosContacto = new DatosContacto(TELEFONO, CORREO, DIRECCION_POSTAL);
			assertEquals(TELEFONO, datosContacto.getTelefono());
			assertEquals(CORREO, datosContacto.getCorreo());
			assertEquals(DIRECCION_POSTAL, datosContacto.getDireccionPostal());
			assertFalse(DIRECCION_POSTAL == datosContacto.getDireccionPostal());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorTelefonoNoValidoTest() {
		DatosContacto datosContacto = null;
		try {
			datosContacto = new DatosContacto(null, CORREO, DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El teléfono no puede ser nulo.", e.getMessage());
		}
		try {
			datosContacto = new DatosContacto("95011223", CORREO, DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El formato del teléfono no es correcto.", e.getMessage());
		}
		try {
			datosContacto = new DatosContacto("9501122334", CORREO, DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El formato del teléfono no es correcto.", e.getMessage());
		}
	}
	
	@Test
	public void constructorCorreoNoValidoTest() {
		DatosContacto datosContacto = null;
		try {
			datosContacto = new DatosContacto(TELEFONO, null, DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El correo no puede ser nulo.", e.getMessage());
		}
		try {
			datosContacto = new DatosContacto(TELEFONO, "123@1", DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El formato del correo no es correcto.", e.getMessage());
		}
		try {
			datosContacto = new DatosContacto(TELEFONO, "123@11.1", DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El formato del correo no es correcto.", e.getMessage());
		}
		try {
			datosContacto = new DatosContacto(TELEFONO, "123@11.123456", DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El formato del correo no es correcto.", e.getMessage());
		}
		try {
			datosContacto = new DatosContacto(TELEFONO, "123@11.11.11", DIRECCION_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("El formato del correo no es correcto.", e.getMessage());
		}
	}
	
	@Test
	public void constructorDireccionPostalNoValidaTest() {
		DatosContacto datosContacto = null;
		try {
			datosContacto = new DatosContacto(TELEFONO, CORREO, null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(datosContacto);
			assertEquals("La dirección postal no puede ser nula.", e.getMessage());
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		try {
			DatosContacto datosContacto1 = new DatosContacto(TELEFONO, CORREO, DIRECCION_POSTAL);
			DatosContacto datosContacto2 = new DatosContacto(datosContacto1);
			assertEquals(TELEFONO, datosContacto2.getTelefono());
			assertEquals(CORREO, datosContacto2.getCorreo());
			assertEquals(DIRECCION_POSTAL, datosContacto2.getDireccionPostal());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		DatosContacto datosContacto = null;
		try {
			datosContacto = new DatosContacto(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se pueden copiar unos datos de contacto nulos.", e.getMessage());
			assertNull(datosContacto);
		}
	}
	
	@Test
	public void equalTest() {
		DatosContacto datosContacto1 = new DatosContacto(TELEFONO, CORREO, DIRECCION_POSTAL);
		DatosContacto datosContacto2 = new DatosContacto(datosContacto1);
		DatosContacto datosContacto3 = new DatosContacto("950223344", CORREO, DIRECCION_POSTAL);
		assertNotEquals(datosContacto1, null);
		assertNotEquals(datosContacto1, "");
		assertEquals(datosContacto1, datosContacto1);
		assertEquals(datosContacto1, datosContacto2);
		assertNotEquals(datosContacto1, datosContacto3);
	}
	
	@Test
	public void hashCodeTest() {
		DatosContacto datosContacto1 = new DatosContacto(TELEFONO, CORREO, DIRECCION_POSTAL);
		DatosContacto datosContacto2 = new DatosContacto(datosContacto1);
		DatosContacto datosContacto3 = new DatosContacto("950223344", CORREO, DIRECCION_POSTAL);
		assertEquals(datosContacto1.hashCode(), datosContacto1.hashCode());
		assertEquals(datosContacto1.hashCode(), datosContacto2.hashCode());
		assertNotEquals(datosContacto1.hashCode(), datosContacto3.hashCode());
	}
	
	@Test
	public void toStringTest() {
		DatosContacto datosContacto = new DatosContacto(TELEFONO, CORREO, DIRECCION_POSTAL);
		assertEquals("[teléfono=950112233, correo=prueba@iesalandalus.org, dirección postal="
				+ "[dirección=Finca Santa Isabel s/n, localidad=Almería, código postal=04008]]", datosContacto.toString());
	}
}
