package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Prenda {

	private boolean comercializada;
	private String descripcion;
	private boolean estadoEmpeno;
	private int idPrenda;
	private float montoPrestamo;
	private float montoValuo;
	private String tipoPrenda;

	public Prenda(){

	}

	public void finalize() throws Throwable {

	}

	public String getdescripcion(){
		return descripcion;
	}

	public int getidPrenda(){
		return idPrenda;
	}

	public float getmontoPrestamo(){
		return montoPrestamo;
	}

	public float getmontoValuo(){
		return montoValuo;
	}

	public String gettipoPrenda(){
		return tipoPrenda;
	}

	public boolean iscomercializada(){
		return comercializada;
	}

	public boolean isestadoEmpeno(){
		return estadoEmpeno;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcomercializada(boolean newVal){
		comercializada = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(String newVal){
		descripcion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setestadoEmpeno(boolean newVal){
		estadoEmpeno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidPrenda(int newVal){
		idPrenda = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmontoPrestamo(float newVal){
		montoPrestamo = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmontoValuo(float newVal){
		montoValuo = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settipoPrenda(String newVal){
		tipoPrenda = newVal;
	}

}