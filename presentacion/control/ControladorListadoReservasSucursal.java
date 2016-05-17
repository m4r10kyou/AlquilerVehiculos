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
import Logica.Reserva;
import Logica.Sucursal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListadoReservasSucursal extends ControladorCasoDeUso {
	 @FXML
	 private ComboBox<Sucursal> sucursalRec;
	 @FXML
	 private TableColumn<Reserva, Integer> idRec;
	 @FXML
	 private TableColumn<Reserva, String> clienteRec;
	 @FXML
	 private TableColumn<Reserva, LocalDateTime> fechaRecogidaRec;
	 @FXML
	 private TableColumn<Reserva, String> lugarRecogidaRec;
	 @FXML
	 private TableColumn<Reserva, LocalDateTime> fechaDevolucionRec;
	 @FXML
	 private TableColumn<Reserva, String> lugarDevolucionRec;
	 @FXML
	 private TableColumn<Reserva, String> modalidadRec;
	 @FXML
	 private TableColumn<Reserva, Categoria> categoriaRec;
	 @FXML
	 private TableColumn<Reserva, Integer> idDev;
	 @FXML
	 private TableColumn<Reserva, String> clienteDev;
	 @FXML
	 private TableColumn<Reserva, LocalDateTime> fechaRecogidaDev;
	 @FXML
	 private TableColumn<Reserva, String> lugarRecogidaDev;
	 @FXML
	 private TableColumn<Reserva, LocalDateTime> fechaDevolucionDev;
	 @FXML
	 private TableColumn<Reserva, String> lugarDevolucionDev;
	 @FXML
	 private TableColumn<Reserva, String> modalidadDev;
	 @FXML
	 private TableColumn<Reserva, Categoria> categoriaDev;
	 @FXML
	 private TableView<Reserva> tablaRec;
	 @FXML
	 private TableView<Reserva> tablaDev;
	 @FXML
	 private Button cerrar;

	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("LISTADO RESERVAS SUCURSAL");
		 cerrar.setOnAction(event -> stage.close());

		 idRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		 clienteRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDNICliente().getDni()));
		 fechaRecogidaRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaRegogida()));
		 lugarRecogidaRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				 AlquilerVehiculos.getInstance().getSucursal(param.getValue().getLugar_recogida()).getDireccion()));
		 fechaDevolucionRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaDevolucion()));
		 lugarDevolucionRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				 AlquilerVehiculos.getInstance().getSucursal(param.getValue().getLugar_devolucion()).getDireccion()));
		 modalidadRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getModalidadAlquiler()));
		 categoriaRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getCatergoria()));

		 idDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		 clienteDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDNICliente().getDni()));
		 fechaRecogidaDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaRegogida()));
		 lugarRecogidaDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				 AlquilerVehiculos.getInstance().getSucursal(param.getValue().getLugar_recogida()).getDireccion()));
		 fechaDevolucionDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaDevolucion()));
		 lugarDevolucionDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				 AlquilerVehiculos.getInstance().getSucursal(param.getValue().getLugar_devolucion()).getDireccion()));
		 modalidadDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getModalidadAlquiler()));
		 categoriaDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getCatergoria()));

		 List<Sucursal> listasucursa=new ArrayList<Sucursal>();
		 Map<Integer, Sucursal> mapSucursales = AlquilerVehiculos.getInstance().getSucursales();
		 for (Integer key : mapSucursales.keySet()) {
			 listasucursa.add(mapSucursales.get(key));
		 }
		 this.sucursalRec.getItems().addAll(listasucursa);


		 //Listener
		 sucursalRec.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalues)->{
			tablaRec.getItems().clear();
			tablaRec.getItems().addAll(AlquilerVehiculos.getInstance().listarReservasSucursal(newvalues.getId()));
			tablaDev.getItems().clear();
			tablaDev.getItems().addAll(AlquilerVehiculos.getInstance().listarReservasSucursalD(newvalues.getId()));
			});

	 }
	}