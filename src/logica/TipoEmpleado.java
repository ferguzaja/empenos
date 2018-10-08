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
public class TipoEmpleado {
    private int idTipoEmpleado;
    private String nombre;

    public TipoEmpleado(int idTipoEmpleado, String nombre) {
        this.idTipoEmpleado = idTipoEmpleado;
        this.nombre = nombre;
    }

    public int getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(int idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
