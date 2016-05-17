package persistencia.dto;

public class CocheDTO {
	private String matricula;
	private Double ksmActuales;
	private int sucursal;
	private String categoria;
	private String nombre;

	//Constructor
	public CocheDTO(String matricula, Double ksmActuales, int sucursal, String categoria, String nombre) {
		this.matricula = matricula;
		this.ksmActuales = ksmActuales;
		this.sucursal = sucursal;
		this.categoria = categoria;
		this.nombre = nombre;
	}


	//Getters & Setters
	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Double getKsmActuales() {
		return ksmActuales;
	}


	public void setKsmActuales(Double ksmActuales) {
		this.ksmActuales = ksmActuales;
	}


	public int getSucursal() {
		return sucursal;
	}


	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
