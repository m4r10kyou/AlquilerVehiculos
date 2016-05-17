package Logica;

public class Coche {

	private String matricula;
	private Double kmsactuales;
	private int sucursal;
	private String categoria;
	private Categoria nombre;

	//constructor
	public Coche(String matricula, Double kmsactuales, int sucursal, String categoria, Categoria nombre) {
		this.matricula = matricula;
		this.kmsactuales = kmsactuales;
		this.sucursal = sucursal;
		this.categoria = categoria;
		this.nombre = nombre;
	}

	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Double getKmsactuales() {
		return kmsactuales;
	}
	public void setKmsactuales(Double kmsactuales) {
		this.kmsactuales = kmsactuales;
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
	public Categoria getNombre() {
		return nombre;
	}
	public void setNombre(Categoria nombre) {
		this.nombre = nombre;
	}




}



