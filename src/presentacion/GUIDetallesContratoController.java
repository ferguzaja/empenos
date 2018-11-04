/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Fotoprenda;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.Empeno;
import logica.Prenda;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GUIDetallesContratoController implements Initializable {

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
    private Tab TabDatosContrato;
    @FXML
    private Tab TabPrendas;
    @FXML
    private Tab TabPagos;
    @FXML
    private TableView<Prenda> tablaPrendas;
    @FXML
    private TableColumn<Prenda, String> tipoArticuloColum;
    @FXML
    private TableColumn<Prenda, String> descripcionColumn;
    @FXML
    private TableColumn<Prenda, String> montoAvaluoColumn;
    @FXML
    private TableColumn<Prenda, String> montoPrestamoColumn;
    @FXML
    private Button verFotosButton;
    @FXML
    private TableView tablaPagos;
    @FXML
    private TableColumn noColumn;
    @FXML
    private TableColumn prestamoColumn;
    @FXML
    private TableColumn interesColumn;
    @FXML
    private TableColumn ivaColumn;
    @FXML
    private TableColumn refrendoColumn;
    @FXML
    private TableColumn desempeñoColumn;
    @FXML
    private TableColumn fechasColumn;
    private List<logica.Prenda> listaPrendas;
  
    public void recibe(Empeno empeno){
        
        llenaDatos(empeno);
        llenaPrendas(empeno);
        
    }
    public void llenaDatos(Empeno empeno){
    TFFechaInicio.setText(empeno.getFechaInicio().toString());
    TFFechaFin.setText(empeno.getFechaFinEmpeno().toString());
    if(empeno.getCotitular()!=null)
        TFCotitular.setText(empeno.getCotitular());
    if(empeno.getNumExtencionTiempo()!=0){
        TFNoExtension.setText(Integer.toString(empeno.getNumExtencionTiempo()));
        TFFechaExtension.setText(empeno.getFechaExtencion().toString());
    }else{
        TFNoExtension.setText("N/A");
        TFFechaExtension.setText("N/A");
    }
    TFNoBolsa.setText(Integer.toString(empeno.getNumBolsa()));
    TFInteresMensual.setText(Double.toString(datos.Variblesempeno.obtenDatos(empeno.getIdEmpeno()).getIntereMensual()));
    TFMontoPrestado.setText(String.valueOf(datos.Prenda.montoPagar(datos.Prenda.encuentraContrato(empeno.getIdEmpeno()))));
    TFIva.setText(Double.toString(datos.Variblesempeno.obtenDatos(empeno.getIdEmpeno()).getIva()));
    TFCat.setText(Double.toString(datos.Variblesempeno.obtenDatos(empeno.getIdEmpeno()).getCat()));
    }
    public void llenaPrendas(Empeno empeno){
        listaPrendas=datos.Prenda.encuentraContrato(empeno.getIdEmpeno());
        ObservableList<logica.Prenda> obsPrenda = FXCollections.observableArrayList(listaPrendas);
        tipoArticuloColum.setCellValueFactory(new PropertyValueFactory<Prenda,String>("tipoPrenda"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("descripcion"));
        montoAvaluoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoValuo"));
        montoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("montoPrestamo"));
        tablaPrendas.setEditable(false);
        tablaPrendas.setItems(obsPrenda);
    }
    public void muestraFotos(){
        if(tablaPrendas.getSelectionModel().getSelectedItem()!=null){
            Fotoprenda fotoprenda = new Fotoprenda();
            List <Fotoprenda> fotos=fotoprenda.buscaFotos(tablaPrendas.getSelectionModel().getSelectedItem().getIdPrenda());
        }
        //PONER UN BOTON VER GALERIA PARA MOSTRAR TODAS LAS FOTOS DEL EMPEÑO
        
    }
    private void llenaTablaPrestamo(Empeno empeno){
        List<logica.Pago> pagos =datos.Pago.regresaPagos(empeno.getIdEmpeno());
        ObservableList<logica.Pago> obsPago = FXCollections.observableArrayList(pagos);
        noColumn.setCellValueFactory(new PropertyValueFactory<Prenda,String>("noPeriodo"));
        prestamoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("prestamo"));
        interesColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("interes"));
        ivaColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("iva"));
        refrendoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("refrendo"));
        desempeñoColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("desempeño"));
        fechasColumn.setCellValueFactory(new PropertyValueFactory<Prenda, String>("fecha"));
        tablaPagos.setEditable(false);
        tablaPagos.setItems(obsPago);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
