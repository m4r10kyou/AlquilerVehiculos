package Logica;

import java.time.LocalDateTime;

public abstract class DecoradorReserva extends IReserva {
	IReserva reserva;
	
	public DecoradorReserva(IReserva reservaADecorar) {
		super(reservaADecorar.getId(), reservaADecorar.getFechaRegogida(),
				reservaADecorar.getFechaDevolucion(), reservaADecorar.getModalidadAlquiler(),
				reservaADecorar.getCatergoria(),reservaADecorar.getLugar_devolucion(),
				reservaADecorar.getLugar_recogida(), reservaADecorar.getDNICliente());
		reserva = reservaADecorar;
	}
	
	@Override
	public double calcularCoste(){
		return reserva.calcularCoste();
	}

}
