/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.sun.javafx.scene.control.skin.VirtualFlow;
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
import javafx.stage.Stage;
import javax.swing.JButton;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIEmpenosController implements Initializable {

    @FXML
    private Button botonNuevoCliente;

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
    private TableColumn<Prenda, String> tipoArticulo;
    
    @FXML
    private TableColumn<Prenda, String> descripcion;
    
    @FXML
    private TableColumn<Prenda, String> montoValuo;
    
    @FXML
    private TableColumn<Prenda, String> montoPrestamo;
    
    @FXML
    private TableColumn<Prenda, String> fotografia;
    @FXML
    private TableColumn<Prenda, JButton> eliminar;
    @FXML
    private JButton agregarPrenda;

    @FXML
    private void botonNuevoCliente(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("GUICliente.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIEmpenosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void botonAgregarPrenda(ActionEvent event){
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("GUIAgregarProducto.fxml"));                    
                    Scene scene = new Scene(root);                    
                    Stage stage = new Stage();
                    stage.setScene(scene);                    
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
    }

    @FXML
    private void botonBuscarCliente(ActionEvent event) {

        ClienteJpaController clienteJPA = new ClienteJpaController();
        List<datos.Cliente> clientes = clienteJPA.findClienteEntities();

        List<logica.Cliente> listaClientes = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNombre().equals(txtBuscar.getText())) {
                logica.Cliente client = new logica.Cliente();
                client.setNombre(clientes.get(i).getNombre());
                client.setApellidoMaterno(clientes.get(i).getApellidoMaterno());
                client.setApellidoPaterno(clientes.get(i).getApeliidoPaterno());
                client.setDireccion(clientes.get(i).getDireccion());
                client.setNoIdentificacion(clientes.get(i).getNoIdentificacion());
                listaClientes.add(client);
            }
        }
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println(listaClientes.get(i).getNombre());
        }

        ObservableList<logica.Cliente> obsClientes = FXCollections.observableArrayList(listaClientes);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente,String> ("nombre"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente,String> ("apellidoPaterno"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Cliente,String> ("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente,String> ("direccion"));
        noIdentColumn.setCellValueFactory(new PropertyValueFactory<Cliente,String> ("noIdentificacion"));
        
        tablaClientes.setItems(obsClientes);
    }
    
    public void agregarPrenda(Prenda prenda){
        tipoArticulo.setCellValueFactory(new PropertyValueFactory<Prenda,String>("tipArticulo"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Prenda,String>("descripcion"));
        montoValuo.setCellValueFactory(new PropertyValueFactory<Prenda,String>("montoValuo"));
        montoPrestamo.setCellValueFactory(new PropertyValueFactory<Prenda,String>("montoPrestamo"));
        fotografia.setCellValueFactory(new PropertyValueFactory<Prenda,String>("fotografia"));
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
