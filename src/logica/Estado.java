package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Estado {

	private int idEstado;
	private String nombre;
	public Ciudad m_Ciudad;

	public Estado(){

	}

	public void finalize() throws Throwable {

	}

	public int getidEstado(){
		return idEstado;
	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidEstado(int newVal){
		idEstado = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

}