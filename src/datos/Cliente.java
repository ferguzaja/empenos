/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.persistence.Lob;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByApeliidoPaterno", query = "SELECT c FROM Cliente c WHERE c.apeliidoPaterno = :apeliidoPaterno")
    , @NamedQuery(name = "Cliente.findByApellidoMaterno", query = "SELECT c FROM Cliente c WHERE c.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByNoIdentificacion", query = "SELECT c FROM Cliente c WHERE c.noIdentificacion = :noIdentificacion")
    , @NamedQuery(name = "Cliente.findByListaNegra", query = "SELECT c FROM Cliente c WHERE c.listaNegra = :listaNegra")
    , @NamedQuery(name = "Cliente.findByFechaNac", query = "SELECT c FROM Cliente c WHERE c.fechaNac = :fechaNac")})
public class Cliente implements Serializable {

    @Lob
    @Column(name = "huellaDigital")
    private byte[] huellaDigital;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apeliidoPaterno")
    private String apeliidoPaterno;
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "noIdentificacion")
    private String noIdentificacion;
    @Column(name = "listaNegra")
    private Short listaNegra;
    @Column(name = "fechaNac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Venta> ventaList;
    @JoinColumn(name = "ciudad_idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private Ciudad ciudadIdciudad;
    @JoinColumn(name = "ocupacion_idocupacion", referencedColumnName = "idocupacion")
    @ManyToOne(optional = false)
    private Ocupacion ocupacionIdocupacion;
    @JoinColumn(name = "tipoidentificacion_idtipoidentificacion", referencedColumnName = "idtipoidentificacion")
    @ManyToOne(optional = false)
    private Tipoidentificacion tipoidentificacionIdtipoidentificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Fotocliente> fotoclienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Remate> remateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Empeno> empenoList;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, String nombre) {
        this.idcliente = idcliente;
        this.nombre = nombre;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApeliidoPaterno() {
        return apeliidoPaterno;
    }

    public void setApeliidoPaterno(String apeliidoPaterno) {
        this.apeliidoPaterno = apeliidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public Short getListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(Short listaNegra) {
        this.listaNegra = listaNegra;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }


    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    public Ciudad getCiudadIdciudad() {
        return ciudadIdciudad;
    }

    public void setCiudadIdciudad(Ciudad ciudadIdciudad) {
        this.ciudadIdciudad = ciudadIdciudad;
    }

    public Ocupacion getOcupacionIdocupacion() {
        return ocupacionIdocupacion;
    }

    public void setOcupacionIdocupacion(Ocupacion ocupacionIdocupacion) {
        this.ocupacionIdocupacion = ocupacionIdocupacion;
    }

    public Tipoidentificacion getTipoidentificacionIdtipoidentificacion() {
        return tipoidentificacionIdtipoidentificacion;
    }

    public void setTipoidentificacionIdtipoidentificacion(Tipoidentificacion tipoidentificacionIdtipoidentificacion) {
        this.tipoidentificacionIdtipoidentificacion = tipoidentificacionIdtipoidentificacion;
    }

    @XmlTransient
    public List<Fotocliente> getFotoclienteList() {
        return fotoclienteList;
    }

    public void setFotoclienteList(List<Fotocliente> fotoclienteList) {
        this.fotoclienteList = fotoclienteList;
    }

    @XmlTransient
    public List<Remate> getRemateList() {
        return remateList;
    }

    public void setRemateList(List<Remate> remateList) {
        this.remateList = remateList;
    }

    @XmlTransient
    public List<Empeno> getEmpenoList() {
        return empenoList;
    }

    public void setEmpenoList(List<Empeno> empenoList) {
        this.empenoList = empenoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Cliente[ idcliente=" + idcliente + " ]";
    }
    
    public logica.Cliente clonar(){
        logica.Cliente cliente = new logica.Cliente();
        cliente.setIdCliente(getIdcliente());
        cliente.setNombre(getNombre());
        cliente.setApellidoPaterno(getApeliidoPaterno());
        cliente.setApellidoMaterno(getApellidoMaterno());
        return cliente;
    }
    
    public static datos.Cliente recuperarCliente(int idCliente){
        ClienteJpaController clienteJPA = new ClienteJpaController();
        datos.Cliente cliente = clienteJPA.findCliente(idCliente);
        return cliente;
    }

    public static List<logica.Cliente> buscaClientes(String s){
        ClienteJpaController clienteJPA = new ClienteJpaController();
        List<datos.Cliente> clientes = clienteJPA.findClienteEntities();

        List<logica.Cliente> listaClientes = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNombre().contains(s)) {
                logica.Cliente client = new logica.Cliente();
                client.setIdCliente(clientes.get(i).getIdcliente());
                client.setNombre(clientes.get(i).getNombre());
                client.setApellidoMaterno(clientes.get(i).getApellidoMaterno());
                client.setApellidoPaterno(clientes.get(i).getApeliidoPaterno());
                client.setDireccion(clientes.get(i).getDireccion());
                client.setIdTipoIdentificacion(clientes.get(i).getTipoidentificacionIdtipoidentificacion().getIdtipoidentificacion());
                client.setNoIdentificacion(clientes.get(i).getNoIdentificacion());
                client.setFechaNacimiento(clientes.get(i).getFechaNac().toString());
                //Para convertir de date que esta en la bd a String
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                client.setFechaNacimiento(sdf.format(clientes.get(i).getFechaNac()));
                client.setIdCiudad(clientes.get(i).getCiudadIdciudad().getIdciudad());
                client.setIdPais(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().getPaisIdpais().getIdpais());
                client.setIdEstado(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().getIdestado());
                client.setIdOcupacion(clientes.get(i).getOcupacionIdocupacion().getIdocupacion());
                client.setPais(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().getPaisIdpais().clonar());
                client.setEstado(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().clonar());
                client.setCiudad(clientes.get(i).getCiudadIdciudad().clonar());
                client.setOcupacion(clientes.get(i).getOcupacionIdocupacion().clonar());
                client.setTipoIden(clientes.get(i).getTipoidentificacionIdtipoidentificacion().clonar());
                listaClientes.add(client);
            }
    }
 return listaClientes;   
}

    public byte[] getHuellaDigital() {
        return huellaDigital;
    }

    public void setHuellaDigital(byte[] huellaDigital) {
        this.huellaDigital = huellaDigital;
    }
}
