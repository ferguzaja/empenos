package logica;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 03-oct.-2018 03:56:25 p. m.
 */
public class Prenda {

	private int idPrenda;
        private String descripcion;
        private float montoValuo;
        private float montoPrestamo;
	private int tipoPrenda;
	private boolean estadoEmpeno;
	private boolean comercializada;
	
	
	

	public Prenda(){

	}

    public Prenda(String descripcion, float montoValuo, float montoPrestamo, int tipoPrenda) {
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
        this.tipoPrenda = tipoPrenda;
    }

    public Prenda(int idPrenda, String descripcion, float montoValuo, float montoPrestamo, int tipoPrenda) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
        this.tipoPrenda = tipoPrenda;
    }
    
    
    public Prenda(int idPrenda, String descripcion, float montoValuo, float montoPrestamo, int tipoPrenda, boolean estadoEmpeno, boolean comercializada) {
        this.idPrenda = idPrenda;
        this.descripcion = descripcion;
        this.montoValuo = montoValuo;
        this.montoPrestamo = montoPrestamo;
        this.tipoPrenda = tipoPrenda;
        this.estadoEmpeno = estadoEmpeno;
        this.comercializada = comercializada;
    }

    

    public Prenda(String descripcion, float montoValuo, float montoPrestamo) {
        this.descripcion = descripcion;
        this.montoPrestamo = montoPrestamo;
        this.montoValuo = montoValuo;
    }

    
        

	public void finalize() throws Throwable {

	}

	public String getdescripcion(){
		return descripcion;
	}

	public int getidPrenda(){
		return idPrenda;
	}

	public float getmontoPrestamo(){
		return montoPrestamo;
	}

	public float getmontoValuo(){
		return montoValuo;
	}

	public int gettipoPrenda(){
		return tipoPrenda;
	}

	public boolean iscomercializada(){
		return comercializada;
	}

	public boolean isestadoEmpeno(){
		return estadoEmpeno;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setcomercializada(boolean newVal){
		comercializada = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setdescripcion(String newVal){
		descripcion = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setestadoEmpeno(boolean newVal){
		estadoEmpeno = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setidPrenda(int newVal){
		idPrenda = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmontoPrestamo(float newVal){
		montoPrestamo = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setmontoValuo(float newVal){
		montoValuo = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void settipoPrenda(int newVal){
		tipoPrenda = newVal;
	}

}