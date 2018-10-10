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
public class Empeno {
    private int idEmpeno;
    private Date fechaInicio;
    private Date fechaFinEmpeno;
    private int idEmpleado;
    private int cotitular;
    private int numExtencionTiempo;
    private Date fechaExtencion;
    private Date fechaFinExtencion;
    private float iva;
    private float cat;
    private float interesMensual;
    private float porcentajeComercializacion;
    private float porcentajeMutuo;
    private int numBolsa;
    private float monto;
    private int idCliente;
    private String nombreCliente;
    private String nombreCotitular;

    public Empeno() {
    }

    public Empeno(int idEmpeno, Date fechaInicio, Date fechaFinEmpeno, int idEmpleado, int cotitular, int numExtencionTiempo, Date fechaExtencion, Date fechaFinExtencion, float iva, float cat, float interesMensual, float porcentajeComercializacion, float porcentajeMutuo, int numBolsa, float monto, int idCliente, String nombreCliente, String nombreCotitular) {
        this.idEmpeno = idEmpeno;
        this.fechaInicio = fechaInicio;
        this.fechaFinEmpeno = fechaFinEmpeno;
        this.idEmpleado = idEmpleado;
        this.cotitular = cotitular;
        this.numExtencionTiempo = numExtencionTiempo;
        this.fechaExtencion = fechaExtencion;
        this.fechaFinExtencion = fechaFinExtencion;
        this.iva = iva;
        this.cat = cat;
        this.interesMensual = interesMensual;
        this.porcentajeComercializacion = porcentajeComercializacion;
        this.porcentajeMutuo = porcentajeMutuo;
        this.numBolsa = numBolsa;
        this.monto = monto;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.nombreCotitular = nombreCotitular;
    }

    public int getIdEmpeno() {
        return idEmpeno;
    }

    public void setIdEmpeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinEmpeno() {
        return fechaFinEmpeno;
    }

    public void setFechaFinEmpeno(Date fechaFinEmpeno) {
        this.fechaFinEmpeno = fechaFinEmpeno;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getCotitular() {
        return cotitular;
    }

    public void setCotitular(int cotitular) {
        this.cotitular = cotitular;
    }

    public int getNumExtencionTiempo() {
        return numExtencionTiempo;
    }

    public void setNumExtencionTiempo(int numExtencionTiempo) {
        this.numExtencionTiempo = numExtencionTiempo;
    }

    public Date getFechaExtencion() {
        return fechaExtencion;
    }

    public void setFechaExtencion(Date fechaExtencion) {
        this.fechaExtencion = fechaExtencion;
    }

    public Date getFechaFinExtencion() {
        return fechaFinExtencion;
    }

    public void setFechaFinExtencion(Date fechaFinExtencion) {
        this.fechaFinExtencion = fechaFinExtencion;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getCat() {
        return cat;
    }

    public void setCat(float cat) {
        this.cat = cat;
    }

    public float getInteresMensual() {
        return interesMensual;
    }

    public void setInteresMensual(float interesMensual) {
        this.interesMensual = interesMensual;
    }

    public float getPorcentajeComercializacion() {
        return porcentajeComercializacion;
    }

    public void setPorcentajeComercializacion(float porcentajeComercializacion) {
        this.porcentajeComercializacion = porcentajeComercializacion;
    }

    public float getPorcentajeMutuo() {
        return porcentajeMutuo;
    }

    public void setPorcentajeMutuo(float porcentajeMutuo) {
        this.porcentajeMutuo = porcentajeMutuo;
    }

    public int getNumBolsa() {
        return numBolsa;
    }

    public void setNumBolsa(int numBolsa) {
        this.numBolsa = numBolsa;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCotitular() {
        return nombreCotitular;
    }

    public void setNombreCotitular(String nombreCotitular) {
        this.nombreCotitular = nombreCotitular;
    }
    
    
}
