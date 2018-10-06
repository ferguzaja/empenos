package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Empleado extends Persona {

        private String usuario;
	private String password;
	private int tipoUsuario;


	public Empleado(){

	}

    public Empleado(String usuario,String password, int tipoUsuario) {
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
    }

    public Empleado(String usuario, String password, int tipoUsuario, int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono) {
        super(nombre, apellidoPaterno, apellidoMaterno, direccion, telefono);
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }
    
    
        

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getpassword(){
		return password;
	}

	public int gettipoUsuario(){
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
	public void settipoUsuario(int newVal){
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