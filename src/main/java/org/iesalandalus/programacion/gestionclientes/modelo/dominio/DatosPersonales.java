package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatosPersonales implements Serializable {

	private static final String ER_DNI = "\\d{8}[A-Za-z]";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String nombre;
	private String apellidos;
	private String dni;
	private LocalDate fechaNacimiento;
	
	public DatosPersonales(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
		setFechaNacimiento(fechaNacimiento);
	}
	
	public DatosPersonales(String nombre, String apellidos, String dni, String fechaNacimiento) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
		setFechaNacimiento(fechaNacimiento);
	}
	
	public DatosPersonales(DatosPersonales datosPersonales) {
		if (datosPersonales == null) {
			throw new IllegalArgumentException("No se pueden copiar unos datos personales nulos.");
		}
		setNombre(datosPersonales.nombre);
		setApellidos(datosPersonales.apellidos);
		setDni(datosPersonales.dni);
		setFechaNacimiento(datosPersonales.fechaNacimiento);
	}
	
	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException("El nombre no puede ser nulo.");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("El nombre no puede estar vacío.");
		}
		this.nombre = nombre;
	}
	
	private void setApellidos(String apellidos) {
		if (apellidos == null) {
			throw new IllegalArgumentException("Los apellidos no pueden ser nulos.");
		}
		if (apellidos.trim().equals("")) {
			throw new IllegalArgumentException("Los apellidos no pueden estar vacíos.");
		}
		this.apellidos = apellidos;
	}
	
	private void setDni(String dni) {
		if (dni == null) {
			throw new IllegalArgumentException("El dni no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("El formato del dni no es correcto.");
		}
		this.dni = dni;
	}
	
	private void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
		}
		this.fechaNacimiento = fechaNacimiento;
	}
	
	private void setFechaNacimiento(String fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
		}
		try {
			this.fechaNacimiento = LocalDate.parse(fechaNacimiento, FORMATO_FECHA);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("El formato de la fecha de nacimiento no es correcto.");
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public String getDni() {
		return dni;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DatosPersonales)) {
			return false;
		}
		DatosPersonales other = (DatosPersonales) obj;
		if (dni == null) {
			if (other.dni != null) {
				return false;
			}
		} else if (!dni.equals(other.dni)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + 
				", fecha nacimiento=" + fechaNacimiento.format(FORMATO_FECHA) + "]";
	}

}
