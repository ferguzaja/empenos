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
        cal.setTimeInMillis(fechaRecibida.getTime());
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
    public static String[] separaString(String fecha){
    return fecha.split("-");
}
    public static String regresaFecha(String[] fechaSpli){
        String[] aux=fechaSpli[2].split(" ");
        return fechaSpli[0]+"-"+fechaSpli[1]+"-"+aux[0];
    }
    public static Date stringADate(String fecha){
        String[] aux=separaString(fecha);
       
        Date dateFinal = java.sql.Date.valueOf( regresaFecha(aux)
        );
        return dateFinal;
    }
    public static int diasDiferencia(Date fecha){
        int dias= (int) (regresaMilisegundos()-fecha.getTime())/86400000;
        
        return dias;
    }
}
