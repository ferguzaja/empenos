/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Variables;
import datos.VariablesJpaController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIParametrosController implements Initializable {
    
    @FXML
    private TextField txtIva;
    
    @FXML
    private TextField txtCat;
    
    @FXML
    private TextField txtPorMutuo;
    
    @FXML
    private TextField txtInteMensual;
    
    @FXML
    private TextField txtPorcentajeComer;
    
    
    VariablesJpaController variableJPA = new VariablesJpaController();
    List<Variables> variables = variableJPA.findVariablesEntities();
    
    
    @FXML
    public void mostrarVariables(){        
        txtIva.setText(variables.get(0).getIva().toString());
        txtCat.setText(variables.get(0).getCat().toString());
        txtInteMensual.setText(variables.get(0).getInteresMensual().toString());
        txtPorcentajeComer.setText(variables.get(0).getPorcentajeComercializacion().toString());
        txtPorMutuo.setText(variables.get(0).getPorcentajeMutuo().toString());
    }
    
    @FXML
    public void actualizarVariables(ActionEvent event){
        try {
            datos.Variables variable = new Variables();
            variable.setIdvariables(1);
            variable.setIva(Float.parseFloat(txtIva.getText()));
            variable.setCat(Float.parseFloat(txtCat.getText()));
            variable.setInteresMensual(Float.parseFloat(txtInteMensual.getText()));
            variable.setPorcentajeComercializacion(Float.parseFloat(txtPorcentajeComer.getText()));
            variable.setPorcentajeMutuo(Float.parseFloat(txtPorMutuo.getText()));            
            variableJPA.edit(variable);
        } catch (Exception ex) {
            Logger.getLogger(GUIParametrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        mostrarVariables();        
    }    
    
}
