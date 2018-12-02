/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Frost
 */
public class JasperReports {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static String ds[] = {"localhost","3306","ventas","root","1001"};
    
    public static String generarReporteJasper(String jasper, HashMap param){
        String basepath = System.getProperty("user.dir")+"/jasper/";
        basepath = basepath.replaceAll("\\\\", "/");
        param.put("p_path",basepath);
        //-----------OUTPUT----------------//
        String outpath = basepath+"/salida/";
        String token = ""+new Date().getTime();
        try{
            JasperPrint po = JasperReports.getPrint(basepath, jasper, param);
            String file = String.format("%s%s_%s.pdf",createPath(outpath),jasper,token);
            JasperExportManager.exportReportToPdfFile(po,file);
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String generarPDFReciboVenta(String folio){
        String basepath = System.getProperty("user.dir")+"/jasper/";
        basepath = basepath.replaceAll("\\\\", "/");
        HashMap parameters = new HashMap();
        parameters.put("p_folio", folio);
        parameters.put("p_path",basepath);
        //-----------OUTPUT----------------//
        String outpath = basepath+"/salida/";
        String token = ""+new Date().getTime();
        String jasper = "ReciboVenta";
        try{
            JasperPrint po = JasperReports.getPrint(basepath, jasper, parameters);
            String file = String.format("%s%s_%s.pdf",createPath(outpath),jasper,token);
            JasperExportManager.exportReportToPdfFile(po,file);
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static JasperPrint getPrint(String basepath, String reporte, HashMap param) {
        JasperPrint result = null;
        try {
            String jasper = basepath + reporte + ".jrxml";
            Connect conn = new Connect(Connect.MYSQL, ds[0], Integer.parseInt(ds[1]), ds[2], ds[3], ds[4]);
            JasperReport report = JasperCompileManager.compileReport(jasper);
            result = JasperFillManager.fillReport(report, param, conn.connection());
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    private static String createPath(String path) {
        File f = new File(path);  
        if(!f.exists()) f.mkdir();
        return path;
    }
}
