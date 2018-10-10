/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Jahir
 */
public class InicioSesion extends Application {
    
    @FXML
    private ImageView imageView;
    
    @Override
    public void start(Stage stage) throws Exception {
                        
        
        Parent root = FXMLLoader.load(getClass().getResource("InicioSesion.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Inicio de sesión");
        
        Image imagen = new Image("/icons/inicio.jpg");
        imageView = new ImageView(imagen);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
