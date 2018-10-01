package logica;

import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Empleado extends Persona {

	private String password;
	private String tipoUsuario;
	private String usuario;

	public Empleado(){

	}

    public Empleado(String password, String tipoUsuario, String usuario) {
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
    }

    public Empleado(String password, String tipoUsuario, String usuario, String apellidoMaterno, String apellidoPaterno, String direccion, Date fechaNacimiento, int idPersona, String nombre, String telefono, Ciudad m_Ciudad) {
        super(apellidoMaterno, apellidoPaterno, direccion, fechaNacimiento, idPersona, nombre, telefono, m_Ciudad);
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
        

	public void finalize() throws Throwable {
		super.finalize();
	}

}