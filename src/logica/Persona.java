package logica;

import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 07-oct.-2018 10:37:56 p. m.
 */
public class Persona {
        private int idPersona;
	private String nombre;
        private String apellidoPaterno;
	private String apellidoMaterno;
	private String direccion;
        private String telefono;

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
    }
        
	private Date fechaNacimiento;


	
	public Ciudad m_Ciudad;

	public Persona(){

	}

	public void finalize() throws Throwable {

	}

	public String getapellidoMaterno(){
		return apellidoMaterno;
	}

	public String getapellidoPaterno(){
		return apellidoPaterno;
	}

	public String getdireccion(){
		return direccion;
	}

	public Date getfechaNacimiento(){
		return fechaNacimiento;
	}

	public int getidPersona(){
		return idPersona;
	}

	public String getnombre(){
		return nombre;
	}

	public String gettelefono(){
		return telefono;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setapellidoMaterno(String newVal){
		apellidoMaterno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setapellidoPaterno(String newVal){
		apellidoPaterno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdireccion(String newVal){
		direccion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setfechaNacimiento(Date newVal){
		fechaNacimiento = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidPersona(int newVal){
		idPersona = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setnombre(String newVal){
		nombre = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settelefono(String newVal){
		telefono = newVal;
	}

}