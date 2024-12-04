package com.construccion.proyecto.control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.construccion.proyecto.dao.DaoHabitaciones;
import com.construccion.proyecto.dao.DaoReservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class EmpleadoCheckController implements SceneAware{

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnReservar;


    @FXML
    private ChoiceBox<String> choiceTipo;


    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtReserva;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnCancelar;

    private SceneManager sceneManager;

    @FXML
    private Accordion accReservas;

    @FXML
private Accordion accordion;

private DaoReservas daoReservas = new DaoReservas();
private DaoHabitaciones daoHabitaciones = new DaoHabitaciones();

@FXML
public void initialize() {
    // Configurar el ChoiceBox con los tipos de habitación
    ObservableList<String> tipos = FXCollections.observableArrayList("SNG", "DBL", "ST", "Sin tipo");
    choiceTipo.setItems(tipos);
    choiceTipo.getSelectionModel().select("Sin tipo");  // Opción por defecto

    try {
        Map<Integer, List<LocalDate>> ocupaciones = daoReservas.obtenerFechasOcupadasPorHabitacion();
        ObservableList<TitledPane> panes = FXCollections.observableArrayList();

        for (Map.Entry<Integer, List<LocalDate>> entry : ocupaciones.entrySet()) {
            int idHabitacion = entry.getKey();
            List<LocalDate> fechas = entry.getValue();

            // Crear un String con las fechas ocupadas
            StringBuilder sb = new StringBuilder();
            for (LocalDate fecha : fechas) {
                sb.append(fecha.toString()).append("\n");
            }

            // Crear el TitledPane para cada habitación
            TitledPane pane = new TitledPane("Habitación #" + idHabitacion, new Label(sb.toString()));
            panes.add(pane);
        }

        // Añadir todos los TitledPanes al Accordion
        accordion.getPanes().setAll(panes);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/Login.fxml");
    }

    @FXML
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoDashboard.fxml");
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
    void btnCancelarClicked(ActionEvent event) {
    String idReservacionStr = txtReserva.getText(); // Obtener el texto del campo txtReservacion

    if (idReservacionStr.isEmpty()) {
        // Si el campo está vacío, mostramos un mensaje de error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ID de Reservación vacío");
        alert.setContentText("Por favor, ingresa un ID de reservación válido.");
        alert.showAndWait();
        return;
    }

    try {
        int idReservacion = Integer.parseInt(idReservacionStr); // Convertir el texto a un número
        DaoReservas daoReservas = new DaoReservas(); // Crear una instancia del DAO

        boolean eliminado = daoReservas.eliminarReservacion(idReservacion); // Llamamos al DAO para eliminar la reservación

        if (eliminado) {
            // Si la eliminación fue exitosa, mostramos una ventana de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Reservación eliminada");
            alert.setContentText("La reservación con ID " + idReservacion + " fue eliminada exitosamente.");
            alert.showAndWait();
        } else {
            // Si no se encontró la reservación, mostramos un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Reservación no encontrada");
            alert.setContentText("No se encontró una reservación con el ID proporcionado.");
            alert.showAndWait();
        }
    } catch (NumberFormatException e) {
        // Si el texto no se puede convertir a un número, mostramos un mensaje de error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ID de Reservación inválido");
        alert.setContentText("El ID de reservación debe ser un número válido.");
        alert.showAndWait();
    } catch (SQLException e) {
        // Si hay un error de base de datos, mostramos un mensaje de error
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Base de Datos");
        alert.setHeaderText("No se pudo eliminar la reservación");
        alert.setContentText("Hubo un error al intentar eliminar la reservación. Por favor, inténtalo nuevamente.");
        alert.showAndWait();
    }
}


    @FXML
void btnFiltrarClicked(ActionEvent event) {
    String tipoSeleccionado = choiceTipo.getValue();
    String numeroIngresado = txtNumero.getText();
    ObservableList<TitledPane> panesFiltrados = FXCollections.observableArrayList();

    try {
        // Filtrar por número de habitación si se ingresó un número
        List<Integer> idsHabitaciones = new ArrayList<>();
        if (!numeroIngresado.isEmpty()) {
            int numeroHabitacion = Integer.parseInt(numeroIngresado);
            idsHabitaciones.add(numeroHabitacion);
        } else if ("Sin tipo".equals(tipoSeleccionado)) {
            idsHabitaciones = daoHabitaciones.getAllHabitaciones();  // Mostrar todas si no se especifica número
        } else {
            idsHabitaciones = daoHabitaciones.getHabitacionesPorTipo(tipoSeleccionado);  // Filtrar por tipo
        }

        // Filtrar las habitaciones en el Accordion
        Map<Integer, List<LocalDate>> ocupaciones = daoReservas.obtenerFechasOcupadasPorHabitacion();
        for (Map.Entry<Integer, List<LocalDate>> entry : ocupaciones.entrySet()) {
            int idHabitacion = entry.getKey();
            if (idsHabitaciones.contains(idHabitacion)) {
                List<LocalDate> fechas = entry.getValue();

                StringBuilder sb = new StringBuilder();
                for (LocalDate fecha : fechas) {
                    sb.append(fecha.toString()).append("\n");
                }

                // Crear el TitledPane para la habitación
                TitledPane pane = new TitledPane("Habitación #" + idHabitacion, new Label(sb.toString()));
                panesFiltrados.add(pane);
            }
        }

        // Actualizar el Accordion con las habitaciones filtradas
        accordion.getPanes().setAll(panesFiltrados);
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        // Manejo de error si el número ingresado no es válido
        System.out.println("Número de habitación inválido.");
    }
}



}
