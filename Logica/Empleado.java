package Logica;

import java.util.ArrayList;
import java.util.List;

public class Empleado {

   private String dni;
   private String nombre;
   private boolean administrador;
   private List<Entrega> listaEntregas;
   private List<Devolucion> listaDevolucion;
   private Sucursal sucursal;

   /*Constructor */
   public Empleado(String dni, String nombre, boolean administrador, Sucursal sucursal) {
	    this.dni = dni;
		this.nombre = nombre;
		this.administrador = administrador;
		this.sucursal = sucursal;
		this.listaEntregas = new ArrayList<Entrega>();
		this.listaDevolucion = new ArrayList<Devolucion>();
	}
   /* Getter & setter */
   public String getDni() {
	   return this.dni;
   }
   public void setDni(String dni) {
	   this.dni = dni;
   }
   
	public List<Entrega> getListaEntregas() {
		return listaEntregas;
	}
	
	public void setListaEntregas(List<Entrega> listaEntregas) {
		this.listaEntregas = listaEntregas;
	}
	
	public List<Devolucion> getListaDevolucion() {
		return listaDevolucion;
	}
	
	public void setListaDevolucion(List<Devolucion> listaDevolucion) {
		this.listaDevolucion = listaDevolucion;
	}
	
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public String getNombre() {
	    return nombre;
	}
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}
	public void setAdministrador(boolean administrador) {
	    this.administrador = administrador;
	}
	public boolean isAdministrador() {
	    return administrador;
	}

/* Modificadores de listas */

    public boolean addListaDevolucion(Devolucion d) {
        return listaDevolucion.add(d);
    }
    public Devolucion removeDevolucion(int indice) {
        return listaDevolucion.remove(indice);
     }
    public boolean removeDevolucion(Devolucion d) {
        return listaDevolucion.remove(d);
     }
    public Devolucion getDevolucion(int indice) {
    	return listaDevolucion.get(indice);
    }

    public boolean addlistaEntrega(Entrega e) {
        return listaEntregas.add(e);
    }
    public boolean removeEntrega(Entrega e) {
        return listaEntregas.remove(e);
    }
    public Entrega removeEntrega(int indice) {
       return listaEntregas.remove(indice);
    }
    public Entrega getEntrega(int indice) {
    	return listaEntregas.get(indice);
    }
}