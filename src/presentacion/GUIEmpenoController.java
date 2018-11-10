/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.EmpenoJpaController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.Empeno;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIEmpenoController implements Initializable {

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button botonBuscar;
    @FXML
    private Button altaContratoButton;
    @FXML
    private Button refrendarButton;
    @FXML
    private Button finiquitarButton;
    @FXML
    private Button extencionButton;
    @FXML
    private Button verDetallesButton;
    @FXML
    private Button botonAtras;
    @FXML
    private Button botonAdelante;
    @FXML
    private TableView<logica.Empeno> tablaEmpenos;

    @FXML
    private TableColumn<Empeno, String> nombreClienteColumn;

    @FXML
    private TableColumn<Empeno, String> fechaInicioColumn;

    @FXML
    private TableColumn<Empeno, String> fechaFinColumn;

    @FXML
    private TableColumn<Empeno, String> numeroExtencionColumn;

    @FXML
    private TableColumn<Empeno, String> numeroBolsaColumn;
    @FXML
    private TableColumn<Empeno, String> statusColumn;

    public void buscarEmpenos(ActionEvent event) {
        EmpenoJpaController empenoJPA = new EmpenoJpaController();
        List<datos.Empeno> empenos = empenoJPA.findEmpenoEntities();

        List<logica.Empeno> listaEmpenos = new ArrayList<>();
        for (int i = 0; i < empenos.size(); i++) {
            if (empenos.get(i).getIdempeno().equals(Integer.parseInt(txtBuscar.getText()))) {
                logica.Empeno emp = new logica.Empeno();
                emp.setIdEmpeno(empenos.get(i).getIdempeno());
                emp.setFechaInicio(empenos.get(i).getFechaInicioEmpeno());
                emp.setFechaFinEmpeno(empenos.get(i).getFechaFinEmpeno());
                emp.setIdEmpleado(datos.Empleado.datosALogicaClonar(empenos.get(i).getEmpleadoidEmpleado()));//clonar
                emp.setCotitular(empenos.get(i).getCotitular());
                emp.setNumExtencionTiempo(empenos.get(i).getExtencionTiempo());
                if (empenos.get(i).getExtencionTiempo() != 0) {
                    emp.setFechaExtencion(empenos.get(i).getFechaExtencion());
                }
                emp.setEstatus(empenos.get(i).getEstatus());
                emp.setNumBolsa(empenos.get(i).getNoBolsa());
                emp.setCliente(empenos.get(i).getClienteIdcliente().clonar());
                listaEmpenos.add(emp);
            }
        }
        llenarTabla(listaEmpenos);
    }
    
    public void llenarTabla(List<logica.Empeno> listaEmpenos){
        ObservableList<logica.Empeno> obsempenos = FXCollections.observableArrayList(listaEmpenos);
        nombreClienteColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("cliente"));
        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("fechaInicio"));
        fechaFinColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("fechaFinEmpeno"));
        numeroExtencionColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("numExtencionTiempo"));
        numeroBolsaColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("numBolsa"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Empeno, String>("estatus"));
        tablaEmpenos.setItems(obsempenos);
    }

    private int auxNavegacion = 0;
    private int navegacion = 1;
    //rango navegacion define cuantos registros se muestran en la tabla
    private int rangoNavegacion = 3;

    @FXML
    public void navegarAtras(ActionEvent event) {        
        navegacion = navegacion - (rangoNavegacion);
        auxNavegacion = navegacion - (rangoNavegacion);
        if (auxNavegacion == 1 || navegacion == 1) {
            botonAtras.setDisable(true);
        }
        System.out.println(auxNavegacion);
        System.out.println(navegacion);
        List<logica.Empeno> empenos = datos.Empeno.empenosNavegacion(auxNavegacion, navegacion);
        llenarTabla(empenos);
        botonAdelante.setDisable(false);
    }

    @FXML
    public void navegarAdelante(ActionEvent event) {
        navegacion = navegacion + rangoNavegacion;
        auxNavegacion = navegacion - rangoNavegacion;
        if (navegacion != 1) {
            botonAtras.setDisable(false);
        }
        System.out.println(auxNavegacion);
        System.out.println(navegacion);
        List<logica.Empeno> empenos = datos.Empeno.empenosNavegacion(auxNavegacion, navegacion);
        llenarTabla(empenos);
        if(empenos.contains(null)){
            botonAdelante.setDisable(true);
        }
    }

    @FXML
    public void verDetalles() {
        if (utilerias.validacion.seleccionado(tablaEmpenos)) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIDetallesContrato.fxml").openStream());
                Scene scene = new Scene(root);
                Stage planillaStage = new Stage();
                planillaStage.setScene(scene);
                GUIDetallesContratoController detallesController = (GUIDetallesContratoController) loader.getController();
                detallesController.recibe(tablaEmpenos.getSelectionModel().getSelectedItem());
                planillaStage.show();
            } catch (IOException ex) {
                Logger.getLogger(GUIEmpenoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            utilerias.mensajes.mensage("favor de seleccionar un contrato");
        }

    }

    @FXML
    public void botonFiniquitar() {
        if (utilerias.validacion.seleccionado(tablaEmpenos)) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIFiniquito.fxml").openStream());
                Scene scene = new Scene(root);
                Stage planillaStage = new Stage();
                planillaStage.setScene(scene);
                GUIFiniquitoController finiquitarController = (GUIFiniquitoController) loader.getController();
                finiquitarController.recibeParametros(planillaStage, tablaEmpenos.getSelectionModel().getSelectedItem());
                planillaStage.show();
            } catch (IOException ex) {
                Logger.getLogger(GUIEmpenoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            utilerias.mensajes.mensage("favor de seleccionar un contrato");
        }
    }

    @FXML
    private void botonExtension() {
        if (utilerias.validacion.seleccionado(tablaEmpenos)) {
            if (extensionTiempo()) {
                try {
                    FXMLLoader loader = new FXMLLoader();

                    //agregamos el openStream (no se para que)
                    AnchorPane root = (AnchorPane) loader.load(getClass().getResource("GUIExtensionTiempo.fxml").openStream());
                    //ahora creo una instancia del controlador del form que voy a abrir casteando
                    Scene scene = new Scene(root);
                    Stage planillaStage = new Stage();
                    planillaStage.setScene(scene);
                    GUIExtensionTiempoController extensionController = (GUIExtensionTiempoController) loader.getController();
                    extensionController.recibeStage(planillaStage, this);
                    planillaStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(GUIEmpenoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                utilerias.mensajes.mensage("ya no se admite otra extension para este contrato");
            }

        } else {
            utilerias.mensajes.mensage("favor de seleccionar un contrato");
        }
    }

    @FXML
    private boolean extensionTiempo() {
        boolean aceptar = false;
        if (tablaEmpenos.getSelectionModel().getSelectedItem().getNumExtencionTiempo() < 3) {
            aceptar = true;
        }
        return aceptar;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (navegacion == 1) {
            botonAtras.setDisable(true);
        }
    }
}
