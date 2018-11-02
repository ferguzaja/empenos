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
import datos.exceptions.NonexistentEntityException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    GUIEmpenosController guiEmpenosControlerEnGUICliente;
    Stage stagemaster;

    private int clicEdit;
    private int idCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboIdentificacion();
        llenarComboOcupacion();
        llenarComboPais();
    }

    public void llenarComboIdentificacion() {
        TipoidentificacionJpaController tiposIdenJPA = new TipoidentificacionJpaController();
        List<Tipoidentificacion> tiposIdentificaciones = tiposIdenJPA.findTipoidentificacionEntities();

        List<TipoIdentificacion> listaTiposIden = new ArrayList<>();
        for (int i = 0; i < tiposIdentificaciones.size(); i++) {

            TipoIdentificacion iden = new TipoIdentificacion(tiposIdentificaciones.get(i).getIdtipoidentificacion(),
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

    public void selectItemPais(ActionEvent event) {
        int idPais = comboPais.getSelectionModel().getSelectedItem().getIdPais();
        llenarComboEstado(idPais);
    }

    public void llenarComboEstado(int idPais) {
        EstadoJpaController estadoJPA = new EstadoJpaController();
        List<datos.Estado> estados = estadoJPA.findEstadoEntities();

        List<Estado> listaEstados = new ArrayList<>();
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getPaisIdpais().getIdpais().equals(idPais)) {
                Estado estado = new Estado(estados.get(i).getIdestado(), estados.get(i).getNombre());
                listaEstados.add(estado);
            }
        }
        obsEstado = FXCollections.observableArrayList(listaEstados);
        comboEstado.setItems(obsEstado);
    }

    public void selectItemEstado(ActionEvent event) {
        int idEstado = comboEstado.getSelectionModel().getSelectedItem().getIdEstado();
        llenarComboCiudad(idEstado);
    }

    public void llenarComboCiudad(int idEstado) {
        CiudadJpaController ciudadJPA = new CiudadJpaController();
        List<datos.Ciudad> ciudades = ciudadJPA.findCiudadEntities();

        List<Ciudad> listaCiudades = new ArrayList<>();
        for (int i = 0; i < ciudades.size(); i++) {
            if (ciudades.get(i).getEstadoIdestado().getIdestado().equals(idEstado)) {
                Ciudad ciudad = new Ciudad(ciudades.get(i).getIdciudad(), ciudades.get(i).getNombre());
                listaCiudades.add(ciudad);
            }
        }
        obsCiudad = FXCollections.observableArrayList(listaCiudades);
        comboCiudad.setItems(obsCiudad);
    }

    @FXML
    private void botonGuardar(ActionEvent event) throws ParseException {
        ClienteJpaController clienteJPA = new ClienteJpaController();

        if (clicEdit == 1) {
            Cliente cliente = new Cliente();
            try {
                cliente.toString();
                cliente = leerCliente();
                cliente.setIdcliente(idCliente);

                CiudadJpaController ciudadJPA = new CiudadJpaController();
                datos.Ciudad ciudad
                        = ciudadJPA.findCiudad(comboCiudad.getSelectionModel()
                                .getSelectedItem().getIdCiudad());
                cliente.setCiudadIdciudad(ciudad);

                clienteJPA.edit(cliente);
                mensajePantalla("Cliente editado");
                stagemaster.close();
            } catch (NonexistentEntityException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            if (validarCamposVacios()) {
                Cliente cliente = new Cliente();
                cliente = leerCliente();
                clienteJPA.create(cliente);
                mensajePantalla("Cliente agregado");
                stagemaster.close();
            } else {
                mensajePantalla("Por favor completa los campos vacíos");
            }
        }
    }

    public Cliente leerCliente() {
        Cliente cliente = new Cliente();
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
        return cliente;
    }
    
    public void recibeStage(Stage stage){
        stagemaster = stage;
    }
    
    public boolean validarCamposVacios() {
        boolean bandera = true;
        if (nombre.getText().isEmpty() || apellidoM.getText().isEmpty()
                || apellidoP.getText().isEmpty()
                || direccion.getText().isEmpty()
                || noIdentificacion.getText().isEmpty()
                || comboPais.getSelectionModel().isEmpty()
                || comboEstado.getSelectionModel().isEmpty()
                || comboCiudad.getSelectionModel().isEmpty()
                || comboOcupacion.getSelectionModel().isEmpty()
                || comboTipoIdentificacion.getSelectionModel().isEmpty()
                || fechaNacimiento.getValue() == null) {
            bandera = false;
        }
        return bandera;
    }

    public void recibeParametros(GUIEmpenosController controlador,
            logica.Cliente cliente, int clicEditar,Stage stage) {
        clicEdit = clicEditar;
        idCliente = cliente.getIdCliente();
        guiEmpenosControlerEnGUICliente = controlador;
        nombre.setText(cliente.getNombre());
        apellidoM.setText(cliente.getApellidoMaterno());
        apellidoP.setText(cliente.getApellidoPaterno());
        direccion.setText(cliente.getDireccion());
        noIdentificacion.setText(cliente.getNoIdentificacion());
        //la fecha viene en la forma yyyy/mm/dd pero la necesito sin los / 
        //por eso uso substring para quitarlos
        String fecha = new String();
        fecha = cliente.getFechaNacimiento().substring(0, 4)
                + cliente.getFechaNacimiento().substring(5, 7)
                + cliente.getFechaNacimiento().substring(8, 10);

        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate date = LocalDate.parse(fecha, formatter);
        fechaNacimiento.setValue(date);

        llenarComboOcupacion();
        Ocupacion ocupacion = new Ocupacion(cliente.getOcupacion().getIdOcupacion(), cliente.getOcupacion().getNombre());
        comboOcupacion.getSelectionModel().select(ocupacion);

        llenarComboIdentificacion();
        TipoIdentificacion tipoIden = new TipoIdentificacion(cliente.getTipoIden().getIdTipoIdentificacion(), cliente.getTipoIden().getNombre());
        comboTipoIdentificacion.getSelectionModel().select(tipoIden);

        llenarComboPais();
        Pais pais = new Pais(cliente.getPais().getIdPais(), cliente.getPais().getNombre());
        comboPais.getSelectionModel().select(pais);

        llenarComboEstado(pais.getIdPais());
        Estado estado = new Estado(cliente.getEstado().getIdEstado(), cliente.getEstado().getNombre());
        comboEstado.getSelectionModel().select(estado);

        llenarComboCiudad(estado.getIdEstado());
        Ciudad ciudad = new Ciudad(cliente.getCiudad().getIdCiudad(), cliente.getCiudad().getNombre());
        comboCiudad.getSelectionModel().select(ciudad);
        stagemaster=stage;
    }

    @FXML
    private void mensajePantalla(String mensaje) {
        Alert dialogo = new Alert(Alert.AlertType.INFORMATION);
        dialogo.setTitle("Aviso");
        dialogo.setHeaderText(null);
        dialogo.setContentText(mensaje);
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
}
