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
@Table(name = "cotitular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotitular.findAll", query = "SELECT c FROM Cotitular c")
    , @NamedQuery(name = "Cotitular.findByIdcotitular", query = "SELECT c FROM Cotitular c WHERE c.idcotitular = :idcotitular")
    , @NamedQuery(name = "Cotitular.findByNombre", query = "SELECT c FROM Cotitular c WHERE c.nombre = :nombre")})
public class Cotitular implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcotitular")
    private Integer idcotitular;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotitularidCotitular")
    private List<Empeno> empenoList;

    public Cotitular() {
    }

    public Cotitular(Integer idcotitular) {
        this.idcotitular = idcotitular;
    }

    public Cotitular(Integer idcotitular, String nombre) {
        this.idcotitular = idcotitular;
        this.nombre = nombre;
    }

    public Integer getIdcotitular() {
        return idcotitular;
    }

    public void setIdcotitular(Integer idcotitular) {
        this.idcotitular = idcotitular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Empeno> getEmpenoList() {
        return empenoList;
    }

    public void setEmpenoList(List<Empeno> empenoList) {
        this.empenoList = empenoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcotitular != null ? idcotitular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotitular)) {
            return false;
        }
        Cotitular other = (Cotitular) object;
        if ((this.idcotitular == null && other.idcotitular != null) || (this.idcotitular != null && !this.idcotitular.equals(other.idcotitular))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.entidades.Cotitular[ idcotitular=" + idcotitular + " ]";
    }
    
}
