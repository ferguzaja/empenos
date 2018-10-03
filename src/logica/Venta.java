package logica;


import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Venta {

	private Date fechaHora;
	private float ganancia;
	private int idVenta;
	public ArticuloVenta m_ArticuloVenta;
	public Cliente m_Cliente;
	public Empleado m_Empleado;

	public Venta(){

	}

	public void finalize() throws Throwable {

	}

	public Date getfechaHora(){
		return fechaHora;
	}

	public float getganancia(){
		return ganancia;
	}

	public int getidVenta(){
		return idVenta;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaHora(Date newVal){
		fechaHora = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setganancia(float newVal){
		ganancia = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidVenta(int newVal){
		idVenta = newVal;
	}

}