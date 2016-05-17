package Logica;

import java.time.LocalDateTime;

public abstract class IReserva {
	private LocalDateTime fechaRegogida, fechaDevolucion;
    private String modalidadAlquiler;
    private Categoria catergoria;
    private Entrega entrega;
    private int lugar_devolucion, lugar_recogida;
    private Cliente cliente;
    private int id;

    /* Constructor */
    public IReserva(int id, LocalDateTime fechaRegogida, LocalDateTime fechaDevolucion, String modalidadAlquiler, Categoria catergoria,
			int lugar_devolucion, int lugar_recogida, Cliente cliente) {
    	this.id = id;
		this.fechaRegogida = fechaRegogida;
		this.fechaDevolucion = fechaDevolucion;
		this.modalidadAlquiler = modalidadAlquiler;
		this.catergoria = catergoria;
		this.lugar_devolucion = lugar_devolucion;
		this.lugar_recogida = lugar_recogida;
		this.cliente = cliente;
	}

    /* Cliente */
    public String getNombreCliente(){
    	return cliente.getNombre()+cliente.getApellidos();
    }

    public String getDniCliente(){
    	return cliente.getDni();
    }

    /* Getter & Setter */

	public void setDnicliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categoria getCatergoria() {
		return catergoria;
	}

	public void setCatergoria(Categoria catergoria) {
		this.catergoria = catergoria;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public int getLugar_devolucion() {
		return lugar_devolucion;
	}

	public void setLugar_devolucion(int lugar_devolucion) {
		this.lugar_devolucion = lugar_devolucion;
	}

	public int getLugar_recogida() {
		return lugar_recogida;
	}

	public void setLugar_recogida(int lugar_recogida) {
		this.lugar_recogida = lugar_recogida;
	}

	public Cliente getDNICliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getFechaRegogida() {
        return fechaRegogida;
    }

    public void setFechaRegogida(LocalDateTime fechaRegogida) {
        this.fechaRegogida = fechaRegogida;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getModalidadAlquiler() {
        return modalidadAlquiler;
    }

    public void setModalidadAlquiler(String modalidadAlquiler) {
        this.modalidadAlquiler = modalidadAlquiler;
    }
    public abstract double calcularCoste();
}
