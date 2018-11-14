/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

/**
 *
 * @author ferguzaja
 */
public class FotoPrenda {
 private int idFotoPrenda;
 private Image foto;
 private Date FechaHora;
 private Prenda prenda;

    public FotoPrenda(int idFotoPrenda, Image foto, Date FechaHora, Prenda prenda) {
        this.idFotoPrenda = idFotoPrenda;
        this.foto = foto;
        this.FechaHora = FechaHora;
        this.prenda = prenda;
    }

    public FotoPrenda() {
    }

    public FotoPrenda(Image foto, Date FechaHora, Prenda prenda) {
        this.foto = foto;
        this.FechaHora = FechaHora;
        this.prenda = prenda;
    }

    public int getIdFotoPrenda() {
        return idFotoPrenda;
    }

    public void setIdFotoPrenda(int idFotoPrenda) {
        this.idFotoPrenda = idFotoPrenda;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Date getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(Date FechaHora) {
        this.FechaHora = FechaHora;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }
// public static Blob convertirImagenABlob ( Image imagen ) {
//
//      Blob imagenBlob = null;
//      BufferedImage bi;
//     bi = new BufferedImage ((int) imagen.getWidth(), (int) imagen.getHeight(), BufferedImage.TYPE_INT_ARGB );
//      Graphics bg = bi.getGraphics ();
//      BufferedImage image = SwingFXUtils.fromFXImage(imagen, null);
//      bg.drawImage ( image, 0, 0, null );
//      bg.dispose ();
//
//      ByteArrayOutputStream baos = new ByteArrayOutputStream ();
//      try {
//         ImageIO.write (
//               bi,
//               image.getDescription ().substring (
//                     imagen.getDescription ().length () - 3 ), baos );
//         baos.flush ();
//         baos.close ();
//      } catch ( IOException e ) {
//         e.printStackTrace ();
//      }
//
//      byte [] imagenByte = baos.toByteArray ();
//
//      try {
//         imagenBlob = new SerialBlob ( imagenByte );
//      } catch ( SerialException se ) {
//         se.printStackTrace ();
//      } catch ( SQLException sqle ) {
//         sqle.printStackTrace ();
//      }
//      return imagenBlob;
//   }

    public Blob convertirImagenABlob(ImageView imgWebCamCapturedImage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
