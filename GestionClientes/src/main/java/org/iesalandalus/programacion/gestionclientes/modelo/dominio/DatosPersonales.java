package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

public class DatosPersonales {

	private static final String ER_DNI = "\\d{8}[A-Za-z]";
	
	private String nombre;
	private String apellidos;
	private String dni;
	
	public DatosPersonales(String nombre, String apellidos, String dni) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDni(dni);
	}
	
	public DatosPersonales(DatosPersonales datosPersonales) {
		if (datosPersonales == null) {
			throw new IllegalArgumentException("No se pueden copiar unos datos personales nulos.");
		}
		setNombre(datosPersonales.nombre);
		setApellidos(datosPersonales.apellidos);
		setDni(datosPersonales.dni);
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
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public String getDni() {
		return dni;
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
		return "[nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + "]";
	}

}
