/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "fotocliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotocliente.findAll", query = "SELECT f FROM Fotocliente f")
    , @NamedQuery(name = "Fotocliente.findByIdfotoCliente", query = "SELECT f FROM Fotocliente f WHERE f.idfotoCliente = :idfotoCliente")
    , @NamedQuery(name = "Fotocliente.findByFechaHora", query = "SELECT f FROM Fotocliente f WHERE f.fechaHora = :fechaHora")})
public class Fotocliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfotoCliente")
    private Integer idfotoCliente;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;

    public Fotocliente() {
    }

    public Fotocliente(Integer idfotoCliente) {
        this.idfotoCliente = idfotoCliente;
    }

    public Integer getIdfotoCliente() {
        return idfotoCliente;
    }

    public void setIdfotoCliente(Integer idfotoCliente) {
        this.idfotoCliente = idfotoCliente;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
        hash += (idfotoCliente != null ? idfotoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotocliente)) {
            return false;
        }
        Fotocliente other = (Fotocliente) object;
        if ((this.idfotoCliente == null && other.idfotoCliente != null) || (this.idfotoCliente != null && !this.idfotoCliente.equals(other.idfotoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.entidades.Fotocliente[ idfotoCliente=" + idfotoCliente + " ]";
    }
    
}
