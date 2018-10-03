package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:24 p. m.
 */
public class Ciudad {

	private int idCiudad;
	private String nombre;

	public Ciudad(){

	}

	public void finalize() throws Throwable {

	}

	public int getidCiudad(){
		return idCiudad;
	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidCiudad(int newVal){
		idCiudad = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

}