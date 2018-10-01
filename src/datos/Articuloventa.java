/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "articuloventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articuloventa.findAll", query = "SELECT a FROM Articuloventa a")
    , @NamedQuery(name = "Articuloventa.findByIdarticuloventa", query = "SELECT a FROM Articuloventa a WHERE a.idarticuloventa = :idarticuloventa")
    , @NamedQuery(name = "Articuloventa.findByDescripcionArticulo", query = "SELECT a FROM Articuloventa a WHERE a.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "Articuloventa.findByPrecioVenta", query = "SELECT a FROM Articuloventa a WHERE a.precioVenta = :precioVenta")
    , @NamedQuery(name = "Articuloventa.findByEstado", query = "SELECT a FROM Articuloventa a WHERE a.estado = :estado")})
public class Articuloventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticuloventa")
    private Integer idarticuloventa;
    @Column(name = "descripcionArticulo")
    private String descripcionArticulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioVenta")
    private Float precioVenta;
    @Column(name = "estado")
    private Short estado;
    @JoinTable(name = "ventaarticuloventa", joinColumns = {
        @JoinColumn(name = "articuloventa_idarticuloventa", referencedColumnName = "idarticuloventa")}, inverseJoinColumns = {
        @JoinColumn(name = "venta_idventa", referencedColumnName = "idventa")})
    @ManyToMany
    private List<Venta> ventaList;
    @JoinTable(name = "articuloventaremate", joinColumns = {
        @JoinColumn(name = "articuloventa_idarticuloventa", referencedColumnName = "idarticuloventa")}, inverseJoinColumns = {
        @JoinColumn(name = "remate_idremate", referencedColumnName = "idremate")})
    @ManyToMany
    private List<Remate> remateList;
    @JoinColumn(name = "prenda_idprenda", referencedColumnName = "idprenda")
    @ManyToOne(optional = false)
    private Prenda prendaIdprenda;

    public Articuloventa() {
    }

    public Articuloventa(Integer idarticuloventa) {
        this.idarticuloventa = idarticuloventa;
    }

    public Integer getIdarticuloventa() {
        return idarticuloventa;
    }

    public void setIdarticuloventa(Integer idarticuloventa) {
        this.idarticuloventa = idarticuloventa;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Remate> getRemateList() {
        return remateList;
    }

    public void setRemateList(List<Remate> remateList) {
        this.remateList = remateList;
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
        hash += (idarticuloventa != null ? idarticuloventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articuloventa)) {
            return false;
        }
        Articuloventa other = (Articuloventa) object;
        if ((this.idarticuloventa == null && other.idarticuloventa != null) || (this.idarticuloventa != null && !this.idarticuloventa.equals(other.idarticuloventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.entidades.Articuloventa[ idarticuloventa=" + idarticuloventa + " ]";
    }
    
}
