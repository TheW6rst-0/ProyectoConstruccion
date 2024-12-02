package com.construccion.proyecto.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdminDashController implements SceneAware {

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnReservar;

    @FXML
    private Button btnVentas;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblUsername1;

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
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminReservar.fxml");
    }

    @FXML
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }

}
