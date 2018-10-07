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
@Table(name = "tipoidentificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoidentificacion.findAll", query = "SELECT t FROM Tipoidentificacion t")
    , @NamedQuery(name = "Tipoidentificacion.findByIdtipoidentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.idtipoidentificacion = :idtipoidentificacion")
    , @NamedQuery(name = "Tipoidentificacion.findByNombre", query = "SELECT t FROM Tipoidentificacion t WHERE t.nombre = :nombre")})
public class Tipoidentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipoidentificacion")
    private Integer idtipoidentificacion;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoidentificacionIdtipoidentificacion")
    private List<Cliente> clienteList;

    public Tipoidentificacion() {
    }

    public Tipoidentificacion(Integer idtipoidentificacion) {
        this.idtipoidentificacion = idtipoidentificacion;
    }

    public Integer getIdtipoidentificacion() {
        return idtipoidentificacion;
    }

    public void setIdtipoidentificacion(Integer idtipoidentificacion) {
        this.idtipoidentificacion = idtipoidentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoidentificacion != null ? idtipoidentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoidentificacion)) {
            return false;
        }
        Tipoidentificacion other = (Tipoidentificacion) object;
        if ((this.idtipoidentificacion == null && other.idtipoidentificacion != null) || (this.idtipoidentificacion != null && !this.idtipoidentificacion.equals(other.idtipoidentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Tipoidentificacion[ idtipoidentificacion=" + idtipoidentificacion + " ]";
    }
    
}
