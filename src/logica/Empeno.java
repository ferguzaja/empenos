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
    private String cotitular;
    private int numExtencionTiempo;
    private Date fechaExtencion;
    private Date fechaFinExtencion;
    private int numBolsa;
    private double montoRecibido;
    private Cliente cliente;
    private Date fechaFinalizacion;
    private String tipoFinalizacion;

    public Empeno() {
    }

    public Empeno(int idEmpeno, Date fechaInicio, Date fechaFinEmpeno, int idEmpleado, String cotitular, int numExtencionTiempo, Date fechaExtencion, Date fechaFinExtencion, int numBolsa, double monto, Cliente cliente, Date fechaFinalizacion, String tipoFinalizacion) {
        this.idEmpeno = idEmpeno;
        this.fechaInicio = fechaInicio;
        this.fechaFinEmpeno = fechaFinEmpeno;
        this.idEmpleado = idEmpleado;
        this.cotitular = cotitular;
        this.numExtencionTiempo = numExtencionTiempo;
        this.fechaExtencion = fechaExtencion;
        this.fechaFinExtencion = fechaFinExtencion;
        this.numBolsa = numBolsa;
        this.montoRecibido = monto;
        this.cliente = cliente;
        this.fechaFinalizacion = fechaFinalizacion;
        this.tipoFinalizacion = tipoFinalizacion;
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

    public String getCotitular() {
        return cotitular;
    }

    public void setCotitular(String cotitular) {
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

    public int getNumBolsa() {
        return numBolsa;
    }

    public void setNumBolsa(int numBolsa) {
        this.numBolsa = numBolsa;
    }

    public double getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(double monto) {
        this.montoRecibido = monto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getTipoFinalizacion() {
        return tipoFinalizacion;
    }

    public void setTipoFinalizacion(String tipoFinalizacion) {
        this.tipoFinalizacion = tipoFinalizacion;
    }

    
    
    
}
