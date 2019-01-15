package org.iesalandalus.programacion.gestionclientes.vista;

public enum Opcion {
	SALIR("Salir") {
		public void ejecutar() {
			vista.salir();
		}
	},
	INSERTAR_CLIENTE("Insertar cliente") {
		public void ejecutar() {
			vista.insertarCliente();
		}
	},
	BORRAR_CLIENTE("Borrar cliente") {
		public void ejecutar() {
			vista.borrarCliente();
		}
	},
	BUSCAR_CLIENTE("Buscar cliente") {
		public void ejecutar() {
			vista.buscarCliente();
		}
	},
	LISTAR_CLIENTES("Listar clientes") {
		public void ejecutar() {
			vista.listarClientes();
		}
	};
	
	private String mensajeAMostrar;
	private static IUTextual vista;
	
	private Opcion(String mensajeAMostrar) {
		this.mensajeAMostrar = mensajeAMostrar;
	}
	
	public String getMensaje() {
		return mensajeAMostrar;
	}
	
	public abstract void ejecutar();
	
	protected static void setVista(IUTextual vista) {
		Opcion.vista = vista;
	}
	
	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensajeAMostrar);
	}
	
	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if (esOrdinalValido(ordinal))
			return values()[ordinal];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}
	
	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}
}
