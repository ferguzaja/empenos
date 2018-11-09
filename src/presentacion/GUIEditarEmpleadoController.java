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
    private GUIAdministrarEmpleadosController admin;    
    @FXML
    private ArrayList<TextField> listaText= new ArrayList<>();
    
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
    private void guardarEmpleado() throws NonexistentEntityException{
            if(datos.Empleado.guardarEmpleado(obtenEmpleado())){    
                utilerias.mensajes.mensage("Empleado Guardado Exitosamente");
                admin.llenaTabla();
                stagemaster.close();
        }else{
            utilerias.mensajes.mensage("Error");
        }
            
          
    }
    @FXML
    public void setEmpleado(Empleado empleado,Stage stage,GUIAdministrarEmpleadosController admin){
        nombre.setText(empleado.getNombre());
        apellidoP.setText(empleado.getApellidoPaterno());
        apellidoM.setText(empleado.getApellidoMaterno());
        direccion.setText(empleado.getDireccion());
        telefono.setText(empleado.getTelefono());
        usuario.setText(empleado.getUsuario());
        contraseña.setText(empleado.getPassword());
        confirmacion.setText(empleado.getPassword());
        idEmpleado=empleado.getIdEmpleado();
        //creas una instancia del tipo del objeto del combo box y luego se l mandandas con el metodo select en la linea de abajo
        TipoEmpleado tipoEmpleado= new TipoEmpleado(empleado.getTipoUsuario(),empleado.getNombreTipoEmpleado());
        tipo.getSelectionModel().select(tipoEmpleado);
        stagemaster=stage;
        this.admin=admin;
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
        utilerias.validacion.elliminarEspacios(listaText);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarComboTipoEmpleado();
        listaText.add(nombre);
        listaText.add(apellidoP);
        listaText.add(apellidoM);
        listaText.add(telefono);
        listaText.add(direccion);
        listaText.add(usuario);
        listaText.add(confirmacion);
        listaText.add(contraseña);
    }    
    
}
