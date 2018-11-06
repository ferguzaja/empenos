/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;

/**
 *
 * @author ferguzaja
 */
public class Pago {
    private int noPeriodo;
    private double prestamo;
    private double interes;
    private double iva;
    private double refrendo;
    private double desempeño;
    private Date fecha;

    public Pago(int noPeriodo, double prestamo, double interes, double iva, double refrendo, double desempeño, Date fecha) {
        this.noPeriodo = noPeriodo;
        this.prestamo = prestamo;
        this.interes = interes;
        this.iva = iva;
        this.refrendo = refrendo;
        this.desempeño = desempeño;
        this.fecha = fecha;
    }

    public Pago() {
    }
    

    public int getNoPeriodo() {
        return noPeriodo;
    }

    public void setNoPeriodo(int noPeriodo) {
        this.noPeriodo = noPeriodo;
    }

    public double getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(double prestamo) {
        this.prestamo = prestamo;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getRefrendo() {
        return refrendo;
    }

    public void setRefrendo(double refrendo) {
        this.refrendo = refrendo;
    }

    public double getDesempeño() {
        return desempeño;
    }

    public void setDesempeño(double desempeño) {
        this.desempeño = desempeño;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String toString(){
        return noPeriodo+" "+prestamo;
    }
    
}
