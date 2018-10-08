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
public class TipoPrenda {
    private int idTipoPrenda;
    private String nombre;

    public TipoPrenda(int idTipoPrenda, String nombre) {
        this.idTipoPrenda = idTipoPrenda;
        this.nombre = nombre;
    }

    public int getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(int idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
}
