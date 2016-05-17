package Logica;

public class ExtraSilla extends DecoradorReserva {

	public ExtraSilla(IReserva reservaADecorar) {
		super(reservaADecorar);
	}
	public double calcularCoste(){
		return super.calcularCoste() + 15;
	}
}
