package org.iesalandalus.programacion.gestionclientes.vista.iugventanas.controladoresvistas;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.vista.iugventanas.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorAnadirCliente {
	
	private IControladorGestionClientes controladorMVC;
	private ControladorDatosCliente datosCliente;
	
	public void setControladorMVC(IControladorGestionClientes controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	public void setDatosCliente(ControladorDatosCliente datosCliente) {
		this.datosCliente = datosCliente;
	}

	@FXML private Button btAnadir;
	@FXML private Button btCancelar;
	
	@FXML
	private void anadirCliente() {
		Cliente cliente = null;
		try {
			cliente = datosCliente.getCliente();
			controladorMVC.insertarCliente(cliente);
			Stage propietario =((Stage) btAnadir.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Cliente", "Cliente añadido satisfactoriamente", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir cliente", e.getMessage());
		}	
	}
	
	@FXML
	private void cancelar() {
		((Stage) btCancelar.getScene().getWindow()).close();
	}

}
