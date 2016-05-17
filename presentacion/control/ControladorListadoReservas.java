package presentacion.control;
import java.net.URL;
import java.security.AllPermission;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import org.hsqldb.Table;

import com.sun.javafx.css.converters.StringConverter;

import Logica.AlquilerVehiculos;
import Logica.Categoria;
import Logica.Cliente;
import Logica.Reserva;
import Logica.Sucursal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListadoReservas extends ControladorCasoDeUso {
	 @FXML
	 private ComboBox<Sucursal> sucursalRec;
	 @FXML
	 private TableColumn<Reserva, Integer> id;
	 @FXML
	 private TableColumn<Reserva, String> cliente;
	 @FXML
	 private TableColumn<Reserva, LocalDateTime> fechaRecogida;
	 @FXML
	 private TableColumn<Reserva, LocalDateTime> fechaDevolucion;
	 @FXML
	 private TableColumn<Reserva, String> dni;
	 @FXML
	 private TableView<Reserva> tablaRec;
	 @FXML
	 private Button cancelar;
	 @FXML
	 private Button siguiente;

	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("LISTADO RESERVAS SUCURSAL");
		 cancelar.setOnAction(event -> stage.close());
		 
		 id.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		 dni.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDniCliente()));
		 cliente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getNombreCliente()));


		 fechaRecogida.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaRegogida()));
		 fechaDevolucion.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaDevolucion()));


		 List<Sucursal> listasucursa=new ArrayList<Sucursal>();
		 Map<Integer, Sucursal> mapSucursales = AlquilerVehiculos.getInstance().getSucursales();
		 for (Integer key : mapSucursales.keySet()) {
			 listasucursa.add(mapSucursales.get(key));
		 }
		 this.sucursalRec.getItems().addAll(listasucursa);


		 //Listener
		 sucursalRec.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalues)->{
			 AlquilerVehiculos.getInstance().cargarEntregasPorSucursal(newvalues.getId());
			 
			tablaRec.getItems().clear();
			List<Reserva> lis = new ArrayList<Reserva>();
			for(Reserva r : AlquilerVehiculos.getInstance().listarReservasSucursal(newvalues.getId())) {
				if(r.getEntrega() == null)
					lis.add(r);
			}
			tablaRec.getItems().addAll(lis);
			siguiente.setDisable(true);
			});
		 
		 tablaRec.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)->{
			 
			 siguiente.setDisable(false);
		 });
		 
		 siguiente.setOnAction(event -> {
			 /*
			 FXMLLoader fxmlLoader = new FXMLLoader(ControladorListadoReservas.class.getResource("../vista/crearEntrega.fxml"));
			 ControladorCrearEntrega controlador = null;
			 
			 try{

				 controlador = fxmlLoader.getController();
				 Parent parent = fxmlLoader.load();
				 System.out.println(controlador);
				 System.out.println(tablaRec.getSelectionModel().getSelectedItem());
				 System.out.println(sucursalRec.getSelectionModel().getSelectedItem());
				 /*controlador.setReserva(tablaRec.getSelectionModel().getSelectedItem());
				 controlador.setSucursal(sucursalRec.getSelectionModel().getSelectedItem());

				 controlador.stage.setScene(new Scene(parent));
				 controlador.stage.initOwner(stage);
				 controlador.show();
			 }catch(Exception e){
				 System.out.println(e);
				 e.printStackTrace();
			 }*/
			 
			//ControladorCasoDeUso.initCasoDeUso(, ControladorCrearCliente.class, stage, ControladorPrincipal.this);
			 FXMLLoader fxmlLoader = new FXMLLoader(ControladorListadoReservas.class.getResource("../vista/crearEntrega.fxml"));
			 ControladorCrearEntrega controlador = null;
			 try{
				 Parent parent = fxmlLoader.load();
				 controlador = fxmlLoader.getController();
				 
				 System.out.println(controlador);
				 System.out.println(tablaRec.getSelectionModel().getSelectedItem());
				 System.out.println(sucursalRec.getSelectionModel().getSelectedItem());
				 controlador.setReserva(tablaRec.getSelectionModel().getSelectedItem());
				 controlador.setSucursal(sucursalRec.getSelectionModel().getSelectedItem());
				 controlador.init();
				 controlador.stage.setScene(new Scene(parent));
				 controlador.stage.initOwner(stage);
				 controlador.show();
			 }catch(Exception e){
				 System.out.println(e);
				 e.printStackTrace();
			 }
		 });

	 }
	}