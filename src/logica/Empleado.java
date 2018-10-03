package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Empleado extends Persona {

	private String password;
	private String tipoUsuario;
	private String usuario;

	public Empleado(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getpassword(){
		return password;
	}

	public String gettipoUsuario(){
		return tipoUsuario;
	}

	public String getusuario(){
		return usuario;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setpassword(String newVal){
		password = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settipoUsuario(String newVal){
		tipoUsuario = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setusuario(String newVal){
		usuario = newVal;
	}

}