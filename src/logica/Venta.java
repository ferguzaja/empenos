package logica;


import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Venta {

	private Date fechaHora;
	private float ganancia;
	private int idVenta;
	public ArticuloVenta m_ArticuloVenta;
	public Cliente m_Cliente;
	public Empleado m_Empleado;

	public Venta(){

	}

    public Venta(Date fechaHora, float ganancia, int idVenta, ArticuloVenta m_ArticuloVenta, Cliente m_Cliente, Empleado m_Empleado) {
        this.fechaHora = fechaHora;
        this.ganancia = ganancia;
        this.idVenta = idVenta;
        this.m_ArticuloVenta = m_ArticuloVenta;
        this.m_Cliente = m_Cliente;
        this.m_Empleado = m_Empleado;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public float getGanancia() {
        return ganancia;
    }

    public void setGanancia(float ganancia) {
        this.ganancia = ganancia;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public Empleado getM_Empleado() {
        return m_Empleado;
    }

    public void setM_Empleado(Empleado m_Empleado) {
        this.m_Empleado = m_Empleado;
    }

	public void finalize() throws Throwable {

	}

}