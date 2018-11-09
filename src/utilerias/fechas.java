/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ferguzaja
 */
public class fechas {
    
    public static Date aumentaDias(Date fechaRecibida, int dias){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dias);
        int mesSiguiente = cal.get(Calendar.MONTH) + 1;
        String fechaFinal = cal.get(cal.YEAR) + "-" + mesSiguiente + "-" + cal.get(cal.DATE);
        Date dateFinal = java.sql.Date.valueOf(fechaFinal);
        return dateFinal;
    }
    public static Date Fecha(long milisegundos){
        Date fecha = new Date(milisegundos);
        return fecha;
    }
    	
    public static String convertirFechaString(Date date){
	
   return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
   
    }


    public static Long regresaMilisegundos(){
        Calendar cal= Calendar.getInstance();
        return cal.getTimeInMillis();
    }
}
