    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIPrincipalController implements Initializable {
    
    @FXML
    private Pane panelPrincipal;
    
   
    
    @FXML
    private void menuItemParametros(ActionEvent event) {
        try {                                               
            Parent root = FXMLLoader.load(getClass().getResource("GUIParametros.fxml"));            
            panelPrincipal.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void menuItemEmpenos(ActionEvent event) {
        try {                                               
            Parent root = FXMLLoader.load(getClass().getResource("GUIEmpenos.fxml"));            
            panelPrincipal.getChildren().setAll(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void menuItemHistorial(ActionEvent event) {
        try {                                               
            Parent root = FXMLLoader.load(getClass().getResource("GUIEmpeno.fxml"));            
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }        
}
