/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.EmpleadoJpaController;
import datos.Tipoempleado;
import datos.TipoempleadoJpaController;
import datos.exceptions.NonexistentEntityException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.Empleado;
import logica.TipoEmpleado;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIEditarEmpleadoController implements Initializable {

    private int idEmpleado;
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
    private ComboBox<TipoEmpleado> tipo;
    @FXML
    private ObservableList<TipoEmpleado> obsTipoEmpleado;
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
    private Empleado empleado;
    private Stage stagemaster;
    
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
    private boolean guardarEmpleado() throws NonexistentEntityException{
        boolean guardar=false;
        try {
            
            EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
            empleadoJPA.edit(obtenEmpleado());
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }
    @FXML
    public void setEmpleado(Empleado empleado,Stage stage){
        nombre.setText(empleado.getNombre());
        apellidoP.setText(empleado.getApellidoPaterno());
        apellidoM.setText(empleado.getApellidoMaterno());
        direccion.setText(empleado.getDireccion());
        telefono.setText(empleado.getTelefono());
        usuario.setText(empleado.getUsuario());
        contraseña.setText(empleado.getPassword());
        confirmacion.setText(empleado.getPassword());
        stagemaster=stage;
        
        //falta el comboBox
    }
    @FXML
    private void botonCancelar(){
        stagemaster.close();
    }
    @FXML
    public void llenarComboTipoEmpleado() {
         TipoempleadoJpaController tipoEmpleadoJPA = new TipoempleadoJpaController();
        List<datos.Tipoempleado> tiposEmpleado = tipoEmpleadoJPA.findTipoempleadoEntities();
        List<TipoEmpleado> listaEmpleados = new ArrayList<>();
        for (int i = 0; i < tiposEmpleado.size(); i++) {
            TipoEmpleado tipos = new TipoEmpleado(tiposEmpleado.get(i).getIdtipoempleado(), tiposEmpleado.get(i).getNombre());
            listaEmpleados.add(tipos);
        }
        obsTipoEmpleado= FXCollections.observableArrayList(listaEmpleados);
        //SelectionModel tipoSeleccionado(prueba);
        //tipo.setSelectionModel(tipoSeleccionado);
        tipo.setItems(obsTipoEmpleado);

    }
    private datos.Empleado obtenEmpleado(){
        datos.Empleado empleado= new datos.Empleado();
        empleado.setIdempleado(idEmpleado);
        empleado.setNombre(nombre.getText());
        empleado.setApellidoPaterno(apellidoP.getText());
        empleado.setApellidoMaterno(apellidoM.getText());
        empleado.setDireccion(direccion.getText());
        empleado.setUsuario(usuario.getText());
        empleado.setContrasena(contraseña.getText());
        Tipoempleado tipoempleado= new Tipoempleado();
        tipoempleado.setIdtipoempleado(tipo.getValue().getIdTipoEmpleado());
        tipoempleado.setNombre(tipo.getValue().getNombre());
        empleado.setTipoempleadoIdtipoempleado(tipoempleado);
        empleado.setTelefono(telefono.getText());
        return empleado;
    }
    @FXML
    private void botonGuardar(ActionEvent event)throws ParseException, NonexistentEntityException {
        elliminarEspacios();
        if(!validarCamposVacios()){
            mensajePantalla("Favor de no dejar Campos Vacios");
        }else{
            if(contraseña.equals(confirmacion)){
                if(guardarEmpleado()){
                    mensajePantalla("Empleado Guardado Exitosamente");
                }else{
                    mensajePantalla("Error");
                }
            }else{
                mensajePantalla("contraseñas no coinciden");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
