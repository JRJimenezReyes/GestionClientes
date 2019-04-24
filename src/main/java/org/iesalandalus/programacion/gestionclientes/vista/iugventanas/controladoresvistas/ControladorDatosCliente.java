package org.iesalandalus.programacion.gestionclientes.vista.iugventanas.controladoresvistas;

import org.iesalandalus.programacion.gestionclientes.modelo.dominio.Cliente;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosContacto;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DatosPersonales;
import org.iesalandalus.programacion.gestionclientes.modelo.dominio.DireccionPostal;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControladorDatosCliente {

	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";
	private static final String ER_DNI = "\\d{8}[A-Za-z]";
	private static final String ER_CODIGO_POSTAL = "\\d{5}";
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfApellidos;
	@FXML private TextField tfDni;
	@FXML private DatePicker dpFechaNacimiento;
	@FXML private TextField tfTelefono;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfDireccion;
	@FXML private TextField tfLocalidad;
	@FXML private TextField tfCodigoPostal;
	
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
	
	private void compruebaCampoTexto(String er, TextField campoTexto) {	
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green");
		}
		else {
			campoTexto.setStyle("-fx-border-color: red");
		}
	}
	
	public Cliente getCliente() {
		DireccionPostal direccionPostal = new DireccionPostal(tfDireccion.getText(), tfLocalidad.getText(), tfCodigoPostal.getText());
		DatosContacto datosContacto = new DatosContacto(tfTelefono.getText(), tfCorreo.getText(), direccionPostal);
		DatosPersonales datosPersonales = new DatosPersonales(tfNombre.getText(), tfApellidos.getText(), tfDni.getText(), dpFechaNacimiento.getValue());
		return new Cliente(datosPersonales, datosContacto);
	}
	
	public void setCliente(Cliente cliente) {
		if (cliente != null) {
			DatosPersonales datosPersonales = cliente.getDatosPersonales();
			DatosContacto datosContacto = cliente.getDatosContacto();
			DireccionPostal direccionPostal = datosContacto.getDireccionPostal();
			tfNombre.setText(datosPersonales.getNombre());
			tfApellidos.setText(datosPersonales.getApellidos());
			tfDni.setText(datosPersonales.getDni());
			dpFechaNacimiento.setValue(datosPersonales.getFechaNacimiento());
			tfTelefono.setText(datosContacto.getTelefono());
			tfCorreo.setText(datosContacto.getCorreo());
			tfDireccion.setText(direccionPostal.getDireccion());
			tfLocalidad.setText(direccionPostal.getLocalidad());
			tfCodigoPostal.setText(direccionPostal.getCodigoPostal());
		} else {
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
	}
	
	public void inhabilitaEdicionCampos() {
		tfNombre.setDisable(true);
		tfApellidos.setDisable(true);
		tfDni.setDisable(true);
		dpFechaNacimiento.setDisable(true);
		tfTelefono.setDisable(true);
		tfCorreo.setDisable(true);
		tfDireccion.setDisable(true);
		tfLocalidad.setDisable(true);
		tfCodigoPostal.setDisable(true);
	}

}
