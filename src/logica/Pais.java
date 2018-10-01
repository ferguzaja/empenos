package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Pais {

	private int idPais;
	private String nombre;
	public Estado m_Estado;

	public Pais(){

	}

    public Pais(int idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
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