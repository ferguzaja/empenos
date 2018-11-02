/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Empleado;
import datos.EmpleadoJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jahir
 */
public class InicioSesionController implements Initializable {

    @FXML
    private TextField boxUsuario;

    @FXML
    private TextField boxContrasena;

    private Map<String, Object> parametrosGlobales = new HashMap<>();

    @FXML
    private void botonIniciarSesion(ActionEvent event) {
        if (validarCamposVacios()) {
            int estatus = 0;
            estatus = validarCuenta(boxUsuario.getText(), boxContrasena.getText());
            if (estatus == 0) {
                mensajePantalla("El usuario no existe");
            }
            if (estatus == 1) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    //agregamos el openStream (no se para que)
                    AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIPrincipall.fxml").openStream());
                    //ahora creo una instancia del controlador del form que voy a abrir casteando
                    Scene scene = new Scene(root);
                    Stage planillaStage = new Stage();
                    planillaStage.setScene(scene);
                    
                    GUIPrincipalController principalController = (GUIPrincipalController) loader.getController();
                    principalController.recibeHashMap(parametrosGlobales);
                    planillaStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                /*try {
                    Parent root = FXMLLoader.load(getClass().getResource("GUIPrincipall.fxml"));                    
                    Scene scene = new Scene(root);                    
                    Stage stage = new Stage();
                    stage.setScene(scene);                    
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }*/
            }
            if (estatus == 2) {
                mensajePantalla("Contraseña incorrecta");
            }
        }
    }

    public int validarCuenta(String nombre, String contrasena) {
        int estadoCuenta = 0;
        EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
        List<Empleado> empleados = empleadoJPA.findEmpleadoEntities();

        for (int i = 0; i < empleados.size(); i++) {
            if (verificarUsuarioRegistrado(nombre)) {
                if (empleados.get(i).getUsuario().equals(nombre)
                        && empleados.get(i).getContrasena().equals(contrasena)) {
                    parametrosGlobales.put("idSesion", empleados.get(i).getIdempleado());                    
                    //Regresa 1 si el usuario y contraseña coinciden
                    estadoCuenta = 1;
                    break;
                } else {
                    //Regresa 2 si el usuario coincide pero la contraseña no
                    estadoCuenta = 2;
                }
            } else {
                //Regresa 0 cuando el usuario no existe
                estadoCuenta = 0;
            }
        }
        return estadoCuenta;
    }

    public boolean verificarUsuarioRegistrado(String nombre) {
        boolean existe = false;
        EmpleadoJpaController empleadoJPA = new EmpleadoJpaController();
        List<Empleado> empleados = empleadoJPA.findEmpleadoEntities();
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println(empleados.get(i).getNombre());
            if (empleados.get(i).getUsuario().equals(nombre)) {
                existe = true;
            }
        }
        return existe;
    }

    @FXML
    private boolean validarCamposVacios() {
        boolean bandera = false;
        if (boxUsuario.getText().isEmpty() || boxContrasena.getText().isEmpty()) {
            mensajePantalla("Por favor completa los campos vacíos");
        } else {
            bandera = true;
        }
        return bandera;
    }

    @FXML
    private void mensajePantalla(String mensaje) {
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Aviso");
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
