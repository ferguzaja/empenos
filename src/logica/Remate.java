package logica;


import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Remate {

	private Date fechaHora;
	private int idRemate;
	private float perdida;
	public Empleado m_Empleado;
	public ArticuloVenta m_ArticuloVenta;
	public Cliente m_Cliente;

	public Remate(){

	}

    public Remate(Date fechaHora, int idRemate, float perdida, Empleado m_Empleado, ArticuloVenta m_ArticuloVenta, Cliente m_Cliente) {
        this.fechaHora = fechaHora;
        this.idRemate = idRemate;
        this.perdida = perdida;
        this.m_Empleado = m_Empleado;
        this.m_ArticuloVenta = m_ArticuloVenta;
        this.m_Cliente = m_Cliente;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdRemate() {
        return idRemate;
    }

    public void setIdRemate(int idRemate) {
        this.idRemate = idRemate;
    }

    public float getPerdida() {
        return perdida;
    }

    public void setPerdida(float perdida) {
        this.perdida = perdida;
    }

    public Empleado getM_Empleado() {
        return m_Empleado;
    }

    public void setM_Empleado(Empleado m_Empleado) {
        this.m_Empleado = m_Empleado;
    }

    public ArticuloVenta getM_ArticuloVenta() {
        return m_ArticuloVenta;
    }

    public void setM_ArticuloVenta(ArticuloVenta m_ArticuloVenta) {
        this.m_ArticuloVenta = m_ArticuloVenta;
    }

    public Cliente getM_Cliente() {
        return m_Cliente;
    }

    public void setM_Cliente(Cliente m_Cliente) {
        this.m_Cliente = m_Cliente;
    }
        

	public void finalize() throws Throwable {

	}

}