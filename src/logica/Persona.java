package logica;

import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Persona {

	private String apellidoMaterno;
	private String apellidoPaterno;
	private String direccion;
	private Date fechaNacimiento;
	private int idPersona;
	private String nombre;
	private String telefono;
	public Ciudad m_Ciudad;

	public Persona(){

	}

    public Persona(String apellidoMaterno, String apellidoPaterno, String direccion, Date fechaNacimiento, int idPersona, String nombre, String telefono, Ciudad m_Ciudad) {
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.m_Ciudad = m_Ciudad;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ciudad getM_Ciudad() {
        return m_Ciudad;
    }

    public void setM_Ciudad(Ciudad m_Ciudad) {
        this.m_Ciudad = m_Ciudad;
    }
        

	public void finalize() throws Throwable {

	}

}