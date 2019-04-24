package org.iesalandalus.programacion.gestionclientes.vista.iugtablas;

import org.iesalandalus.programacion.gestionclientes.controlador.IControladorGestionClientes;
import org.iesalandalus.programacion.gestionclientes.vista.IVistaGestionClientes;
import org.iesalandalus.programacion.gestionclientes.vista.iugtablas.controladoresvistas.ControladorVentanaPrincipal;
import org.iesalandalus.programacion.gestionclientes.vista.iugtablas.utilidades.Dialogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaGestionClientes extends Application implements IVistaGestionClientes {

	private IControladorGestionClientes controladorMVC = null;
	private static VistaGestionClientes instancia = null;
	
	public VistaGestionClientes() {
		if (instancia != null) {
			controladorMVC = instancia.controladorMVC;
		} else {
			instancia = this;
		}
	}

	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(getClass().getResource("vistas/VentanaPrincipal.fxml"));
			VBox raiz = cargadorVentanaPrincipal.load();	
			ControladorVentanaPrincipal cVentanaPrincipal = cargadorVentanaPrincipal.getController();
			cVentanaPrincipal.setControlador(controladorMVC);
			cVentanaPrincipal.actualizaClientes();
			Scene escena = new Scene(raiz);
			escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Gestión de Clientes");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", escenarioPrincipal)) {
			controladorMVC.salir();
			escenarioPrincipal.close();
		}
		else
			e.consume();	
	}

	@Override
	public void setControlador(IControladorGestionClientes controlador) {
		this.controladorMVC = controlador;
	}

	@Override
	public void comenzar() {
		launch(this.getClass());
	}
}
