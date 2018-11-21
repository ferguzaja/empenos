/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
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
import logica.FotoPrenda;

/**
 *
 * @author Jahir
 */
@Entity
@Table(name = "fotoprenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotoprenda.findAll", query = "SELECT f FROM Fotoprenda f")
    , @NamedQuery(name = "Fotoprenda.findByIdfotoPrenda", query = "SELECT f FROM Fotoprenda f WHERE f.idfotoPrenda = :idfotoPrenda")
    , @NamedQuery(name = "Fotoprenda.findByFechaHora", query = "SELECT f FROM Fotoprenda f WHERE f.fechaHora = :fechaHora")})
public class Fotoprenda implements Serializable {

    

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfotoPrenda")
    private Integer idfotoPrenda;
    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "prenda_idprenda", referencedColumnName = "idprenda")
    @ManyToOne(optional = false)
    private Prenda prendaIdprenda;

    public Fotoprenda() {
    }

    public Fotoprenda(Integer idfotoPrenda) {
        this.idfotoPrenda = idfotoPrenda;
    }

    public Integer getIdfotoPrenda() {
        return idfotoPrenda;
    }

    public void setIdfotoPrenda(Integer idfotoPrenda) {
        this.idfotoPrenda = idfotoPrenda;
    }


    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
        hash += (idfotoPrenda != null ? idfotoPrenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotoprenda)) {
            return false;
        }
        Fotoprenda other = (Fotoprenda) object;
        if ((this.idfotoPrenda == null && other.idfotoPrenda != null) || (this.idfotoPrenda != null && !this.idfotoPrenda.equals(other.idfotoPrenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Fotoprenda[ idfotoPrenda=" + idfotoPrenda + " ]";
    }
    public List <Fotoprenda> buscaFotos(int prenda){
        FotoprendaJpaController foto = new FotoprendaJpaController();
        List <Fotoprenda> fotos = foto.findFotoprendaEntities();
         List<Fotoprenda> listafotos = new ArrayList<>();
        for (int i = 0; i < fotos.size(); i++) {
            if (fotos.get(i).getPrendaIdprenda().getIdprenda()==prenda) {
                listafotos.add(fotos.get(i));
            }
    }
        return listafotos;
    
}

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    private static byte[] convertirImagen(Image foto) {
        byte [] imagenInByte= null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage img= SwingFXUtils.fromFXImage(foto, null);
            ImageIO.write( img, "jpg", baos);
            try {
                baos.flush();
            } catch (IOException ex) {
                Logger.getLogger(Fotoprenda.class.getName()).log(Level.SEVERE, null, ex);
            }
            imagenInByte =baos.toByteArray();
            baos.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Fotoprenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagenInByte;
    }
    public static List<datos.Fotoprenda> deLogicaADatos(List<logica.FotoPrenda> listaFotos){
        List<datos.Fotoprenda> listaEnviar = new ArrayList<>();
        for(int i=0; i<listaFotos.size(); i++){
           Fotoprenda foto= new Fotoprenda();
           foto.setFechaHora(listaFotos.get(i).getFechaHora());
           foto.setPrendaIdprenda(datos.Prenda.deLogicaADatos(listaFotos.get(i).getPrenda()));
           foto.setFoto(convertirImagen(listaFotos.get(i).getFoto()));
           listaEnviar.add(foto);
        }
        return listaEnviar;
    }
    public static void guardarFotosPrendas(List<List<logica.FotoPrenda>> listaFotos){
        List< List<datos.Fotoprenda>> lista = new ArrayList<>();
        for(int i=0; i<listaFotos.size();i++){
             List<datos.Fotoprenda> listalocal=deLogicaADatos(listaFotos.get(i));
             lista.add(listalocal);
        }
        FotoprendaJpaController FPJPA= new FotoprendaJpaController();
        
        for(int i=0; i<lista.size(); i++){
            for(int x=0; x<lista.get(i).size(); x++){
                try {
                FPJPA.create(lista.get(i).get(x));
            } catch (Exception ex) {
                Logger.getLogger(Fotoprenda.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
        }
    }
    
    public static List<logica.FotoPrenda> devuelveFotos(int idPrenda){
        List<logica.FotoPrenda> listaFotos= new ArrayList<>();
        FotoprendaJpaController fotoJPA = new FotoprendaJpaController();
        List<datos.Fotoprenda> fotos =fotoJPA.findFotoprendaEntities();
        int x;
        for(int i=0; i<fotos.size(); i++){
            if(fotos.get(i).getPrendaIdprenda().getIdprenda()==idPrenda){
                FotoPrenda fotonueva = datosALogica(fotos.get(i));
                x=i+1;
                fotonueva.setNombre("foto"+x);
                listaFotos.add(fotonueva);
            }
        }
        
        return listaFotos;
    }
    public static logica.FotoPrenda datosALogica(datos.Fotoprenda foto){
        FotoPrenda fotoenviar = new FotoPrenda();
        fotoenviar.setFechaHora(foto.getFechaHora());
        fotoenviar.setPrenda(datos.Prenda.clonar(foto.getPrendaIdprenda()));
        fotoenviar.setFoto(devuelveImagen(foto.getFoto()));
        return fotoenviar;
    }
    public static Image devuelveImagen(byte[] bytes){
        BufferedImage img = null;
        try {
                img = ImageIO.read(new ByteArrayInputStream(bytes));
                    } catch (IOException ex) {
            Logger.getLogger(Fotoprenda.class.getName()).log(Level.SEVERE, null, ex);
        }
         return SwingFXUtils.toFXImage(img, null);
    }
}
