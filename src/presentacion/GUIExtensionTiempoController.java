/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Empeno;
import logica.VariblesEmpeno;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIExtensionTiempoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField TFFechaInicio;
    @FXML
    private TextField TFFechaFin;
    @FXML
    private TextField TFCotitular;
    @FXML
    private TextField TFNoExtension;
    @FXML
    private TextField TFFechaExtension;
    @FXML
    private TextField TFNoBolsa;
    @FXML
    private TextField TFInteresMensual;
    @FXML
    private TextField TFMontoPrestado;
    @FXML
    private TextField TFIva;
    @FXML
    private TextField TFCat;
    @FXML
    private Button solicitarExtensionButton;
    private Stage stage;
    private GUIEmpenoController controller;
    private Empeno emp= new Empeno();
    private Date fechaExtension;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void recibeStage(Stage planillaStage, GUIEmpenoController aThis,Empeno empeno) {
        stage=planillaStage;
        controller=aThis;
        llenaDatos(empeno);
        emp=empeno;
    }
    public void llenaDatos(Empeno empeno){
    TFFechaInicio.setText(empeno.getFechaInicio().toString());
    TFFechaFin.setText(empeno.getFechaFinEmpeno().toString());
    if(empeno.getCotitular()!=null)
        TFCotitular.setText(empeno.getCotitular());
    if(empeno.getNumExtencionTiempo()!=0){
        TFNoExtension.setText(String.valueOf(empeno.getNumExtencionTiempo()+1));
        TFFechaExtension.setText(utilerias.fechas.convertirFechaString(utilerias.fechas.aumentaDias(empeno.getFechaExtencion(), 5)));
    }else{
        TFNoExtension.setText("1");
        fechaExtension=utilerias.fechas.aumentaDias(empeno.getFechaFinEmpeno(), 5);
        TFFechaExtension.setText(utilerias.fechas.convertirFechaString(fechaExtension));
    }
    if(empeno.getCotitular() == null){
        TFCotitular.setText("N/A");
    }else{
        TFCotitular.setText(empeno.getCotitular());
    }
    TFNoBolsa.setText(Integer.toString(empeno.getNumBolsa()));
        VariblesEmpeno var=datos.Variblesempeno.obtenDatos(empeno.getIdEmpeno());
    TFInteresMensual.setText(String.valueOf(var.getIntereMensual()));
    TFMontoPrestado.setText(String.valueOf(datos.Prenda.montoPagar(datos.Prenda.encuentraContrato(empeno.getIdEmpeno()))));
    TFIva.setText(String.valueOf(var.getIva()));
    TFCat.setText(String.valueOf(var.getCat()));
    }
    @FXML
    private void botonExtension(){
        if(utilerias.mensajes.mensageConfirmacion("Extender Contrato", "¿Desea Extender Su Contrato?")){
            generaEmpeno();
            datos.Empeno.ExtenderDias(emp);
            controller.navegarAtras();
            controller.navegarAdelante();
            
            stage.close();
        }else{
            
        }
    }
    private void generaEmpeno(){
        emp.setNumExtencionTiempo(Integer.parseInt(TFNoExtension.getText()));
        emp.setFechaExtencion(fechaExtension);
    }
}
