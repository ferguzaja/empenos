/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Jahir
 */
public class Variable {
    private int idVariable;
    private float iva;
    private float cat;
    private float intereMensual;
    private float porcentajeComercializacion;
    private float porcentajeMutuo;

    public Variable() {
    }

    public Variable(int idVariable, float iva, float cat, float intereMensual, float porcentajeComercializacion, float porcentajeMutuo) {
        this.idVariable = idVariable;
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

    public float getIntereMensual() {
        return intereMensual;
    }

    public void setIntereMensual(float intereMensual) {
        this.intereMensual = intereMensual;
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
    
    
}
