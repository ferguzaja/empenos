/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import logica.FotoPrenda;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class GuiFotosController implements Initializable {

    @FXML
    private ListView<FotoPrenda> listaFotos;
    @FXML
    private ImageView fotomuestra;
    @FXML
    private Button verFotoButton;
    private ObservableList<FotoPrenda> obsfotos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void seleccionaImagen() {
        if (listaFotos.getSelectionModel().isEmpty()) {
            utilerias.mensajes.mensage("favor de seleccionar un Articulo de venta para ver las fotos");
        } else {
            fotomuestra.setImage(listaFotos.getSelectionModel().getSelectedItem().getFoto());
        }
    }
    public void recibeIdPrenda(int idPrenda){
        obsfotos = FXCollections.observableArrayList(datos.Fotoprenda.devuelveFotos(idPrenda,true));
            listaFotos.setItems(obsfotos);
    }
    public void recibeLista(List<FotoPrenda> lista){
        obsfotos = FXCollections.observableArrayList(lista);
            listaFotos.setItems(obsfotos);
    }
}
