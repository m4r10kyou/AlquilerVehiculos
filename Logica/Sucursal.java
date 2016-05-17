package Logica;
import java.util.*;

/**
 *
 */

/**
 * @author Jos�Luis
 *
 */
public class Sucursal {

	private int id;
	private String direccion;
	private AlquilerVehiculos alquilervehiculos;
	private	List<Coche> coches;
	private List<Empleado> empleados;
	private List<Reserva> lugares_recogida;
	private List<Reserva> lugares_devolucion;

	public Sucursal(int id, String direccion) {
		this.id = id;
		this.direccion = direccion;
		this.coches=new ArrayList<Coche>();
		this.empleados=new ArrayList<Empleado>();
		this.lugares_recogida=new ArrayList<Reserva>();
		this.lugares_devolucion=new ArrayList<Reserva>();
	}

	/* Getters and setters */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public AlquilerVehiculos getAlquilervehiculos() {
		return alquilervehiculos;
	}
	public void setAlquilervehiculos(AlquilerVehiculos alquilervehiculos) {
		this.alquilervehiculos = alquilervehiculos;
	}
	public List<Coche> getCoches() {
		return coches;
	}
	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public List<Reserva> getLugaresRecogida() {
		return lugares_recogida;
	}
	public void setLugaresRecogida(List<Reserva> lugares_recogida) {
		this.lugares_recogida = lugares_recogida;
	}
	public List<Reserva> getLugaresDevolucion() {
		return lugares_devolucion;
	}
	public void setLugaresDevolucion(List<Reserva> lugares_devolucion) {
		this.lugares_devolucion = lugares_devolucion;
	}

	/* Modificadores de listas */
	public void addCoche(Coche c) {
		coches.add(c);
	}
	public boolean removeCoche(Coche c) {
		return coches.remove(c);
	}
	public Coche removeCoche(int index) {
		return coches.remove(index);
	}
	public Coche getCoche(int index) {
		return coches.get(index);
	}

	public void addEmpleado(Empleado e) {
		empleados.add(e);
	}
	public boolean removeEmpleado(Empleado e) {
		return empleados.remove(e);
	}
	public Empleado removeEmpleado(int index) {
		return empleados.remove(index);
	}
	public Empleado getEmpleado(int index) {
		return empleados.get(index);
	}

	public void addLugarRecogida(Reserva r) {
		lugares_recogida.add(r);
	}
	public boolean removeLugarRecogida(Reserva r) {
		return lugares_recogida.remove(r);
	}
	public Reserva removeLugarRecogida(int index) {
		return lugares_recogida.remove(index);
	}
	public Reserva getLugarRecogida(int index) {
		return lugares_recogida.get(index);
	}

	public void addLugarDevolucion(Reserva r) {
		lugares_devolucion.add(r);
	}
	public boolean removeLugarDevolucion(Reserva r) {
		return lugares_devolucion.remove(r);
	}
	public Reserva removeLugarDevolucion(int index) {
		return lugares_devolucion.remove(index);
	}
	public Reserva getLugarDevolucion(int index) {
		return lugares_devolucion.get(index);
	}
	public String toString(){
		return direccion+"";

	}
}
