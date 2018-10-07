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
@Table(name = "tipoprenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoprenda.findAll", query = "SELECT t FROM Tipoprenda t")
    , @NamedQuery(name = "Tipoprenda.findByIdtipoprenda", query = "SELECT t FROM Tipoprenda t WHERE t.idtipoprenda = :idtipoprenda")
    , @NamedQuery(name = "Tipoprenda.findByNombre", query = "SELECT t FROM Tipoprenda t WHERE t.nombre = :nombre")})
public class Tipoprenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipoprenda")
    private Integer idtipoprenda;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoprendaIdtipoprenda")
    private List<Prenda> prendaList;

    public Tipoprenda() {
    }

    public Tipoprenda(Integer idtipoprenda) {
        this.idtipoprenda = idtipoprenda;
    }

    public Integer getIdtipoprenda() {
        return idtipoprenda;
    }

    public void setIdtipoprenda(Integer idtipoprenda) {
        this.idtipoprenda = idtipoprenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Prenda> getPrendaList() {
        return prendaList;
    }

    public void setPrendaList(List<Prenda> prendaList) {
        this.prendaList = prendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoprenda != null ? idtipoprenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoprenda)) {
            return false;
        }
        Tipoprenda other = (Tipoprenda) object;
        if ((this.idtipoprenda == null && other.idtipoprenda != null) || (this.idtipoprenda != null && !this.idtipoprenda.equals(other.idtipoprenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Tipoprenda[ idtipoprenda=" + idtipoprenda + " ]";
    }
    
}
