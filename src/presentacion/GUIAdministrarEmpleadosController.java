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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button EditarEmpleadoButton;
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private TableView<logica.Empleado> tablaEmpleados;
    
    @FXML
    private TableColumn<Empleado, String> nombreColumn;
    
    @FXML
    private TableColumn<Empleado, String> apMaternoColumn;
    
    @FXML
    private TableColumn<Empleado, String> apPaternoColumn;
    
    @FXML
    private TableColumn<Empleado, String> direccionColumn;
    
    @FXML
    private TableColumn<Empleado, String> tipoEmpleadoColumn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void botonNuevoEmpleado(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("GUIAltaEmpleado.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
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
            if (empleado.get(i).getNombre().equals(txtBuscar.getText())) {
                logica.Empleado emple = new logica.Empleado();
                emple.setnombre(empleado.get(i).getNombre());
                emple.setapellidoPaterno(empleado.get(i).getApellidoPaterno());
                emple.setapellidoMaterno(empleado.get(i).getApellidoMaterno());
                emple.setdireccion(empleado.get(i).getDireccion());
                emple.settipoUsuario(empleado.get(i).getTipoempleadoIdtipoempleado().getNombre());
                listaEmpleado.add(emple);
            }
        }
        for (int i = 0; i < listaEmpleado.size(); i++) {
            System.out.println(listaEmpleado.get(i).getnombre());
        }

        ObservableList<logica.Empleado> obsEmpleado = FXCollections.observableArrayList(listaEmpleado);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("nombre"));
        apMaternoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("apellidoPaterno"));
        apPaternoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("apellidoMaterno"));
        direccionColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("direccion"));
        tipoEmpleadoColumn.setCellValueFactory(new PropertyValueFactory<Empleado,String> ("Tipo Empleado"));
        
        tablaEmpleados.setItems(obsEmpleado);
    }
    @FXML
    private void botonEditarEmpleado(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GUIEditarEmpleado.fxml"));
            Empleado empleado = tablaEmpleados.getSelectionModel().getSelectedItem();
            //aqui se manda los datos del empleado seleccionado
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIAdministrarEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
