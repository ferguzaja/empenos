/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import datos.TipoprendaJpaController;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.Prenda;
import logica.TipoPrenda;

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
    private ComboBox<TipoPrenda> tipoPrenda;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField montoValuo;
    @FXML
    private TextField montoPrestamo;
    @FXML
    private Button tomarFotografia;
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
    @FXML
    private ObservableList<TipoPrenda> obsPrendas;
    
    private GUIEmpenosController controlador;
    private Stage planillaStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboTipoPrenda();
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
        Prenda prenda = new Prenda(descripcion.getText(),Double.parseDouble(montoValuo.getText()),Double.parseDouble(montoPrestamo.getText()),tipoPrenda.getValue().getIdTipoPrenda(),tipoPrenda.getValue().getNombre());
        controlador.agregarPrenda(prenda,planillaStage);
        
        //dispose();
            
        }
    }
    @FXML
    private void botontCancelar(ActionEvent event){
        planillaStage.close();
    }
    public void recibeVariable(GUIEmpenosController controlador,Stage planillaStage){
        this.controlador=controlador;
        this.planillaStage=planillaStage;
    }
    public void llenarComboTipoPrenda() {
         TipoprendaJpaController tipoPrendaJPA = new TipoprendaJpaController();
        List<datos.Tipoprenda> prendas = tipoPrendaJPA.findTipoprendaEntities();
        List<logica.TipoPrenda> listaPrendas = new ArrayList<>();
        for (int i = 0; i < prendas.size(); i++) {
            TipoPrenda prenda = new TipoPrenda(prendas.get(i).getIdtipoprenda(), prendas.get(i).getNombre());
            listaPrendas.add(prenda);
        }
        obsPrendas= FXCollections.observableArrayList(listaPrendas);
        tipoPrenda.setItems(obsPrendas);
    }
}
