/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logica.Empeno;
import logica.Prenda;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIFiniquitoController implements Initializable {
    
    @FXML
    private Button finiquito;
    @FXML
    private TextField TFMontoPagar;
    @FXML
    private TextField TFFecha;
    @FXML
    private TableView<Prenda> tablaPrenda;
    @FXML
    private TableColumn<Prenda, String> tipoArticuloColum;
    @FXML
    private TableColumn<Prenda, String> descripcionColumn;
    @FXML
    private TableColumn<Prenda, String> montoPrestamoColumn;
    private List<logica.Prenda> listaPrendas;
    private Empeno empeno;
    @FXML
    private ComboBox<String> combo;
    private Stage stage;
    @FXML
    final ToggleGroup group = new ToggleGroup();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarCombo();
        combo.getSelectionModel().select("Por Periodo");
    }
    public void recibeParametros(Stage stage,Empeno empeno){
        this.empeno=empeno;
        this.stage=stage;
        TFFecha.setText(empeno.getFechaFinEmpeno().toString());
        llenaTabla(empeno.getIdEmpeno());
        cambiar();
    }
    private void llenaTabla(int idEmpeno){
        listaPrendas=datos.Prenda.prendasPorContrato(idEmpeno);
        ObservableList<logica.Prenda> obsPrenda = FXCollections.observableArrayList(listaPrendas);
        tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<Prenda,String>("tipoPrenda"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
        montoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoPrestamo"));
        tablaPrenda.setEditable(false);
        tablaPrenda.setItems(obsPrenda);
    }
    public void llenarCombo(){
        List<String> lista = new ArrayList<>();
        lista.add("Por Periodo");
        lista.add("Por Dia");
        ObservableList obs = FXCollections.observableArrayList(lista);
        combo.setItems(obs);
    }
    @FXML
    private void cambiar(){
        if (combo.getSelectionModel().getSelectedIndex()==0){
            TFMontoPagar.setText(String.valueOf(datos.Pago.montoPorPeriodo(empeno)));
    
        } else {
            TFMontoPagar.setText(String.valueOf(datos.Pago.cambiarDias(empeno)));
        }
    }
    @FXML
    private void botonFiniquitar(ActionEvent event){
        if(utilerias.mensajes.mensageConfirmacion("Finiquitar Contrato", "¿Desea finiquitar su contrato?")){
            datos.Empeno.finiquitarContrato(cambiarStatus());
            datos.Prenda.cambiarPrendasFiniquitadas(listaPrendas);
            stage.close();
            //

        }
       
    }
    private Empeno cambiarStatus(){
        empeno.setMontoRecibido(Double.valueOf(TFMontoPagar.getText()));
        empeno.setFechaFinalizacion(utilerias.fechas.Fecha(utilerias.fechas.regresaMilisegundos()));
        empeno.setEstatus("Finiquito");
        return empeno;
    }
    
    
    }
    
    
    
    
    

