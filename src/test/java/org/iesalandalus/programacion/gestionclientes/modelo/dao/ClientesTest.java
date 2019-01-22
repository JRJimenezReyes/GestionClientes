package org.iesalandalus.programacion.gestionclientes.modelo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.gestionclientes.modelo.dao.Clientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContacto;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonales;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostal;
import org.junit.Test;

public class ClientesTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final DatosPersonales DATOS_PERSONALES1 = new DatosPersonales("Cliente", "1", "11111111A", LocalDate.of(2010, 1, 1));
	private static final DatosPersonales DATOS_PERSONALES2 = new DatosPersonales("Cliente", "2", "22222222B", LocalDate.of(2011,  2,  2));
	private static final DatosPersonales DATOS_PERSONALES3 = new DatosPersonales("Cliente", "3", "33333333C", LocalDate.of(2012, 3, 3));
	private static final DireccionPostal DIRECCION_POSTAL = new DireccionPostal("Finca Santa Isabel s/n", "Almería", "04008");
	private static final DatosContacto DATOS_CONTACTO1 = new DatosContacto("950111111", "a@a.es", DIRECCION_POSTAL);
	private static final DatosContacto DATOS_CONTACTO2 = new DatosContacto("950222222", "b@b.es", DIRECCION_POSTAL);
	private static final DatosContacto DATOS_CONTACTO3 = new DatosContacto("950333333", "c@c.es", DIRECCION_POSTAL);

	private static final Cliente CLIENTE1 = new Cliente(DATOS_PERSONALES1, DATOS_CONTACTO1);
	private static final Cliente CLIENTE2 = new Cliente(DATOS_PERSONALES2, DATOS_CONTACTO2);
	private static final Cliente CLIENTE3 = new Cliente(DATOS_PERSONALES3, DATOS_CONTACTO3);
	
	@Test
	public void constructorDefectoTest() {
		Clientes clientes = new Clientes();
		assertEquals(0, clientes.getNumClientes());
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Clientes clientes1 = new Clientes();
		Clientes clientes2;
		clientes2 = new Clientes(clientes1);
		assertEquals(0, clientes2.getNumClientes());
		assertNotEquals(clientes1.getClientes(), clientes2.getNumClientes());
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Clientes clientes1 = null;
		Clientes clientes2 = null;
		try {
			clientes2 = new Clientes(clientes1);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se pueden copiar clientes nulos.", e.getMessage());
			assertNull(clientes2);
		}
	}
	
	@Test
	public void insertarUnoValidoTest() {
		Clientes clientes = new Clientes();
		try {
			clientes.insertar(CLIENTE1);
			assertEquals(1, clientes.getNumClientes());
			assertEquals(CLIENTE1, clientes.buscar(CLIENTE1));
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void insertarNuloTest() {
		Clientes clientes = new Clientes();
		try {
			clientes.insertar(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se puede insertar un cliente nulo.", e.getMessage());
			assertEquals(0, clientes.getNumClientes());
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void insertarRepetidoTest() {
		Clientes clientes = new Clientes();
		try {
			clientes.insertar(CLIENTE1);
			clientes.insertar(CLIENTE1);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("El cliente ya existe.", e.getMessage());
			assertEquals(1, clientes.getNumClientes());
		}
	}
	
	@Test
	public void insertarTresValidoTest() {
		Clientes clientes = new Clientes();
		try {
			clientes.insertar(CLIENTE1);
			assertEquals(1, clientes.getNumClientes());
			assertEquals(CLIENTE1, clientes.buscar(CLIENTE1));
			clientes.insertar(CLIENTE2);
			assertEquals(2, clientes.getNumClientes());
			assertEquals(CLIENTE2, clientes.buscar(CLIENTE2));
			clientes.insertar(CLIENTE3);
			assertEquals(3, clientes.getNumClientes());
			assertEquals(CLIENTE3, clientes.buscar(CLIENTE3));
			Cliente[] arrayProfesores = clientes.getClientes();
			assertEquals(CLIENTE1, arrayProfesores[0]);
			assertEquals(CLIENTE2, arrayProfesores[1]);
			assertEquals(CLIENTE3, arrayProfesores[2]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void getClientesTest() {
		Clientes clientes = new Clientes();
		try {
			clientes.insertar(CLIENTE1);
			Cliente[] arrayClientes = clientes.getClientes();
			assertFalse(arrayClientes == clientes.getClientes());
			assertFalse(arrayClientes[0] == clientes.getClientes()[0]);
			assertEquals(arrayClientes[0], clientes.getClientes()[0]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	private Clientes insertarTres() {
		Clientes clientes = new Clientes();
		try {
			clientes.insertar(CLIENTE1);
			clientes.insertar(CLIENTE2);
			clientes.insertar(CLIENTE3);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
		return clientes;
	}
	
	@Test
	public void borrarPrincipioValidoTest() {
		Clientes clientes = insertarTres();
		try {
			clientes.borrar(CLIENTE1);
			assertEquals(2, clientes.getNumClientes());
			assertNull(clientes.buscar(CLIENTE1));
			Cliente[] arrayClientes = clientes.getClientes();
			assertEquals(CLIENTE2, arrayClientes[0]);
			assertEquals(CLIENTE3, arrayClientes[1]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarMedioValidoTest() {
		Clientes clientes = insertarTres();
		try {
			clientes.borrar(CLIENTE2);
			assertEquals(2, clientes.getNumClientes());
			assertNull(clientes.buscar(CLIENTE2));
			Cliente[] arrayClientes = clientes.getClientes();
			assertEquals(CLIENTE1, arrayClientes[0]);
			assertEquals(CLIENTE3, arrayClientes[1]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarFinalValidoTest() {
		Clientes clientes = insertarTres();
		try {
			clientes.borrar(CLIENTE3);
			assertEquals(2, clientes.getNumClientes());
			assertNull(clientes.buscar(CLIENTE3));
			Cliente[] arrayClientes = clientes.getClientes();
			assertEquals(CLIENTE1, arrayClientes[0]);
			assertEquals(CLIENTE2, arrayClientes[1]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarNuloTest() {
		Clientes clientes = insertarTres();
		try {
			clientes.borrar(null);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se puede borrar un cliente nulo.", e.getMessage());
			assertEquals(3, clientes.getNumClientes());
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void borrarNoValidoTest() {
		Clientes clientes = insertarTres();
		try {
			DatosPersonales datosPersonales = new DatosPersonales("Cliente", "4", "44444444D", LocalDate.of(2014, 4, 4));
			Cliente cliente = new Cliente(datosPersonales, DATOS_CONTACTO1);
			clientes.borrar(cliente);
			fail(ERROR_EXCEPCION);
		} catch (OperationNotSupportedException e) {
			assertEquals("El cliente a borrar no existe.", e.getMessage());
			assertEquals(3, clientes.getNumClientes());
		}
	}
	
	@Test
	public void borrarInsertarTest() {
		Clientes clientes = insertarTres();
		try {
			clientes.borrar(CLIENTE1);
			assertEquals(2, clientes.getNumClientes());
			assertNull(clientes.buscar(CLIENTE1));
			clientes.insertar(CLIENTE1);
			assertEquals(3, clientes.getNumClientes());
			assertEquals(CLIENTE1, clientes.buscar(CLIENTE1));
			Cliente[] arrayClientes = clientes.getClientes();
			assertEquals(CLIENTE2, arrayClientes[0]);
			assertEquals(CLIENTE3, arrayClientes[1]);
			assertEquals(CLIENTE1, arrayClientes[2]);
		} catch (OperationNotSupportedException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void buscarNuloTest() {
		Clientes clientes = insertarTres();
		assertNull(clientes.buscar(null));
	}
	
	@Test
	public void representarTest() {
		Clientes clientes = insertarTres();
		String[] representacion = clientes.representar();
		assertEquals(CLIENTE1.toString(), representacion[0]);
		assertEquals(CLIENTE2.toString(), representacion[1]);
		assertEquals(CLIENTE3.toString(), representacion[2]);
	}

}
