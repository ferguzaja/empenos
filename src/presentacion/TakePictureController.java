/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.FotoPrenda;

/**
 * FXML Controller class
 *
 * @author ferguzaja
 */
public class TakePictureController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<Webcam> cmb_webcams;
    private WebcamPanel panel = null;
    private String NAMECAM = null;
    private Webcam webCam = null;
    @FXML
    private FlowPane bottomCameraControlPane;
    @FXML
    private Button tomarFoto;
    @FXML
    private Button tomarOtraFoto;
    @FXML
    private Button guardarFoto;
    private boolean stopCamera = false;
    private BufferedImage grabbedImage;
    @FXML
    private ImageView imgWebCamCapturedImage;
    private Stage stage;
    private GUIAgregarProductoController controller;
    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private int array;

    private void cargarWebcams() {
        List<Webcam> list = Webcam.getWebcams();
        if (list != null && list.size() > 0) {
            NAMECAM = list.get(0).getName();
            System.out.println("*1* " + NAMECAM);
            ObservableList obs;
            obs = FXCollections.observableArrayList(list);
            cmb_webcams.setItems(obs);
            System.out.println("*2* " + NAMECAM);
        }
    }

    @FXML
    protected void initializeWebCam(final int webCamIndex) {

        Task<Void> webCamTask = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                if (webCam != null) {

                    webCam = Webcam.getWebcams().get(webCamIndex);
                    webCam.open();
                } else {
                    webCam = Webcam.getWebcams().get(webCamIndex);
                    webCam.open();
                }

                startWebCamStream();
                return null;
            }
        };

        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();
        bottomCameraControlPane.setDisable(false);

    }

    protected void startWebCamStream() {
        stopCamera = false;
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                while (!stopCamera) {
                    try {
                        if ((grabbedImage = webCam.getImage()) != null) {
//							System.out.println("Captured Image height*width:"+grabbedImage.getWidth()+"*"+grabbedImage.getHeight());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    final Image mainiamge = SwingFXUtils
                                            .toFXImage(grabbedImage, null);
                                    imageProperty.set(mainiamge);
                                }
                            });
                            grabbedImage.flush();
                        }
                    } catch (Exception e) {
                    } finally {

                    }
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);

    }

    @FXML
    private void selectItem(ActionEvent event) {
        initializeWebCam(cmb_webcams.getSelectionModel().getSelectedIndex());
        tomarFoto.setDisable(false);
    }

    @FXML
    private void tomarFoto() {
        stopCamera = true;
        tomarOtraFoto.setDisable(false);
        tomarFoto.setDisable(true);
        guardarFoto.setDisable(false);
    }

    @FXML
    private void tomarOtraFoto() {
        this.tomarFoto.setDisable(false);
        this.guardarFoto.setDisable(true);
        this.tomarOtraFoto.setDisable(true);
        initializeWebCam(cmb_webcams.getSelectionModel().getSelectedIndex());
        stopCamera = false;
    }

    @FXML
    private void guardar() {
        FotoPrenda foto = new FotoPrenda();
        foto.setNombre("Foto " + (array + 1));
        foto.setFechaHora(utilerias.fechas.Fecha(utilerias.fechas.regresaMilisegundos()));
        foto.setFoto(imgWebCamCapturedImage.getImage());
        controller.recibeImagen(foto);
        webCam.close();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarWebcams();
        startWebCamStream();
    }

    void recibeStage(Stage planillaStage, GUIAgregarProductoController aThis, int tamañoArray) {
        stage = planillaStage;
        controller = aThis;
        array = tamañoArray;
    }

}
