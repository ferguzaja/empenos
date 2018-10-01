package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Prenda {

	private boolean comercializada;
	private String descripcion;
	private boolean estadoEmpeno;
	private int idPrenda;
	private float montoPrestamo;
	private float montoValuo;
	private String tipoPrenda;

	public Prenda(){

	}

    public Prenda(String descripcion, int idPrenda, float montoPrestamo, float montoValuo, String tipoPrenda) {
        this.descripcion = descripcion;
        this.idPrenda = idPrenda;
        this.montoPrestamo = montoPrestamo;
        this.montoValuo = montoValuo;
        this.tipoPrenda = tipoPrenda;
    }

    public Prenda(boolean comercializada, String descripcion, boolean estadoEmpeno, int idPrenda, float montoPrestamo, float montoValuo, String tipoPrenda) {
        this.comercializada = comercializada;
        this.descripcion = descripcion;
        this.estadoEmpeno = estadoEmpeno;
        this.idPrenda = idPrenda;
        this.montoPrestamo = montoPrestamo;
        this.montoValuo = montoValuo;
        this.tipoPrenda = tipoPrenda;
    }

    public boolean isComercializada() {
        return comercializada;
    }

    public void setComercializada(boolean comercializada) {
        this.comercializada = comercializada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstadoEmpeno() {
        return estadoEmpeno;
    }

    public void setEstadoEmpeno(boolean estadoEmpeno) {
        this.estadoEmpeno = estadoEmpeno;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public float getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(float montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    public float getMontoValuo() {
        return montoValuo;
    }

    public void setMontoValuo(float montoValuo) {
        this.montoValuo = montoValuo;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }
    
    

	public void finalize() throws Throwable {

	}

}