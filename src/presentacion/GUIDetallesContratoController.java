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
import logica.Pago;
import logica.Prenda;
import logica.VariblesEmpeno;

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
    private TableView<Pago> tablaPagos;
    @FXML
    private TableColumn<Pago, String> noColumn;
    @FXML
    private TableColumn<Pago, String> prestamoColumn;
    @FXML
    private TableColumn<Pago, String> interesColumn;
    @FXML
    private TableColumn<Pago, String> ivaColumn;
    @FXML
    private TableColumn <Pago, String>refrendoColumn;
    @FXML
    private TableColumn<Pago, String> desempeñoColumn;
    @FXML
    private TableColumn<Pago, String> fechasColumn;
    private List<logica.Prenda> listaPrendas;
  
    public void recibe(Empeno empeno){
        
        llenaDatos(empeno);
        llenaPrendas(empeno);
        llenaTablaPrestamo(empeno);
        
    }
    public void llenaDatos(Empeno empeno){
    TFFechaInicio.setText(empeno.getTxtfechaInicio());
    TFFechaFin.setText(empeno.getTxtfechaFinEmpeno());
    if(empeno.getCotitular()!=null)
        TFCotitular.setText(empeno.getCotitular());
    if(empeno.getNumExtencionTiempo()!=0){
        TFNoExtension.setText(Integer.toString(empeno.getNumExtencionTiempo()));
        TFFechaExtension.setText(empeno.getTxtfechaExtencion());
    }else{
        TFNoExtension.setText("N/A");
        TFFechaExtension.setText("N/A");
    }
    if(empeno.getCotitular() == null){
        TFCotitular.setText("N/A");
    }else{
        TFCotitular.setText(empeno.getCotitular());
    }
    TFNoBolsa.setText(Integer.toString(empeno.getNumBolsa()));
        VariblesEmpeno var=datos.Variblesempeno.obtenDatos(empeno.getIdEmpeno());
    TFInteresMensual.setText(String.valueOf(var.getIntereMensual()));
    TFMontoPrestado.setText(String.valueOf(datos.Prenda.montoPagar(datos.Prenda.prendasPorContrato(empeno.getIdEmpeno(),true))));
    TFIva.setText(String.valueOf(var.getIva()));
    TFCat.setText(String.valueOf(var.getCat()));
    }
    public void llenaPrendas(Empeno empeno){
        listaPrendas=datos.Prenda.prendasPorContrato(empeno.getIdEmpeno(),true);
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
        noColumn.setCellValueFactory(new PropertyValueFactory<Pago,String>("noPeriodo"));
        prestamoColumn.setCellValueFactory(new PropertyValueFactory<Pago, String>("prestamo"));
        interesColumn.setCellValueFactory(new PropertyValueFactory<Pago, String>("interes"));
        ivaColumn.setCellValueFactory(new PropertyValueFactory<Pago, String>("iva"));
        refrendoColumn.setCellValueFactory(new PropertyValueFactory<Pago, String>("refrendo"));
        desempeñoColumn.setCellValueFactory(new PropertyValueFactory<Pago, String>("desempeño"));
        fechasColumn.setCellValueFactory(new PropertyValueFactory<Pago, String>("fecha"));
        tablaPagos.setEditable(false);
        tablaPagos.setItems(obsPago);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
