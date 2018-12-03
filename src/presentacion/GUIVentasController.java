/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Cliente;
import jasper.JasperReports;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import logica.ArticuloVenta;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIVentasController implements Initializable {

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
    private TableColumn<ArticuloVenta, Double> precioColumn;
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
    Map<String, Object> parametrosInterfaz = new HashMap<>();
    @FXML
    private Button comprarButton;
    @FXML
    private TableView<ArticuloVenta> tablaCarrito;
    @FXML
    private TableColumn<ArticuloVenta, String> tipoArticuloCarrito;
    @FXML
    private TableColumn<ArticuloVenta, String> descripcionCarrito;
    @FXML
    private TableColumn<ArticuloVenta, String> precioCarrito;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button eliminarCarritoButton;

    public void initialize(URL url, ResourceBundle rb) {
        descripcionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        precioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }
    
    public void verTicket(){
        HashMap<String,String> parametros = new HashMap<String,String>();
        parametros.put("p_idventa", "1");
        String path = JasperReports.generarReporteJasper("Ticket_Ventas",parametros);
        //EXTRA SOLO SI QUIEREN ABRIR EL PDF EN LA MAQUINA
        openPDF(path);
    }
    
    public static void openPDF(String url){
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(url);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void cambiarDescripcion(CellEditEvent edditcell){
        logica.ArticuloVenta articulo = tablaArticulos.getSelectionModel().getSelectedItem();
        articulo.setDescripcion(edditcell.getNewValue().toString());
        datos.Articuloventa.editarDescripcionArticuloVenta(articulo);
        utilerias.mensajes.mensage("La descripción ha sido editada");
    }
    
    public void cambiarPrecio(CellEditEvent edditcell){
        logica.ArticuloVenta articulo = tablaArticulos.getSelectionModel().getSelectedItem();
        articulo.setPrecioVenta((Double)edditcell.getNewValue());
        datos.Articuloventa.editarPrecioArticuloVenta(articulo);
        utilerias.mensajes.mensage("El precio ha sido editado");
    }
    
    public void guardarEdicionArticulo(){
        descripcionColumn.setOnEditCommit(data -> { data.getRowValue().setDescripcion(data.getNewValue()); });
        precioColumn.setOnEditCommit(data -> { data.getRowValue().setPrecioVenta(data.getNewValue()); });
        System.out.println(tablaArticulos.getSelectionModel().getSelectedItem().getDescripcion());
        System.out.println(tablaArticulos.getSelectionModel().getSelectedItem().getPrecioVenta());
    }

    @FXML
    public void botonMostrar() {
        if (utilerias.validacion.seleccionado(tablaArticulos)) {
            parametrosInterfaz = utilerias.mensajes.nuevaInterfaz("GuiFotos.fxml", this);
            GuiFotosController fotosController = (GuiFotosController) ((FXMLLoader) parametrosInterfaz.get("Loader")).getController();
            fotosController.recibeIdPrenda(tablaArticulos.getSelectionModel().getSelectedItem().getPrenda().getIdPrenda());
            //deseleccionar 
            tablaArticulos.getSelectionModel().clearSelection();
        }else{
            if(utilerias.validacion.seleccionado(tablaCarrito)){
               parametrosInterfaz = utilerias.mensajes.nuevaInterfaz("GuiFotos.fxml", this);
            GuiFotosController fotosController = (GuiFotosController) ((FXMLLoader) parametrosInterfaz.get("Loader")).getController();
            fotosController.recibeIdPrenda(tablaCarrito.getSelectionModel().getSelectedItem().getPrenda().getIdPrenda());
            tablaCarrito.getSelectionModel().clearSelection();
            }
            else{
                utilerias.mensajes.mensage("Favor de seleccionar una articulo para mostrar las fotos");
            }
        }

    }

    @FXML
    public void llenarTablaArticulos() {
            List<ArticuloVenta> listabusqueda = datos.Articuloventa.obtenArticuloVentas(TFBuscarArticulos.getText());
            ObservableList<logica.ArticuloVenta> obsArticulos = FXCollections.observableArrayList(estaEnCarrito(listabusqueda));
            tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("tipoArticulo"));                                    
            descripcionColumn.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("descripcion"));                        
            precioColumn.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, Double>("precioVenta"));           
            tablaArticulos.setItems(obsArticulos);
       
    }
    @FXML
    public void buscarBotonArticulos(){
        TFBuscarArticulos.setText(TFBuscarArticulos.getText().trim());
        if (TFBuscarArticulos.getText() != "" || TFBuscarArticulos != null) {
            llenarTablaArticulos();
        } else {
            utilerias.mensajes.mensage("favor de introducir un valor para la busqueda");
        }
    }

    @FXML
    public void llenarTablaCarrito() {
        ObservableList<logica.ArticuloVenta> obsArticulos = FXCollections.observableArrayList(ListaCarrito);
        tipoArticuloCarrito.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("tipoArticulo"));
        descripcionCarrito.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("descripcion"));
        precioCarrito.setCellValueFactory(new PropertyValueFactory<ArticuloVenta, String>("precioVenta"));
        tablaCarrito.setItems(obsArticulos);

    }

    @FXML
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
            for (int i = 0; i < ListaCarrito.size(); i++) {
                for (int x = 0; x < listaEntrada.size(); x++) {
                    if (ListaCarrito.get(i).getIdArticuloVenta() == listaEntrada.get(x).getIdArticuloVenta()) {
                        listaEntrada.remove(x);
                        break;
                    }
                }
            }
            listaSalida = listaEntrada;
        }
        return listaSalida;
    }
    @FXML
    public void ventaORemate(){
        if(((int)parametrosGlobales.get("Venta")==0)){
            agregarVenta();
        }else{
            agregarRemate();
        }
    }
    public void agregarVenta() {
        if (utilerias.validacion.seleccionado(tablaArticulos)) {
            if (utilerias.mensajes.mensajeCarrito(tablaArticulos.getSelectionModel().getSelectedItem())) {
                agregarCarrito();
            }
        } else {
            utilerias.mensajes.mensage("favor de seleccionar un articulo para agregar a su carrito");
        }
    }
    public void agregarCarrito(){
        ListaCarrito.add(tablaArticulos.getSelectionModel().getSelectedItem());
        tablaArticulos.getItems().remove(tablaArticulos.getSelectionModel().getSelectedIndex());
        tablaArticulos.refresh();
        llenarTablaCarrito();
         sumarTotal();
    }
    @FXML
    public void eliminarCarrito() {
        if (utilerias.validacion.seleccionado(tablaCarrito)) {
            ListaCarrito.remove(tablaCarrito.getSelectionModel().getSelectedIndex());
            tablaCarrito.getItems().remove(tablaCarrito.getSelectionModel().getSelectedIndex());
            tablaCarrito.refresh();
            llenarTablaCarrito();
            sumarTotal();

        } else {
            utilerias.mensajes.mensage("favor de seleccionar un articulo para eliminar de su carrito");
        }
    }
    public void sumarTotal(){
        if(!ListaCarrito.isEmpty()){
            txtTotal.setText(String.valueOf(suma(ListaCarrito)));
        }
    }
    private double suma(List<ArticuloVenta> lista){
        double monto=0;
        for(int i=0; i<lista.size(); i++){
            monto=monto+lista.get(i).getPrecioVenta();
        }
        return monto;
    }

    public void recibeHashMap(Map<String, Object> parametros) {
        parametrosGlobales = parametros;
    }
    @FXML
    public void generaVenta(){
        if(!utilerias.validacion.seleccionado(tablaClientes)){
            if(utilerias.mensajes.mensageConfirmacion("Venta Sin Cliente", "Desea guardar la venta como publico general")){
                datos.Venta.guardarVenta(regresaVenta(), ListaCarrito);
            }   
        }else{
            datos.Venta.guardarVenta(regresaVenta(),ListaCarrito);
        }       
    }
    
    @FXML
    public void seleccionaVentaRemate(){
         if(((int)parametrosGlobales.get("Venta")==0)){
             generaVenta();
             limpia();
         }else{
             generaRemate();
             limpia();
         }
    }
    
    public logica.Venta regresaVenta(){
        logica.Venta venta = new logica.Venta();
        venta.setFechaHora(utilerias.fechas.fecha());
        venta.setEmpleado(datos.Empleado.datosALogicaClonar(datos.Empleado.recuperarEmpleado(Integer.parseInt(parametrosGlobales.get("idSesion").toString()))));
        venta.setGananciaTotal(datos.Articuloventa.regresaMonto(ListaCarrito));
        if(utilerias.validacion.seleccionado(tablaClientes)){
            venta.setCliente(tablaClientes.getSelectionModel().getSelectedItem());
        }else{
            logica.Cliente cliente = new logica.Cliente();
            cliente.setIdCliente(1);
            venta.setCliente(cliente);
        }
        return venta;
    }
    public logica.Remate regresaRemate(){
         logica.Remate remate= new logica.Remate();
        remate.setFechaHora(utilerias.fechas.fecha());
        remate.setEmpleado(datos.Empleado.datosALogicaClonar(datos.Empleado.recuperarEmpleado(Integer.parseInt(parametrosGlobales.get("idSesion").toString()))));
        remate.setPerdidaTotal(datos.Articuloventa.regresaMonto(ListaCarrito));
        if(utilerias.validacion.seleccionado(tablaClientes)){
            remate.setCliente(tablaClientes.getSelectionModel().getSelectedItem());
        }else{
            logica.Cliente cliente = new logica.Cliente();
            cliente.setIdCliente(1);
            remate.setCliente(cliente);
        }
        return remate;
    }

    private void agregarRemate() {
        if (utilerias.validacion.seleccionado(tablaArticulos)) {
            double nuevoPrecio=utilerias.mensajes.remate(tablaArticulos.getSelectionModel().getSelectedItem());
            if(tablaArticulos.getSelectionModel().getSelectedItem().getPrenda().getMontoPrestamo()>=nuevoPrecio){
                utilerias.mensajes.mensage("la prenda no puede tener un precio menor al de lo que se presto");
            }else{
                tablaArticulos.getSelectionModel().getSelectedItem().setPrecioVenta(nuevoPrecio);
                agregarCarrito();
            }
        }
    }

    private void generaRemate() {
         if(!utilerias.validacion.seleccionado(tablaClientes)){
            if(utilerias.mensajes.mensageConfirmacion("Remate Sin Cliente", "Desea guardar el Remate como publico general")){
               datos.Remate.guardarRemate(regresaRemate());
               limpia();
            }   
    }else{
            datos.Remate.guardarRemate(regresaRemate());
        }
    }
    public void limpia(){
        tablaArticulos.getItems().remove(0, tablaArticulos.getSelectionModel().getSelectedIndex());
        tablaClientes.getItems().remove(0,tablaClientes.getSelectionModel().getSelectedIndex());
        tablaCarrito.getItems().remove(0,tablaCarrito.getSelectionModel().getSelectedIndex());
    }
    public void imprimeTicket(){
        if(((int)parametrosInterfaz.get("Venta"))==0){
            
        }
    }
}
