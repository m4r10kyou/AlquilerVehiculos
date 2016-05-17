package presentacion.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import excepciones.*;


public class ControladorPrincipal {

	@FXML MenuItem salirfxID;
	@FXML MenuItem crearReservaClifxID;
	@FXML MenuItem crearClienteClifxID;
	@FXML MenuItem crearReservaEmpfxID;
	@FXML MenuItem listarReservasEmpfxID;
	@FXML MenuItem listarSucursalesAdmfxID;
	@FXML MenuItem listarVehiculosDispfxID;
	@FXML MenuItem entregarVehiculosfxID;

	private static final String CREAR_CLIENTE = "../vista/crearCliente.fxml";
	private static final String LISTAR_RESERVAS_SUCURSAL = "../vista/listadoReservasSucursal.fxml";
	private static final String LISTAR_SUCURSALES = "../vista/listadoSucursales.fxml";
	private static final String CREAR_RESERVA = "../vista/crearReserva.fxml";
	private static final String LISTAR_VEHICULOS_DISPONIBLES = "../vista/listarVehiculosDisponibles.fxml";
	private static final String LISTAR_ENTREGAS = "../vista/listadoReservas.fxml";


	//TODO a�adir constantes de tipo String para la vistas correspondientes a los
	//casos de uso Crear Reserva y Listar Reservas de una Sucursal
	private Stage primaryStage;
	//private ControladorPrincipal miControlador;

	@FXML
	void listarSucursales(ActionEvent event) throws LogicaExcepcion {
		initCasoDeUso(LISTAR_SUCURSALES, ControladorListarSucursales.class).show();
	}

	@FXML
	void crearCliente(ActionEvent event) throws LogicaExcepcion {
		initCasoDeUso(CREAR_CLIENTE, ControladorCrearCliente.class).show();
	}

	@FXML
	void crearReserva(ActionEvent event) {
		initCasoDeUso(CREAR_RESERVA, ControladorCrearReserva.class).show();
	}

	@FXML
	void listadoReservasSucursal(ActionEvent event) throws LogicaExcepcion {
		initCasoDeUso(LISTAR_RESERVAS_SUCURSAL, ControladorListadoReservasSucursal.class).show();
	}
	@FXML
	void listarVehiculosDisponibles(ActionEvent event) throws LogicaExcepcion {
		initCasoDeUso(LISTAR_VEHICULOS_DISPONIBLES, ControladorListadoVehiculosDisponibles.class).show();
	}
	@FXML
	void listadorReservas(ActionEvent event) throws LogicaExcepcion {
		initCasoDeUso(LISTAR_ENTREGAS, ControladorListadoReservas.class).show();
	}

	@FXML
	void salir(ActionEvent event) {
		Platform.exit();
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private <T extends ControladorCasoDeUso> T initCasoDeUso(String urlVista, Class<T> controlClass) {
		return ControladorCasoDeUso.initCasoDeUso(urlVista, controlClass, primaryStage, ControladorPrincipal.this);
	}
}

