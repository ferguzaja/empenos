/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "prenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prenda.findAll", query = "SELECT p FROM Prenda p")
    , @NamedQuery(name = "Prenda.findByIdprenda", query = "SELECT p FROM Prenda p WHERE p.idprenda = :idprenda")
    , @NamedQuery(name = "Prenda.findByDescripcion", query = "SELECT p FROM Prenda p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Prenda.findByMontoValuo", query = "SELECT p FROM Prenda p WHERE p.montoValuo = :montoValuo")
    , @NamedQuery(name = "Prenda.findByMontoPrestamo", query = "SELECT p FROM Prenda p WHERE p.montoPrestamo = :montoPrestamo")
    , @NamedQuery(name = "Prenda.findByEstadoEmpeno", query = "SELECT p FROM Prenda p WHERE p.estadoEmpeno = :estadoEmpeno")
    , @NamedQuery(name = "Prenda.findByComercializada", query = "SELECT p FROM Prenda p WHERE p.comercializada = :comercializada")})
public class Prenda implements Serializable {

    @Basic(optional = false)
    @Column(name = "montoValuo")
    private double montoValuo;
    @Basic(optional = false)
    @Column(name = "montoPrestamo")
    private double montoPrestamo;
    @Column(name = "estadoEmpeno")
    private int estadoEmpeno;
    @Column(name = "comercializada")
    private Short comercializada;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprenda")
    private Integer idprenda;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prendaIdprenda")
    private List<Articuloventa> articuloventaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prendaIdprenda")
    private List<Fotoprenda> fotoprendaList;
    @JoinColumn(name = "empeno_idempeno", referencedColumnName = "idempeno")
    @ManyToOne(optional = false)
    private Empeno empenoIdempeno;
    @JoinColumn(name = "tipoprenda_idtipoprenda", referencedColumnName = "idtipoprenda")
    @ManyToOne(optional = false)
    private Tipoprenda tipoprendaIdtipoprenda;

    public Prenda() {
    }

    public Prenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    public Prenda(Integer idprenda, double montoValuo, double montoPrestamo) {
        this.idprenda = idprenda;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
    }

    public Integer getIdprenda() {
        return idprenda;
    }

