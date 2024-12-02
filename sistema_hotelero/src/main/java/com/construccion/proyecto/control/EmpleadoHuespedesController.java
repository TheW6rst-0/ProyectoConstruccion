package com.construccion.proyecto.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmpleadoHuespedesController implements SceneAware {

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnReservar;

    @FXML
    private Label lblUsername;

    @FXML
    private TableColumn<?, ?> colEntrada;

    @FXML
    private TableColumn<?, ?> colIDHabitacion;

    @FXML
    private TableColumn<?, ?> colIDHuesped;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colPersonas;

    @FXML
    private TableColumn<?, ?> colReserva;

    @FXML
    private TableColumn<?, ?> colSalida;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableView<?> tblHuesped;

     @FXML
    private Button btnCuarto;

    @FXML
    private Button btnNombre;

    @FXML
    private Button btnReservacion;

    @FXML
    private TextField txtCuarto;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtReservacion;

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
        sceneManager.switchScene("/view/empleado/EmpleadoCheck.fxml");
    }

    @FXML
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoDashboard.fxml");
    }

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoReservar.fxml");
    }

    public TextField getTxtCuarto() {
        return txtCuarto;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtReservacion() {
        return txtReservacion;
    }

    

}
