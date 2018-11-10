/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;
import logica.Empleado;
import datos.EmpleadoJpaController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIAdministrarEmpleadosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button nuevoEmpleadoButton;
    @FXML
    private Button buscar;
    @FXML
    private Button editarEmpleadoButton;
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private TableView<logica.Empleado> tablaEmpleados;
    
    @FXML
    private TableColumn<Empleado, String> nombreColumn;
    
    @FXML
    private TableColumn<Empleado, String> apPaternoColumn;
    
    @FXML
    private TableColumn<Empleado, String> apMaternoColumn;
    
    @FXML
    private TableColumn<Empleado, String> direccionColumn;
    
    @FXML
    private TableColumn<Empleado, String> tipoEmpleadoColumn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenaTabla();
        // TODO
    }
    @FXML
    private void botonNuevoEmpleado(ActionEvent event) {

        try {
            FXMLLoader loader= new FXMLLoader();
            
            //agregamos el openStream (no se para que)
            AnchorPane root =(AnchorPane)loader.load(getClass().getResource("GUIAltaEmpleado.fxml").openStream());
            //ahora creo una instancia del controlador del form que voy a abrir casteando
            Scene scene = new Scene(root);
            Stage planillaStage=new Stage();
            planillaStage.setScene(scene);           
            GUIAltaEmpleadoController empleadoController=(GUIAltaEmpleadoController)loader.getController();
            empleadoController.recibeStage(planillaStage,this);
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIAdministrarEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    @FXML
    private void botonBuscarEmpleado(ActionEvent event) {

        EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
        List<datos.Empleado> empleado = empleadoJPA.findEmpleadoEntities();

        List<logica.Empleado> listaEmpleado = new ArrayList<>();
        for (int i = 0; i < empleado.size(); i++) {
            if (empleado.get(i).getNombre().contains(txtBuscar.getText())) {
                logica.Empleado emple = new logica.Empleado();
                emple.setIdEmpleado(empleado.get(i).getIdempleado());
                emple.setNombre(empleado.get(i).getNombre());
                emple.setApellidoPaterno(empleado.get(i).getApellidoPaterno());
                emple.setApellidoMaterno(empleado.get(i).getApellidoMaterno());
                emple.setDireccion(empleado.get(i).getDireccion());
                emple.setTelefono(empleado.get(i).getTelefono());
                emple.setUsuario(empleado.get(i).getUsuario());
                emple.setNombreTipoEmpleado(empleado.get(i).getTipoempleadoIdtipoempleado().getNombre());
                emple.setPassword(empleado.get(i).getContrasena());
                emple.setTipoUsuario(empleado.get(i).getTipoempleadoIdtipoempleado().getIdtipoempleado());
                listaEmpleado.add(emple);
                }
        }

        ObservableList<logica.Empleado> obsEmpleado = FXCollections.observableArrayList(listaEmpleado);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("nombre"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("apellidoPaterno"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("direccion"));
        tipoEmpleadoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("nombreTipoEmpleado"));
        
        tablaEmpleados.setItems(obsEmpleado);
    }
    @FXML
    private void botonEditarEmpleado(ActionEvent event){
        if(utilerias.validacion.seleccionado(tablaEmpleados)){
       try {
           FXMLLoader loader= new FXMLLoader();
            AnchorPane root =(AnchorPane)loader.load(getClass().getResource("GUIEditarEmpleado.fxml").openStream());
            Scene scene = new Scene(root);
            Stage planillaStage=new Stage();
            planillaStage.setScene(scene);           
            GUIEditarEmpleadoController empleadoController=(GUIEditarEmpleadoController)loader.getController();
            empleadoController.setEmpleado(tablaEmpleados.getSelectionModel().getSelectedItem(),planillaStage,this);
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIAdministrarEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }}else{
           utilerias.mensajes.mensage("favor de seleccionar un empleado");
        }
        }
    public void llenaTabla(){
        tablaEmpleados.getSelectionModel().clearSelection();
        EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
        List<datos.Empleado> empleado = empleadoJPA.findEmpleadoEntities();
        List<logica.Empleado> listaEmpleado = new ArrayList<>();
        for (int i = 0; i < empleado.size(); i++) {
                logica.Empleado emple = new logica.Empleado();emple.setIdEmpleado(empleado.get(i).getIdempleado());
                emple.setNombre(empleado.get(i).getNombre());
                emple.setApellidoPaterno(empleado.get(i).getApellidoPaterno());
                emple.setApellidoMaterno(empleado.get(i).getApellidoMaterno());
                emple.setDireccion(empleado.get(i).getDireccion());
                emple.setTelefono(empleado.get(i).getTelefono());
                emple.setUsuario(empleado.get(i).getUsuario());
                emple.setNombreTipoEmpleado(empleado.get(i).getTipoempleadoIdtipoempleado().getNombre());
                emple.setPassword(empleado.get(i).getContrasena());
                emple.setTipoUsuario(empleado.get(i).getTipoempleadoIdtipoempleado().getIdtipoempleado());
                listaEmpleado.add(emple);
            
        }

        ObservableList<logica.Empleado> obsEmpleado = FXCollections.observableArrayList(listaEmpleado);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("nombre"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("apellidoPaterno"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("direccion"));
        tipoEmpleadoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("nombreTipoEmpleado"));
        
        tablaEmpleados.setItems(obsEmpleado);
        
    }
    }
    
