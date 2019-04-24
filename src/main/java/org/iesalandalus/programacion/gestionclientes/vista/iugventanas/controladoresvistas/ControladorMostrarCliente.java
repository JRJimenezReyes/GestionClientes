package org.iesalandalus.programacion.gestionclientes.vista.iugventanas.controladoresvistas;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.vista.iugventanas.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorMostrarCliente {

	private IControladorGestionClientes controladorMVC;
	private ControladorDatosCliente datosCliente;
	
	public void setControladorMVC(IControladorGestionClientes controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	public void setDatosCliente(ControladorDatosCliente datosCliente) {
		this.datosCliente = datosCliente;
	}
	
	public void setCliente(Cliente cliente) {
		datosCliente.setCliente(cliente);
	}

	@FXML private Button btBorrar;
	@FXML private Button btAceptar;
	
	@FXML
	private void borrarCliente() {
		Cliente cliente = null;
		try {
			if (Dialogos.mostrarDialogoConfirmacion("Borrar", "¿Estás seguro de que quieres borrar el cliente?", null)) {
				cliente = datosCliente.getCliente();
				controladorMVC.borrarCliente(cliente);
				Stage propietario =((Stage) btBorrar.getScene().getWindow());
				Dialogos.mostrarDialogoInformacion("Borrar Cliente", "Cliente borrado satisfactoriamente", propietario);
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar cliente", e.getMessage());
		}	
	}
	
	@FXML
	private void aceptar() {
		((Stage) btAceptar.getScene().getWindow()).close();
	}

}
