/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import logica.VariblesEmpeno;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "variblesempeno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Variblesempeno.findAll", query = "SELECT v FROM Variblesempeno v")
    , @NamedQuery(name = "Variblesempeno.findByIdvariblesempeno", query = "SELECT v FROM Variblesempeno v WHERE v.idvariblesempeno = :idvariblesempeno")
    , @NamedQuery(name = "Variblesempeno.findByIva", query = "SELECT v FROM Variblesempeno v WHERE v.iva = :iva")
    , @NamedQuery(name = "Variblesempeno.findByCat", query = "SELECT v FROM Variblesempeno v WHERE v.cat = :cat")
    , @NamedQuery(name = "Variblesempeno.findByInteresMensual", query = "SELECT v FROM Variblesempeno v WHERE v.interesMensual = :interesMensual")
    , @NamedQuery(name = "Variblesempeno.findByPorcentajeComercializacion", query = "SELECT v FROM Variblesempeno v WHERE v.porcentajeComercializacion = :porcentajeComercializacion")
    , @NamedQuery(name = "Variblesempeno.findByPorcentajeMutuo", query = "SELECT v FROM Variblesempeno v WHERE v.porcentajeMutuo = :porcentajeMutuo")})
public class Variblesempeno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvariblesempeno")
    private Integer idvariblesempeno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private double iva;
    @Column(name = "cat")
    private double cat;
    @Column(name = "interesMensual")
    private double interesMensual;
    @Column(name = "porcentajeComercializacion")
    private double porcentajeComercializacion;
    @Column(name = "porcentajeMutuo")
    private double porcentajeMutuo;
    @JoinColumn(name = "empeno_idempeno", referencedColumnName = "idempeno")
    @ManyToOne(optional = false)
    private Empeno empenoIdempeno;

    public Variblesempeno() {
    }

    public Variblesempeno(Integer idvariblesempeno) {
        this.idvariblesempeno = idvariblesempeno;
    }

    public Integer getIdvariblesempeno() {
        return idvariblesempeno;
    }

    public void setIdvariblesempeno(Integer idvariblesempeno) {
        this.idvariblesempeno = idvariblesempeno;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public double getCat() {
        return cat;
    }

    public void setCat(double cat) {
        this.cat = cat;
    }

    public double getInteresMensual() {
        return interesMensual;
    }

    public void setInteresMensual(double interesMensual) {
        this.interesMensual = interesMensual;
    }

    public double getPorcentajeComercializacion() {
        return porcentajeComercializacion;
    }

    public void setPorcentajeComercializacion(double porcentajeComercializacion) {
        this.porcentajeComercializacion = porcentajeComercializacion;
    }

    public double getPorcentajeMutuo() {
        return porcentajeMutuo;
    }

    public void setPorcentajeMutuo(double porcentajeMutuo) {
        this.porcentajeMutuo = porcentajeMutuo;
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
        hash += (idvariblesempeno != null ? idvariblesempeno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variblesempeno)) {
            return false;
        }
        Variblesempeno other = (Variblesempeno) object;
        if ((this.idvariblesempeno == null && other.idvariblesempeno != null) || (this.idvariblesempeno != null && !this.idvariblesempeno.equals(other.idvariblesempeno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Variblesempeno[ idvariblesempeno=" + idvariblesempeno + " ]";
    }
    public static logica.VariblesEmpeno obtenDatos(int idEmpeno){
        VariblesempenoJpaController variables= new VariblesempenoJpaController();
        List<Variblesempeno> var=variables.findVariblesempenoEntities();
        logica.VariblesEmpeno variable= new VariblesEmpeno();
        for(int i=0; i<var.size(); i++){
            if(var.get(i).getEmpenoIdempeno().getIdempeno()==idEmpeno){
                variable.setCat(var.get(i).getCat());
                variable.setIntereMensual(var.get(i).getInteresMensual());
                variable.setIva(var.get(i).getIva());
            }
        }
        return variable;
    }
    
}
