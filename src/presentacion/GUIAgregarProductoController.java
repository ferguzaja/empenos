/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.TipoprendaJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.FotoPrenda;
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
    private ImageView imagen;
    @FXML
    private ObservableList<TipoPrenda> obsPrendas;
    @FXML
    private ListView<FotoPrenda> lista;
    @FXML
    private ObservableList<FotoPrenda> obsfotos;
    private GUIEmpenosController controlador;
    private Stage planillaStage;
    private List<FotoPrenda> listaFotos=new ArrayList<>();
    
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
    if(!validarCamposVacios()||(listaFotos.isEmpty())){
        utilerias.mensajes.mensage("Favor de no dejar Campos Vacios o sin agregar Fotos");
    }else{
        Prenda prenda = new Prenda(descripcion.getText(),Double.parseDouble(montoValuo.getText()),Double.parseDouble(montoPrestamo.getText()),tipoPrenda.getValue());
        controlador.agregarPrenda(prenda,listaFotos);
        planillaStage.close();
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
    @FXML
    private void botonTomarFoto(){
        try {
            FXMLLoader loader= new FXMLLoader();
            
            //agregamos el openStream (no se para que)
            AnchorPane root =(AnchorPane)loader.load(getClass().getResource("TakePicture.fxml").openStream());
            //ahora creo una instancia del controlador del form que voy a abrir casteando
            Scene scene = new Scene(root);
            Stage planillaStage=new Stage();
            planillaStage.setScene(scene);           
            TakePictureController takePictureController=(TakePictureController)loader.getController();
            takePictureController.recibeStage(planillaStage,this,listaFotos.size());
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIAdministrarEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    
}
    public void actualizaLista(){
        lista.setItems(obsfotos);
    }
    public void seleccionaImagen(ActionEvent event){
        imagen.setImage(lista.getSelectionModel().getSelectedItem().getFoto());
                }

    public void recibeImagen(FotoPrenda foto) {
        listaFotos.add(foto);
        obsfotos=FXCollections.observableArrayList(listaFotos);
        actualizaLista();   
        }       
    }
