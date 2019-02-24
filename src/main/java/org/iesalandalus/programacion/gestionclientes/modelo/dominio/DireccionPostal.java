package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import java.io.Serializable;

public class DireccionPostal implements Serializable {

	private static final String ER_CODIGO_POSTAL = "\\d{5}";
	
	private String direccion;
	private String localidad;
	private String codigoPostal;
	
	public DireccionPostal(String direccion, String localidad, String codigoPostal) {
		setDireccion(direccion);
		setLocalidad(localidad);
		setCodigoPostal(codigoPostal);
	}
	
	public DireccionPostal(DireccionPostal direccionPostal) {
		if (direccionPostal == null) {
			throw new IllegalArgumentException("No se puede copiar una dirección postal nula.");
		}
		setDireccion(direccionPostal.direccion);
		setLocalidad(direccionPostal.localidad);
		setCodigoPostal(direccionPostal.codigoPostal);
	}
	
	private void setDireccion(String direccion) {
		if (direccion == null) {
			throw new IllegalArgumentException("La dirección no puede ser nula.");
		}
		if (direccion.trim().equals("")) {
			throw new IllegalArgumentException("La dirección no puede estar vacía.");
		}
		this.direccion = direccion;
	}
	
	private void setLocalidad(String localidad) {
		if (localidad == null) {
			throw new IllegalArgumentException("La localidad no puede ser nula.");
		}
		if (localidad.trim().equals("")) {
			throw new IllegalArgumentException("La localidad no puede estar vacía.");
		}
		this.localidad = localidad;
	}
	
	private void setCodigoPostal(String codigoPostal) {
		if (codigoPostal == null) {
			throw new IllegalArgumentException("El código postal no puede ser nulo.");
		}
		if (!codigoPostal.matches(ER_CODIGO_POSTAL)) {
			throw new IllegalArgumentException("El cógido postal no tiene un formato correcto.");
		}
		this.codigoPostal = codigoPostal;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getLocalidad() {
		return localidad;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
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
		if (!(obj instanceof DireccionPostal)) {
			return false;
		}
		DireccionPostal other = (DireccionPostal) obj;
		if (codigoPostal == null) {
			if (other.codigoPostal != null) {
				return false;
			}
		} else if (!codigoPostal.equals(other.codigoPostal)) {
			return false;
		}
		if (direccion == null) {
			if (other.direccion != null) {
				return false;
			}
		} else if (!direccion.equals(other.direccion)) {
			return false;
		}
		if (localidad == null) {
			if (other.localidad != null) {
				return false;
			}
		} else if (!localidad.equals(other.localidad)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[dirección=" + direccion + ", localidad=" + localidad + ", código postal=" + codigoPostal + "]";
	}
}
