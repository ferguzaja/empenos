package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:24 p. m.
 */
public class Cliente extends Persona {

	private byte[] foto;
	private byte[] huellaDactilar;
	private boolean listaNegra;
	private String noIdentificacion;
	private String ocupacion;

	public Cliente(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public byte[] getfoto(){
		return foto;
	}

	public byte[] gethuellaDactilar(){
		return huellaDactilar;
	}

	public String getnoIdentificacion(){
		return noIdentificacion;
	}

	public String getocupacion(){
		return ocupacion;
	}

	public boolean islistaNegra(){
		return listaNegra;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfoto(byte[] newVal){
		foto = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void sethuellaDactilar(byte[] newVal){
		huellaDactilar = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setlistaNegra(boolean newVal){
		listaNegra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnoIdentificacion(String newVal){
		noIdentificacion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setocupacion(String newVal){
		ocupacion = newVal;
	}

}