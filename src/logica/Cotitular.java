package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Cotitular {

	private int idCotitular;
	private String nombre;

	public Cotitular(){

	}

	public void finalize() throws Throwable {

	}

	public int getidCotitular(){
		return idCotitular;
	}

	public String getnombre(){
		return nombre;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidCotitular(int newVal){
		idCotitular = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

}