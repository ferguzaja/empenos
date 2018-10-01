package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Estado {

	private int idEstado;
	private String nombre;
	public Ciudad m_Ciudad;

	public Estado(){

	}

    public Estado(int idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        

	public void finalize() throws Throwable {

	}

}