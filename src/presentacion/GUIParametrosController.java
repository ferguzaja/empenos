/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Tipoidentificacion;
import datos.Variables;
import datos.VariablesJpaController;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import logica.Variable;

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
        
        /*logica.Variable variable = new Variable();
        variable.setIva(variables.get(0).getIva());
        variable.setCat(variables.get(0).getCat());
        variable.setIntereMensual(variables.get(0).getInteresMensual());
        variable.setPorcentajeComercializacion(variables.get(0).getPorcentajeComercializacion());
        variable.setPorcentajeMutuo(variables.get(0).getPorcentajeMutuo());*/
        
        txtIva.setText(variables.get(0).getIva().toString());
        txtCat.setText(variables.get(0).getCat().toString());
        txtInteMensual.setText(variables.get(0).getInteresMensual().toString());
        txtPorcentajeComer.setText(variables.get(0).getPorcentajeComercializacion().toString());
        txtPorMutuo.setText(variables.get(0).getPorcentajeMutuo().toString());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        mostrarVariables();        
    }    
    
}
