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
 * @author ferguzaja
 */
public class Remate {
    private int idRemate;
    private Date fechaHora;
    private Empleado empleado;
    private double perdidaTotal;
    private Cliente cliente;
    private String txtfecha;
    private SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Remate() {
    }

    public Remate(int idVenta, Date fechaHora, Empleado empleado, double gananciaTotal, Cliente cliente) {
        this.idRemate = idVenta;
        this.fechaHora = fechaHora;
        this.empleado = empleado;
        this.perdidaTotal = gananciaTotal;
        this.cliente = cliente;
    }

    public int getIdRemate() {
        return idRemate;
    }

    public void setIdRemate(int idVenta) {
        this.idRemate = idVenta;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
        txtfecha=spd.format(fechaHora);
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public double getPerdidaTotal() {
        return perdidaTotal;
    }

    public void setPerdidaTotal(double gananciaTotal) {
        this.perdidaTotal = gananciaTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
