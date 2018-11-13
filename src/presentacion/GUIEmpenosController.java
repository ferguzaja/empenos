/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Cliente;
import logica.Prenda;
import datos.ClienteJpaController;
import datos.Empleado;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Button botonAgregarContrato;

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
    private TableColumn<Prenda, String> fotografia;

    @FXML
    private TableColumn<Button, Button> eliminar;

    @FXML
    private Button agregarPrenda;

    @FXML
    private List<logica.Prenda> listaPrenda = new ArrayList<>();

    GUIEmpenosController guiEmpenosControler;

    private int clicEditar;
    private Map<String, Object> parametrosGlobales;
    private GUIEmpenoController control;
    private Stage stagemaster;

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
        if(utilerias.validacion.seleccionado(tablaClientes)||!tablaPrenda.getSelectionModel().isEmpty()){
        //Registro del empeño
        Empeno empeno = new logica.Empeno();
        Date date =utilerias.fechas.Fecha(utilerias.fechas.regresaMilisegundos());
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
        datos.Pago.guardarPago(datos.Pago.regresaLista(datos.Variblesempeno.buscarVariables(datos.Empeno.recuperaID()), datos.Empeno.recuperaID()));
        stagemaster.close();
        control.navegarAdelante();
        control.navegarAtras();
    }else{
          utilerias.mensajes.mensage("Debe de Seleccionar un cliente y tener prendas para realizar un contrato");
        }}
    
    
    private List<logica.Prenda> asignaID(Empeno empeno) {
        for (int i = 0; i < listaPrenda.size(); i++) {
            listaPrenda.get(i).setEmpeno(empeno);
        }
        return listaPrenda;
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
        try {
            FXMLLoader loader = new FXMLLoader();
            //agregamos el openStream (no se para que)
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIAgregarProducto.fxml").openStream());
            //ahora creo una instancia del controlador del form que voy a abrir casteando
            Scene scene = new Scene(root);
            Stage planillaStage = new Stage();
            planillaStage.setScene(scene);
            GUIAgregarProductoController productosController = (GUIAgregarProductoController) loader.getController();
            productosController.recibeVariable(this, planillaStage);
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIAgregarProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void botonBuscarCliente(ActionEvent event) {

        
        

        ObservableList<logica.Cliente> obsClientes = FXCollections.observableArrayList(datos.Cliente.buscaClientes(txtBuscar.getText()));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoPaterno"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        noIdentColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("noIdentificacion"));
        tablaClientes.setItems(obsClientes);
    }

    public void agregarPrenda(Prenda prenda, Stage stage) {
        //en lista prenda se guardan las prendas de ahi jalala
        listaPrenda.add(prenda);
        ObservableList<logica.Prenda> obsPrenda = FXCollections.observableArrayList(listaPrenda);
        tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<Prenda, String>("tipoPrenda"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
        montoAvaluoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoValuo"));
        montoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoPrestamo"));
        //tablaPrenda.setEditable(true);
        tablaPrenda.setItems(obsPrenda);
        stage.close();
        //fotografia.setCellValueFactory(new PropertyValueFactory<Prenda,String>("fotografia"));
    }

    public void recibeHashMap(Map<String, Object> parametros,Stage stage, GUIEmpenoController controller) {
        parametrosGlobales = parametros;
        stagemaster= stage;
        control=controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guiEmpenosControler = this;
    }

}
