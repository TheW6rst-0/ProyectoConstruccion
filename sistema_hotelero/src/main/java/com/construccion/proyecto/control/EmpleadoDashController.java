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
import javafx.scene.control.TextField;
/**
 * Controlador para la vista del dashboard de empleado, donde se pueden gestionar las habitaciones, 
 * revisar la disponibilidad y navegar entre diferentes vistas de la aplicación.
 */

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
     /**
     * Establece el manejador de escena para la navegación entre vistas.
     *
     * @param sceneManager El objeto encargado de gestionar las escenas.
     */

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    
        /**
     * Maneja el evento de clic en el botón de "Cerrar sesión".
     * Redirige al usuario a la pantalla de inicio de sesión.
     *
     * @param event El evento de clic.
     */

    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/Login.fxml");
    }
        /**
     * Maneja el evento de clic en el botón de "Check-in".
     * Redirige al usuario a la vista de check-in de huéspedes.
     *
     * @param event El evento de clic.
     */

    @FXML
    void btnCheckClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoCheck.fxml");
    }
        /**
     * Maneja el evento de clic en el botón de "Huéspedes".
     * Redirige al usuario a la vista de gestión de huéspedes.
     *
     * @param event El evento de clic.
     */

    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoHuespedes.fxml");
    }
        /**
     * Maneja el evento de clic en el botón de "Reservar".
     * Redirige al usuario a la vista de realizar una nueva reserva.
     *
     * @param event El evento de clic.
     */

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoReservar.fxml");
    }
        /**
     * Maneja el evento de clic en el botón de "Toggle DBL".
     * Alterna la disponibilidad de las habitaciones tipo DBL.
     *
     * @param event El evento de clic.
     */

    @FXML
void btnToggleDBLClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceDBL, txtDispDBL);
}
    /**
     * Maneja el evento de clic en el botón de "Toggle SNG".
     * Alterna la disponibilidad de las habitaciones tipo SNG.
     *
     * @param event El evento de clic.
     */

@FXML
void btnToggleSNGClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceSNG, txtDispSNG);
}
    /**
     * Maneja el evento de clic en el botón de "Toggle ST".
     * Alterna la disponibilidad de las habitaciones tipo ST.
     *
     * @param event El evento de clic.
     */

@FXML
void btnToggleSTClicked(ActionEvent event) {
    cambiarDisponibilidad(choiceST, txtDispST);
}
/**
     * Alterna la disponibilidad de una habitación en función de su ID.
     * Actualiza la base de datos y la interfaz de usuario con el nuevo estado.
     *
     * @param choiceBox El ChoiceBox que contiene la lista de habitaciones.
     * @param txtField El campo de texto que muestra la disponibilidad.
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
     * Inicializa la vista, cargando las habitaciones de los tipos SNG, DBL y ST en los ChoiceBoxes.
     * También configura los listeners para mostrar la disponibilidad al seleccionar una habitación.
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
     * Muestra la disponibilidad de una habitación en un campo de texto.
     * Si la habitación está disponible, muestra "1", de lo contrario, muestra "0".
     *
     * @param idHabitacion El ID de la habitación.
     * @param txtField El campo de texto donde se muestra la disponibilidad.
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
     * Carga las habitaciones disponibles en el ChoiceBox correspondiente según el tipo de habitación.
     *
     * @param choiceBox El ChoiceBox que se actualizará con las habitaciones.
     * @param tipo El tipo de habitación (SNG, DBL, ST).
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
     * Obtiene los IDs de las habitaciones disponibles para el tipo especificado.
     *
     * @param tipo El tipo de habitación (SNG, DBL, ST).
     * @return Una lista de IDs de habitaciones del tipo especificado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */

    private List<Integer> obtenerIdsPorTipo(String tipo) throws SQLException {
        return daoHabitaciones.getHabitacionesPorTipo(tipo);
    }

}
