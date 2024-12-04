package com.construccion.proyecto.control;

import java.sql.SQLException;
import java.util.List;

import com.construccion.proyecto.dao.DaoHabitaciones;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EmpleadoDashController implements SceneAware {

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnReservar;

    @FXML
    private Button btnToggleDBL;

    @FXML
    private Button btnToggleSNG;

    @FXML
    private Button btnToggleST;

    @FXML
    private TextField txtDispDBL;

    @FXML
    private TextField txtDispSNG;

    @FXML
    private TextField txtDispST;


    @FXML
    private ChoiceBox<Integer> choiceDBL;

    @FXML
    private ChoiceBox<Integer> choiceSNG;

    @FXML
    private ChoiceBox<Integer> choiceST;

    private DaoHabitaciones daoHabitaciones = new DaoHabitaciones();


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
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoHuespedes.fxml");
    }
    
    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoReservar.fxml");
    }

    @FXML
void btnToggleDBLClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceDBL, txtDispDBL);
}

@FXML
void btnToggleSNGClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceSNG, txtDispSNG);
}

@FXML
void btnToggleSTClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceST, txtDispST);
}

private void cambiarDisponibilidad(ChoiceBox<Integer> choiceBox, TextField txtField) {
    Integer idHabitacion = choiceBox.getSelectionModel().getSelectedItem();
    if (idHabitacion != null) {
        try {
            // Obtener la disponibilidad actual
            boolean disponibilidadActual = daoHabitaciones.getDisponibilidadPorId(idHabitacion);

            // Alternar la disponibilidad
            boolean nuevaDisponibilidad = !disponibilidadActual;

            // Actualizar en la base de datos
            daoHabitaciones.actualizarDisponibilidad(idHabitacion, nuevaDisponibilidad);

            // Actualizar en la interfaz
            txtField.setText(nuevaDisponibilidad ? "1" : "0");

            System.out.println("Disponibilidad actualizada exitosamente para ID: " + idHabitacion);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al cambiar la disponibilidad: " + e.getMessage());
        }
    } else {
        System.out.println("No se seleccionó ninguna habitación.");
    }
}

    @FXML
void initialize() {
    cargarHabitaciones(choiceSNG, "SNG");
    cargarHabitaciones(choiceDBL, "DBL");
    cargarHabitaciones(choiceST, "ST");

    // Listener para mostrar disponibilidad
    choiceSNG.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        mostrarDisponibilidad(newValue, txtDispSNG);
    });

    choiceDBL.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        mostrarDisponibilidad(newValue, txtDispDBL);
    });

    choiceST.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        mostrarDisponibilidad(newValue, txtDispST);
    });
}

private void mostrarDisponibilidad(Integer idHabitacion, TextField txtField) {
    if (idHabitacion != null) {
        try {
            boolean disponibilidad = daoHabitaciones.getDisponibilidadPorId(idHabitacion);
            txtField.setText(disponibilidad ? "1" : "0"); // 1 para disponible, 0 para no disponible
        } catch (SQLException e) {
            e.printStackTrace();
            txtField.setText("Error");
        }
    } else {
        txtField.clear();
    }
}

    private void cargarHabitaciones(ChoiceBox<Integer> choiceBox, String tipo) {
        try {
            List<Integer> ids = obtenerIdsPorTipo(tipo);
            ObservableList<Integer> opciones = FXCollections.observableArrayList(ids);
            choiceBox.setItems(opciones);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> obtenerIdsPorTipo(String tipo) throws SQLException {
        return daoHabitaciones.getHabitacionesPorTipo(tipo);
    }

}
