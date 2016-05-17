package Logica;

import java.time.LocalDateTime;
import java.util.List;


public class Entrega {

	private LocalDateTime fecha;
    private String tipoSeguro;
    private double kms;
    private double combustible;
    private Devolucion devolucion;
    private List<Damage> damages;
    private Coche coche;
    private Empleado empleado;

    /* Constructor */
    public Entrega(LocalDateTime fecha, String tipoSeguro, double kms, double combustible, Coche coche, Empleado empleado) {
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.empleado = empleado;
		this.coche = coche;
	}

    /* Getters y setters */
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public double getKms() {
		return kms;
	}
	public void setKms(double kms) {
		this.kms = kms;
	}
	public double getCombustible() {
		return combustible;
	}
	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}
	public Devolucion getDevolucion() {
		return devolucion;
	}
	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}
	public List<Damage> getDamages() {
		return damages;
	}
	public void setDamages(List<Damage> damages) {
		this.damages = damages;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche coche) {
		this.coche = coche;
	}

    /* Modificadores de listas */
	public void addDamage(Damage d) {
		damages.add(d);
	}
	public boolean removeDamage(Damage d) {
		return damages.remove(d);
	}
	public Damage removeDamage(int index) {
		return damages.remove(index);
	}
	public Damage getDamage(int index) {
		return damages.get(index);
	}
}
