package logica;

/**
 * @author ferguzaja
 * @version 1.0
 * @created 07-oct.-2018 10:35:57 p. m.
 */
public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String telefono;
    private String usuario;
    private String password;
    private int tipoUsuario;
    private String nombreTipoEmpleado;

    public Empleado() {

    }

    public Empleado(int idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono, String usuario, String password, int tipoUsuario, String nombreTipoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.nombreTipoEmpleado = nombreTipoEmpleado;
    }

    public Empleado(String nombre, String apellidoPaterno, String apellidoMaterno, String direccion, String telefono, String usuario, String password, int tipoUsuario) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", direccion=" + direccion + ", telefono=" + telefono + ", usuario=" + usuario + ", password=" + password + ", tipoUsuario=" + tipoUsuario + ", nombreTipoEmpleado=" + nombreTipoEmpleado + '}';
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreTipoEmpleado() {
        return nombreTipoEmpleado;
    }

    public void setNombreTipoEmpleado(String nombreTipoEmpleado) {
        this.nombreTipoEmpleado = nombreTipoEmpleado;
    }

}
