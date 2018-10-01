package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Cotitular {

	private int idCotitular;
	private String nombre;

	public Cotitular(){

	}

    public Cotitular(int idCotitular, String nombre) {
        this.idCotitular = idCotitular;
        this.nombre = nombre;
    }

    public int getIdCotitular() {
        return idCotitular;
    }

    public void setIdCotitular(int idCotitular) {
        this.idCotitular = idCotitular;
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