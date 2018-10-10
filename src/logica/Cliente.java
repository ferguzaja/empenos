/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;

/**
 *
 * @author Jahir
 */
public class Cliente {
    
    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String noIdentificacion;
    private boolean listaNegra;
    private String fechaNacimiento;
    private int idCiudad;
    private int idPais;
    private int idEstado;
    
    private int idTipoIdentificacion;
    private int idOcupacion;
    
    
    

    public Cliente() {
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, 
            String direccion, String noIdentificacion, boolean listaNegra, 
            String fechaNacimiento, int idCiudad, int idTipoIdentificacion, int idOcupacion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
        this.listaNegra = listaNegra;
        this.fechaNacimiento = fechaNacimiento;
        this.idCiudad = idCiudad;
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.idOcupacion = idOcupacion;
    }

    public Cliente(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String noIdentificacion, boolean listaNegra, String fechaNacimiento, int idCiudad, int idPais, int idEstado, int idTipoIdentificacion, int idOcupacion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
        this.listaNegra = listaNegra;
        this.fechaNacimiento = fechaNacimiento;
        this.idCiudad = idCiudad;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.idOcupacion = idOcupacion;
    }
        

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String noIdentificacion) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
    }        

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public boolean isListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(boolean listaNegra) {
        this.listaNegra = listaNegra;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public int getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(int idOcupacion) {
        this.idOcupacion = idOcupacion;
    }
    
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
    
    
}
