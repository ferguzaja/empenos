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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
    , @NamedQuery(name = "Pago.findByIdpago", query = "SELECT p FROM Pago p WHERE p.idpago = :idpago")
    , @NamedQuery(name = "Pago.findByNoPeriodo", query = "SELECT p FROM Pago p WHERE p.noPeriodo = :noPeriodo")
    , @NamedQuery(name = "Pago.findByPrestamo", query = "SELECT p FROM Pago p WHERE p.prestamo = :prestamo")
    , @NamedQuery(name = "Pago.findByInteres", query = "SELECT p FROM Pago p WHERE p.interes = :interes")
    , @NamedQuery(name = "Pago.findByIva", query = "SELECT p FROM Pago p WHERE p.iva = :iva")
    , @NamedQuery(name = "Pago.findByRefrendo", query = "SELECT p FROM Pago p WHERE p.refrendo = :refrendo")
    , @NamedQuery(name = "Pago.findByDesempeno", query = "SELECT p FROM Pago p WHERE p.desempeno = :desempeno")
    , @NamedQuery(name = "Pago.findByFechaPeriodo", query = "SELECT p FROM Pago p WHERE p.fechaPeriodo = :fechaPeriodo")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpago")
    private Integer idpago;
    @Column(name = "noPeriodo")
    private Integer noPeriodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prestamo")
    private Float prestamo;
    @Column(name = "interes")
    private Float interes;
    @Column(name = "iva")
    private Float iva;
    @Column(name = "refrendo")
    private Float refrendo;
    @Column(name = "desempeno")
    private Float desempeno;
    @Column(name = "fechaPeriodo")
    @Temporal(TemporalType.DATE)
    private Date fechaPeriodo;
    @JoinColumn(name = "empeno_idempeno", referencedColumnName = "idempeno")
    @ManyToOne(optional = false)
    private Empeno empenoIdempeno;

    public Pago() {
    }

    public Pago(Integer idpago) {
        this.idpago = idpago;
    }

    public Integer getIdpago() {
        return idpago;
    }

    public void setIdpago(Integer idpago) {
        this.idpago = idpago;
    }

    public Integer getNoPeriodo() {
        return noPeriodo;
    }

    public void setNoPeriodo(Integer noPeriodo) {
        this.noPeriodo = noPeriodo;
    }

    public Float getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Float prestamo) {
        this.prestamo = prestamo;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getRefrendo() {
        return refrendo;
    }

    public void setRefrendo(Float refrendo) {
        this.refrendo = refrendo;
    }

    public Float getDesempeno() {
        return desempeno;
    }

    public void setDesempeno(Float desempeno) {
        this.desempeno = desempeno;
    }

    public Date getFechaPeriodo() {
        return fechaPeriodo;
    }

    public void setFechaPeriodo(Date fechaPeriodo) {
        this.fechaPeriodo = fechaPeriodo;
    }

    public Empeno getEmpenoIdempeno() {
        return empenoIdempeno;
    }

    public void setEmpenoIdempeno(Empeno empenoIdempeno) {
        this.empenoIdempeno = empenoIdempeno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpago != null ? idpago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idpago == null && other.idpago != null) || (this.idpago != null && !this.idpago.equals(other.idpago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Pago[ idpago=" + idpago + " ]";
    }
    public static List<logica.Pago> regresaPagos(int empeno){
        PagoJpaController pagoJPA = new PagoJpaController();
        List<datos.Pago> pagos = pagoJPA.findPagoEntities();

        List<logica.Pago> pago = new ArrayList<>();
        for (int i = 0; i < pagos.size(); i++) {
            if (pagos.get(i).getEmpenoIdempeno().getIdempeno()==empeno) {
                logica.Pago pagar= new logica.Pago();
                pagar.setDesempeño(pagos.get(i).getDesempeno());
                pagar.setFecha(pagos.get(i).getFechaPeriodo());
                pagar.setInteres(pagos.get(i).getInteres());
                pagar.setIva(pagos.get(i).getIva());
                pagar.setNoPeriodo(pagos.get(i).getNoPeriodo());
                pagar.setPrestamo(pagos.get(i).getPrestamo());
                pagar.setRefrendo(pagos.get(i).getRefrendo());
            }
                
            }
        return pago;
    }
    
}
