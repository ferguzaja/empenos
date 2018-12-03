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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByIdventa", query = "SELECT v FROM Venta v WHERE v.idventa = :idventa")
    , @NamedQuery(name = "Venta.findByFechaHora", query = "SELECT v FROM Venta v WHERE v.fechaHora = :fechaHora")
    , @NamedQuery(name = "Venta.findByGananciaTotal", query = "SELECT v FROM Venta v WHERE v.gananciaTotal = :gananciaTotal")})
public class Venta implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gananciaTotal")
    private double gananciaTotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idventa")
    private Integer idventa;
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @ManyToMany(mappedBy = "ventaList")
    private List<Articuloventa> articuloventaList;
    @JoinColumn(name = "empleado_idEmpleado", referencedColumnName = "idempleado")
    @ManyToOne(optional = false)
    private Empleado empleadoidEmpleado;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;

    public Venta() {
    }

    public Venta(Integer idventa) {
        this.idventa = idventa;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @XmlTransient
    public List<Articuloventa> getArticuloventaList() {
        return articuloventaList;
    }

    public void setArticuloventaList(List<Articuloventa> articuloventaList) {
        this.articuloventaList = articuloventaList;
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
        hash += (idventa != null ? idventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.idventa == null && other.idventa != null) || (this.idventa != null && !this.idventa.equals(other.idventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Venta[ idventa=" + idventa + " ]";
    }

    public static datos.Venta deLogicaADatos(logica.Venta venta) {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setEmpleadoidEmpleado(datos.Empleado.recuperarEmpleado(venta.getEmpleado().getIdEmpleado()));
        if (venta.getCliente() != null) {
            nuevaVenta.setClienteIdcliente(datos.Cliente.recuperarCliente(venta.getCliente().getIdCliente()));
        }
        nuevaVenta.setFechaHora(venta.getFechaHora());
        nuevaVenta.setGananciaTotal(venta.getGananciaTotal());
        return nuevaVenta;
    }

    public static void guardarVenta(logica.Venta venta, List<logica.ArticuloVenta> articulos) {
        try {
            //Primero se guarda la venta
            VentaJpaController VJPA = new VentaJpaController();
            VJPA.create(deLogicaADatos(venta));
            //se recupera la venta que se acaba de guardar en un objeto venta            
            datos.Venta objVentas = VJPA.findVenta(recuperaUltimaVenta().getIdventa());
            //se guardan los articulos de la venta en una lista (hasta ahora pues está vacía)
            List<Articuloventa> listaArticulos = objVentas.getArticuloventaList();
            //Se crea el controlador de los articulos venta
            ArticuloventaJpaController artVentaJPA = new ArticuloventaJpaController();
            //se recupera el articulo que se quiere relacionar a la venta           
            for (int i = 0; i < articulos.size(); i++) {
                Articuloventa articuloVenta = artVentaJPA.findArticuloventa(articulos.get(i).getIdArticuloVenta());
                //Se agrega el articulo a la lista de articulos de la venta que estaba vacía
                listaArticulos.add(articuloVenta);
            }                        
            //se pasa la lista de articulos al objeto de la venta
            objVentas.setArticuloventaList(listaArticulos);
            //por ultimo se edita la venta para que guarde los cambios
            VJPA.edit(objVentas);
        } catch (Exception ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    private static logica.Venta clonarDatosALogica(Venta venta) {
        logica.Venta ventaEnviar = new logica.Venta();
        ventaEnviar.setCliente(venta.getClienteIdcliente().clonar());
        ventaEnviar.setEmpleado(datos.Empleado.datosALogicaClonar(venta.getEmpleadoidEmpleado()));
        ventaEnviar.setFechaHora(venta.getFechaHora());
        ventaEnviar.setGananciaTotal(venta.getGananciaTotal());
        ventaEnviar.setIdVenta(venta.getIdventa());
        return ventaEnviar;
    }

    public static List<logica.Venta> ventasNavegacion(int inicio, int fin) {
        List<logica.Venta> ventas = new ArrayList<logica.Venta>();
        VentaJpaController ventaJPa = new VentaJpaController();
        Venta venta = new Venta();
        for (int i = inicio; i < fin; i++) {
            venta = ventaJPa.findVenta(i);
            if (venta != null) {
                ventas.add(clonarDatosALogica(venta));
            } else {
                ventas.add(null);
                break;
            }
        }
        return ventas;
    }

    public static List<logica.Venta> buscarVentas(String text) {
        VentaJpaController ventaJPa = new VentaJpaController();
        List<logica.Venta> ventas = new ArrayList<logica.Venta>();
        List<datos.Venta> ventasEncontradas = ventaJPa.findVentaEntities();
        for (int i = 0; i < ventasEncontradas.size(); i++) {
            //criterio de busqueda
            ventas.add(clonarDatosALogica(ventasEncontradas.get(i)));
        }
        return ventas;
    }

    public static int ultimaVenta() {
        VentaJpaController ventaJPA = new VentaJpaController();
        List<datos.Venta> ventasEncontradas = ventaJPA.findVentaEntities();
        return ventasEncontradas.size() + 1;
    }
    
    public static datos.Venta recuperaUltimaVenta(){
        VentaJpaController ventaJPA = new VentaJpaController();
        List<Venta> ventas = ventaJPA.findVentaEntities();
        Venta venta = ventas.get(ventas.size() -1);
        return venta;
    }
}
