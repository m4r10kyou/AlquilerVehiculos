package presentacion.control;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import Logica.AlquilerVehiculos;
import Logica.Sucursal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListarSucursales extends ControladorCasoDeUso {
	 @FXML
	 private TableView<Sucursal> sucursales;
	 @FXML
	 private TableColumn<Sucursal, Integer> id;
	 @FXML
	 private TableColumn<Sucursal, String> direccion;
	 @FXML
	 private Button aceptar;
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("LISTADO DE SUCURSALES");
		 aceptar.setOnAction(event -> stage.close());
		 id.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		 direccion.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDireccion()));
		 
		 Map<Integer, Sucursal> mapSucursales = AlquilerVehiculos.getInstance().getSucursales(); 
		 for (Integer key : mapSucursales.keySet())
			 this.sucursales.getItems().add(mapSucursales.get(key));
	 }
	}