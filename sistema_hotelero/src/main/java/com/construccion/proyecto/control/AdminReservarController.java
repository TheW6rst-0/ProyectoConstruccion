package com.construccion.proyecto.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class AdminReservarController implements SceneAware{

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private RadioButton btnEfectivo;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnProceder;

    @FXML
    private RadioButton btnTarjeta;

    @FXML
    private Button btnVentas;

    @FXML
    private ChoiceBox<?> choiceTipo;

    @FXML
    private DatePicker fechaLlegada;

    @FXML
    private DatePicker fechaSalida;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblUsername1;

    @FXML
    private TextField txtCamas;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDisponibilidad;

    @FXML
    private TextField txtNoches;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtTotal;

    private SceneManager sceneManager;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }


    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/Login.fxml");
    }

    @FXML
    void btnCheckClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminCheck.fxml");
    }

    @FXML
    void btnEmpleadosClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminEmpleados.fxml");
    }

    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminHuespedes.fxml");
    }   

    @FXML
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }

    @FXML
    void btnProcederClicked(ActionEvent event) {

    }
    @FXML
    void btnHabitacionesClicked(ActionEvent event) {

    }

}

