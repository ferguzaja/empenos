/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import datos.CiudadJpaController;
import datos.Cliente;
import datos.ClienteJpaController;
import datos.EstadoJpaController;
import datos.OcupacionJpaController;
import datos.PaisJpaController;
import datos.Tipoidentificacion;
import datos.TipoidentificacionJpaController;
import static java.lang.String.format;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import logica.Ciudad;
import logica.Estado;
import logica.Ocupacion;
import logica.Pais;
import logica.TipoIdentificacion;

/**
 * FXML Controller class
 *
 * @author Jahir
 */
public class GUIClienteController implements Initializable {

    @FXML
    private ComboBox<TipoIdentificacion> comboTipoIdentificacion;
    @FXML
    private ObservableList<TipoIdentificacion> obsTipos;

    @FXML
    private ComboBox<Ocupacion> comboOcupacion;
    @FXML
    private ObservableList<Ocupacion> obsOcupacion;

    @FXML
    private ComboBox<Pais> comboPais;
    @FXML
    private ObservableList<Pais> obsPais;

    @FXML
    private ComboBox<Estado> comboEstado;
    @FXML
    private ObservableList<Estado> obsEstado;

    @FXML
    private ComboBox<Ciudad> comboCiudad;
    @FXML
    private ObservableList<Ciudad> obsCiudad;
    
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField apellidoM;
    
    @FXML
    private TextField apellidoP;
    
    @FXML
    private TextField direccion;
    
    @FXML
    private TextField noIdentificacion;
    
    @FXML
    private DatePicker fechaNacimiento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboIdentificacion();
        llenarComboOcupacion();
        llenarComboPais();
        llenarComboEstado();
        llenarComboCiudad();
    }

    public void llenarComboIdentificacion() {
        TipoidentificacionJpaController tiposIdenJPA = new TipoidentificacionJpaController();
        List<Tipoidentificacion> tiposIdentificaciones = tiposIdenJPA.findTipoidentificacionEntities();

        List<TipoIdentificacion> listaTiposIden = new ArrayList<>();
        for (int i = 0; i < tiposIdentificaciones.size(); i++) {

            TipoIdentificacion iden = new TipoIdentificacion
                    (tiposIdentificaciones.get(i).getIdtipoidentificacion(),
                    tiposIdentificaciones.get(i).getNombre());

            listaTiposIden.add(iden);
        }
        obsTipos = FXCollections.observableArrayList(listaTiposIden);
        comboTipoIdentificacion.setItems(obsTipos);
    }

    public void llenarComboOcupacion() {
        OcupacionJpaController ocupacionJPA = new OcupacionJpaController();
        List<datos.Ocupacion> ocupaciones = ocupacionJPA.findOcupacionEntities();

        List<Ocupacion> listaOcupaciones = new ArrayList<>();
        for (int i = 0; i < ocupaciones.size(); i++) {

            Ocupacion ocupacion = new Ocupacion(ocupaciones.get(i).
                    getIdocupacion(), ocupaciones.get(i).getNombre());
            listaOcupaciones.add(ocupacion);
        }
        obsOcupacion = FXCollections.observableArrayList(listaOcupaciones);
        comboOcupacion.setItems(obsOcupacion);
    }

    public void llenarComboPais() {
        PaisJpaController paisJPA = new PaisJpaController();
        List<datos.Pais> paises = paisJPA.findPaisEntities();

        List<Pais> listaPaises = new ArrayList<>();
        for (int i = 0; i < paises.size(); i++) {
            Pais pais = new Pais(paises.get(i).getIdpais(), paises.get(i).getNombre());
            listaPaises.add(pais);
        }
        obsPais = FXCollections.observableArrayList(listaPaises);
        comboPais.setItems(obsPais);
    }

    public void llenarComboEstado() {
        EstadoJpaController estadoJPA = new EstadoJpaController();
        List<datos.Estado> estados = estadoJPA.findEstadoEntities();

        List<Estado> listaEstados = new ArrayList<>();
        for (int i = 0; i < estados.size(); i++) {
            Estado estado = new Estado(estados.get(i).getIdestado(), estados.get(i).getNombre());
            listaEstados.add(estado);
        }
        obsEstado = FXCollections.observableArrayList(listaEstados);
        comboEstado.setItems(obsEstado);
    }

    public void llenarComboCiudad() {
        CiudadJpaController ciudadJPA = new CiudadJpaController();
        List<datos.Ciudad> ciudades = ciudadJPA.findCiudadEntities();

        List<Ciudad> listaCiudades = new ArrayList<>();
        for (int i = 0; i < ciudades.size(); i++) {
            Ciudad ciudad = new Ciudad(ciudades.get(i).getIdciudad(), ciudades.get(i).getNombre());
            listaCiudades.add(ciudad);
        }
        obsCiudad = FXCollections.observableArrayList(listaCiudades);
        comboCiudad.setItems(obsCiudad);
    }
    
    @FXML
    private void botonGuardar(ActionEvent event) throws ParseException {
        
        ClienteJpaController clienteJPA = new ClienteJpaController();
        List<datos.Cliente> clientes = clienteJPA.findClienteEntities();        
        
        Cliente cliente = new Cliente();
        String feNacimiento = fechaNacimiento.getValue().toString();
        int idCiudad = comboCiudad.getValue().getIdCiudad();
        int idTipoIdentificacion = comboTipoIdentificacion.getValue().getIdTipoIdentificacion();
        int idOcupacion = comboOcupacion.getValue().getIdOcupacion();
        
        cliente.setNombre(nombre.getText());
        cliente.setApellidoMaterno(apellidoM.getText());
        cliente.setApeliidoPaterno(apellidoP.getText());
        cliente.setDireccion(direccion.getText());
        cliente.setNoIdentificacion(noIdentificacion.getText());                      
        
        Date fecha = java.sql.Date.valueOf(fechaNacimiento.getValue());
        cliente.setFechaNac(fecha);
        
        CiudadJpaController ciudadJPA = new CiudadJpaController();
        datos.Ciudad ciudad = ciudadJPA.findCiudad(idCiudad);
        cliente.setCiudadIdciudad(ciudad);
        
        OcupacionJpaController ocupacionJPA = new OcupacionJpaController();
        datos.Ocupacion ocupacion = ocupacionJPA.findOcupacion(idOcupacion);
        cliente.setOcupacionIdocupacion(ocupacion);
        
        TipoidentificacionJpaController tipoIdenJPA = new TipoidentificacionJpaController();
        datos.Tipoidentificacion tipoIden = tipoIdenJPA.findTipoidentificacion(idTipoIdentificacion);
        cliente.setTipoidentificacionIdtipoidentificacion(tipoIden);
                
        clienteJPA.create(cliente);
    }
}
