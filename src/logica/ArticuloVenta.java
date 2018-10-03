package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:24 p. m.
 */
public class ArticuloVenta {

	private String descripcio;
	private boolean estado;
	private int idArticuloVenta;
	private float precioVenta;
	public Prenda m_Prenda;

	public ArticuloVenta(){

	}

	public void finalize() throws Throwable {

	}

	public String getdescripcio(){
		return descripcio;
	}

	public int getidArticuloVenta(){
		return idArticuloVenta;
	}

	public float getprecioVenta(){
		return precioVenta;
	}

	public boolean isestado(){
		return estado;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcio(String newVal){
		descripcio = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setestado(boolean newVal){
		estado = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidArticuloVenta(int newVal){
		idArticuloVenta = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setprecioVenta(float newVal){
		precioVenta = newVal;
	}

}