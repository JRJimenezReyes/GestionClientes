package org.iesalandalus.programacion.gestionclientes.vista.iugventanas.controladoresvistas;

import java.io.IOException;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContacto;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonales;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostal;
import org.iesalandalus.programacion.gestionclientes.vista.iugventanas.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {
	
	private static final DireccionPostal DIRECCION_POSTAL_FICTICIA = new DireccionPostal("C/ 1", "1", "11111");
	private static final DatosContacto DATOS_CONTACTO_FICTICIOS = new DatosContacto("950111111", "1@1.es", DIRECCION_POSTAL_FICTICIA);
	
	
	private IControladorGestionClientes controladorMVC;
	private Stage listadoClientes;
	private Stage anadirCliente;
	private Stage mostrarCliente;
	private ControladorMostrarCliente cMostrarCliente;
	
	public void setControlador(IControladorGestionClientes controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	@FXML
	private void anadirCliente() throws IOException {
		crearAnadirCliente();
		anadirCliente.showAndWait();
	}
	
	@FXML
	private void buscarCliente() throws IOException {
		String dni = Dialogos.mostrarDialogoTexto("Buscar cliente", "Introduce el DNI del cliente a buscar");
		if (dni != null) {
			DatosPersonales datosPersonalesDni = new DatosPersonales("Cliente", "1", dni, "01/01/1999");
			Cliente cliente = new Cliente(datosPersonalesDni, DATOS_CONTACTO_FICTICIOS);
			cliente = controladorMVC.buscarCliente(cliente);
			if (cliente != null) {
			    crearMostrarCliente(cliente);
			    mostrarCliente.showAndWait();
			} else {
				Dialogos.mostrarDialogoError("Cliente no encontrado", "No existe ningún cliente con ese DNI");
			}
		}
	}
	
	@FXML
	private void listarClientes() throws IOException {
		crearListadoClientes();
		listadoClientes.showAndWait();
	}
	
	@FXML
	private void salir() {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", null)) {
			controladorMVC.salir();
			System.exit(0);
		}
	}
	
	@FXML
	private void acercaDe() {
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.setTitle("Acerca de ...");
		DialogPane panelDialogo = dialogo.getDialogPane();
		panelDialogo.getStylesheets().add(getClass().getResource("../iugventanas.css").toExternalForm());
		panelDialogo.lookupButton(ButtonType.OK).setId("btAceptar");
		VBox contenido = new VBox();
		contenido.setAlignment(Pos.CENTER);
		contenido.setPadding(new Insets(20, 20, 0, 20));
		contenido.setSpacing(20);
		Image logo = new Image(getClass().getResourceAsStream("../../imagenes/logo-ies.png"), 200, 200, true, true);
		Label texto = new Label("Módulo de Programación - JavaFX");
		texto.setStyle("-fx-font: 20 Arial");
		contenido.getChildren().addAll(new ImageView(logo), texto);
		panelDialogo.setHeader(contenido);
		dialogo.showAndWait();
	}
	
	private void crearListadoClientes() throws IOException {
		if (listadoClientes == null) {
			listadoClientes = new Stage();
			FXMLLoader cargadorListadoClientes = new FXMLLoader(
						getClass().getResource("../vistas/ListadoClientes.fxml"));
			VBox raizListadoClientes = cargadorListadoClientes.load();
			ControladorListadoClientes cListadoClientes = cargadorListadoClientes.getController();
			cListadoClientes.setControladorMVC(controladorMVC);
			cListadoClientes.actualizaClientes();
			Scene escenaListadoClientes = new Scene(raizListadoClientes);
			listadoClientes.setTitle("Listar clientes");
			listadoClientes.initModality(Modality.APPLICATION_MODAL); 
			listadoClientes.setScene(escenaListadoClientes);
		}
	}
	
	private void crearAnadirCliente() throws IOException {
		if (anadirCliente == null) {
			anadirCliente = new Stage();
			FXMLLoader cargadorAnadirCliente = new FXMLLoader(
						getClass().getResource("../vistas/AnadirCliente.fxml"));
			VBox raizAnadirCliente = cargadorAnadirCliente.load();
			ControladorAnadirCliente cAnadirCliente = cargadorAnadirCliente.getController();
			cAnadirCliente.setControladorMVC(controladorMVC);

			FXMLLoader cargadorDatosCliente = new FXMLLoader(
					getClass().getResource("../vistas/DatosCliente.fxml"));
			GridPane gpDatosCliente = cargadorDatosCliente.load();
			ControladorDatosCliente cDatosCliente = cargadorDatosCliente.getController();
			cAnadirCliente.setDatosCliente(cDatosCliente);
			raizAnadirCliente.getChildren().add(0, gpDatosCliente);
			Scene escenaAnadirCliente = new Scene(raizAnadirCliente);
			anadirCliente.setTitle("Añadir cliente");
			anadirCliente.initModality(Modality.APPLICATION_MODAL); 
			anadirCliente.setScene(escenaAnadirCliente);
		}
	}
	
	private void crearMostrarCliente(Cliente cliente) throws IOException {
		if (mostrarCliente == null) {
			mostrarCliente = new Stage();
			FXMLLoader cargadorMostrarCliente = new FXMLLoader(
						getClass().getResource("../vistas/MostrarCliente.fxml"));
			VBox raizMostrarCliente = cargadorMostrarCliente.load();
			cMostrarCliente = cargadorMostrarCliente.getController();
			cMostrarCliente.setControladorMVC(controladorMVC);

			FXMLLoader cargadorDatosCliente = new FXMLLoader(
					getClass().getResource("../vistas/DatosCliente.fxml"));
			GridPane gpDatosCliente = cargadorDatosCliente.load();
			ControladorDatosCliente cDatosCliente = cargadorDatosCliente.getController();
			cMostrarCliente.setDatosCliente(cDatosCliente);
			cMostrarCliente.setCliente(cliente);
			cDatosCliente.inhabilitaEdicionCampos();
			raizMostrarCliente.getChildren().add(0, gpDatosCliente);
			Scene escenaMostrarCliente = new Scene(raizMostrarCliente);
			mostrarCliente.setTitle("Mostrar cliente");
			mostrarCliente.initModality(Modality.APPLICATION_MODAL); 
			mostrarCliente.setScene(escenaMostrarCliente);
		} else {
			cMostrarCliente.setCliente(cliente);
		}
	}

}
