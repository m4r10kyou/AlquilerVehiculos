package Logica;

import java.time.LocalDate;
import java.util.List;

public class Cliente {
    private String dni, nombre, digitosTC, apellidos, direccion, poblacion, tipoTC;
    private int cod_postal, mesTC, anyoTC, cvcTC;
    private LocalDate fechaCarnetConducir;
    private List<Reserva> listaReservas;

    /*Constructor*/
    public Cliente(String dni,String nombre, String apellidos,  String direccion,  String poblacion, String tipoTC,
    		int cod_postal, String digitosTC, int mesTC, int anyoTC, int cvcTC, LocalDate fechaCarnetConducir){
    	this.dni=dni;
    	this.nombre=nombre;
    	this.apellidos=apellidos;
    	this.direccion=direccion;
    	this.poblacion=poblacion;
    	this.tipoTC=tipoTC;
    	this.cod_postal = cod_postal;
    	this.digitosTC=digitosTC;
    	this.mesTC=mesTC;
    	this.anyoTC=anyoTC;
    	this.cvcTC=cvcTC;
    	this.fechaCarnetConducir=fechaCarnetConducir;

    }

    /* Getters y setters */
    public String getDni(){
    	return dni;
    }
    public void setDni(String dni){
    	 this.dni=dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getTipoTC() {
        return tipoTC;
    }

    public void setTipoTC(String tipoTC) {
        this.tipoTC = tipoTC;
    }

    public int getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(int cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getDigitosTC() {
        return digitosTC;
    }

    public void setDigitosTC(String digitosTC) {
        this.digitosTC = digitosTC;
    }

    public int getMesTC() {
        return mesTC;
    }

    public void setMesTC(int mesTC) {
        this.mesTC = mesTC;
    }

    public int getAnyoTC() {
        return anyoTC;
    }

    public void setAnyoTC(int anyoTC) {
        this.anyoTC = anyoTC;
    }

    public int getCvcTC() {
        return cvcTC;
    }

    public void setCvcTC(int cvcTC) {
        this.cvcTC = cvcTC;
    }

    public LocalDate getFechaCarnetConducir() {
        return fechaCarnetConducir;
    }

    public void setFechaCarnetConducir(LocalDate fechaCarnetConducir) {
        this.fechaCarnetConducir = fechaCarnetConducir;
    }

    /* Modificadores de listas */
    public boolean addReserva(Reserva reserva) {
        return listaReservas.add(reserva);
    }
    public boolean removeReserva(Reserva reserva) {
        return listaReservas.remove(reserva);
    }
    public Reserva removeReserva(int index) {
        return listaReservas.remove(index);
    }
    public Reserva getReserva(int index) {
 	   return listaReservas.get(index);
    }
}
