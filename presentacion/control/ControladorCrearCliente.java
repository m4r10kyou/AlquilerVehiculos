package presentacion.control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Logica.AlquilerVehiculos;
import Logica.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorCrearCliente extends ControladorCasoDeUso {
	 private static final Logger LOG = Logger.getLogger(ControladorCrearCliente.class.getName());
	 @FXML
	 private TextField dni;
	 @FXML
	 private TextField nombre;
	 @FXML
	 private TextField apellidos;
	 @FXML
	 private TextField direccion;
	 @FXML
	 private TextField anyoTC;
	 @FXML
	 private TextField mesTC;
	 @FXML
	 private TextField codigoPostal;
	 @FXML
	 private TextField poblacion;
	 @FXML
	 private DatePicker fechaCarnet;
	 @FXML
	 private TextField cvc;
	 @FXML
	 private TextField tipoTarjeta;
	 @FXML
	 private TextField digitosTC;
	 @FXML
	 private Button aceptar;
	 @FXML
	 private Button cancelar;
	 private Cliente nuevoCliente;
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("CREAR CLIENTE");
		 cancelar.setOnAction(event -> stage.close());
		 aceptar.setOnAction(event -> {
			 nuevoCliente = new Cliente(
					 dni.getText(),
					 nombre.getText(),
					 apellidos.getText(),
					 direccion.getText(),
					 poblacion.getText(),
					 tipoTarjeta.getText(),
					 Integer.parseInt(codigoPostal.getText()),
					 digitosTC.getText(),
					 Integer.parseInt(mesTC.getText()),
					 Integer.parseInt(anyoTC.getText()),
					 Integer.parseInt(cvc.getText()),
					 fechaCarnet.getValue());


			 if (nuevoCliente != null) {
			 //Invocamos el servicio encargado de Crear un nuevo cliente

				 AlquilerVehiculos.getInstance().crearCliente(
						 dni.getText(),
						 nombre.getText(),
						 apellidos.getText(),
						 direccion.getText(),
						 poblacion.getText(),
						 tipoTarjeta.getText(),
						 Integer.parseInt(codigoPostal.getText()),
						 digitosTC.getText(),
						 Integer.parseInt(mesTC.getText()),
						 Integer.parseInt(anyoTC.getText()),
						 Integer.parseInt(cvc.getText()),
						 fechaCarnet.getValue());

				 LOG.log(Level.INFO, "Se ha creado un nuevo Cliente: " + nuevoCliente);
			 } else {
				 LOG.log(Level.INFO, "No se ha podido crear un nuevo cliente.");

			 }
				 stage.close();
		 });
	 }
	}
