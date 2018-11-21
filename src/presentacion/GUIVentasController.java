/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Cliente;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private List<ArticuloVenta> ListaCarrito = new ArrayList<>();

    @FXML
    private Button buscarArticulosButton;
    private Map<String, Object> parametrosGlobales;
    @FXML
    private Button mostrarButton;
    @FXML
    private Button verFotoButton;
    private ObservableList<FotoPrenda> obsfotos;
    @FXML
    private TableView<logica.Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> nombreColumn;
    @FXML
    private TableColumn<Cliente, String> apPaternoColumn;
    @FXML
    private TableColumn<Cliente, String> apMaternoColumn;
    @FXML
    private TableColumn<Cliente, String> direccionColumn;
    @FXML
    private TableColumn<Cliente, String> noIdentColumn;

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void botonMostrar() {
        if (utilerias.validacion.seleccionado(tablaArticulos)) {
            listaFotos.getSelectionModel().clearSelection();
            obsfotos = FXCollections.observableArrayList(datos.Fotoprenda.devuelveFotos(tablaArticulos.getSelectionModel().getSelectedItem().getPrenda().getIdPrenda()));
            listaFotos.setItems(obsfotos);
        }

    }

    public void seleccionaImagen() {
        if (listaFotos.getSelectionModel().isEmpty()) {
            utilerias.mensajes.mensage("favor de seleccionar un Articulo de venta para ver las fotos");
        } else {
            fotomuestra.setImage(listaFotos.getSelectionModel().getSelectedItem().getFoto());
        }
    }

    public void llenarTablaArticulos() {
        TFBuscarArticulos.setText(TFBuscarArticulos.getText().trim());
        if (TFBuscarArticulos.getText() != "" || TFBuscarArticulos != null) {
            List<ArticuloVenta> listabusqueda = datos.Articuloventa.obtenArticuloVentas(TFBuscarArticulos.getText());
            ObservableList<logica.ArticuloVenta> obsArticulos = FXCollections.observableArrayList(estaEnCarrito(listabusqueda));
            tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("prenda.getTipoArticulo"));
            descripcionColumn.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("descripcion"));
            precioColumn.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("precioVenta"));
            tablaArticulos.setItems(obsArticulos);
        } else {
            utilerias.mensajes.mensage("favor de introducir un valor para la busqueda");
        }
    }

    public void llenarTablaClientes() {
        ObservableList<logica.Cliente> obsClientes = FXCollections.observableArrayList(datos.Cliente.buscaClientes(TFbuscarClientes.getText()));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoPaterno"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        noIdentColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("noIdentificacion"));
        tablaClientes.setItems(obsClientes);

    }

    public List<ArticuloVenta> estaEnCarrito(List<ArticuloVenta> listaEntrada) {
        List<ArticuloVenta> listaSalida = new ArrayList<>();
        if (ListaCarrito.isEmpty()) {
            listaSalida = listaEntrada;
        } else {
            for (int i = 0; i < listaEntrada.size(); i++) {
                for (int x = 0; x < ListaCarrito.size(); x++) {
                    if (listaEntrada.get(i).getIdArticuloVenta()==(ListaCarrito.get(x).getIdArticuloVenta())) {
                        listaEntrada.remove(i);
                        break;
                    }
                }
            }
            listaSalida = listaEntrada;
        }
        return listaSalida;
    }

    @FXML
    public void agregarCarrito() {
        if (utilerias.validacion.seleccionado(tablaArticulos)) {
            if (utilerias.mensajes.mensajeCarrito(tablaArticulos.getSelectionModel().getSelectedItem())) {
                ListaCarrito.add(tablaArticulos.getSelectionModel().getSelectedItem());
                tablaArticulos.getItems().remove(tablaArticulos.getSelectionModel().getSelectedIndex());
                tablaArticulos.refresh();
                        }
        } else {
            utilerias.mensajes.mensage("favor de seleccionar un articulo para agregar a su carrito");
        }
    }

    public void recibeHashMap(Map<String, Object> parametros) {
        parametrosGlobales = parametros;
    }

}
