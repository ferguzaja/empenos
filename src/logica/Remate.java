package logica;


import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Remate {

	private Date fechaHora;
	private int idRemate;
	private int perdida;
	public Empleado m_Empleado;
	public ArticuloVenta m_ArticuloVenta;
	public Cliente m_Cliente;

	public Remate(){

	}

	public void finalize() throws Throwable {

	}

	public Date getfechaHora(){
		return fechaHora;
	}

	public int getidRemate(){
		return idRemate;
	}

	public int getperdida(){
		return perdida;
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
	public void setidRemate(int newVal){
		idRemate = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setperdida(int newVal){
		perdida = newVal;
	}

}