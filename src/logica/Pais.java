package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Pais {

	private int idPais;
	private String nombre;
	public Estado m_Estado;

	public Pais(){

	}

	public void finalize() throws Throwable {

	}

	public int getidPais(){
		return idPais;
	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidPais(int newVal){
		idPais = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

}