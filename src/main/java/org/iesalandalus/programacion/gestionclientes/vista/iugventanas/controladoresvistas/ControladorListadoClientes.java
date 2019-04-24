package org.iesalandalus.programacion.gestionclientes.vista.iugventanas.controladoresvistas;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ControladorListadoClientes {
	
	private IControladorGestionClientes controladorMVC;
	private ObservableList<String> clientes = FXCollections.observableArrayList();
	
	@FXML
	private ListView<String> lvClientes;
	
	@FXML
	private Label lbTitulo;
	
	@FXML
	private void initialize() {
		lvClientes.setItems(clientes);
	}
	
	public void setControladorMVC(IControladorGestionClientes controlador) {
		this.controladorMVC = controlador;
	}
	
	public void actualizaClientes() {
		clientes.setAll(controladorMVC.listarClientes());
	}

}
