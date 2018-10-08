/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.sun.deploy.uitoolkit.ToolkitStore;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import logica.Prenda;
import presentacion.GUIEmpenosController;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIAgregarProductoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JComboBox tipoPrenda;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField montoValuo;
    @FXML
    private TextField montoPrestamo;
    @FXML
    private JButton tomarFotografia;
    @FXML
    private JButton guardar;
    @FXML
    private JButton cancelar;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    private void montoValuoKeyTyped(java.awt.event.KeyEvent evt){
        char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') ||
         (caracter > '9')) ||(caracter=='.')&&
         (caracter != '\b' /*corresponde a BACK_SPACE*/))
      {
         evt.consume();  // ignorar el evento de teclado
      }
        }
    private void montoPrestamoKeyTyped(java.awt.event.KeyEvent evt){
        char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') ||
         (caracter > '9')) ||(caracter=='.')&&
         (caracter != '\b' /*corresponde a BACK_SPACE*/))
      {
         evt.consume();  // ignorar el evento de teclado
      }
        }
    
    @FXML
    private void mensajePantalla(String mensaje) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("Aviso");
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
    @FXML
    private boolean validarCamposVacios(){
        boolean correcto=true;
        if(descripcion.getText().isEmpty()||"".equals(descripcion.getText())){
           correcto=false;
        }
        if(montoValuo.getText().isEmpty()||"".equals(montoValuo.getText())){
            correcto=false;
        }
        if(montoPrestamo.getText().isEmpty()||"".equals(montoPrestamo.getText())){
           correcto=false;
        }
        return correcto;
    }
    
    @FXML
    private void botonGuardar(ActionEvent event) throws Exception {
    if(!validarCamposVacios()){
        mensajePantalla("Favor de no dejar Campos Vacios");
    }else{
        Prenda prenda = new Prenda(tipoPrenda.getSelectedIndex()+1,descripcion.getText(),Float.parseFloat(montoValuo.getText()),Float.parseFloat(montoPrestamo.getText()));
        //agregarPrenda(prenda);
        dispose();
            
        }
    }
}
