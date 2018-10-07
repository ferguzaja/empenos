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
public class TipoIdentificacion {
    
    private int idTipoIdentificacion;
    private String nombre;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(int idTipoIdentificacion, String nombre) {
        this.idTipoIdentificacion = idTipoIdentificacion;
        this.nombre = nombre;
    }

    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
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
