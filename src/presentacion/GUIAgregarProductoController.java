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
import java.util.Map;
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
    private ObservableList<TipoPrenda> obsPrendas;
    @FXML
    private ListView<FotoPrenda> lista;
    private ObservableList<FotoPrenda> obsfotos;
    private List<FotoPrenda> listaFotos = new ArrayList<>();
    @FXML
    private Button mostrarButton;
    @FXML
    private Button eliminarButton;
    private Map<String, Object> parametrosInterfaz;
    private GUIAgregarProductoController control;
    private Prenda prenda= new Prenda();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboTipoPrenda();
        control=this;
        // TODO
    }

    @FXML
    private void asignaPrestamo() {
        if (montoValuo.getText() != null && !montoValuo.getText().isEmpty()) {
            montoPrestamo.setText(String.valueOf(calculaMonto(Double.valueOf(montoValuo.getText()))));
            //agregar para mutiplicar por las variables de prestamo y validacion numero

        }

    }

    private boolean validarCamposVacios() {
        boolean correcto = true;
        if (descripcion.getText().isEmpty() || "".equals(descripcion.getText())) {
            correcto = false;
        }
        if (montoValuo.getText().isEmpty() || "".equals(montoValuo.getText())) {
            correcto = false;
        }
        if (montoPrestamo.getText().isEmpty() || "".equals(montoPrestamo.getText())) {
            correcto = false;
        }
        return correcto;
    }

    @FXML
    private void botonGuardar(ActionEvent event) throws Exception {
        
        if (!validarCamposVacios() || (listaFotos.isEmpty())) {
            utilerias.mensajes.mensage("Favor de no dejar Campos Vacios o sin agregar Fotos");
        } else {
            if(((int)parametrosInterfaz.get("Prenda"))==1){
                editar(getPrenda());
            }else{
                guardar(getPrenda());
            }
        }
    }
    public double calculaMonto(double montoValuo){
        return datos.Variables.calcula(montoValuo);
    }
    public void guardar(Prenda prenda){
        ((GUIEmpenosController)parametrosInterfaz.get("Empenos")).agregarPrenda(prenda, listaFotos);
        ((Stage)parametrosInterfaz.get("Stage")).close();
    }
    public void editar(Prenda prenda){
        ((GUIEmpenosController)parametrosInterfaz.get("Empenos")).editarPrenda(prenda, listaFotos,((int)parametrosInterfaz.get("posicion")));
        ((Stage)parametrosInterfaz.get("Stage")).close();
    }
    public Prenda getPrenda(){
        prenda.setDescripcion(descripcion.getText());
        prenda.setMontoValuo(Double.parseDouble(montoValuo.getText()));
        prenda.setMontoPrestamo(Double.parseDouble(montoPrestamo.getText()));
        prenda.setTipoPrenda(tipoPrenda.getValue());
        return prenda;
    }
    public void setPrenda(Prenda prenda){
        descripcion.setText(prenda.getDescripcion());
        montoValuo.setText(""+prenda.getMontoValuo());
        obsfotos=FXCollections.observableArrayList(listaFotos);
        lista.setItems(obsfotos);
        tipoPrenda.getSelectionModel().select(prenda.getTipoPrenda());
                
        
    }
    private void botontCancelar(ActionEvent event) {
        ((Stage)parametrosInterfaz.get("Stage")).close();
    }

    public void recibeHasgMap(Map<String, Object> parametrosInterfaz) {
        this.parametrosInterfaz=parametrosInterfaz;
        if(((int)parametrosInterfaz.get("Prenda"))==1){
            listaFotos=((List<FotoPrenda>)parametrosInterfaz.get("ListaFotos"));
            prenda=((Prenda)parametrosInterfaz.get("Editar"));
            setPrenda(prenda);
        }
    }

    public void llenarComboTipoPrenda() {
        TipoprendaJpaController tipoPrendaJPA = new TipoprendaJpaController();
        List<datos.Tipoprenda> prendas = tipoPrendaJPA.findTipoprendaEntities();
        List<logica.TipoPrenda> listaPrendas = new ArrayList<>();
        for (int i = 0; i < prendas.size(); i++) {
            TipoPrenda prenda = new TipoPrenda(prendas.get(i).getIdtipoprenda(), prendas.get(i).getNombre());
            listaPrendas.add(prenda);
        }
        obsPrendas = FXCollections.observableArrayList(listaPrendas);
        tipoPrenda.setItems(obsPrendas);
    }

    @FXML
    private void botonTomarFoto() {
        Map<String, Object> parametrosFoto;
        parametrosFoto=utilerias.mensajes.nuevaInterfaz("TakePicture.fxml", this);
        TakePictureController takePictureController = (TakePictureController)((FXMLLoader)parametrosFoto.get("Loader")).getController();
        parametrosFoto.put("size", listaFotos.size());
        parametrosFoto.put("Control", control);
        takePictureController.recibeHashMap(parametrosFoto);

    }

    public void actualizaLista() {
        lista.setItems(obsfotos);
    }

    @FXML
    public void seleccionaImagen(ActionEvent event) {
        imagen.setImage(lista.getSelectionModel().getSelectedItem().getFoto());
    }

    public void recibeImagen(FotoPrenda foto) {
        listaFotos.add(foto);
        obsfotos = FXCollections.observableArrayList(listaFotos);
        actualizaLista();
    }

    @FXML
    private void seleccionaImagen() {
        if (lista.getSelectionModel().isEmpty()) {
            utilerias.mensajes.mensage("favor de seleccionar un Articulo de venta para ver las fotos");
        } else {
            imagen.setImage(lista.getSelectionModel().getSelectedItem().getFoto());
        }
    }
    @FXML
    private void eliminarImagen(){
        if(lista.getSelectionModel().getSelectedItem()==null){
            utilerias.mensajes.mensage("Favor de seleccionar una foto a eliminar");
        }else{
            listaFotos.remove(lista.getSelectionModel().getSelectedIndex());
            lista.getItems().remove(lista.getSelectionModel().getSelectedIndex());
        }
    }
}
