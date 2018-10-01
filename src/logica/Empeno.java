package logica;


import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Empeno {

	private int extencionTiempo;
	private Date fechaExtencion;
	private Date fechaFinEmpeno;
	private Date fechaInicioEmpeno;
	private int idEmpeno;
	private int montoRecibido;
	private int noBolsa;
	public Cliente m_Cliente;
	public Prenda m_Prenda;
	public Cotitular m_Cotitular;
	public Empleado m_Empleado;

	public Empeno(){

	}

    public Empeno(Date fechaFinEmpeno, Date fechaInicioEmpeno, int idEmpeno, int noBolsa, Cliente m_Cliente, Prenda m_Prenda, Cotitular m_Cotitular, Empleado m_Empleado) {
        this.fechaFinEmpeno = fechaFinEmpeno;
        this.fechaInicioEmpeno = fechaInicioEmpeno;
        this.idEmpeno = idEmpeno;
        this.noBolsa = noBolsa;
        this.m_Cliente = m_Cliente;
        this.m_Prenda = m_Prenda;
        this.m_Cotitular = m_Cotitular;
        this.m_Empleado = m_Empleado;
    }

    public Empeno(int extencionTiempo, Date fechaExtencion, Date fechaFinEmpeno, Date fechaInicioEmpeno, int idEmpeno, int montoRecibido, int noBolsa, Cliente m_Cliente, Prenda m_Prenda, Cotitular m_Cotitular, Empleado m_Empleado) {
        this.extencionTiempo = extencionTiempo;
        this.fechaExtencion = fechaExtencion;
        this.fechaFinEmpeno = fechaFinEmpeno;
        this.fechaInicioEmpeno = fechaInicioEmpeno;
        this.idEmpeno = idEmpeno;
        this.montoRecibido = montoRecibido;
        this.noBolsa = noBolsa;
        this.m_Cliente = m_Cliente;
        this.m_Prenda = m_Prenda;
        this.m_Cotitular = m_Cotitular;
        this.m_Empleado = m_Empleado;
    }

    public int getExtencionTiempo() {
        return extencionTiempo;
    }

    public void setExtencionTiempo(int extencionTiempo) {
        this.extencionTiempo = extencionTiempo;
    }

    public Date getFechaExtencion() {
        return fechaExtencion;
    }

    public void setFechaExtencion(Date fechaExtencion) {
        this.fechaExtencion = fechaExtencion;
    }

    public Date getFechaFinEmpeno() {
        return fechaFinEmpeno;
    }

    public void setFechaFinEmpeno(Date fechaFinEmpeno) {
        this.fechaFinEmpeno = fechaFinEmpeno;
    }

    public Date getFechaInicioEmpeno() {
        return fechaInicioEmpeno;
    }

    public void setFechaInicioEmpeno(Date fechaInicioEmpeno) {
        this.fechaInicioEmpeno = fechaInicioEmpeno;
    }

    public int getIdEmpeno() {
        return idEmpeno;
    }

    public void setIdEmpeno(int idEmpeno) {
        this.idEmpeno = idEmpeno;
    }

    public int getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(int montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public int getNoBolsa() {
        return noBolsa;
    }

    public void setNoBolsa(int noBolsa) {
        this.noBolsa = noBolsa;
    }

    public Cliente getM_Cliente() {
        return m_Cliente;
    }

    public void setM_Cliente(Cliente m_Cliente) {
        this.m_Cliente = m_Cliente;
    }

    public Prenda getM_Prenda() {
        return m_Prenda;
    }

    public void setM_Prenda(Prenda m_Prenda) {
        this.m_Prenda = m_Prenda;
    }

    public Cotitular getM_Cotitular() {
        return m_Cotitular;
    }

    public void setM_Cotitular(Cotitular m_Cotitular) {
        this.m_Cotitular = m_Cotitular;
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