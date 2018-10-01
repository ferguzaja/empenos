package logica;

import java.util.Date;



/**
 * @author ferguzaja
 * @version 1.0
 * @created 24-sep.-2018 02:05:42 p. m.
 */
public class Cliente extends Persona {

	private byte[] foto;
	private byte[] huellaDactilar;
	private boolean listaNegra;
	private String noIdentificacion;
	private String ocupacion;

	public Cliente(){

	}

    public Cliente(boolean listaNegra, String noIdentificacion, String ocupacion, String apellidoMaterno, String apellidoPaterno, String direccion, Date fechaNacimiento, int idPersona, String nombre, String telefono, Ciudad m_Ciudad) {
        super(apellidoMaterno, apellidoPaterno, direccion, fechaNacimiento, idPersona, nombre, telefono, m_Ciudad);
        this.listaNegra = listaNegra;
        this.noIdentificacion = noIdentificacion;
        this.ocupacion = ocupacion;
    }

    public Cliente(byte[] foto, byte[] huellaDactilar, boolean listaNegra, String noIdentificacion, String ocupacion, String apellidoMaterno, String apellidoPaterno, String direccion, Date fechaNacimiento, int idPersona, String nombre, String telefono, Ciudad m_Ciudad) {
        super(apellidoMaterno, apellidoPaterno, direccion, fechaNacimiento, idPersona, nombre, telefono, m_Ciudad);
        this.foto = foto;
        this.huellaDactilar = huellaDactilar;
        this.listaNegra = listaNegra;
        this.noIdentificacion = noIdentificacion;
        this.ocupacion = ocupacion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getHuellaDactilar() {
        return huellaDactilar;
    }

    public void setHuellaDactilar(byte[] huellaDactilar) {
        this.huellaDactilar = huellaDactilar;
    }

    public boolean isListaNegra() {
        return listaNegra;
    }

    public void setListaNegra(boolean listaNegra) {
        this.listaNegra = listaNegra;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
        

	public void finalize() throws Throwable {
		super.finalize();
	}

}