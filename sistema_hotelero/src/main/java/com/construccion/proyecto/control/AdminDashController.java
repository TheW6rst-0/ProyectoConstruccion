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
/**
 * Controlador de la vista principal del panel de administración.
 * Administra las habitaciones y las transiciones entre vistas.
 */

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
    /**
     * Establece el manejador de escenas para realizar transiciones entre vistas.
     *
     * @param sceneManager el manejador de escenas.
     */

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Cambia la vista a la pantalla de inicio de sesión.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/Login.fxml");
    }
    /**
     * Cambia la vista al panel de verificación de habitaciones.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
    void btnCheckClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminCheck.fxml");
    }
    /**
     * Cambia la vista al panel de administración de empleados.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
    void btnEmpleadosClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminEmpleados.fxml");
    }
    /**
     * Cambia la vista al panel de administración de huéspedes.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminHuespedes.fxml");
    }   
    /**
     * Cambia la vista al panel de reservaciones.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminReservar.fxml");
    }

    /**
     * Cambia la vista al panel de ventas.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }
    /**
     * Alterna la disponibilidad de las habitaciones de tipo DBL.
     *
     * @param event el evento de clic en el botón.
     */

    @FXML
void btnToggleDBLClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceDBL, txtDispDBL);
}
    /**
     * Alterna la disponibilidad de las habitaciones de tipo SNG.
     *
     * @param event el evento de clic en el botón.
     */

@FXML
void btnToggleSNGClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceSNG, txtDispSNG);
}
    /**
     * Alterna la disponibilidad de las habitaciones de tipo ST.
     *
     * @param event el evento de clic en el botón.
     */

@FXML
void btnToggleSTClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceST, txtDispST);
}
/**
     * Cambia la disponibilidad de una habitación específica.
     *
     * @param choiceBox el ChoiceBox que contiene los IDs de las habitaciones.
     * @param txtField  el campo de texto para mostrar la disponibilidad.
     */

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
    /**
     * Inicializa los datos del controlador, cargando habitaciones y configurando listeners.
     */

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
    /**
     * Muestra la disponibilidad de una habitación en el campo de texto correspondiente.
     *
     * @param idHabitacion el ID de la habitación.
     * @param txtField     el campo de texto donde se mostrará la disponibilidad.
     */

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
    /**
     * Carga los IDs de las habitaciones según su tipo en el ChoiceBox correspondiente.
     *
     * @param choiceBox el ChoiceBox donde se cargarán los IDs.
     * @param tipo      el tipo de habitación (SNG, DBL, ST).
     */

    private void cargarHabitaciones(ChoiceBox<Integer> choiceBox, String tipo) {
        try {
            List<Integer> ids = obtenerIdsPorTipo(tipo);
            ObservableList<Integer> opciones = FXCollections.observableArrayList(ids);
            choiceBox.setItems(opciones);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**
     * Obtiene los IDs de habitaciones por tipo desde la base de datos.
     *
     * @param tipo el tipo de habitación (SNG, DBL, ST).
     * @return una lista de IDs de habitaciones.
     * @throws SQLException si ocurre un error en la consulta a la base de datos.
     */

    private List<Integer> obtenerIdsPorTipo(String tipo) throws SQLException {
        return daoHabitaciones.getHabitacionesPorTipo(tipo);
    }

}
