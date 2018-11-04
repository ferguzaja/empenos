/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void recibeParametros(Stage stage,Empeno empeno){
        this.empeno=empeno;
        this.stage=stage;
        TFFecha.setText(empeno.getFechaFinEmpeno().toString());
        llenaTabla(empeno.getIdEmpeno());
        TFMontoPagar.setText(String.valueOf(datos.Prenda.montoPagar(datos.Prenda.encuentraContrato(empeno.getIdEmpeno()))));
    }
    private void llenaTabla(int idEmpeno){
        listaPrendas=datos.Prenda.encuentraContrato(idEmpeno);
        ObservableList<logica.Prenda> obsPrenda = FXCollections.observableArrayList(listaPrendas);
        tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<Prenda,String>("tipoPrenda"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
        montoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoPrestamo"));
        tablaPrenda.setEditable(false);
        tablaPrenda.setItems(obsPrenda);
    }
    
    @FXML
    private void botonFiniquitar(ActionEvent event){
        if(confirmacion()){
            datos.Empeno.finiquitarContrato(cambiarStatus());
            datos.Prenda.cambiarPrendasFiniquitadas(listaPrendas);
            stage.close();
            //

        }
       
    }
    private Empeno cambiarStatus(){
        empeno.setMontoRecibido(Double.valueOf(TFMontoPagar.getText()));
        //recuperar fecha del sistema
        Calendar cal = Calendar.getInstance(); 
        int mes = cal.get(Calendar.MONTH) + 1;
        String fecha = cal.get(cal.YEAR) + "-" + mes + "-" + cal.get(cal.DATE);               
        Date date = java.sql.Date.valueOf(fecha);
        empeno.setFechaFinalizacion(date);
        empeno.setTipoFinalizacion("Finiquito");
        return empeno;
    }
    private boolean confirmacion(){
        boolean confiramcion=false;
        Alert alert = new Alert(AlertType.CONFIRMATION); alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Finiquitar Contrato");
        alert.setContentText("¿Esta Seguro que quiere Finiquitar Su Contrato?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
            confiramcion=true;
        
        return false;
    }
    
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
    
    
    
    
    

