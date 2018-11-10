/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author ferguzaja
 */
public class validacion {
    public static boolean seleccionado(TableView tabla){
        boolean seleccionado=false;
        if(tabla.getSelectionModel().getSelectedItem()!=null){
            seleccionado=true;
        }
        return seleccionado;
    }
    public static void elliminarEspacios(List<TextField> listaText ) {
        for(int i=0; i<listaText.size(); i++){
            listaText.get(i).setText(listaText.get(i).getText());
            }
    }
    
}
