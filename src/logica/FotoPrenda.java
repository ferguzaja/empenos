/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author ferguzaja
 */
public class FotoPrenda {
 private int idFotoPrenda;
 private Image foto;
 private String nombre;
 private Date FechaHora;
 private Prenda prenda;

    public FotoPrenda(int idFotoPrenda, Image foto, Date FechaHora, Prenda prenda) {
        this.idFotoPrenda = idFotoPrenda;
        this.foto = foto;
        this.FechaHora = FechaHora;
        this.prenda = prenda;
    }

    public FotoPrenda() {
    }

    public FotoPrenda(Image foto, Date FechaHora, Prenda prenda) {
        this.foto = foto;
        this.FechaHora = FechaHora;
        this.prenda = prenda;
    }

    public int getIdFotoPrenda() {
        return idFotoPrenda;
    }

    public void setIdFotoPrenda(int idFotoPrenda) {
        this.idFotoPrenda = idFotoPrenda;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Date getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(Date FechaHora) {
        this.FechaHora = FechaHora;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return nombre;
    }
}
