/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.EmpenoJpaController;
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
    private Button altaContratoButton;
    @FXML
    private Button refrendarButton;
    @FXML
    private Button finiquitarButton;
    @FXML
    private Button extencionButton;
    @FXML
    private Button verDetallesButton;
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
            if (empenos.get(i).getIdempeno().equals(Integer.parseInt(txtBuscar.getText()))){
                logica.Empeno emp = new logica.Empeno();
                emp.setCliente(empenos.get(i).getClienteIdcliente().clonar());
//                if(!empenos.get(i).getCotitular().isEmpty()){
//                    emp.setCotitular(empenos.get(i).getCotitular());
//                }
                emp.setFechaInicio(empenos.get(i).getFechaInicioEmpeno());
                emp.setFechaFinEmpeno(empenos.get(i).getFechaFinEmpeno());
                emp.setNumExtencionTiempo(empenos.get(i).getExtencionTiempo());
                emp.setNumBolsa(empenos.get(i).getNoBolsa());
                emp.setIdEmpeno(empenos.get(i).getIdempeno());
                listaEmpenos.add(emp);
            }
        }        
        ObservableList<logica.Empeno> obsempenos = FXCollections.observableArrayList(listaEmpenos);
        nombreClienteColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("cliente"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("fechaInicio"));
        fechaFinColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("fechaFinEmpeno"));
        numeroExtencionColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("numExtencionTiempo"));
        numeroBolsaColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("numBolsa"));
        tablaEmpenos.setItems(obsempenos);
    }
    @FXML
    public void verDetalles(){
        if(validarSeleccionado()){
            try {
           FXMLLoader loader= new FXMLLoader();
            AnchorPane root =(AnchorPane)loader.load(getClass().getResource("GUIDetallesContrato.fxml").openStream());
            Scene scene = new Scene(root);
            Stage planillaStage=new Stage();
            planillaStage.setScene(scene);
            GUIDetallesContratoController detallesController=(GUIDetallesContratoController)loader.getController();
            detallesController.recibe(tablaEmpenos.getSelectionModel().getSelectedItem());
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIEmpenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    @FXML
    public void botonFiniquitar(){
        if(validarSeleccionado()){
            try {
           FXMLLoader loader= new FXMLLoader();
            AnchorPane root =(AnchorPane)loader.load(getClass().getResource("GUIFiniquito.fxml").openStream());
            Scene scene = new Scene(root);
            Stage planillaStage=new Stage();
            planillaStage.setScene(scene);
            GUIFiniquitoController finiquitarController=(GUIFiniquitoController)loader.getController();
            finiquitarController.recibeParametros(planillaStage,tablaEmpenos.getSelectionModel().getSelectedItem());
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIEmpenoController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
    }
    public boolean validarSeleccionado(){
        boolean seleccionado=false;
        if(tablaEmpenos.getSelectionModel().getSelectedItem()!=null){
            seleccionado=true;
        }
        return seleccionado;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
