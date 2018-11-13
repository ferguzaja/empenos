/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import logica.Empeno;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIRefrendoController implements Initializable {

    private GUIEmpenoController controller;
    private Stage stagemaster;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void recibeParametros(Stage planillaStage, Empeno empeno, GUIEmpenoController control) {
    stagemaster=planillaStage;
    controller=control;
    }
    
}
