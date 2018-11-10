/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.TipoempleadoJpaController;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.stage.StageStyle;
import datos.Empleado;
import logica.TipoEmpleado;
import datos.Tipoempleado;
import javafx.stage.Stage;
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
    private ComboBox<TipoEmpleado> tipo;
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
    @FXML
    private ObservableList<TipoEmpleado> obsTipoEmpleado;
    @FXML
    private Stage stage;
    @FXML
    private ArrayList<TextField> listaText= new ArrayList<>();
    private GUIAdministrarEmpleadosController admin;
    
    @FXML
    private void botonGuardar(ActionEvent event)throws ParseException {
    elliminarEspacios();
    if(!validarCamposVacios()){
        utilerias.mensajes.mensage("Favor de no dejar Campos Vacios");
    }else{
        if(contraseña.getText().equals(confirmacion.getText())){
            guardarEmpleado();
        }else{
            utilerias.mensajes.mensage("contraseñas no coinciden");
        }
    }
    }
    @FXML
    private void elliminarEspacios( ) {
        for(int i=0; i<listaText.size(); i++){
            listaText.get(i).setText(listaText.get(i).getText());
            }
    }
    
    @FXML
    private boolean validarCamposVacios(){
        boolean correcto=true;
        for(int i=0; i<listaText.size(); i++){
            if(listaText.get(i).getText().isEmpty()||"".equals(listaText.get(i).getText())){
                correcto=false;
            }
        }        
        return correcto;
    }
    @FXML
    private void guardarEmpleado(){
        datos.Empleado empleado = new Empleado();
        if(empleado.guardarEmpleado(obtenEmpleado())){
            utilerias.mensajes.mensage("Empleado Guardado Exitosamente");
            admin.llenaTabla();
            stage.close();
        }else{
            utilerias.mensajes.mensage("Error: no se pudo guardar el empleado");
        }                
    }
    private datos.Empleado obtenEmpleado(){
        Empleado empleado= new Empleado();
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
    public void llenarComboTipoEmpleado() {
         TipoempleadoJpaController tipoEmpleadoJPA = new TipoempleadoJpaController();
        List<datos.Tipoempleado> tiposEmpleado = tipoEmpleadoJPA.findTipoempleadoEntities();
        List<TipoEmpleado> listaEmpleados = new ArrayList<>();
        for (int i = 0; i < tiposEmpleado.size(); i++) {
            TipoEmpleado tipos = new TipoEmpleado(tiposEmpleado.get(i).getIdtipoempleado(), tiposEmpleado.get(i).getNombre());
            listaEmpleados.add(tipos);
        }
        obsTipoEmpleado= FXCollections.observableArrayList(listaEmpleados);
        tipo.setItems(obsTipoEmpleado);

    }
    @FXML 
    public void cerrar(){
        stage.close();
    }
    public void recibeStage(Stage stage, GUIAdministrarEmpleadosController admin){
        this.stage=stage;
        this.admin=admin;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboTipoEmpleado();
        listaText.add(nombre);
        listaText.add(apellidoP);
        listaText.add(apellidoM);
        listaText.add(telefono);
        listaText.add(direccion);
        listaText.add(usuario);
        listaText.add(confirmacion);
        listaText.add(contraseña);
        
        //set seleccionModel
        // TODO
    }    
    
}
