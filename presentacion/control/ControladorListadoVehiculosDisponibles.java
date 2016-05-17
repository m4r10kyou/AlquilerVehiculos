
package presentacion.control;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import Logica.AlquilerVehiculos;
import Logica.Coche;
import Logica.Sucursal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListadoVehiculosDisponibles extends ControladorCasoDeUso {
	 @FXML
	 private ComboBox<Sucursal> sucursalV;
	 @FXML
	 private TableColumn<Coche, String> matricula;
	 @FXML
	 private TableColumn<Coche, Double> kmsActuales;
	 @FXML
	 private TableColumn<Coche, String> categoria;
	 @FXML
	 private TableView<Coche> tablaVehiculo;
	 @FXML
	 private Button cerrarV;

	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		 stage = new Stage(StageStyle.DECORATED);
		 stage.setTitle("LISTADO VEHICULOS DISPONIBLES");
		 cerrarV.setOnAction(event -> stage.close());

	     matricula.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getMatricula()));
		 kmsActuales.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKmsactuales()));
		 categoria.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getCategoria()));
		 List<Sucursal> listasucursa=new ArrayList<Sucursal>();

		 Map<Integer, Sucursal> mapSucursales = AlquilerVehiculos.getInstance().getSucursales();
		 for (Integer key : mapSucursales.keySet()) {
			 listasucursa.add(mapSucursales.get(key));
		 }
		 this.sucursalV.getItems().addAll(listasucursa);

		 //Listener
		 	sucursalV.getSelectionModel().selectedItemProperty().addListener((observable,oldvalue,newvalues)->{
			 tablaVehiculo.getItems().clear();
			 tablaVehiculo.getItems().addAll(AlquilerVehiculos.getInstance().listaCochesDisponibles((newvalues.getId())));
			});

	 }
	}