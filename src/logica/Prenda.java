package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Prenda {

	private int idPrenda;
        private String descripcion;
        private Double montoValuo;
        private Double montoPrestamo;
	private int tipoPrenda;
	private boolean estadoEmpeno;
	private boolean comercializada;
        private String nombreTipoPrenda;

    public String getNombreTipoPrenda() {
        return nombreTipoPrenda;
    }

    public void setNombreTipoPrenda(String nombreTipoPrenda) {
        this.nombreTipoPrenda = nombreTipoPrenda;
    }
	
	
	

	public Prenda(){

	}

    public Prenda(String descripcion, Double montoValuo, Double montoPrestamo, int tipoPrenda,String nombreTipoPrenda) {
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
        this.tipoPrenda = tipoPrenda;
        this.nombreTipoPrenda=nombreTipoPrenda;
    }

    public Prenda(int idPrenda, String descripcion, Double montoValuo, Double montoPrestamo, int tipoPrenda) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
        this.tipoPrenda = tipoPrenda;
    }
    
    
    public Prenda(int idPrenda, String descripcion, Double montoValuo, Double montoPrestamo, int tipoPrenda, boolean estadoEmpeno, boolean comercializada) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
        this.tipoPrenda = tipoPrenda;
        this.estadoEmpeno = estadoEmpeno;
        this.comercializada = comercializada;
    }

    public Prenda(String descripcion, Double montoValuo, Double montoPrestamo) {
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
    }
    
    

    @Override
    public String toString() {
        return "Prenda{" + "idPrenda=" + getIdPrenda() + ", descripcion=" + getDescripcion() + ", montoValuo=" + getMontoValuo() + ", montoPrestamo=" + getMontoPrestamo() + ", tipoPrenda=" + getTipoPrenda() + ", estadoEmpeno=" + isEstadoEmpeno() + ", comercializada=" + isComercializada() + '}';
    }

    /**
     * @return the idPrenda
     */
    public int getIdPrenda() {
        return idPrenda;
    }

    /**
     * @param idPrenda the idPrenda to set
     */
    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the montoValuo
     */
    public Double getMontoValuo() {
        return montoValuo;
    }

    /**
     * @param montoValuo the montoValuo to set
     */
    public void setMontoValuo(Double montoValuo) {
        this.montoValuo = montoValuo;
    }

    /**
     * @return the montoPrestamo
     */
    public Double getMontoPrestamo() {
        return montoPrestamo;
    }

    /**
     * @param montoPrestamo the montoPrestamo to set
     */
    public void setMontoPrestamo(Double montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }

    /**
     * @return the tipoPrenda
     */
    public int getTipoPrenda() {
        return tipoPrenda;
    }

    /**
     * @param tipoPrenda the tipoPrenda to set
     */
    public void setTipoPrenda(int tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    /**
     * @return the estadoEmpeno
     */
    public boolean isEstadoEmpeno() {
        return estadoEmpeno;
    }

    /**
     * @param estadoEmpeno the estadoEmpeno to set
     */
    public void setEstadoEmpeno(boolean estadoEmpeno) {
        this.estadoEmpeno = estadoEmpeno;
    }

    /**
     * @return the comercializada
     */
    public boolean isComercializada() {
        return comercializada;
    }

    /**
     * @param comercializada the comercializada to set
     */
    public void setComercializada(boolean comercializada) {
        this.comercializada = comercializada;
    }

    
}