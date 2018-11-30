/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.Remate;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIHistorialRemateController implements Initializable {

    @FXML
    private TableView<Remate> tablaRemate;
    @FXML
    private TableColumn<Remate, String> fechaVentaColumn;
    @FXML
    private TableColumn<Remate, String> empleadoColumn;
    @FXML
    private TableColumn<Remate, String> gananciaColumn;
    @FXML
    private TableColumn<Remate, String> clienteColumn;
    @FXML
    private Button atrasButton;
    @FXML
    private Button adelanteButton;
    @FXML
    private Button buscarButton;
    @FXML
    private Button detallesButton;
    @FXML
    private TextField txtBuscar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void llenaTabla(List<logica.Remate> listaRemate){
        ObservableList<logica.Remate> obsRemate = FXCollections.observableArrayList(listaRemate);
        fechaVentaColumn.setCellValueFactory(new PropertyValueFactory<Remate, String>("txtfecha"));
        empleadoColumn.setCellValueFactory(new PropertyValueFactory<Remate, String>("empleado"));
        gananciaColumn.setCellValueFactory(new PropertyValueFactory<Remate, String>("perdidaTotal"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<Remate, String>("cliente"));
        tablaRemate.setItems(obsRemate);
    }
    private int auxNavegacion = 0;
    private int navegacion = 1;
    //rango navegacion define cuantos registros se muestran en la tabla
    private int rangoNavegacion = 5;
    @FXML
    public void navegarAtras() {
        navegacion = navegacion - (rangoNavegacion);
        auxNavegacion = navegacion - (rangoNavegacion);
        if (auxNavegacion == 1 || navegacion == 1) {
            atrasButton.setDisable(true);
        }
        List<logica.Remate> listaRemate = datos.Remate.remateNavegacion(auxNavegacion, navegacion);
        llenaTabla(listaRemate);
        adelanteButton.setDisable(false);
    }

    @FXML
    public void navegarAdelante() {
        navegacion = navegacion + rangoNavegacion;
        auxNavegacion = navegacion - rangoNavegacion;
        if (navegacion != 1) {
            atrasButton.setDisable(false);
        }
        List<logica.Remate> listaRemate = datos.Remate.remateNavegacion(auxNavegacion, navegacion);
        llenaTabla(listaRemate);
        if (listaRemate.contains(null)) {
            adelanteButton.setDisable(true);
        }
    }
    @FXML
    public void buscarVenta(){
        txtBuscar.setText(txtBuscar.getText().trim());
        if(txtBuscar.getText() != "" || txtBuscar != null){ 
            llenaTabla(datos.Remate.buscarRemate(txtBuscar.getText()));
        }else{
            utilerias.mensajes.mensage("favor de ingresar un criterio de busqueda");
        }
    }
}
