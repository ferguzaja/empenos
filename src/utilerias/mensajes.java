/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    public static Map<String, Object> nuevaInterfaz(String ventana, Object objeto){
        Map<String, Object> parametrosInterfaz = new HashMap<>();
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(objeto.getClass().getResource(ventana).openStream());
            Scene scene = new Scene(root);
            Stage planillaStage = new Stage();
            planillaStage.setScene(scene);
            parametrosInterfaz.put("Loader",loader);
            parametrosInterfaz.put("Stage", planillaStage);
            parametrosInterfaz.put("Scene",scene);
            planillaStage.show();
        } catch (IOException ex) {
            Logger.getLogger(mensajes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parametrosInterfaz;
    }
       public static double remate(ArticuloVenta art){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("El articulo: "+art.getDescripcion()+" tiene un costo de: "+art.getPrecioVenta()+"¿Desea Agregarlo?");
        dialog.setContentText("Precio de remate");
        Optional<String> result = dialog.showAndWait();
        double nuevoPrecio=art.getPrecioVenta();
        if(result.isPresent()){
            nuevoPrecio=Double.parseDouble(result.get());
        }
        return nuevoPrecio;
    }
}
