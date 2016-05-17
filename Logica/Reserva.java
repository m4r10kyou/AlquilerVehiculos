package Logica;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reserva extends IReserva {



    /* Constructor */
    public Reserva(int id, LocalDateTime fechaRegogida, LocalDateTime fechaDevolucion,
    		String modalidadAlquiler, Categoria catergoria,
			int lugar_devolucion, int lugar_recogida, Cliente cliente) {
    	super(id, fechaRegogida, fechaDevolucion, modalidadAlquiler, catergoria,
    			lugar_devolucion, lugar_recogida, cliente);
	}

	@Override
	public double calcularCoste(){
		double diasAlquilado;
		diasAlquilado = ChronoUnit.DAYS.between(this.getFechaRegogida(), this.getFechaDevolucion());
		double precio = this.getCatergoria().getPrecioKmModKms() * diasAlquilado;
		return precio;
		}
	    

}
