package org.iesalandalus.programacion.gestionclientes.modelo.mongodb.utilidades;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.bson.Document;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContacto;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonales;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostal;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	
	private static final String SERVIDOR = "35.180.192.242";
	private static final int PUERTO = 27017;
	private static final String BD = "gestionclientes";
	private static final String USUARIO = "gestion-clientes";
	private static final String CONTRASENA = "clientes-2019";
	
	public static final String DATOS_PERSONALES = "datosPersonales";
	public static final String DATOS_CONTACTO = "datosContacto";
	public static final String DIRECCION_POSTAL = "direccionPostal";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDOS = "apellidos";
	public static final String DNI = "dni";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String TELEFONO = "telefono";
	public static final String CORREO = "correo";
	public static final String DIRECCION = "direccion";
	public static final String LOCALIDAD = "localidad";
	public static final String CODIGO_POSTAL = "codigoPostal";
	public static final String DATOS_PERSONALES_DNI = DATOS_PERSONALES + "." + DNI;
	public static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static MongoClient cliente = null;
	
	private MongoDB() {
		// Evitamos que se cree el constructor por defecto
	}
	
	public static MongoClient establecerConexion() {
		if (cliente == null) {
			MongoCredential credenciales = MongoCredential.createScramSha1Credential(USUARIO, BD, CONTRASENA.toCharArray());
			cliente = MongoClients.create(
			        MongoClientSettings.builder()
	                .applyToClusterSettings(builder -> 
	                        builder.hosts(Arrays.asList(new ServerAddress(SERVIDOR, PUERTO))))
	                .credential(credenciales)
	                .build());
			System.out.println("Conexión a MongoDB realizada correctamente.");
		}
		return cliente;
	}
	
	public static MongoClient getCliente() {
		return cliente;
	}
	
	public static MongoDatabase getBD() {
		if (cliente == null) {
			establecerConexion();
		}
		return cliente.getDatabase(BD);
	}
	
	public static void cerrarCliente() {
		if (cliente != null) {
			cliente.close();
			System.out.println("Conexión a MongoDB cerrada.");
		}
	}
	
	public static Document obtenerDocumentoDesdeCliente(Cliente cliente) {
		if (cliente == null) {
			return null;
		}
		DatosPersonales datosPersonales = cliente.getDatosPersonales();
		Document dDatosPersonales = new Document()
				.append(NOMBRE, datosPersonales.getNombre())
				.append(APELLIDOS, datosPersonales.getApellidos())
				.append(DNI, datosPersonales.getDni())
				.append(FECHA_NACIMIENTO, datosPersonales.getFechaNacimiento().format(FORMATO_DIA));
		DatosContacto datosContacto = cliente.getDatosContacto();
		DireccionPostal direccionPostal = datosContacto.getDireccionPostal();
		Document dDireccionPostal = new Document()
				.append(DIRECCION, direccionPostal.getDireccion())
				.append(LOCALIDAD, direccionPostal.getLocalidad())
				.append(CODIGO_POSTAL, direccionPostal.getCodigoPostal());
		Document dDatosContacto = new Document()
				.append(CORREO, datosContacto.getCorreo())
				.append(TELEFONO, datosContacto.getTelefono())
				.append(DIRECCION_POSTAL, dDireccionPostal);
		return new Document()
				.append(DATOS_CONTACTO, dDatosContacto)
				.append(DATOS_PERSONALES, dDatosPersonales);
	}
	
	public static Cliente obtenerClienteDesdeDocumento(Document documentoCliente) {
		if (documentoCliente == null) {
			return null;
		}	
		Document dDatosPersonales = (Document) documentoCliente.get(DATOS_PERSONALES);
		String nombre = dDatosPersonales.getString(NOMBRE);
		String apellidos = dDatosPersonales.getString(APELLIDOS);
		String dni = dDatosPersonales.getString(DNI);
		String fechaNacimiento = dDatosPersonales.getString(FECHA_NACIMIENTO);
		DatosPersonales datosPersonales = new DatosPersonales(nombre, apellidos, dni, fechaNacimiento);
		Document dDatosContacto = (Document) documentoCliente.get(DATOS_CONTACTO);
		String correo = dDatosContacto.getString(CORREO);
		String telefono = dDatosContacto.getString(TELEFONO);
		Document dDireccionPostal = (Document) dDatosContacto.get(DIRECCION_POSTAL);
		String direccion = dDireccionPostal.getString(DIRECCION);
		String localidad = dDireccionPostal.getString(LOCALIDAD);
		String codigoPostal = dDireccionPostal.getString(CODIGO_POSTAL);
		DireccionPostal direccionPostal = new DireccionPostal(direccion, localidad, codigoPostal);
		DatosContacto datosContacto = new DatosContacto(telefono, correo, direccionPostal);
		return new Cliente(datosPersonales, datosContacto);
	}
}
