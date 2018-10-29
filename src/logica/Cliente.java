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
    private Pais pais;
    private Estado estado;
    private Ciudad ciudad;
    private Ocupacion ocupacion;
    private TipoIdentificacion tipoIden;
    
    

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String noIdentificacion, boolean listaNegra, String fechaNacimiento, Pais pais, Estado estado, Ciudad ciudad, Ocupacion ocupacion, TipoIdentificacion tipoIden) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
        this.listaNegra = listaNegra;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.estado = estado;
        this.ciudad = ciudad;
        this.ocupacion = ocupacion;
        this.tipoIden = tipoIden;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public TipoIdentificacion getTipoIden() {
        return tipoIden;
    }

    public void setTipoIden(TipoIdentificacion tipoIden) {
        this.tipoIden = tipoIden;
    }
    @Override
    public String toString(){
        return getNombre()+" "+getApellidoPaterno()+" "+getApellidoMaterno();
    }
    
}
