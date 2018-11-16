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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpago")
    private Integer idpago;
    @Column(name = "noPeriodo")
    private Integer noPeriodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prestamo")
    private double prestamo;
    @Column(name = "interes")
    private double interes;
    @Column(name = "iva")
    private double iva;
    @Column(name = "refrendo")
    private double refrendo;
    @Column(name = "desempeno")
    private double desempeno;
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

    public double getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(double prestamo) {
        this.prestamo = prestamo;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getRefrendo() {
        return refrendo;
    }

    public void setRefrendo(double refrendo) {
        this.refrendo = refrendo;
    }

    public double getDesempeno() {
        return desempeno;
    }

    public void setDesempeno(double desempeno) {
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
                pago.add(pagar);
            }
                
            }
        return pago;
    }
    public static void guardarPago(List<datos.Pago> listaPagos){
        PagoJpaController pagos = new PagoJpaController();
        for(int i=0; i<listaPagos.size(); i++){
            pagos.create(listaPagos.get(i));
        }
    }
    public static List<datos.Pago> regresaLista(Variblesempeno var, logica.Empeno emp){
        List<datos.Pago> pagos = new ArrayList<>();
        Pago pago = new Pago();
        Pago pago2 = new Pago();
        pago.setNoPeriodo(1);
        pago.setPrestamo(datos.Prenda.montoPagar(datos.Prenda.prendasPorContrato(emp.getIdEmpeno())));
        pago.setInteres((var.getInteresMensual() * pago.getPrestamo()) / 200);
        pago.setIva(pago.getInteres() * var.getIva() / 200);
        pago.setRefrendo(pago.getIva() + pago.getInteres());
        pago.setDesempeno(pago.refrendo + pago.prestamo);
        pago.setEmpenoIdempeno(datos.Empeno.clonar(emp));
        pago.setFechaPeriodo(utilerias.fechas.aumentaDias(emp.getFechaInicio(),15));

        pago2.noPeriodo = 2;
        pago2.prestamo=pago.prestamo;
        pago2.interes = pago.interes * 2;
        pago2.iva = pago.iva * 2;
        pago2.refrendo = pago.refrendo * 2;
        pago2.desempeno = pago2.refrendo + pago.prestamo;
        pago2.empenoIdempeno=datos.Empeno.clonar(emp);
        pago2.fechaPeriodo=utilerias.fechas.aumentaDias(emp.getFechaInicio(),30);           
        pagos.add(pago);
        pagos.add(pago2);
        return pagos;
    }
}
