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
@Table(name = "tipoempleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoempleado.findAll", query = "SELECT t FROM Tipoempleado t")
    , @NamedQuery(name = "Tipoempleado.findByIdtipoempleado", query = "SELECT t FROM Tipoempleado t WHERE t.idtipoempleado = :idtipoempleado")
    , @NamedQuery(name = "Tipoempleado.findByNombre", query = "SELECT t FROM Tipoempleado t WHERE t.nombre = :nombre")})
public class Tipoempleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtipoempleado")
    private Integer idtipoempleado;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoempleadoIdtipoempleado")
    private List<Empleado> empleadoList;

    public Tipoempleado() {
    }

    public Tipoempleado(Integer idtipoempleado) {
        this.idtipoempleado = idtipoempleado;
    }

    public Integer getIdtipoempleado() {
        return idtipoempleado;
    }

    public void setIdtipoempleado(Integer idtipoempleado) {
        this.idtipoempleado = idtipoempleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoempleado != null ? idtipoempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoempleado)) {
            return false;
        }
        Tipoempleado other = (Tipoempleado) object;
        if ((this.idtipoempleado == null && other.idtipoempleado != null) || (this.idtipoempleado != null && !this.idtipoempleado.equals(other.idtipoempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Tipoempleado[ idtipoempleado=" + idtipoempleado + " ]";
    }
    public logica.TipoEmpleado clonar(){
        logica.TipoEmpleado tipo = new logica.TipoEmpleado(getIdtipoempleado(), getNombre());
        return  tipo;
    }
    
}
