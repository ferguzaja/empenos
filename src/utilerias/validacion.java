/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import javafx.scene.control.TableView;

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
}
