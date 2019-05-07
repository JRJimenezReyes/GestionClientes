package org.iesalandalus.programacion.gestionclientes.vista.iugtablas.controladoresvistas;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContacto;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonales;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostal;
import org.iesalandalus.programacion.gestionclientes.vista.iugventanas.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirCliente {
	
	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private static final String ER_DNI = "\\d{8}[A-Za-z]";
	private static final String ER_CODIGO_POSTAL = "\\d{5}";
	
	private IControladorGestionClientes controladorMVC;
	private ObservableList<Cliente> clientes;
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfApellidos;
	@FXML private TextField tfDni;
	@FXML private DatePicker dpFechaNacimiento;
	@FXML private TextField tfTelefono;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfDireccion;
	@FXML private TextField tfLocalidad;
	@FXML private TextField tfCodigoPostal;
	@FXML private Button btAnadir;
	@FXML private Button btCancelar;
	
	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfNombre));
		tfApellidos.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfApellidos));
		tfDni.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_DNI, tfDni));
		tfTelefono.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_TELEFONO, tfTelefono));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
		tfDireccion.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfDireccion));
		tfLocalidad.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_OBLIGATORIO, tfLocalidad));
		tfCodigoPostal.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CODIGO_POSTAL, tfCodigoPostal));
	}
	
	public void setControladorMVC(IControladorGestionClientes controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	public void setClientes(ObservableList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void inicializa() {
		tfNombre.setText("");
		tfApellidos.setText("");
		tfDni.setText("");
		dpFechaNacimiento.setValue(null);
		tfTelefono.setText("");
		tfCorreo.setText("");
		tfDireccion.setText("");
		tfLocalidad.setText("");
		tfCodigoPostal.setText("");
	}
	
	@FXML
	private void anadirCliente() {
		Cliente cliente = null;
		try {
			cliente = getCliente();
			controladorMVC.insertarCliente(cliente);
			clientes.add(cliente);
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
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {	
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green");
		}
		else {
			campoTexto.setStyle("-fx-border-color: red");
		}
	}
	
	private Cliente getCliente() {
		DireccionPostal direccionPostal = new DireccionPostal(tfDireccion.getText(), tfLocalidad.getText(), tfCodigoPostal.getText());
		DatosContacto datosContacto = new DatosContacto(tfTelefono.getText(), tfCorreo.getText(), direccionPostal);
		DatosPersonales datosPersonales = new DatosPersonales(tfNombre.getText(), tfApellidos.getText(), tfDni.getText(), dpFechaNacimiento.getValue());
		return new Cliente(datosPersonales, datosContacto);
	}

}
