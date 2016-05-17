package presentacion.control;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import excepciones.*;
import persistencia.ClienteDAOImp;
import persistencia.IClienteDAO;
import persistencia.dto.ClienteDTO;
import Logica.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorCrearReserva extends ControladorCasoDeUso {
	 private static final Logger LOG = Logger.getLogger(ControladorCrearReserva.class.getName());
	 @FXML
	 private TextField dni;
	 @FXML
	 private TextField nombreCliente;
	 @FXML
	 private Button aceptar;
	 @FXML
	 private Button cancelar;
	 @FXML
	 private Button buscar;
	 @FXML
	 private Button nuevoCliente;
	 @FXML
	 private ComboBox<Sucursal> sRecogida;
	 @FXML
	 private ComboBox<Sucursal> sEntrega;
	 @FXML
	 private ComboBox<Categoria> categoria;
	 @FXML
	 private ComboBox<String> modalidad;
	 @FXML
	 private DatePicker fechaRecogida;
	 @FXML
	 private DatePicker FechaDevolucion;
	 @FXML
	 private Button CalcularCoste;
	 @FXML
	 private CheckBox checkSilla;
	 @FXML
	 private CheckBox checkGPS;
	 @FXML
	 private CheckBox checkDVD;

	 private Reserva nuevaReserva;
	 Cliente client ;
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("CREAR RESERVA");
		 cancelar.setOnAction(event -> stage.close());

		 aceptar.setOnAction(event -> {
			 nuevaReserva = new Reserva(
					 dni.getText().hashCode()+fechaRecogida.getValue().atStartOfDay().hashCode(),
					 fechaRecogida.getValue().atStartOfDay(),
					 FechaDevolucion.getValue().atStartOfDay(),
					 modalidad.getValue(),
					 categoria.getValue(),
					 sEntrega.getValue().getId(),
					 sRecogida.getValue().getId(),
					 client);


			 if (nuevaReserva != null) {
			 //Invocamos el servicio encargado de Crear un nuevo cliente
				 int modal;
				 if(modalidad.getValue().equals("Kilometraje ilimitado")){
					 modal = 0;
				 } else modal=1;

				 AlquilerVehiculos.getInstance().crearReserva(nuevaReserva);
				 LOG.log(Level.INFO, "Se ha creado una nueva reserva: " + nuevaReserva);
			 } else {
				 LOG.log(Level.INFO, "No se ha podido crear una nueva reserva.");

			 }
				 stage.close();
		 });//fin crear reserva

		 Map<Integer, Sucursal> mapSucursales = AlquilerVehiculos.getInstance().getSucursales();
		 for (Integer key : mapSucursales.keySet()){
			this.sRecogida.getItems().addAll(mapSucursales.get(key));
		 	this.sEntrega.getItems().addAll(mapSucursales.get(key));
		 }

		 modalidad.getItems().addAll("Kilometraje ilimitado");
		 modalidad.getItems().addAll("Kilometraje mï¿½nimo");

		 Map<String, Categoria> mapCategoria = AlquilerVehiculos.getInstance().getCategorias();
		 for (String key : mapCategoria.keySet()){
			this.categoria.getItems().addAll(mapCategoria.get(key));
		 }
		 aceptar.setDisable(true);
		 buscar.setOnAction(event -> {
			 client=AlquilerVehiculos.getInstance().cargarCliente(dni.getText());
			 if(client == null){
				 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("Error de identificacion");
				 alert.setContentText("El cliente no existe");
				 alert.show();
			 } else {
				 aceptar.setDisable(false);
				 nombreCliente.setText(client.getNombre()+client.getApellidos());
				 dni.setDisable(true);
			 }
		 });//fin buscar


		 nuevoCliente.setOnAction(event -> {
			 //ControladorCasoDeUso.initCasoDeUso(, ControladorCrearCliente.class, stage, ControladorPrincipal.this);
			 FXMLLoader fxmlLoader = new FXMLLoader(ControladorCrearReserva.class.getResource("../vista/crearCliente.fxml"));
			 ControladorCrearCliente controlador = null;
			 try{
				 Parent parent = fxmlLoader.load();
				 controlador = fxmlLoader.getController();
				 controlador.stage.setScene(new Scene(parent));
				 controlador.stage.initOwner(stage);
				 controlador.show();
			 }catch(Exception e){
				 System.out.println(e);
				 e.printStackTrace();
			 }

		 }); // fin nuevoCliente
		 
		    checkDVD.setSelected(false);
		    checkGPS.setSelected(false);
		    checkSilla.setSelected(false);
		    
		    CalcularCoste.setOnAction(event -> {
		    	IReserva wrapper = new Reserva(
						 dni.getText().hashCode()+fechaRecogida.getValue().atStartOfDay().hashCode(),
						 fechaRecogida.getValue().atStartOfDay(),
						 FechaDevolucion.getValue().atStartOfDay(),
						 modalidad.getValue(),
						 categoria.getValue(),
						 sEntrega.getValue().getId(),
						 sRecogida.getValue().getId(),
						 client);
		    	
		    	if(checkDVD.isSelected())
		    		wrapper = new ExtraDVD(wrapper);
		    	if(checkGPS.isSelected())
		    		wrapper = new ExtraGPS(wrapper);
		    	if(checkSilla.isSelected())
		    		wrapper = new ExtraSilla(wrapper);
		    	
		    	 Alert alert = new Alert(AlertType.INFORMATION);
				 alert.setTitle("Coste de la reserva");
				 alert.setContentText("El coste de la reserva es de: " + 
				 wrapper.calcularCoste() + "€");
				 alert.show();
		    });



	 }//fin initialize
}//FIN

