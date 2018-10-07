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
public class Ocupacion {
    private int idOcupacion;
    private String nombre;

    public Ocupacion() {
    }

    public Ocupacion(int idOcupacion, String nombre) {
        this.idOcupacion = idOcupacion;
        this.nombre = nombre;
    }

    public int getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(int idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
        
}
