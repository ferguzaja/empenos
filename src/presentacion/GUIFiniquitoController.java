/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.EmpenoJpaController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import logica.Empeno;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIFiniquitoController implements Initializable {
    
    @FXML
    private Button buscar;
    @FXML
    private Button finiquito;
    @FXML
    private TextField txtBuscar;
    
    @FXML
    private TableView<logica.Empeno> tablaEmpeno;
    
    @FXML
    private TableColumn<Empeno, String> nombreClienteColumn;
    
    @FXML
    private TableColumn<Empeno, String> fechaFinColumn;
    
    @FXML
    private TableColumn<Empeno, String> noBolsaColum;
    
    @FXML
    private TableColumn<Empeno, String> montoAPagarColummn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void llenarTabla(){
        
    }
    @FXML
    private void buscarEmpenos(ActionEvent event){
        EmpenoJpaController empenoJPA = new EmpenoJpaController();
        List<datos.Empeno> empenos = empenoJPA.findEmpenoEntities();

        List<logica.Empeno> listaEmpenos = new ArrayList<>();
        for (int i = 0; i < empenos.size(); i++) {
            if (empenos.get(i).getClienteIdcliente().getNombre().equals(txtBuscar.getText())) {
                logica.Empeno empenoaux = new logica.Empeno();
                //empenoaux.setM_Cliente(empenos.get(i).getClienteIdcliente().getNombre());
                empenoaux.setFechaFinEmpeno(empenos.get(i).getFechaFinEmpeno());
                empenoaux.setNumBolsa(empenos.get(i).getNoBolsa());
                //aqui va la consulta para sacar el monto
                listaEmpenos.add(empenoaux);
            }
        }

        ObservableList<logica.Empeno> obsEmpenos = FXCollections.observableArrayList(listaEmpenos);
        nombreClienteColumn.setCellValueFactory(new PropertyValueFactory<Empeno,String> ("nombre"));
        fechaFinColumn.setCellValueFactory(new PropertyValueFactory<Empeno,String> ("fecha"));
        noBolsaColum.setCellValueFactory(new PropertyValueFactory<Empeno,String> ("noBolsa"));
        montoAPagarColummn.setCellValueFactory(new PropertyValueFactory<Empeno,String> ("monto a Pagar"));
        
        tablaEmpeno.setItems(obsEmpenos);
    }
    @FXML
    private void botonFiniquitar(ActionEvent event){
        //if(fila seleccionadaget){
            MensajeFiniquitar(tablaEmpeno.getSelectionModel().getSelectedItem());
        //}
       
    }
    @FXML
    private void MensajeFiniquitar(Empeno empeno) {
//        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
//        dialogo.setTitle("Aviso");
//        Button botonno= new Button("No");
//        Button botonSi= new Button("Si");
//        dialogo.setHeaderText(null);
//        dialogo.setContentText(mensaje);
//        dialogo.initStyle(StageStyle.UTILITY);
//        dialogo.showAndWait();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText("¿Desea finiquitar este Empeño?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            mensajePantalla("Empeño Pagado");
            
        }}
    @FXML
    private void mensajePantalla(String mensaje) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("Aviso");
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
    private void pagar(Empeno empeno){
        
    }
    }
    
    
    
    
    

