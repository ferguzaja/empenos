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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.Empeno;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIEmpenoController implements Initializable {

    @FXML
    private TextField txtBuscar;
    
    @FXML
    private Button botonBuscar;
    
    @FXML
    private TableView<logica.Empeno> tablaEmpenos;
    
    @FXML
    private TableColumn<Empeno, String> nombreClienteColumn;
    
    @FXML
    private TableColumn<Empeno, String> fechaInicioColumn;
    
    @FXML
    private TableColumn<Empeno, String> fechaFinColumn;
    
    @FXML
    private TableColumn<Empeno, String> numeroExtencionColumn;
    
    @FXML
    private TableColumn<Empeno, String> numeroBolsaColumn;
      
    
    public void buscarEmpenos(ActionEvent event){
        EmpenoJpaController empenoJPA = new EmpenoJpaController();
        List<datos.Empeno> empenos = empenoJPA.findEmpenoEntities();
        
        List<logica.Empeno> listaEmpenos = new ArrayList<>();
        for (int i = 0; i < empenos.size(); i++) {
            if (empenos.get(i).getClienteIdcliente().getNombre().contains(txtBuscar.getText())){
                logica.Empeno emp = new logica.Empeno();
                emp.setNombreCliente(empenos.get(i).getClienteIdcliente().getNombre());
                if(!empenos.get(i).getCotitularidCotitular().getNombre().isEmpty()){
                    emp.setNombreCotitular(empenos.get(i).getCotitularidCotitular().getNombre());
                }
                emp.setFechaInicio(empenos.get(i).getFechaInicioEmpeno());
                emp.setFechaFinEmpeno(empenos.get(i).getFechaFinEmpeno());
                emp.setNumExtencionTiempo(empenos.get(i).getExtencionTiempo());
                emp.setNumBolsa(empenos.get(i).getNoBolsa());
                emp.setIdEmpeno(empenos.get(i).getIdempeno());
                listaEmpenos.add(emp);
            }
        }        
        ObservableList<logica.Empeno> obsempenos = FXCollections.observableArrayList(listaEmpenos);
        nombreClienteColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("nombreCliente"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("fechaInicio"));
        fechaFinColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("fechaFinEmpeno"));
        numeroExtencionColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("numExtencionTiempo"));
        numeroBolsaColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("numBolsa"));
        tablaEmpenos.setItems(obsempenos);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
