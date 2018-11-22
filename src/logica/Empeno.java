/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jahir
 */
public class Empeno {
    private int idEmpeno;
    private Date fechaInicio;
    private Date fechaFinEmpeno;
    private Empleado empleado;
    private String cotitular;
    private int numExtencionTiempo;
    private Date fechaExtencion;
    private int numBolsa;
    private double montoRecibido;
    private Cliente cliente;
    private Date fechaFinalizacion;
    private String estatus;
    private SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
    private String txtfechaInicio;
    private String txtfechaFinEmpeno;
    private String txtfechaExtencion;
    private String txtfechaFinalizacion;
    public Empeno() {
    }

    public Empeno(int idEmpeno, Date fechaInicio, Date fechaFinEmpeno, Empleado idEmpleado, String cotitular, int numExtencionTiempo, Date fechaExtencion, Date fechaFinExtencion, int numBolsa, double monto, Cliente cliente, Date fechaFinalizacion, String tipoFinalizacion) {
        this.idEmpeno = idEmpeno;
        this.fechaInicio = fechaInicio;
        this.fechaFinEmpeno = fechaFinEmpeno;
        this.empleado = idEmpleado;
        this.cotitular = cotitular;
        this.numExtencionTiempo = numExtencionTiempo;
        this.fechaExtencion = fechaExtencion;
        this.numBolsa = numBolsa;
        this.montoRecibido = monto;
        this.cliente = cliente;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estatus = tipoFinalizacion;
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
        if(fechaInicio!=null)
        this.txtfechaInicio=sdf.format(fechaInicio);
    }

    public Date getFechaFinEmpeno() {
        return fechaFinEmpeno;
    }

    public void setFechaFinEmpeno(Date fechaFinEmpeno) {
        this.fechaFinEmpeno = fechaFinEmpeno;
        if(fechaFinEmpeno!=null)
        this.txtfechaFinEmpeno=sdf.format(fechaFinEmpeno);
    }

    public Empleado getIdEmpleado() {
        return empleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.empleado = idEmpleado;
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
        if(fechaExtencion!=null)
        this.txtfechaExtencion=sdf.format(fechaExtencion);
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
        if(fechaFinalizacion!=null)
        this.txtfechaFinalizacion=sdf.format(fechaFinalizacion);
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String tipoFinalizacion) {
        this.estatus = tipoFinalizacion;
    }

    public String getTxtfechaInicio() {
        return txtfechaInicio;
    }

    public void setTxtfechaInicio(String txtfechaInicio) {
        this.txtfechaInicio = txtfechaInicio;
    }

    public String getTxtfechaFinEmpeno() {
        return txtfechaFinEmpeno;
    }

    public void setTxtfechaFinEmpeno(String txtfechaFinEmpeno) {
        this.txtfechaFinEmpeno = txtfechaFinEmpeno;
    }

    public String getTxtfechaExtencion() {
        return txtfechaExtencion;
    }

    public void setTxtfechaExtencion(String txtfechaExtencion) {
        this.txtfechaExtencion = txtfechaExtencion;
    }

    public String getTxtfechaFinalizacion() {
        return txtfechaFinalizacion;
    }

    public void setTxtfechaFinalizacion(String txtfechaFinalizacion) {
        this.txtfechaFinalizacion = txtfechaFinalizacion;
    }
    
            
}
