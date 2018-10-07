/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "prenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prenda.findAll", query = "SELECT p FROM Prenda p")
    , @NamedQuery(name = "Prenda.findByIdprenda", query = "SELECT p FROM Prenda p WHERE p.idprenda = :idprenda")
    , @NamedQuery(name = "Prenda.findByDescripcion", query = "SELECT p FROM Prenda p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Prenda.findByMontoValuo", query = "SELECT p FROM Prenda p WHERE p.montoValuo = :montoValuo")
    , @NamedQuery(name = "Prenda.findByMontoPrestamo", query = "SELECT p FROM Prenda p WHERE p.montoPrestamo = :montoPrestamo")
    , @NamedQuery(name = "Prenda.findByEstadoEmpeno", query = "SELECT p FROM Prenda p WHERE p.estadoEmpeno = :estadoEmpeno")
    , @NamedQuery(name = "Prenda.findByComercializada", query = "SELECT p FROM Prenda p WHERE p.comercializada = :comercializada")})
public class Prenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprenda")
    private Integer idprenda;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "montoValuo")
    private float montoValuo;
    @Basic(optional = false)
    @Column(name = "montoPrestamo")
    private float montoPrestamo;
    @Column(name = "estadoEmpeno")
    private Short estadoEmpeno;
    @Column(name = "comercializada")
    private Short comercializada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prendaIdprenda")
    private List<Articuloventa> articuloventaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prendaIdprenda")
    private List<Fotoprenda> fotoprendaList;
    @JoinColumn(name = "empeno_idempeno", referencedColumnName = "idempeno")
    @ManyToOne(optional = false)
    private Empeno empenoIdempeno;
    @JoinColumn(name = "tipoprenda_idtipoprenda", referencedColumnName = "idtipoprenda")
    @ManyToOne(optional = false)
    private Tipoprenda tipoprendaIdtipoprenda;

    public Prenda() {
    }

    public Prenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    public Prenda(Integer idprenda, float montoValuo, float montoPrestamo) {
        this.idprenda = idprenda;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
    }

    public Integer getIdprenda() {
        return idprenda;
    }

    public void setIdprenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMontoValuo() {
        return montoValuo;
    }

    public void setMontoValuo(float montoValuo) {
        this.montoValuo = montoValuo;
    }

    public float getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(float montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public Short getEstadoEmpeno() {
        return estadoEmpeno;
    }

    public void setEstadoEmpeno(Short estadoEmpeno) {
        this.estadoEmpeno = estadoEmpeno;
    }

    public Short getComercializada() {
        return comercializada;
    }

    public void setComercializada(Short comercializada) {
        this.comercializada = comercializada;
    }

    @XmlTransient
    public List<Articuloventa> getArticuloventaList() {
        return articuloventaList;
    }

    public void setArticuloventaList(List<Articuloventa> articuloventaList) {
        this.articuloventaList = articuloventaList;
    }

    @XmlTransient
    public List<Fotoprenda> getFotoprendaList() {
        return fotoprendaList;
    }

    public void setFotoprendaList(List<Fotoprenda> fotoprendaList) {
        this.fotoprendaList = fotoprendaList;
    }

    public Empeno getEmpenoIdempeno() {
        return empenoIdempeno;
    }

    public void setEmpenoIdempeno(Empeno empenoIdempeno) {
        this.empenoIdempeno = empenoIdempeno;
    }

    public Tipoprenda getTipoprendaIdtipoprenda() {
        return tipoprendaIdtipoprenda;
    }

    public void setTipoprendaIdtipoprenda(Tipoprenda tipoprendaIdtipoprenda) {
        this.tipoprendaIdtipoprenda = tipoprendaIdtipoprenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprenda != null ? idprenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prenda)) {
            return false;
        }
        Prenda other = (Prenda) object;
        if ((this.idprenda == null && other.idprenda != null) || (this.idprenda != null && !this.idprenda.equals(other.idprenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Prenda[ idprenda=" + idprenda + " ]";
    }
    
}
