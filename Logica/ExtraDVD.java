package Logica;

public class ExtraDVD extends DecoradorReserva {

	public ExtraDVD(IReserva reservaADecorar) {
		super(reservaADecorar);
	}
	public double calcularCoste(){
		return super.calcularCoste() + 25;
	}
}
