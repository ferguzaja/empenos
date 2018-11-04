/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author ferguzaja
 */
public class VariblesEmpeno {
    private int idVariable;
    private Empeno empeno;
    private double iva;
    private double cat;
    private double intereMensual;
    private double porcentajeComercializacion;
    private double porcentajeMutuo;

    public VariblesEmpeno() {
    }

    public VariblesEmpeno(int idVariable, Empeno empeno, double iva, double cat, double intereMensual, double porcentajeComercializacion, double porcentajeMutuo) {
        this.idVariable = idVariable;
        this.empeno = empeno;
        this.iva = iva;
        this.cat = cat;
        this.intereMensual = intereMensual;
        this.porcentajeComercializacion = porcentajeComercializacion;
        this.porcentajeMutuo = porcentajeMutuo;
    }
    

    public int getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(int idVariable) {
        this.idVariable = idVariable;
    }

    public Empeno getEmpeno() {
        return empeno;
    }

    public void setEmpeno(Empeno empeno) {
        this.empeno = empeno;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getCat() {
        return cat;
    }

    public void setCat(double cat) {
        this.cat = cat;
    }

    public double getIntereMensual() {
        return intereMensual;
    }

    public void setIntereMensual(double intereMensual) {
        this.intereMensual = intereMensual;
    }

    public double getPorcentajeComercializacion() {
        return porcentajeComercializacion;
    }

    public void setPorcentajeComercializacion(double porcentajeComercializacion) {
        this.porcentajeComercializacion = porcentajeComercializacion;
    }

    public double getPorcentajeMutuo() {
        return porcentajeMutuo;
    }

    public void setPorcentajeMutuo(double porcentajeMutuo) {
        this.porcentajeMutuo = porcentajeMutuo;
    }
    
}
