    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIPrincipalController implements Initializable {
    
    @FXML
    private Pane panelPrincipal;
    
    private Map<String, Object> parametrosGlobales;
    
    @FXML
    private void menuItemParametros(ActionEvent event) {
        try {                                               
            Parent root = FXMLLoader.load(getClass().getResource("GUIParametros.fxml"));            
            panelPrincipal.getChildren().setAll(root);
            System.out.println(parametrosGlobales.get("idSesion"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void menuItemHistorial(ActionEvent event) {
        try {                                               
            /*Parent root = FXMLLoader.load(getClass().getResource("GUIEmpenos.fxml"));            
            panelPrincipal.getChildren().setAll(root);*/
            //se agrega el .openStream
            
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIEmpeno.fxml").openStream());
            
            //Instancia del controlador 2
            GUIEmpenoController empenoController = (GUIEmpenoController) loader.getController();
            //Ya con la instancia de arriba se puede llamar el método que está en la GUICliente... Lo que hace es pasar
            //como parámetro una instancia de la GUIEmpenos y el objeto
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);            
            empenoController.recibeHashMap(parametrosGlobales);
            panelPrincipal.getChildren().setAll(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void menuVentas(ActionEvent event) {
        try {                                               
            /*Parent root = FXMLLoader.load(getClass().getResource("GUIEmpenos.fxml"));            
            panelPrincipal.getChildren().setAll(root);*/
            //se agrega el .openStream          
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIVentas.fxml").openStream());      
            //Instancia del controlador 2
            GUIVentasController ventasController = (GUIVentasController) loader.getController();
            //Ya con la instancia de arriba se puede llamar el método que está en la GUICliente... Lo que hace es pasar
            //como parámetro una instancia de la GUIEmpenos y el objeto
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);            
            ventasController.recibeHashMap(parametrosGlobales);
            panelPrincipal.getChildren().setAll(root);   
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
            
    @FXML
    private void menuEmpleados(ActionEvent event) {
        try {                                               
            Parent root = FXMLLoader.load(getClass().getResource("GUIAdministrarEmpleados.fxml"));            
            panelPrincipal.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void recibeHashMap(Map<String, Object> parametros){
        parametrosGlobales = parametros;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }        
}
