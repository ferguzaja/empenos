/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.Fotoprenda;
import java.net.URL;
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
    private TextField TFFechaFinalExtension;
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
    private TableColumn almacenajeColumn;
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
    //TFCotitular.setText(empeno.getNombreCotitular());
    //TFNoExtension.setText(Integer.toString(empeno.getNumExtencionTiempo()));
    //TFFechaExtension.setText(empeno.getFechaExtencion().toString());
    //TFFechaFinalExtension.setText(empeno.getFechaFinExtencion().toString());
    TFNoBolsa.setText(Integer.toString(empeno.getNumBolsa()));
    TFInteresMensual.setText(Double.toString(empeno.getInteresMensual()));
    TFMontoPrestado.setText(Double.toString(empeno.getMonto()));
    TFIva.setText(Double.toString(empeno.getIva()));
    TFCat.setText(Double.toString(empeno.getIva()));
    }
    public void llenaPrendas(Empeno empeno){
        datos.Prenda prenda = new datos.Prenda();
        listaPrendas=prenda.encuentraContrato(empeno.getIdEmpeno());
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
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
