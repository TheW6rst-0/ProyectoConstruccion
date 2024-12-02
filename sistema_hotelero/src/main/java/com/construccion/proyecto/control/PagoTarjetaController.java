package com.construccion.proyecto.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PagoTarjetaController {

    @FXML
    private Button btnConfirmar;

    @FXML
    private DatePicker fechaVencimiento;

    @FXML
    private PasswordField txtNip;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSaldo;

    @FXML
    private TextField txtTarjeta;

    @FXML
    private TextField txtTotal;

    @FXML
    void btnConfirmarClicked(ActionEvent event) {

    }



}
