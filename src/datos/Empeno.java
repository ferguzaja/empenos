/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    , @NamedQuery(name = "Empeno.findByEstatus", query = "SELECT e FROM Empeno e WHERE e.estatus = :estatus")
    , @NamedQuery(name = "Empeno.findByNoBolsa", query = "SELECT e FROM Empeno e WHERE e.noBolsa = :noBolsa")
    , @NamedQuery(name = "Empeno.findByMontoRecibido", query = "SELECT e FROM Empeno e WHERE e.montoRecibido = :montoRecibido")})
public class Empeno implements Serializable {

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
    @Column(name = "estatus")
    private String estatus;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empenoIdempeno")
    private List<Pago> pagoList;

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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
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
    
    public static void guardarEmpeno(logica.Empeno emp) {
        datos.Empeno empeno = new datos.Empeno();
        empeno.setFechaInicioEmpeno(emp.getFechaInicio());
        empeno.setFechaFinEmpeno(emp.getFechaFinEmpeno());
        empeno.setEmpleadoidEmpleado(datos.Empleado.recuperarEmpleado(emp.getIdEmpleado().getIdEmpleado()));
        empeno.setClienteIdcliente(datos.Cliente.recuperarCliente(emp.getCliente().getIdCliente()));
        //revisar si hay que validar contra nulo!empeno.getCotitular().isEmpty
        if (empeno.getCotitular()!=null) {
            empeno.setCotitular(emp.getCotitular());
        }
        empeno.setEstatus(emp.getEstatus());
        empeno.setExtencionTiempo(emp.getNumExtencionTiempo());
        empeno.setNoBolsa(emp.getNumBolsa());
        EmpenoJpaController empenoJPA = new EmpenoJpaController();
        empenoJPA.create(empeno);
    }

    public static void actualizarEmpeno(logica.Empeno emp) {
        try {
            datos.Empeno empeno = new datos.Empeno();
            empeno.setIdempeno(emp.getIdEmpeno());
            empeno.setFechaInicioEmpeno(emp.getFechaInicio());
            empeno.setFechaFinEmpeno(emp.getFechaFinEmpeno());
            empeno.setEmpleadoidEmpleado(datos.Empleado.recuperarEmpleado(emp.getIdEmpleado().getIdEmpleado()));
            empeno.setClienteIdcliente(datos.Cliente.recuperarCliente(emp.getCliente().getIdCliente()));
            if (empeno.getCotitular()!=null) {
                empeno.setCotitular(emp.getCotitular());
            }            
            empeno.setNoBolsa(emp.getNumBolsa());
            empeno.setExtencionTiempo(emp.getNumExtencionTiempo());
            empeno.setEstatus(emp.getEstatus());
            EmpenoJpaController empenoJPA = new EmpenoJpaController();
            
            empenoJPA.edit(empeno);
        } catch (Exception ex) {
            Logger.getLogger(Empeno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void finiquitarContrato(logica.Empeno emp) {
        try {
            datos.Empeno empeno = new datos.Empeno();
            empeno.setIdempeno(emp.getIdEmpeno());
            empeno.setFechaInicioEmpeno(emp.getFechaInicio());
            empeno.setFechaFinEmpeno(emp.getFechaFinEmpeno());
            empeno.setEmpleadoidEmpleado(datos.Empleado.recuperarEmpleado(emp.getIdEmpleado().getIdEmpleado()));
            empeno.setCotitular(emp.getCotitular());
            empeno.setExtencionTiempo(emp.getNumExtencionTiempo());
            empeno.setFechaExtencion(emp.getFechaExtencion());
            empeno.setFechaFinalizacion(emp.getFechaFinalizacion());
            empeno.setMontoRecibido(Float.parseFloat(String.valueOf(emp.getMontoRecibido())));
            empeno.setNoBolsa(emp.getNumBolsa());
            empeno.setEstatus(emp.getEstatus());
            empeno.setClienteIdcliente(datos.Cliente.recuperarCliente(emp.getCliente().getIdCliente()));
            EmpenoJpaController empenoJPA = new EmpenoJpaController();
            empenoJPA.edit(empeno);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Empeno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Empeno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Empeno clonar(logica.Empeno empeno) {
        datos.Empeno emp = new Empeno();
        emp.setIdempeno(empeno.getIdEmpeno());
        return emp;
    }

    public static logica.Empeno recuperaID() {
        EmpenoJpaController empenoJPA = new EmpenoJpaController();
        List<datos.Empeno> empenos = empenoJPA.findEmpenoEntities();
        logica.Empeno empeno = new logica.Empeno();
        empeno.setIdEmpeno(empenos.get(empenos.size() - 1).getIdempeno());
        return empeno;
    }
}
