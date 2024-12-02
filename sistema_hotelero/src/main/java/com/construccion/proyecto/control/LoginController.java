package com.construccion.proyecto.control;

import com.construccion.proyecto.dao.DaoEmpleado;
import com.construccion.proyecto.model.Empleado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements SceneAware {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    private SceneManager sceneManager;

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    void btnLoginClicked(ActionEvent event) {
        String usuario = txtUser.getText();
        String contrasenia = txtPassword.getText();

        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            sceneManager.mostrarAlerta("Campos vacíos", "Por favor, completa todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        DaoEmpleado daoEmpleado = new DaoEmpleado();
        Empleado empleado = daoEmpleado.validarCredenciales(usuario, contrasenia);

        if (empleado != null) {
            System.out.println("Login exitoso. Rol: " + (empleado.getRol() == 1 ? "Administrador" : "Empleado"));

            if (empleado.getRol() == 1) {
                // Redirigir a la vista de administrador
                sceneManager.switchScene("/view/admin/AdminDashboard.fxml");
            } else {
                // Redirigir a la vista de empleado
                sceneManager.switchScene("/view/empleado/EmpleadoDashboard.fxml");
            }

        } else {
            sceneManager.mostrarAlerta("Error de login", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }



    public TextField getTxtUser() {
        return txtUser;
    }

    public PasswordField getTxtPassword() {
        return txtPassword;
    }

}
