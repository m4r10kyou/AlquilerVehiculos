package presentacion.control;

import java.net.URL;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.hsqldb.lib.KMPSearchAlgorithm;

import excepciones.*;
import persistencia.ClienteDAOImp;
import persistencia.EmpleadoDAOImp;
import persistencia.EntregaDAOImp;
import persistencia.IClienteDAO;
import persistencia.dto.ClienteDTO;
import persistencia.dto.EmpleadoDTO;
import Logica.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorCrearEntrega extends ControladorCasoDeUso {
	 private static final Logger LOG = Logger.getLogger(ControladorCrearEntrega.class.getName());
	 @FXML
	 private TextField dni;
	 @FXML
	 private TextField nombreEmpleado;
	 @FXML
	 private Button aceptar;
	 @FXML
	 private Button anterior;
	 @FXML
	 private Button buscar;
	 @FXML
     private TableColumn<Coche, String> matricula;
     @FXML
     private TableColumn<Coche, String> categoria;
     @FXML
     private TableColumn<Coche, Double> kms;
     @FXML
     private TextField combustible;
     @FXML
     private TableView<Coche> tablaVehiculos;
     @FXML
	 private ComboBox<String> tipoSeguro;
     
	 //OBJETO ENTREGA
	 private Entrega nuevaEntrega;
	 private Reserva reservaActual; // RESERVA SELECCIONADA.
	 private Coche coche;
	 private Empleado empleadoActual;
	 private Categoria categoriaActual;
	 private Sucursal sucursalActual;

	 public void setReserva(Reserva r) {
		 this.reservaActual = r;
		 categoriaActual = reservaActual.getCatergoria();
	 }
	 public void setSucursal(Sucursal s) {
		 this.sucursalActual = s;
	 }
	 
	 public void init() {
		 System.out.println(categoriaActual);
		 System.out.println(sucursalActual);
		 System.out.println(reservaActual);
		 
		 
		 
		 List<Coche> listaCoches = sucursalActual.getCoches();
		 List<Coche> listaCochesCategoria = new ArrayList<Coche>();
		 while(listaCochesCategoria.isEmpty() && categoriaActual!=null){
			 System.out.println("lista coches: " + listaCoches);
			 System.out.println("categoria actual: " + categoriaActual.getId());
			 for(Coche c : listaCoches){
				 System.out.println("categoria coche: " + c.getNombre().getId());
				 if(c.getNombre().getId() == categoriaActual.getId())
					 listaCochesCategoria.add(c);
			 }
			 if(listaCochesCategoria.isEmpty())
				 this.categoriaActual = categoriaActual.getCategoriaSuperior();
		 }
		 if(categoriaActual == null) {
			 Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("No hay coches");
			 alert.setContentText("No existen vehículos de la categoria solicitada o superior.");
			 alert.show();
			 stage.close();
		 }
		 tablaVehiculos.getItems().clear();
		 tablaVehiculos.getItems().setAll(listaCochesCategoria);
	 }
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("CREAR ENTREGA");
		 
		 anterior.setOnAction(event -> stage.close());
		 
		 tipoSeguro.getItems().addAll("TodoRiesgo");
		 tipoSeguro.getItems().addAll("Terceros");
		 tipoSeguro.getItems().addAll("Ter.+Robo");
		 
		 tablaVehiculos.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalues)->{
			 this.coche = newvalues;
		 });
		 aceptar.setOnAction(event -> {
			 nuevaEntrega = new Entrega(LocalDateTime.now(),
					 					tipoSeguro.getValue(),
					 					coche.getKmsactuales(),
					 				    Double.parseDouble(combustible.getText()),
					 					coche,
					 					empleadoActual);
			 AlquilerVehiculos.getInstance().crearEntrega(reservaActual.getId(), nuevaEntrega);
		     stage.close();
		 });

		 
		 // BUSQEUDA DE EMPLEADO:
		 
		 buscar.setOnAction(event -> {
			 try{
				 EmpleadoDAOImp empleado = new EmpleadoDAOImp();
				 EmpleadoDTO emp = empleado.buscarEmpleado(dni.getText());
				 if(emp == null){
					 Alert alert = new Alert(AlertType.INFORMATION);
					 alert.setTitle("Error de busqueda");
					 alert.setContentText("El empleado no existe!");
					 alert.show();
				 } else {
					 aceptar.setDisable(false);
					 nombreEmpleado.setText(emp.getNombre());
					 dni.setDisable(true);
					 empleadoActual = new Empleado(emp.getDni(), emp.getNombre(), emp.isAdministrador(), AlquilerVehiculos.getInstance().getSucursal(emp.getSucursal()));
				 }
			 }catch(DAOExcepcion e){
				 System.err.println(e);
			 }
		 });//fin buscar



		 matricula.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMatricula()));
		 kms.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKmsactuales()));
		 categoria.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getCategoria()));
		 
		
	 }//fin initialize
}//FIN

