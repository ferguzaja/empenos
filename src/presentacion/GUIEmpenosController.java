/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Cliente;
import logica.Prenda;
import datos.ClienteJpaController;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIEmpenosController implements Initializable {
    
    //kjabkjsdjasjdbsjdsjd
    
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
    private TableColumn<Prenda, String> fotografia;
    
    @FXML
    private TableColumn<Button, Button> eliminar;
    
    @FXML
    private Button agregarPrenda;
    
    @FXML
    private List<logica.Prenda> listaPrenda = new ArrayList<>();
    
    GUIEmpenosController guiEmpenosControler;
    

    
    @FXML
    private void botonNuevoCliente(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GUICliente.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Nuevo cliente");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIEmpenosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void botonEditarCliente(ActionEvent event) {
        try {            
            //objeto que quiero enviar a la GUICLiente
            logica.Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();                        
            //System.out.println(cliente.getNombre()+cliente.getIdTipoIdentificacion());
            //se agrega el .openStream
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUICliente.fxml").openStream());
            
            //Instancia del controlador 2
            GUIClienteController clienteController = (GUIClienteController) loader.getController();                                                            
            //Ya con la instancia de arriba se puede llamar el método que está en la GUICliente... Lo que hace es pasar
            //como parámetro una instancia de la GUIEmpenos y el objeto
            clienteController.recibeParametros(guiEmpenosControler, cliente);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar cliente");
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
            productosController.recibeVariable(this,planillaStage);
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIAgregarProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void botonBuscarCliente(ActionEvent event) {
        
        ClienteJpaController clienteJPA = new ClienteJpaController();
        List<datos.Cliente> clientes = clienteJPA.findClienteEntities();
        
        List<logica.Cliente> listaClientes = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNombre().contains(txtBuscar.getText())) {
                logica.Cliente client = new logica.Cliente();
                client.setIdCliente(clientes.get(i).getIdcliente());
                client.setNombre(clientes.get(i).getNombre());
                client.setApellidoMaterno(clientes.get(i).getApellidoMaterno());
                client.setApellidoPaterno(clientes.get(i).getApeliidoPaterno());
                client.setDireccion(clientes.get(i).getDireccion());
                client.setIdTipoIdentificacion(clientes.get(i).getTipoidentificacionIdtipoidentificacion().getIdtipoidentificacion());
                client.setNoIdentificacion(clientes.get(i).getNoIdentificacion());
                //client.setFechaNacimiento(clientes.get(i).getFechaNac().toString());
                client.setIdCiudad(clientes.get(i).getCiudadIdciudad().getIdciudad());
                client.setIdPais(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().getPaisIdpais().getIdpais());
                client.setIdEstado(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().getIdestado());
                client.setIdOcupacion(clientes.get(i).getOcupacionIdocupacion().getIdocupacion());
                client.setPais(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().getPaisIdpais().clonar());
                client.setEstado(clientes.get(i).getCiudadIdciudad().getEstadoIdestado().clonar());
                client.setCiudad(clientes.get(i).getCiudadIdciudad().clonar());
                client.setOcupacion(clientes.get(i).getOcupacionIdocupacion().clonar());
                client.setTipoIden(clientes.get(i).getTipoidentificacionIdtipoidentificacion().clonar());
                listaClientes.add(client);
            }
        }
        
        ObservableList<logica.Cliente> obsClientes = FXCollections.observableArrayList(listaClientes);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoPaterno"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        noIdentColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("noIdentificacion"));        
        tablaClientes.setItems(obsClientes);
    }
    
    public void agregarPrenda(Prenda prenda,Stage stage) {
        //en lista prenda se guardan las prendas de ahi jalala
        listaPrenda.add(prenda);
        ObservableList<logica.Prenda> obsPrenda = FXCollections.observableArrayList(listaPrenda);
        tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<Prenda,String>("nombreTipoPrenda"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
        montoAvaluoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoValuo"));
        montoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoPrestamo"));
        //tablaPrenda.setEditable(true);
        tablaPrenda.setItems(obsPrenda);
        stage.close();
        //fotografia.setCellValueFactory(new PropertyValueFactory<Prenda,String>("fotografia"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guiEmpenosControler = this;
    }
    
}
