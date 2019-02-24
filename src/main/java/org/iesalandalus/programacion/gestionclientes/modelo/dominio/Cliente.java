package org.iesalandalus.programacion.gestionclientes.modelo.dominio;

import java.io.Serializable;

public class Cliente implements Serializable {

	private DatosPersonales datosPersonales;
	private DatosContacto datosContacto;
	
	public Cliente(DatosPersonales datosPersonales, DatosContacto datosContacto) {
		setDatosPersonales(datosPersonales);
		setDatosContacto(datosContacto);
	}
	
	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new IllegalArgumentException("No se puede copiar un cliente nulo.");
		}
		setDatosPersonales(cliente.datosPersonales);
		setDatosContacto(cliente.datosContacto);
	}
	
	private void setDatosPersonales(DatosPersonales datosPersonales) {
		if (datosPersonales == null) {
			throw new IllegalArgumentException("Los datos personales no pueden ser nulos.");
		}
		this.datosPersonales = new DatosPersonales(datosPersonales);
	}
	
	public void setDatosContacto(DatosContacto datosContacto) {
		if (datosContacto == null) {
			throw new IllegalArgumentException("Los datos de contacto no pueden ser nulos.");
		}
		this.datosContacto = new DatosContacto(datosContacto);
	}
	
	public DatosPersonales getDatosPersonales() {
		return new DatosPersonales(datosPersonales);
	}
	
	public DatosContacto getDatosContacto() {
		return new DatosContacto(datosContacto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datosPersonales == null) ? 0 : datosPersonales.hashCode());
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
		if (!(obj instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) obj;
		if (datosPersonales == null) {
			if (other.datosPersonales != null) {
				return false;
			}
		} else if (!datosPersonales.equals(other.datosPersonales)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [datos personales=" + datosPersonales + ", datos de contacto=" + datosContacto + "]";
	}
	
}
