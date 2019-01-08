package org.iesalandalus.programacion.gestionclientes.modelo;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContactoTest;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonalesTest;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostalTest;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.ClienteTest;
import org.iesalandalus.programacion.gestionclientes.modelo.dao.ClientesTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatosContactoTest.class, DatosPersonalesTest.class, DireccionPostalTest.class, ClienteTest.class, ClientesTest.class })
public class AllTests {

}
