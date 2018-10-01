package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class ArticuloVenta {

	private String descripcio;
	private boolean estado;
	private int idArticuloVenta;
	private float precioVenta;
	public Prenda m_Prenda;

	public ArticuloVenta(){

	}

    public ArticuloVenta(String descripcio, boolean estado, int idArticuloVenta, float precioVenta, Prenda m_Prenda) {
        this.descripcio = descripcio;
        this.estado = estado;
        this.idArticuloVenta = idArticuloVenta;
        this.precioVenta = precioVenta;
        this.m_Prenda = m_Prenda;
    }
        

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdArticuloVenta() {
        return idArticuloVenta;
    }

    public void setIdArticuloVenta(int idArticuloVenta) {
        this.idArticuloVenta = idArticuloVenta;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Prenda getM_Prenda() {
        return m_Prenda;
    }

    public void setM_Prenda(Prenda m_Prenda) {
        this.m_Prenda = m_Prenda;
    }
        

	public void finalize() throws Throwable {

	}

}