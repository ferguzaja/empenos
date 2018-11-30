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
import logica.Empeno;
import logica.Venta;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIHistorialVentasController implements Initializable {

    @FXML
    private TableView<Venta> tablaVentas;
    @FXML
    private TableColumn<Venta, String> fechaVentaColumn;
    @FXML
    private TableColumn<Venta, String> empleadoColumn;
    @FXML
    private TableColumn<Venta, String> gananciaColumn;
    @FXML
    private TableColumn<Venta, String> clienteColumn;
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
    public void llenaTabla(List<logica.Venta> listaVenta){
        ObservableList<logica.Venta> obsVenta = FXCollections.observableArrayList(listaVenta);
        fechaVentaColumn.setCellValueFactory(new PropertyValueFactory<Venta, String>("txtfecha"));
        empleadoColumn.setCellValueFactory(new PropertyValueFactory<Venta, String>("empleado"));
        gananciaColumn.setCellValueFactory(new PropertyValueFactory<Venta, String>("gananciaTotal"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<Venta, String>("cliente"));
        tablaVentas.setItems(obsVenta);
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
        List<logica.Venta> listaVentas = datos.Venta.ventasNavegacion(auxNavegacion, navegacion);
        llenaTabla(listaVentas);
        adelanteButton.setDisable(false);
    }

    @FXML
    public void navegarAdelante() {
        navegacion = navegacion + rangoNavegacion;
        auxNavegacion = navegacion - rangoNavegacion;
        if (navegacion != 1) {
            atrasButton.setDisable(false);
        }
        List<logica.Venta> listaVentas = datos.Venta.ventasNavegacion(auxNavegacion, navegacion);
        llenaTabla(listaVentas);
        if (listaVentas.contains(null)) {
            adelanteButton.setDisable(true);
        }
    }
    @FXML
    public void buscarVenta(){
        txtBuscar.setText(txtBuscar.getText().trim());
        if(txtBuscar.getText() != "" || txtBuscar != null){ 
            llenaTabla(datos.Venta.buscarVentas(txtBuscar.getText()));
        }else{
            utilerias.mensajes.mensage("favor de ingresar un criterio de busqueda");
        }
    }
}
