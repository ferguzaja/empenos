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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "empeno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empeno.findAll", query = "SELECT e FROM Empeno e")
    , @NamedQuery(name = "Empeno.findByIdempeno", query = "SELECT e FROM Empeno e WHERE e.idempeno = :idempeno")
    , @NamedQuery(name = "Empeno.findByFechaInicioEmpeno", query = "SELECT e FROM Empeno e WHERE e.fechaInicioEmpeno = :fechaInicioEmpeno")
    , @NamedQuery(name = "Empeno.findByFechaFinEmpeno", query = "SELECT e FROM Empeno e WHERE e.fechaFinEmpeno = :fechaFinEmpeno")
    , @NamedQuery(name = "Empeno.findByCotitular", query = "SELECT e FROM Empeno e WHERE e.cotitular = :cotitular")
    , @NamedQuery(name = "Empeno.findByExtencionTiempo", query = "SELECT e FROM Empeno e WHERE e.extencionTiempo = :extencionTiempo")
    , @NamedQuery(name = "Empeno.findByFechaExtencion", query = "SELECT e FROM Empeno e WHERE e.fechaExtencion = :fechaExtencion")
    , @NamedQuery(name = "Empeno.findByFechaFinalizacion", query = "SELECT e FROM Empeno e WHERE e.fechaFinalizacion = :fechaFinalizacion")
    , @NamedQuery(name = "Empeno.findByTipofinalizacion", query = "SELECT e FROM Empeno e WHERE e.tipofinalizacion = :tipofinalizacion")
    , @NamedQuery(name = "Empeno.findByNoBolsa", query = "SELECT e FROM Empeno e WHERE e.noBolsa = :noBolsa")
    , @NamedQuery(name = "Empeno.findByMontoRecibido", query = "SELECT e FROM Empeno e WHERE e.montoRecibido = :montoRecibido")})
public class Empeno implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empenoIdempeno")
    private List<Pago> pagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empenoIdempeno")
    private List<Variblesempeno> variblesempenoList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempeno")
    private Integer idempeno;
    @Basic(optional = false)
    @Column(name = "fechaInicioEmpeno")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioEmpeno;
    @Basic(optional = false)
    @Column(name = "fechaFinEmpeno")
    @Temporal(TemporalType.DATE)
    private Date fechaFinEmpeno;
    @Column(name = "cotitular")
    private String cotitular;
    @Column(name = "extencionTiempo")
    private Integer extencionTiempo;
    @Column(name = "fechaExtencion")
    @Temporal(TemporalType.DATE)
    private Date fechaExtencion;
    @Column(name = "fechaFinalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;
    @Column(name = "tipofinalizacion")
    private String tipofinalizacion;
    @Column(name = "noBolsa")
    private Integer noBolsa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montoRecibido")
    private Float montoRecibido;
    @JoinColumn(name = "empleado_idEmpleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado empleadoidEmpleado;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;

    public Empeno() {
    }

    public Empeno(Integer idempeno) {
        this.idempeno = idempeno;
    }

    public Empeno(Integer idempeno, Date fechaInicioEmpeno, Date fechaFinEmpeno) {
        this.idempeno = idempeno;
        this.fechaInicioEmpeno = fechaInicioEmpeno;
        this.fechaFinEmpeno = fechaFinEmpeno;
    }

    public Integer getIdempeno() {
        return idempeno;
    }

    public void setIdempeno(Integer idempeno) {
        this.idempeno = idempeno;
    }

    public Date getFechaInicioEmpeno() {
        return fechaInicioEmpeno;
    }

    public void setFechaInicioEmpeno(Date fechaInicioEmpeno) {
        this.fechaInicioEmpeno = fechaInicioEmpeno;
    }

    public Date getFechaFinEmpeno() {
        return fechaFinEmpeno;
    }

    public void setFechaFinEmpeno(Date fechaFinEmpeno) {
        this.fechaFinEmpeno = fechaFinEmpeno;
    }

    public String getCotitular() {
        return cotitular;
    }

    public void setCotitular(String cotitular) {
        this.cotitular = cotitular;
    }

    public Integer getExtencionTiempo() {
        return extencionTiempo;
    }

    public void setExtencionTiempo(Integer extencionTiempo) {
        this.extencionTiempo = extencionTiempo;
    }

    public Date getFechaExtencion() {
        return fechaExtencion;
    }

    public void setFechaExtencion(Date fechaExtencion) {
        this.fechaExtencion = fechaExtencion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getTipofinalizacion() {
        return tipofinalizacion;
    }

    public void setTipofinalizacion(String tipofinalizacion) {
        this.tipofinalizacion = tipofinalizacion;
    }

    public Integer getNoBolsa() {
        return noBolsa;
    }

    public void setNoBolsa(Integer noBolsa) {
        this.noBolsa = noBolsa;
    }

    public Float getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(Float montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public Empleado getEmpleadoidEmpleado() {
        return empleadoidEmpleado;
    }

    public void setEmpleadoidEmpleado(Empleado empleadoidEmpleado) {
        this.empleadoidEmpleado = empleadoidEmpleado;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempeno != null ? idempeno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empeno)) {
            return false;
        }
        Empeno other = (Empeno) object;
        if ((this.idempeno == null && other.idempeno != null) || (this.idempeno != null && !this.idempeno.equals(other.idempeno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Empeno[ idempeno=" + idempeno + " ]";
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @XmlTransient
    public List<Variblesempeno> getVariblesempenoList() {
        return variblesempenoList;
    }

    public void setVariblesempenoList(List<Variblesempeno> variblesempenoList) {
        this.variblesempenoList = variblesempenoList;
    }
    
}
