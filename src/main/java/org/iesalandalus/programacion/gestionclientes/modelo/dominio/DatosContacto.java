package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import java.io.Serializable;

public class DatosContacto implements Serializable {

	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	
	private String telefono;
	private String correo;
	private DireccionPostal direccionPostal;
	
	public DatosContacto(String telefono, String correo, DireccionPostal direccionPostal) {
		setTelefono(telefono);
		setCorreo(correo);
		setDireccionPostal(direccionPostal);
	}
	
	public DatosContacto(DatosContacto datosContacto) {
		if (datosContacto == null) {
			throw new IllegalArgumentException("No se pueden copiar unos datos de contacto nulos.");
		}
		setTelefono(datosContacto.telefono);
		setCorreo(datosContacto.correo);
		setDireccionPostal(datosContacto.direccionPostal);
	}
	
	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new IllegalArgumentException("El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("El formato del teléfono no es correcto.");
		}
		this.telefono = telefono;
	}
	
	public void setCorreo(String correo) {
		if (correo == null) {
			throw new IllegalArgumentException("El correo no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("El formato del correo no es correcto.");
		}
		this.correo = correo;
	}
	
	public void setDireccionPostal(DireccionPostal direccionPostal) {
		if (direccionPostal == null) {
			throw new IllegalArgumentException("La dirección postal no puede ser nula.");
		}
		this.direccionPostal = new DireccionPostal(direccionPostal);
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public DireccionPostal getDireccionPostal() {
		return new DireccionPostal(direccionPostal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((direccionPostal == null) ? 0 : direccionPostal.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		if (!(obj instanceof DatosContacto)) {
			return false;
		}
		DatosContacto other = (DatosContacto) obj;
		if (correo == null) {
			if (other.correo != null) {
				return false;
			}
		} else if (!correo.equals(other.correo)) {
			return false;
		}
		if (direccionPostal == null) {
			if (other.direccionPostal != null) {
				return false;
			}
		} else if (!direccionPostal.equals(other.direccionPostal)) {
			return false;
		}
		if (telefono == null) {
			if (other.telefono != null) {
				return false;
			}
		} else if (!telefono.equals(other.telefono)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[teléfono=" + telefono + ", correo=" + correo + ", dirección postal=" + direccionPostal + "]";
	}
	
}
