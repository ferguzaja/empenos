/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "remate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remate.findAll", query = "SELECT r FROM Remate r")
    , @NamedQuery(name = "Remate.findByIdremate", query = "SELECT r FROM Remate r WHERE r.idremate = :idremate")
    , @NamedQuery(name = "Remate.findByFechaHora", query = "SELECT r FROM Remate r WHERE r.fechaHora = :fechaHora")
    , @NamedQuery(name = "Remate.findByPerdida", query = "SELECT r FROM Remate r WHERE r.perdida = :perdida")})
public class Remate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idremate")
    private Integer idremate;
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "perdida")
    private double perdida;
    @ManyToMany(mappedBy = "remateList")
    private List<Articuloventa> articuloventaList;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;
    @JoinColumn(name = "empleado_idempleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado empleadoIdempleado;

    public Remate() {
    }

    public Remate(Integer idremate) {
        this.idremate = idremate;
    }

    public Integer getIdremate() {
        return idremate;
    }

    public void setIdremate(Integer idremate) {
        this.idremate = idremate;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

    @XmlTransient
    public List<Articuloventa> getArticuloventaList() {
        return articuloventaList;
    }

    public void setArticuloventaList(List<Articuloventa> articuloventaList) {
        this.articuloventaList = articuloventaList;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    public Empleado getEmpleadoIdempleado() {
        return empleadoIdempleado;
    }

    public void setEmpleadoIdempleado(Empleado empleadoIdempleado) {
        this.empleadoIdempleado = empleadoIdempleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idremate != null ? idremate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remate)) {
            return false;
        }
        Remate other = (Remate) object;
        if ((this.idremate == null && other.idremate != null) || (this.idremate != null && !this.idremate.equals(other.idremate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Remate[ idremate=" + idremate + " ]";
    }
    public static datos.Remate deLogicaADatos(logica.Remate remate){
        datos.Remate nuevoRemate = new Remate();
        nuevoRemate.setEmpleadoIdempleado(datos.Empleado.recuperarEmpleado(remate.getEmpleado().getIdEmpleado()));
        if(remate.getCliente()!=null){
            nuevoRemate.setClienteIdcliente(datos.Cliente.recuperarCliente(remate.getCliente().getIdCliente()));
        }
        nuevoRemate.setFechaHora(remate.getFechaHora());
        nuevoRemate.setPerdida(remate.getPerdidaTotal());
        return nuevoRemate;
    }
     public static void guardarRemate(logica.Remate regresaRemate) {
         RemateJpaController remateJPA=new RemateJpaController();
         remateJPA.create(deLogicaADatos(regresaRemate));
     }
}
