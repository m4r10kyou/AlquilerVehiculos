package Logica;
import java.time.LocalDate;
import java.util.List;

public class Devolucion {
    private LocalDate fechadev;
	private int totalACobrar;
    private int cobrado;
    private List<Damage> damages;
    private Empleado empleado;
    private Entrega entrega;
	private int kms;
    private int combustible;
    
    public Devolucion(LocalDate fechadev, int totalACobrar, int cobrado,
    		Empleado empleado, Entrega entrega, int kms, int combustible) {
		this.fechadev = fechadev;
		this.totalACobrar = totalACobrar;
		this.cobrado = cobrado;
		this.empleado = empleado;
		this.entrega = entrega;
		this.kms = kms;
		this.combustible = combustible;
	}
    /* Getters y setters */
    public LocalDate getFechadev() {
        return fechadev;
    }

    public void setFechadev(LocalDate fechadev) {
        this.fechadev = fechadev;
    }

    public int getTotalACobrar() {
        return totalACobrar;
    }

    public void setTotalACobrar(int totalACobrar) {
        this.totalACobrar = totalACobrar;
    }

    public int getCobrado() {
        return cobrado;
    }

    public void setCobrado(int cobrado) {
        this.cobrado = cobrado;
    }

    public int getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }
    public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	
	/* Modificadores de listas */
	public Damage getDamage(int indice){
    	return damages.get(indice);
    }
    public void addDamage(Damage d){
    	damages.add(d);
    }
    public boolean removeDamage(Damage d){
    	return damages.remove(d);
    }
    public Damage removeDamage(int indice){
    	return damages.remove(indice);
    }

}
