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
public class Venta {
    private int idVenta;
    private Date fechaHora;
    private Empleado empleado;
    private double gananciaTotal;
    private Cliente cliente;
    private SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String txtfecha;

    public Venta() {
    }

    public Venta(int idVenta, Date fechaHora, Empleado empleado, double gananciaTotal, Cliente cliente) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.empleado = empleado;
        this.gananciaTotal = gananciaTotal;
        this.cliente = cliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
