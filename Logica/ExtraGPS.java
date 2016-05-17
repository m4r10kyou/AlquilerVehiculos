package Logica;

public class ExtraGPS extends DecoradorReserva {

	public ExtraGPS(IReserva reservaADecorar) {
		super(reservaADecorar);
	}
	public double calcularCoste(){
		return super.calcularCoste() + 20;
	}
	
}
