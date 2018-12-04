/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import logica.ArticuloVenta;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "articuloventa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articuloventa.findAll", query = "SELECT a FROM Articuloventa a")
    , @NamedQuery(name = "Articuloventa.findByIdarticuloventa", query = "SELECT a FROM Articuloventa a WHERE a.idarticuloventa = :idarticuloventa")
    , @NamedQuery(name = "Articuloventa.findByDescripcionArticulo", query = "SELECT a FROM Articuloventa a WHERE a.descripcionArticulo = :descripcionArticulo")
    , @NamedQuery(name = "Articuloventa.findByPrecioVenta", query = "SELECT a FROM Articuloventa a WHERE a.precioVenta = :precioVenta")
    , @NamedQuery(name = "Articuloventa.findByEstado", query = "SELECT a FROM Articuloventa a WHERE a.estado = :estado")})
public class Articuloventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticuloventa")
    private Integer idarticuloventa;
    @Column(name = "descripcionArticulo")
    private String descripcionArticulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioVenta")
    private double precioVenta;
    @Column(name = "estado")
    private int estado;
    @JoinTable(name = "ventaarticuloventa", joinColumns = {
        @JoinColumn(name = "articuloventa_idarticuloventa", referencedColumnName = "idarticuloventa")}, inverseJoinColumns = {
        @JoinColumn(name = "venta_idventa", referencedColumnName = "idventa")})
    @ManyToMany
    private List<Venta> ventaList;
    @JoinTable(name = "articuloventaremate", joinColumns = {
        @JoinColumn(name = "articuloventa_idarticuloventa", referencedColumnName = "idarticuloventa")}, inverseJoinColumns = {
        @JoinColumn(name = "remate_idremate", referencedColumnName = "idremate")})
    @ManyToMany
    private List<Remate> remateList;
    @JoinColumn(name = "prenda_idprenda", referencedColumnName = "idprenda")
    @ManyToOne(optional = false)
    private Prenda prendaIdprenda;

    public Articuloventa() {
    }

    public Articuloventa(String descripcionArticulo, Float precioVenta, Short estado, Prenda prendaIdprenda) {
        this.descripcionArticulo = descripcionArticulo;
        this.precioVenta = precioVenta;
        this.estado = estado;
        this.prendaIdprenda = prendaIdprenda;
    }        

    public Articuloventa(Integer idarticuloventa) {
        this.idarticuloventa = idarticuloventa;
    }

    public Integer getIdarticuloventa() {
        return idarticuloventa;
    }

    public void setIdarticuloventa(Integer idarticuloventa) {
        this.idarticuloventa = idarticuloventa;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Remate> getRemateList() {
        return remateList;
    }

    public void setRemateList(List<Remate> remateList) {
        this.remateList = remateList;
    }

    public Prenda getPrendaIdprenda() {
        return prendaIdprenda;
    }

    public void setPrendaIdprenda(Prenda prendaIdprenda) {
        this.prendaIdprenda = prendaIdprenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticuloventa != null ? idarticuloventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articuloventa)) {
            return false;
        }
        Articuloventa other = (Articuloventa) object;
        if ((this.idarticuloventa == null && other.idarticuloventa != null) || (this.idarticuloventa != null && !this.idarticuloventa.equals(other.idarticuloventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Articuloventa[ idarticuloventa=" + idarticuloventa + " ]";
    }

    public static List<logica.ArticuloVenta> obtenArticuloVentas(String busqueda) {
        List<logica.ArticuloVenta> articulosEnviar = new ArrayList<>();
        ArticuloventaJpaController AJPA = new ArticuloventaJpaController();
        List<datos.Articuloventa> articulosEncontrados = AJPA.findArticuloventaEntities();
        for (int i = 0; i < articulosEncontrados.size(); i++) {
            //validacion de contenido de datos y que este disponible
            if (articulosEncontrados.get(i).getDescripcionArticulo().contains(busqueda) && articulosEncontrados.get(i).getEstado() == 0) {
                articulosEnviar.add(datosALogica(articulosEncontrados.get(i)));
            }
        }
        return articulosEnviar;
    }

    public static logica.ArticuloVenta datosALogica(datos.Articuloventa articulo) {
        ArticuloVenta enviar = new ArticuloVenta();
        enviar.setDescripcion(articulo.getDescripcionArticulo());
        enviar.setEstado(articulo.getEstado());
        enviar.setIdArticuloVenta(articulo.getIdarticuloventa());
        enviar.setPrecioVenta(articulo.getPrecioVenta());
        enviar.setPrenda(datos.Prenda.clonar(articulo.getPrendaIdprenda()));
        return enviar;
    }

    public static double regresaMonto(List<ArticuloVenta> lista) {
        double monto = 0;
        for (int i = 0; i < lista.size(); i++) {
            monto = +(lista.get(i).getPrecioVenta() - lista.get(i).getPrenda().getMontoPrestamo());
        }
        return monto;
    }
    
    public static void registrarArticulosVenta(List<datos.Prenda> prendas){
        ArticuloventaJpaController articuloJPA = new ArticuloventaJpaController();
        datos.Articuloventa articuloVenta;
        float porcentajeComercializacion = Variables.traerVariables().getPorcentajeComercializacion() / 100;
        float precioVenta;
        for (int i = 0; i < prendas.size(); i++) {
            precioVenta = (float)(prendas.get(i).getMontoValuo() 
                    * porcentajeComercializacion + prendas.get(i).getMontoValuo());            
            articuloVenta = new Articuloventa(prendas.get(i).getDescripcion(),
                    precioVenta,(short)0,prendas.get(i));
            articuloJPA.create(articuloVenta);
        }
    }
    
    public static void editarDescripcionArticuloVenta(logica.ArticuloVenta articulo){
        try {
            ArticuloventaJpaController articuloJPA = new ArticuloventaJpaController();
            datos.Articuloventa articuloventa = new Articuloventa();
            articuloventa.setIdarticuloventa(articulo.getIdArticuloVenta());
            articuloventa.setDescripcionArticulo(articulo.getDescripcion());
            articuloventa.setEstado(articulo.getEstado());
            articuloventa.setPrecioVenta(articulo.getPrecioVenta());
            articuloventa.setPrendaIdprenda(datos.Prenda.deLogicaADatos(articulo.getPrenda()));
            articuloJPA.edit(articuloventa);
        } catch (Exception ex) {
            Logger.getLogger(Articuloventa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editarPrecioArticuloVenta(logica.ArticuloVenta articulo){
        try {
            ArticuloventaJpaController articuloJPA = new ArticuloventaJpaController();
            datos.Articuloventa articuloventa = new Articuloventa();
            articuloventa.setIdarticuloventa(articulo.getIdArticuloVenta());
            articuloventa.setDescripcionArticulo(articulo.getDescripcion());
            articuloventa.setEstado((short)articulo.getEstado());
            articuloventa.setPrecioVenta((float)articulo.getPrecioVenta());
            articuloventa.setPrendaIdprenda(datos.Prenda.deLogicaADatos(articulo.getPrenda()));
            articuloJPA.edit(articuloventa);
        } catch (Exception ex) {
            Logger.getLogger(Articuloventa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void editarVendida(List<datos.Articuloventa> lista){
        ArticuloventaJpaController articuloJPA = new ArticuloventaJpaController();
        for(int i=0; i<lista.size(); i++){
            lista.get(i).setEstado(1);
            try {
                articuloJPA.edit(lista.get(i));
            } catch (Exception ex) {
                Logger.getLogger(Articuloventa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
