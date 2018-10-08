package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 07-oct.-2018 10:35:57 p. m.
 */
public class Empleado extends Persona{
        private String usuario;
        private String tipoUsuario;
	private String password;

    public Empleado(String usuario, String tipoUsuario, String password) {
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public Empleado(String usuario, String tipoUsuario, String password, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono) {
        super(nombre, apellidoPaterno, apellidoMaterno, direccion, telefono);
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }
    
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