/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Cliente;
import logica.Prenda;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.Empeno;
import logica.FotoPrenda;
import utilerias.fechas;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIEmpenosController implements Initializable {

    @FXML
    private Button botonNuevoCliente;

    @FXML
    private Button botonEditarCliente;


    @FXML
    private TextField txtBuscar;

    @FXML
    private TableView<logica.Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> nombreColumn;

    @FXML
    private TableColumn<Cliente, String> apMaternoColumn;

    @FXML
    private TableColumn<Cliente, String> apPaternoColumn;

    @FXML
    private TableColumn<Cliente, String> direccionColumn;

    @FXML
    private TableColumn<Cliente, String> noIdentColumn;

    @FXML
    private TableView<logica.Prenda> tablaPrenda;

    @FXML
    private TableColumn<Prenda, String> tipoArticuloColum;

    @FXML
    private TableColumn<Prenda, String> descripcionColumn;

    @FXML
    private TableColumn<Prenda, String> montoAvaluoColumn;

    @FXML
    private TableColumn<Prenda, String> montoPrestamoColumn;


    @FXML
    private Button agregarPrenda;

    private List<logica.Prenda> listaPrenda = new ArrayList<>();
    private List<List<FotoPrenda>> arregloDeFotos = new ArrayList<>();

    GUIEmpenosController guiEmpenosControler;

    private int clicEditar;
    private Map<String, Object> parametrosGlobales;
    private Map<String, Object> parametrosInterfaz;
    @FXML
    private Button botonBuscar;
    @FXML
    private Button botonGuardarContrato;
    @FXML
    private Button editarPrendaButton;
    @FXML
    private Button eliminarPrendaButton;
    @FXML
    private Button mostrarFotosButton;
    @FXML
    private void botonNuevoCliente(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUICliente.fxml").openStream());

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            GUIClienteController clienteController = (GUIClienteController) loader.getController();
            clienteController.recibeStage(stage);
            stage.setTitle("Nuevo cliente");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIEmpenosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void botonGuardarContrato(ActionEvent event) {
        if (utilerias.validacion.seleccionado(tablaClientes) || !tablaPrenda.getSelectionModel().isEmpty()) {
            //Registro del empeño
            Empeno empeno = new logica.Empeno();
            Date date = utilerias.fechas.fecha();
            empeno.setFechaInicio(date);
            empeno.setFechaFinEmpeno(fechas.aumentaDias(date, 30));

            empeno.setIdEmpleado(datos.Empleado.datosALogicaClonar(datos.Empleado.recuperarEmpleado(Integer.parseInt(parametrosGlobales.get("idSesion").toString()))));

            //falta agregar en la gui el cotitular
            empeno.setCotitular("");
            empeno.setEstatus("activo");
            empeno.setNumExtencionTiempo(0);
            empeno.setCliente(tablaClientes.getSelectionModel().getSelectedItem());
            datos.Empeno.guardarEmpeno(empeno);

            datos.Prenda.guardarPrendas(asignaID(datos.Empeno.recuperaID()));
            empeno.setIdEmpeno(datos.Empeno.recuperaID().getIdEmpeno());
            empeno.setNumBolsa(datos.Empeno.recuperaID().getIdEmpeno());
            datos.Empeno.actualizarEmpeno(empeno);
            datos.Variblesempeno.guardar(datos.Variblesempeno.convertir(datos.Variables.traerVariables(), datos.Empeno.recuperaID()));
            datos.Pago.guardarPago(datos.Pago.regresaListaPagos(datos.Variblesempeno.buscarVariables(datos.Empeno.recuperaID()), datos.Empeno.recuperaID()));
            asignaIdPrenda(datos.Prenda.prendasPorContrato(datos.Empeno.recuperaID().getIdEmpeno(), true));
            datos.Fotoprenda.guardarFotosPrendas(arregloDeFotos);
            ((Stage) parametrosInterfaz.get("Stage")).close();
            ((GUIEmpenoController) parametrosInterfaz.get("Controller")).navegarAdelante();
            ((GUIEmpenoController) parametrosInterfaz.get("Controller")).navegarAtras();
        } else {
            utilerias.mensajes.mensage("Debe de Seleccionar un cliente y tener prendas para realizar un contrato");
        }
    }

    private List<logica.Prenda> asignaID(Empeno empeno) {
        for (int i = 0; i < listaPrenda.size(); i++) {
            listaPrenda.get(i).setEmpeno(empeno);
        }
        return listaPrenda;
    }

    private void asignaIdPrenda(List<logica.Prenda> prendas) {
        for (int i = 0; i < prendas.size(); i++) {
            for (int x = 0; x < arregloDeFotos.get(i).size(); x++) {
                arregloDeFotos.get(i).get(x).setPrenda(prendas.get(i));
            }
        }
    }

    @FXML
    private void botonEditarCliente(ActionEvent event) {
        try {
            clicEditar = 1;
            //objeto que quiero enviar a la GUICLiente
            logica.Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
            //se agrega el .openStream
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUICliente.fxml").openStream());

            //Instancia del controlador 2
            GUIClienteController clienteController = (GUIClienteController) loader.getController();
            //Ya con la instancia de arriba se puede llamar el método que está en la GUICliente... Lo que hace es pasar
            //como parámetro una instancia de la GUIEmpenos y el objeto
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar cliente");
            clienteController.recibeParametros(guiEmpenosControler, cliente, clicEditar, stage);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIEmpenosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void botonAgregarPrenda(ActionEvent event) {
        parametrosInterfaz=utilerias.mensajes.nuevaInterfaz("GUIAgregarProducto.fxml", this);
        GUIAgregarProductoController productosController = (GUIAgregarProductoController) ((FXMLLoader)parametrosInterfaz.get("Loader")).getController();
        parametrosInterfaz.put("Empenos",guiEmpenosControler);
        parametrosInterfaz.put("Prenda",0);
        productosController.recibeHasgMap(parametrosInterfaz);
    }
    @FXML
    private void botonEditarPrenda(ActionEvent event) {
        if(utilerias.validacion.seleccionado(tablaPrenda)){
        parametrosInterfaz=utilerias.mensajes.nuevaInterfaz("GUIAgregarProducto.fxml", this);
        GUIAgregarProductoController productosController = (GUIAgregarProductoController) ((FXMLLoader)parametrosInterfaz.get("Loader")).getController();
        parametrosInterfaz.put("Empenos",guiEmpenosControler);
        parametrosInterfaz.put("Prenda",1);
        parametrosInterfaz.put("Editar", tablaPrenda.getSelectionModel().getSelectedItem());
        parametrosInterfaz.put("posicion",tablaPrenda.getSelectionModel().getSelectedIndex());
        parametrosInterfaz.put("ListaFotos", arregloDeFotos.get(tablaPrenda.getSelectionModel().getSelectedIndex()));
        productosController.recibeHasgMap(parametrosInterfaz);
        }else{
            utilerias.mensajes.mensage("favor de seleccionar una Prenda para editar");
        }
    }

    @FXML
    private void botonBuscarCliente(ActionEvent event) {
        llenarTablaClientes(datos.Cliente.buscaClientes(txtBuscar.getText()));

    }

    private void llenarTablaClientes(List<logica.Cliente> listaClientes) {

        ObservableList<logica.Cliente> obsClientes = FXCollections.observableArrayList(listaClientes);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoPaterno"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        noIdentColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("noIdentificacion"));
        tablaClientes.setItems(obsClientes);
    }

    public void agregarPrenda(Prenda prenda, List<FotoPrenda> listaFotos) {
        //en lista prenda se guardan las prendas de ahi jalala
        listaPrenda.add(prenda);
        arregloDeFotos.add(listaFotos);
        llenarTablaPrenda();

    }

    public void llenarTablaPrenda() {
        ObservableList<logica.Prenda> obsPrenda = FXCollections.observableArrayList(listaPrenda);
        tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<Prenda, String>("tipoPrenda"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
        montoAvaluoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoValuo"));
        montoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoPrestamo"));
        tablaPrenda.setItems(obsPrenda);
    }

    public void recibeHashMap(Map<String, Object> parametros, Map<String, Object> parametrosInterfaz) {
        parametrosGlobales = parametros;
        this.parametrosInterfaz = parametrosInterfaz;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guiEmpenosControler = this;
    }

    public void recibeReEmpeno(Map<String, Object> parametrosGlobales, Map<String, Object> parametrosInterfaz, Empeno selectedItem) {
        this.parametrosGlobales = parametrosGlobales;
        this.parametrosInterfaz = parametrosInterfaz;
        llenarReEmpeno(selectedItem);
    }

    public void llenarReEmpeno(Empeno empeno) {
        listaPrenda = datos.Prenda.prendasPorContrato(empeno.getIdEmpeno(), false);
        arregloDeFotos = datos.Fotoprenda.devuelveArregloFotos(datos.Prenda.prendasPorContrato(empeno.getIdEmpeno(), true), false);
        List<logica.Cliente> lista = new ArrayList<>();
        lista.add(empeno.getCliente());
        llenarTablaClientes(lista);
        llenarTablaPrenda();
    }  
    public void editarPrenda(Prenda prenda, List<FotoPrenda> listaFotos, int numero) {
        listaPrenda.set(numero, prenda);
        arregloDeFotos.set(numero, listaFotos);
        tablaPrenda.getItems().remove(0, listaPrenda.size());
        llenarTablaPrenda();
    }
    @FXML
    public void eliminaPrenda(){
        if(utilerias.validacion.seleccionado(tablaPrenda)){
            listaPrenda.remove(tablaPrenda.getSelectionModel().getSelectedIndex());
            tablaPrenda.getItems().remove(tablaPrenda.getSelectionModel().getSelectedIndex());
            arregloDeFotos.remove(tablaPrenda.getSelectionModel().getSelectedIndex());
        }else{
            utilerias.mensajes.mensage("Favor de seleccionar una prenda para eliminar");
        }
    }
     @FXML
    public void botonMostrar() {
        if (utilerias.validacion.seleccionado(tablaPrenda)) {
            Map<String, Object> parametrosfoto= utilerias.mensajes.nuevaInterfaz("GuiFotos.fxml", this);
            GuiFotosController fotosController = (GuiFotosController) ((FXMLLoader) parametrosfoto.get("Loader")).getController();
            fotosController.recibeLista(arregloDeFotos.get(tablaPrenda.getSelectionModel().getSelectedIndex()));
        }else{
            utilerias.mensajes.mensage("Favor de seleccionar una prenda para ver las fotos");
        }

    }
}
