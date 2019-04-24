package org.iesalandalus.programacion.gestionclientes.vista.iugtablas.controladoresvistas;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.vista.iugventanas.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	private IControladorGestionClientes controladorMVC;

	public void setControlador(IControladorGestionClientes controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@FXML private TableView<Cliente> tvClientes;

	@FXML private TableColumn<Cliente, String> tcNombre;
	@FXML private TableColumn<Cliente, String> tcApellidos;
	@FXML private TableColumn<Cliente, String> tcDni;
	@FXML private TableColumn<Cliente, String> tcFechaNacimiento;
	@FXML private TableColumn<Cliente, String> tcTelefono;
	@FXML private TableColumn<Cliente, String> tcCorreo;
	@FXML private TableColumn<Cliente, String> tcDireccion;
	@FXML private TableColumn<Cliente, String> tcLocalidad;
	@FXML private TableColumn<Cliente, String> tcCodigoPostal;
	
	@FXML private TextField tfFiltrarNombre;
	@FXML private TextField tfFiltrarApellidos;
	@FXML private TextField tfFiltrarCorreo;

	@FXML private ContextMenu cmClientes;

	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	private FilteredList<Cliente> clientesFiltrados = new FilteredList<>(clientes, p -> true);
	private Stage anadirCliente;

	@FXML
	private void initialize() {
		tcNombre.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosPersonales().getNombre()));
		tcApellidos.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosPersonales().getApellidos()));
		tcDni.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosPersonales().getDni()));
		tcFechaNacimiento.setCellValueFactory(cliente -> new SimpleStringProperty(FORMATO_FECHA.format(cliente.getValue().getDatosPersonales().getFechaNacimiento())));
		tcTelefono.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosContacto().getTelefono()));
		tcCorreo.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosContacto().getCorreo()));
		tcDireccion.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosContacto().getDireccionPostal().getDireccion()));
		tcLocalidad.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosContacto().getDireccionPostal().getLocalidad()));
		tcCodigoPostal.setCellValueFactory(cliente -> new SimpleStringProperty(cliente.getValue().getDatosContacto().getDireccionPostal().getCodigoPostal()));
		
		SortedList<Cliente> clientesOrdenados = new SortedList<>(clientesFiltrados);
		clientesOrdenados.comparatorProperty().bind(tvClientes.comparatorProperty());
		tvClientes.setItems(clientesOrdenados);
		
		tfFiltrarNombre.textProperty().addListener((ob, ov, nv) -> 
			clientesFiltrados.setPredicate(cliente -> {
				if (nv == null || nv.isEmpty()) {
					return true;
				}
				String nombre = cliente.getDatosPersonales().getNombre().toLowerCase();
				return nombre.startsWith(nv.toLowerCase());
			})
		);
		tfFiltrarNombre.focusedProperty().addListener((ob, ov, nv) -> {
			tfFiltrarApellidos.setText("");
			tfFiltrarCorreo.setText("");
		});
		tfFiltrarApellidos.textProperty().addListener((ob, ov, nv) -> 
			clientesFiltrados.setPredicate(cliente -> {
				if (nv == null || nv.isEmpty()) {
					return true;
				}
				String apellidos = cliente.getDatosPersonales().getApellidos().toLowerCase();
				return apellidos.startsWith(nv.toLowerCase());
			})
		);
		tfFiltrarApellidos.focusedProperty().addListener((ob, ov, nv) -> {
			tfFiltrarNombre.setText("");
			tfFiltrarCorreo.setText("");
		});
		tfFiltrarCorreo.textProperty().addListener((ob, ov, nv) -> 
			clientesFiltrados.setPredicate(cliente -> {
				if (nv == null || nv.isEmpty()) {
					return true;
				}
				String correo = cliente.getDatosContacto().getCorreo().toLowerCase();
				return correo.contains(nv.toLowerCase());
			})
		);
		tfFiltrarCorreo.focusedProperty().addListener((ob, ov, nv) -> {
			tfFiltrarNombre.setText("");
			tfFiltrarApellidos.setText("");
		});
	}

	@FXML
	private void anadirCliente(ActionEvent event) throws IOException {
		crearAnadirCliente();
		anadirCliente.showAndWait();
	}

	@FXML
	private void borrarCliente(ActionEvent event) {
		Cliente cliente = null;
		try {
			cliente = tvClientes.getSelectionModel().getSelectedItem();
			if (cliente != null && Dialogos.mostrarDialogoConfirmacion("Borrar", "¿Estás seguro de que quieres borrar el cliente?", null)) {
				controladorMVC.borrarCliente(cliente);
				clientes.remove(cliente);
				Dialogos.mostrarDialogoInformacion("Borrar Cliente", "Cliente borrado satisfactoriamente");
			}
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Borrar cliente", e.getMessage());
		}	
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
		panelDialogo.getStylesheets().add(getClass().getResource("../iugtablas.css").toExternalForm());
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
	
	private void crearAnadirCliente() throws IOException {
		if (anadirCliente == null) {
			anadirCliente = new Stage();
			FXMLLoader cargadorAnadirCliente = new FXMLLoader(
						getClass().getResource("../vistas/AnadirCliente.fxml"));
			VBox raizAnadirCliente = cargadorAnadirCliente.load();
			ControladorAnadirCliente cAnadirCliente = cargadorAnadirCliente.getController();
			cAnadirCliente.setControladorMVC(controladorMVC);
			cAnadirCliente.setClientes(clientes);
			Scene escenaAnadirCliente = new Scene(raizAnadirCliente);
			anadirCliente.setTitle("Añadir cliente");
			anadirCliente.initModality(Modality.APPLICATION_MODAL); 
			anadirCliente.setScene(escenaAnadirCliente);
		}
	}

	public void actualizaClientes() {
		clientes.setAll(controladorMVC.getClientes());
	}

}
