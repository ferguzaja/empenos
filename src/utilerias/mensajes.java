/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import logica.ArticuloVenta;

/**
 *
 * @author ferguzaja
 */
public class mensajes {
    public static void mensage(String mensaje){
         Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("Aviso");
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
    public static boolean mensageConfirmacion(String titulo, String cuerpo){
        boolean confiramcion=false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setContentText(cuerpo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
            confiramcion=true;
        
        return confiramcion;
    }
    public static boolean mensajeCarrito(ArticuloVenta art){
        boolean confiramcion=false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Agregar Al Carrito");
        alert.setContentText("El articulo: "+art.getDescripcion()+" tiene un costo de: "+art.getPrecioVenta()+"\n ¿Desea Agregarlo?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
            confiramcion=true;
        
        return confiramcion;
    }
}
