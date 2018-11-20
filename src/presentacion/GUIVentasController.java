/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logica.ArticuloVenta;
import logica.FotoPrenda;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIVentasController implements Initializable {

    @FXML
    private Button carrito;
    @FXML
    private TextField TFbuscarClientes;
    @FXML
    private Button buscarClientesButton;
    @FXML
    private TableView<ArticuloVenta> tablaArticulos;
    @FXML
    private TableColumn<ArticuloVenta, String> tipoArticuloColum;
    @FXML
    private TableColumn<ArticuloVenta, String> descripcionColumn;
    @FXML
    private TableColumn<ArticuloVenta, String> precioColumn;
    @FXML
    private ListView<FotoPrenda> listaFotos;
    @FXML
    private ImageView fotomuestra;
    @FXML
    private Button agregarAlCarritoButton;
    @FXML
    private TextField TFBuscarArticulos;
    @FXML
    private Button buscarArticulosButton;
    @FXML
    private ComboBox<?> tipoBusqueda;
    private Map<String, Object> parametrosGlobales;
    

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void recibeHashMap(Map<String, Object> parametros) {
        parametrosGlobales = parametros;
    }
    
}