    public void setIdprenda(Integer idprenda) {
        this.idprenda = idprenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    @XmlTransient
    public List<Articuloventa> getArticuloventaList() {
        return articuloventaList;
    }

    public void setArticuloventaList(List<Articuloventa> articuloventaList) {
        this.articuloventaList = articuloventaList;
    }

    @XmlTransient
    public List<Fotoprenda> getFotoprendaList() {
        return fotoprendaList;
    }

    public void setFotoprendaList(List<Fotoprenda> fotoprendaList) {
        this.fotoprendaList = fotoprendaList;
    }

    public Empeno getEmpenoIdempeno() {
        return empenoIdempeno;
    }

    public void setEmpenoIdempeno(Empeno empenoIdempeno) {
        this.empenoIdempeno = empenoIdempeno;
    }

    public Tipoprenda getTipoprendaIdtipoprenda() {
        return tipoprendaIdtipoprenda;
    }

    public void setTipoprendaIdtipoprenda(Tipoprenda tipoprendaIdtipoprenda) {
        this.tipoprendaIdtipoprenda = tipoprendaIdtipoprenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprenda != null ? idprenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prenda)) {
            return false;
        }
        Prenda other = (Prenda) object;
        if ((this.idprenda == null && other.idprenda != null) || (this.idprenda != null && !this.idprenda.equals(other.idprenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Prenda[ idprenda=" + idprenda + " ]";
    }

    public static List<logica.Prenda> prendasPorContrato(int noContrato) {
        PrendaJpaController prendaJPA = new PrendaJpaController();
        List<datos.Prenda> prendas = prendaJPA.findPrendaEntities();

        List<logica.Prenda> listaPrendas = new ArrayList<>();
        for (int i = 0; i < prendas.size(); i++) {
            if (prendas.get(i).getEmpenoIdempeno().getIdempeno()==noContrato) {
                logica.Prenda prenda = new logica.Prenda();
                prenda.setIdPrenda(prendas.get(i).getIdprenda());
                prenda.setDescripcion(prendas.get(i).getDescripcion());
                prenda.setMontoPrestamo(prendas.get(i).getMontoPrestamo());
                prenda.setMontoValuo(prendas.get(i).getMontoValuo());
                prenda.setTipoPrenda(prendas.get(i).getTipoprendaIdtipoprenda().clonar());
                prenda.setEmpeno(datos.Empeno.clonarDatosALogica(prendas.get(i).getEmpenoIdempeno()));
                
                listaPrendas.add(prenda);
            }
        }
        return listaPrendas;
    }
    
    public static boolean guardarPrendas(List<logica.Prenda> prendas){
        boolean guardar = true;
        try {
            PrendaJpaController prendaJPA = new PrendaJpaController();
            for (int i = 0; i < prendas.size(); i++) {
                datos.Prenda prenda = new Prenda();
                prenda.setDescripcion(prendas.get(i).getDescripcion());
                prenda.setMontoValuo(prendas.get(i).getMontoValuo());
                prenda.setMontoPrestamo(prendas.get(i).getMontoPrestamo());
                prenda.setEmpenoIdempeno(datos.Empeno.clonar(prendas.get(i).getEmpeno()));
                prenda.setTipoprendaIdtipoprenda(datos.Tipoprenda.recuperarTipoPrenda(prendas.get(i).getTipoPrenda().getIdTipoPrenda()));
                prendaJPA.create(prenda);
            }            
        } catch (Exception e) {
            guardar = false;
            e.printStackTrace();
        }
        return guardar;
    }
    public static double montoPagar(List<logica.Prenda> listaPrendas){
        double monto=0;
        for(int i=0; i<listaPrendas.size();i++){
            monto+=listaPrendas.get(i).getMontoPrestamo();
        }
        
        return monto;
    }
    public static void cambiarPrendasFiniquitadas(List<logica.Prenda> prendas){
        try {
            PrendaJpaController prendaJPA = new PrendaJpaController();
            for (int i = 0; i < prendas.size(); i++) {
                datos.Prenda prenda = deLogicaADatos(prendas.get(i));
                prenda.setEstadoEmpeno(1);//como lo mando? 
                prendaJPA.edit(prenda);
            }
        }catch(Exception e){
            
        }
    }
    public static void cambiarPrendasComercializadas(List<logica.Prenda> prendas){
        try {
            PrendaJpaController prendaJPA = new PrendaJpaController();
            for (int i = 0; i < prendas.size(); i++) {
                datos.Prenda prenda = deLogicaADatos(prendas.get(i));
                prenda.setEstadoEmpeno(1);
                prendaJPA.edit(prenda);
            }
        }catch(Exception e){
            
        }
    }
    public static void cambiarPrendasRefrendadas(List<logica.Prenda> prendas){
        try {
            PrendaJpaController prendaJPA = new PrendaJpaController();
            for (int i = 0; i < prendas.size(); i++) {
                datos.Prenda prenda = deLogicaADatos(prendas.get(i));
                prendaJPA.edit(prenda);
            }
        }catch(Exception e){
            
        }
    }
    public static Prenda deLogicaADatos(logica.Prenda prenda) {
        datos.Prenda prendaDatos = new Prenda();
        prendaDatos.setIdprenda(prenda.getIdPrenda());
        prendaDatos.setEmpenoIdempeno(datos.Empeno.clonar(prenda.getEmpeno()));
        prendaDatos.setDescripcion(prenda.getDescripcion());
        prendaDatos.setMontoValuo(prenda.getMontoValuo());
        Tipoprenda tipoPrenda = Tipoprenda.recuperarTipoPrenda(prenda.getTipoPrenda().getIdTipoPrenda());
        prendaDatos.setTipoprendaIdtipoprenda(tipoPrenda);
        return prendaDatos;
    }
    public static logica.Prenda clonar(datos.Prenda prendadatos) {
        logica.Prenda prenda= new logica.Prenda();
        prenda.setDescripcion(prendadatos.getDescripcion());
        prenda.setEmpeno(datos.Empeno.clonarDatosALogica(prendadatos.getEmpenoIdempeno()));
        prenda.setIdPrenda(prendadatos.getIdprenda());
        prenda.setMontoPrestamo(prendadatos.getMontoPrestamo());
        prenda.setMontoValuo(prendadatos.getMontoValuo());
        prenda.setTipoPrenda(prendadatos.getTipoprendaIdtipoprenda().clonar());
        return prenda;
    }

    public double getMontoValuo() {
        return montoValuo;
    }

    public void setMontoValuo(double montoValuo) {
        this.montoValuo = montoValuo;
    }

    public double getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(double montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public int getEstadoEmpeno() {
        return estadoEmpeno;
    }

    public void setEstadoEmpeno(int estadoEmpeno) {
        this.estadoEmpeno = estadoEmpeno;
    }

    public Short getComercializada() {
        return comercializada;
    }

    public void setComercializada(Short comercializada) {
        this.comercializada = comercializada;
    }
}
