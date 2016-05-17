package Logica;
import java.util.List;


public class Categoria {
	private String id;
	private double precioModIlimitada;
	private double precioModKms;
	private double precioKmModKms;
	private double precioSeguroTRiesgo;
	private double precioSeguroTerceros;
	private List<Reserva> reservas;
	private List<Coche> coches;
	private Categoria categoriasuperior;

	/* Constructor */
 	Categoria (String id, double precioModIlimitada, double precioModKms, double precioKmModKms,
 			double precioSeguroTRiesgo, double precioSeguroTerceros){
 		this.id=id;
 		this.precioModIlimitada=precioModIlimitada;
 		this.precioModKms=precioModKms;
 		this.precioKmModKms=precioKmModKms;
 		this.precioSeguroTRiesgo=precioSeguroTRiesgo;
 		this.precioSeguroTerceros=precioSeguroTerceros;
 	}

	/* Getters y setters */
	public Categoria getCategoriaSuperior() {
		return categoriasuperior;
	}

	public void setCategoriaSuperior(Categoria categoriasuperior) {
		this.categoriasuperior = categoriasuperior;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrecioModIlimitada() {
		return precioModIlimitada;
	}
	public void setPrecioModIlimitada(double precioModIlimitada) {
		this.precioModIlimitada = precioModIlimitada;
	}
	public double getPrecioModKms() {
		return precioModKms;
	}
	public void setPrecioModKms(double precioModKms) {
		this.precioModKms = precioModKms;
	}
	public double getPrecioKmModKms() {
		return precioKmModKms;
	}
	public void setPrecioKmModKms(double precioKmModKms) {
		this.precioKmModKms = precioKmModKms;
	}
	public double getPrecioSeguroTRiesgo() {
		return precioSeguroTRiesgo;
	}
	public void setPrecioSeguroTRiesgo(double precioSeguroTRiesgo) {
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
	}
	public double getPrecioSeguroTerceros() {
		return precioSeguroTerceros;
	}
	public void setPrecioSeguroTerceros(double precioSeguroTerceros) {
		this.precioSeguroTerceros = precioSeguroTerceros;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public List<Coche> getCoches() {
		return coches;
	}
	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	/* Modificadores de listas */
	public void addReserva(Reserva r) {
		reservas.add(r);
	}
	public boolean removeReserva(Reserva r) {
		return reservas.remove(r);
	}
	public Reserva removeReserva(int index) {
		return reservas.remove(index);
	}
	public Reserva getReserva(int index) {
		return reservas.get(index);
	}

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
	public String toString(){
		return id;
	}
}
