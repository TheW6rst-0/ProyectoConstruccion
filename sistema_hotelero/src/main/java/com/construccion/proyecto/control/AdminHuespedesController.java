package com.construccion.proyecto.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminHuespedesController implements SceneAware{

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnCuarto;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnNombre;

    @FXML
    private Button btnReservacion;

    @FXML
    private Button btnReservar;

    @FXML
    private Button btnVentas;

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
    private Label lblUsername;

    @FXML
    private Label lblUsername1;

    @FXML
    private TableView<?> tblHuesped;

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
        sceneManager.switchScene("/view/admin/AdminCheck.fxml");
    }

    @FXML
    void btnEmpleadosClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminEmpleados.fxml");
    }

    @FXML
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminDashboard.fxml");
    }

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminReservar.fxml");
    }

    @FXML
    void btnCuartoClicked(ActionEvent event) {

    }

    @FXML
    void btnNombreClicked(ActionEvent event) {

    }

    @FXML
    void btnReservacionClicked(ActionEvent event) {

    }

    @FXML
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
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
