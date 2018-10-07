/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import logica.Persona;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIAltaEmpleadoController implements Initializable {
@FXML
    private TextField nombre;
    @FXML
    private TextField apellidoP;
    @FXML
    private TextField apellidoM;
    @FXML
    private TextField direccion;
    @FXML
    private TextField telefono;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contraseña;
    @FXML
    private PasswordField confirmacion;
    @FXML
    private JComboBox tipo;
    @FXML
    private JButton guardar;
    @FXML
    private JButton cancelar;
    
    @FXML
    private void botonGuardar(ActionEvent event) {
    elliminarEspacios();
    if(!validarCamposVacios()){
        mensajePantalla("Favor de no dejar Campos Vacios");
    }else{
        if(contraseña.equals(confirmacion)){
            
        }else{
            mensajePantalla("contraseñas no coinciden");
        }
    }
    }
    
    @FXML
    private void elliminarEspacios( ) {
    nombre.setText(nombre.getText().trim());
        apellidoP.setText(apellidoP.getText().trim());
        apellidoM.setText(apellidoM.getText().trim());
        direccion.setText(direccion.getText().trim());
        telefono.setText(telefono.getText().trim());
        contraseña.setText(contraseña.getText().trim());
        usuario.setText(usuario.getText().trim());
        confirmacion.setText(confirmacion.getText().trim());
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
    @FXML
    private boolean validarCamposVacios(){
        boolean correcto=true;
        if(nombre.getText().isEmpty()||"".equals(nombre.getText())){
           correcto=false;
        }
        if(apellidoP.getText().isEmpty()||"".equals(apellidoP.getText())){
            correcto=false;
        }
        if(apellidoM.getText().isEmpty()||"".equals(apellidoM.getText())){
           correcto=false;
        }
        if(direccion.getText().isEmpty()||"".equals(direccion.getText())){
           correcto=false;
        }
        if(telefono.getText().isEmpty()||"".equals(telefono.getText())){
           correcto=false;
        }
        if(usuario.getText().isEmpty()||"".equals(usuario.getText())){
           correcto=false;
        }
        if(contraseña.getText().isEmpty()||"".equals(contraseña.getText())){
            correcto=false;
        }
        if(confirmacion.getText().isEmpty()||"".equals(confirmacion.getText())){
            correcto=false;
        }        
        return correcto;
    }
    @FXML
    private boolean guardarEmpleado(){
        boolean guardar=false;
        //Persona persona = new Persona();
        //Empleado empleado= new Empleado(super(nombre.getText(),apellidoP.getText(),apellidoM.getText(),direccion.getText(),telefono.getText()),usuario.getText(),contraseña.getText(),tipo.getSelectedIndex()+30);
        
        
        
        
        
        return guardar;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
