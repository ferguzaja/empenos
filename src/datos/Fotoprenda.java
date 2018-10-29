/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "fotoprenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotoprenda.findAll", query = "SELECT f FROM Fotoprenda f")
    , @NamedQuery(name = "Fotoprenda.findByIdfotoPrenda", query = "SELECT f FROM Fotoprenda f WHERE f.idfotoPrenda = :idfotoPrenda")
    , @NamedQuery(name = "Fotoprenda.findByFechaHora", query = "SELECT f FROM Fotoprenda f WHERE f.fechaHora = :fechaHora")})
public class Fotoprenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfotoPrenda")
    private Integer idfotoPrenda;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "prenda_idprenda", referencedColumnName = "idprenda")
    @ManyToOne(optional = false)
    private Prenda prendaIdprenda;

    public Fotoprenda() {
    }

    public Fotoprenda(Integer idfotoPrenda) {
        this.idfotoPrenda = idfotoPrenda;
    }

    public Integer getIdfotoPrenda() {
        return idfotoPrenda;
    }

    public void setIdfotoPrenda(Integer idfotoPrenda) {
        this.idfotoPrenda = idfotoPrenda;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Prenda getPrendaIdprenda() {
        return prendaIdprenda;
    }

    public void setPrendaIdprenda(Prenda prendaIdprenda) {
        this.prendaIdprenda = prendaIdprenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfotoPrenda != null ? idfotoPrenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotoprenda)) {
            return false;
        }
        Fotoprenda other = (Fotoprenda) object;
        if ((this.idfotoPrenda == null && other.idfotoPrenda != null) || (this.idfotoPrenda != null && !this.idfotoPrenda.equals(other.idfotoPrenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Fotoprenda[ idfotoPrenda=" + idfotoPrenda + " ]";
    }
    public List <Fotoprenda> buscaFotos(int prenda){
        FotoprendaJpaController foto = new FotoprendaJpaController();
        List <Fotoprenda> fotos = foto.findFotoprendaEntities();
         List<Fotoprenda> listafotos = new ArrayList<>();
        for (int i = 0; i < fotos.size(); i++) {
            if (fotos.get(i).getPrendaIdprenda().getIdprenda()==prenda) {
                listafotos.add(fotos.get(i));
            }
    }
        return listafotos;
    
}}
