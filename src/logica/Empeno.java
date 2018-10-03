package logica;

import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Empeno {

	private int extencionTiempo;
	private Date fechaExtencion;
	private Date fechaFinEmpeno;
	private Date fechaInicioEmpeno;
	private int idEmpeno;
	private int montoRecibido;
	private int noBolsa;
	public Cliente m_Cliente;
	public Prenda m_Prenda;
	public Cotitular m_Cotitular;
	public Empleado m_Empleado;

	public Empeno(){

	}

	public void finalize() throws Throwable {

	}

	public int getextencionTiempo(){
		return extencionTiempo;
	}

	public Date getfechaExtencion(){
		return fechaExtencion;
	}

	public Date getfechaFinEmpeno(){
		return fechaFinEmpeno;
	}

	public Date getfechaInicioEmpeno(){
		return fechaInicioEmpeno;
	}

	public int getidEmpeno(){
		return idEmpeno;
	}

	public int getmontoRecibido(){
		return montoRecibido;
	}

	public int getnoBolsa(){
		return noBolsa;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setextencionTiempo(int newVal){
		extencionTiempo = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaExtencion(Date newVal){
		fechaExtencion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaFinEmpeno(Date newVal){
		fechaFinEmpeno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaInicioEmpeno(Date newVal){
		fechaInicioEmpeno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidEmpeno(int newVal){
		idEmpeno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmontoRecibido(int newVal){
		montoRecibido = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnoBolsa(int newVal){
		noBolsa = newVal;
	}

}