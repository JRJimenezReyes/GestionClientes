package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class DireccionPostalTest {

	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final String DIRECCION = "Finca Santa Isabel s/n";
	private static final String LOCALIDAD = "Almería";
	private static final String CODIGO_POSTAL = "04008";
	
	@Test
	public void constructorValidoTest() {
		try {
			DireccionPostal direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, CODIGO_POSTAL);
			assertEquals(DIRECCION, direccionPostal.getDireccion());
			assertEquals(LOCALIDAD, direccionPostal.getLocalidad());
			assertEquals(CODIGO_POSTAL, direccionPostal.getCodigoPostal());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorDireccionNoValidaTest() {
		DireccionPostal direccionPostal = null;
		try {
			direccionPostal = new DireccionPostal(null, LOCALIDAD, CODIGO_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("La dirección no puede ser nula.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal("", LOCALIDAD, CODIGO_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("La dirección no puede estar vacía.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal("  ", LOCALIDAD, CODIGO_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("La dirección no puede estar vacía.", e.getMessage());
		}
	}
	
	@Test
	public void constructorLocalidadNoValidaTest() {
		DireccionPostal direccionPostal = null;
		try {
			direccionPostal = new DireccionPostal(DIRECCION, null, CODIGO_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("La localidad no puede ser nula.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal(DIRECCION, "", CODIGO_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("La localidad no puede estar vacía.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal(DIRECCION, "   ", CODIGO_POSTAL);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("La localidad no puede estar vacía.", e.getMessage());
		}
	}
	
	@Test
	public void constructorCodigoPostalNoValidoTest() {
		DireccionPostal direccionPostal = null;
		try {
			direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("El código postal no puede ser nulo.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, "");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("El cógido postal no tiene un formato correcto.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, "  ");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("El cógido postal no tiene un formato correcto.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, "12");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("El cógido postal no tiene un formato correcto.", e.getMessage());
		}
		try {
			direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, "123456");
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertNull(direccionPostal);
			assertEquals("El cógido postal no tiene un formato correcto.", e.getMessage());
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		try {
			DireccionPostal direccionPostal1 = new DireccionPostal(DIRECCION, LOCALIDAD, CODIGO_POSTAL);
			DireccionPostal direccionPostal2 = new DireccionPostal(direccionPostal1);
			assertEquals(DIRECCION, direccionPostal2.getDireccion());
			assertEquals(LOCALIDAD, direccionPostal2.getLocalidad());
			assertEquals(CODIGO_POSTAL, direccionPostal2.getCodigoPostal());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		DireccionPostal direccionPostal = null;
		try {
			direccionPostal = new DireccionPostal(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se puede copiar una dirección postal nula.", e.getMessage());
			assertNull(direccionPostal);
		}
	}
	
	@Test
	public void equalTest() {
		DireccionPostal direccionPostal1 = new DireccionPostal(DIRECCION, LOCALIDAD, CODIGO_POSTAL);
		DireccionPostal direccionPostal2 = new DireccionPostal(direccionPostal1);
		DireccionPostal direccionPostal3 = new DireccionPostal("Otra direccion", "Otra localidad", "04008");
		assertNotEquals(direccionPostal1, null);
		assertNotEquals(direccionPostal1, "");
		assertEquals(direccionPostal1, direccionPostal1);
		assertEquals(direccionPostal1, direccionPostal2);
		assertNotEquals(direccionPostal1, direccionPostal3);
	}
	
	@Test
	public void hashCodeTest() {
		DireccionPostal direccionPostal1 = new DireccionPostal(DIRECCION, LOCALIDAD, CODIGO_POSTAL);
		DireccionPostal direccionPostal2 = new DireccionPostal(direccionPostal1);
		DireccionPostal direccionPostal3 = new DireccionPostal("Otra direccion", "Otra localidad", "04008");
		assertEquals(direccionPostal1.hashCode(), direccionPostal1.hashCode());
		assertEquals(direccionPostal1.hashCode(), direccionPostal2.hashCode());
		assertNotEquals(direccionPostal1.hashCode(), direccionPostal3.hashCode());
	}
	
	@Test
	public void toStringTest() {
		DireccionPostal direccionPostal = new DireccionPostal(DIRECCION, LOCALIDAD, CODIGO_POSTAL);
		assertEquals("[dirección=Finca Santa Isabel s/n, localidad=Almería, código postal=04008]", direccionPostal.toString());
	}

}
