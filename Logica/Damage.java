package Logica;
import java.util.List;


public class Damage {

	private String zona;
    private String descripcion;
    private List<Entrega> entrega;
    private List<Devolucion> devolucion;

    /* Constructor */
    public Damage(String zona, String descripcion) {
		this.zona = zona;
		this.descripcion = descripcion;
	}
    
    /* Getters y Setters */
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /* Modificadores de listas */
    public boolean removeDevolucion(Devolucion o) {
        return devolucion.remove(o);
    }
    public Devolucion removeDevolucion(int index) {
    	return devolucion.remove(index);
    }
    public boolean addDevolucion(Devolucion o) {
        return devolucion.add(o);
    }
    public Devolucion getDevolucion(int index) {
    	return devolucion.get(index);
    }

    public boolean addEntrega(Entrega o) {
        return entrega.add(o);
    }
    public Entrega getEntrega(int index) {
    	return entrega.get(index);
    }
    public boolean removeEntrega(Object o) {
        return entrega.remove(o);
    }
    public Entrega removeEntrega(int index) {
    	return entrega.remove(index);
    }
}
