/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "variables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Variables.findAll", query = "SELECT v FROM Variables v")
    , @NamedQuery(name = "Variables.findByIdvariables", query = "SELECT v FROM Variables v WHERE v.idvariables = :idvariables")
    , @NamedQuery(name = "Variables.findByIva", query = "SELECT v FROM Variables v WHERE v.iva = :iva")
    , @NamedQuery(name = "Variables.findByCat", query = "SELECT v FROM Variables v WHERE v.cat = :cat")
    , @NamedQuery(name = "Variables.findByInteresMensual", query = "SELECT v FROM Variables v WHERE v.interesMensual = :interesMensual")
    , @NamedQuery(name = "Variables.findByPorcentajeComercializacion", query = "SELECT v FROM Variables v WHERE v.porcentajeComercializacion = :porcentajeComercializacion")
    , @NamedQuery(name = "Variables.findByPorcentajeMutuo", query = "SELECT v FROM Variables v WHERE v.porcentajeMutuo = :porcentajeMutuo")})
public class Variables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvariables")
    private Integer idvariables;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private Float iva;
    @Column(name = "cat")
    private Float cat;
    @Column(name = "interesMensual")
    private Float interesMensual;
    @Column(name = "porcentajeComercializacion")
    private Float porcentajeComercializacion;
    @Column(name = "porcentajeMutuo")
    private Float porcentajeMutuo;

    public Variables() {
    }

    public Variables(Integer idvariables) {
        this.idvariables = idvariables;
    }

    public Integer getIdvariables() {
        return idvariables;
    }

    public void setIdvariables(Integer idvariables) {
        this.idvariables = idvariables;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getCat() {
        return cat;
    }

    public void setCat(Float cat) {
        this.cat = cat;
    }

    public Float getInteresMensual() {
        return interesMensual;
    }

    public void setInteresMensual(Float interesMensual) {
        this.interesMensual = interesMensual;
    }

    public Float getPorcentajeComercializacion() {
        return porcentajeComercializacion;
    }

    public void setPorcentajeComercializacion(Float porcentajeComercializacion) {
        this.porcentajeComercializacion = porcentajeComercializacion;
    }

    public Float getPorcentajeMutuo() {
        return porcentajeMutuo;
    }

    public void setPorcentajeMutuo(Float porcentajeMutuo) {
        this.porcentajeMutuo = porcentajeMutuo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvariables != null ? idvariables.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Variables)) {
            return false;
        }
        Variables other = (Variables) object;
        if ((this.idvariables == null && other.idvariables != null) || (this.idvariables != null && !this.idvariables.equals(other.idvariables))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Variables[ idvariables=" + idvariables + " ]";
    }
    public static Variables traerVariables(){
        VariablesJpaController var = new VariablesJpaController();
      return var.findVariables(1);
    }
    public static double calcula(double montoValuo) {
        VariablesJpaController var = new VariablesJpaController();
        Variables variable=var.findVariables(1);
        return montoValuo*(variable.getPorcentajeMutuo()/100);
    }
}
