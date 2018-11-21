package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:24 p. m.
 */
public class ArticuloVenta {

        private int idArticuloVenta;
	private String descripcion;
	private double precioVenta;
	private int estado;
	public Prenda prenda;

	public ArticuloVenta(){

      	}

    public ArticuloVenta(int idArticuloVenta, String descripcion, double precioVenta, int estado, Prenda prenda) {
        this.idArticuloVenta = idArticuloVenta;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.estado = estado;
        this.prenda = prenda;
    }

    public ArticuloVenta(String descripcion, double precioVenta, int estado, Prenda prenda) {
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.estado = estado;
        this.prenda = prenda;
    }

    public int getIdArticuloVenta() {
        return idArticuloVenta;
    }

    public void setIdArticuloVenta(int idArticuloVenta) {
        this.idArticuloVenta = idArticuloVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }
        
        

}