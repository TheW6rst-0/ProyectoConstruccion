package com.construccion.proyecto.control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.construccion.proyecto.dao.DaoHabitaciones;
import com.construccion.proyecto.dao.DaoHuesped;
import com.construccion.proyecto.dao.DaoReservas;
import com.construccion.proyecto.dao.DaoTarjeta;
import com.construccion.proyecto.model.Habitacion;
import com.construccion.proyecto.model.Huesped;
import com.construccion.proyecto.model.Pago;
import com.construccion.proyecto.model.Reservacion;
import com.construccion.proyecto.model.Tarjeta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
/**
 * Controlador para la ventana de reservaciones en el sistema del empleado.
 */

public class EmpleadoReservarController implements SceneAware {
  

    @FXML
    private Button btnCerrar, btnCheck, btnEmpleados, btnHabitaciones, btnHuespedes, btnProceder, btnVentas;
    @FXML
    private RadioButton btnEfectivo, btnTarjeta;
    @FXML
    private ChoiceBox<String> choiceTipo;
    @FXML
    private DatePicker fechaLlegada, fechaSalida;

    @FXML
    private TextField txtCamas, txtCorreo, txtDisponibilidad, txtNoches, txtNombre, txtMonto, txtPrecio, txtTotal;

    private SceneManager sceneManager;
    /**
     * Establece el SceneManager para la administración de las pantallas.
     * 
     * @param sceneManager El SceneManager que maneja la navegación entre vistas.
     */

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    /**
     * Maneja el evento de clic en el botón de cerrar sesión.
     * 
     * @param event El evento de clic en el botón.
     */

    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/Login.fxml");
    }
    /**
     * Maneja el evento de clic en el botón para ir a la vista de check-in.
     * 
     * @param event El evento de clic en el botón.
     */

    @FXML
    void btnCheckClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoCheck.fxml");
    }
    /**
     * Maneja el evento de clic en el botón para ir a la vista de huéspedes.
     * 
     * @param event El evento de clic en el botón.
     */


    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoHuespedes.fxml");
    }
    /**
     * Maneja el evento de clic en el botón para ir a la vista de habitaciones.
     * 
     * @param event El evento de clic en el botón.
     */

    @FXML
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoDashboard.fxml");
    }
    private final  DaoReservas daoReservas = new DaoReservas();
    private final Reservacion reserva = new Reservacion(0, 0, 0, null, null);
    private  DaoHuesped daoHuesped = new DaoHuesped();
    private Huesped huesped = new Huesped(0, null, null, 0);
    private DaoHabitaciones daoHabitaciones = new DaoHabitaciones();
    private DaoTarjeta daoTarjeta = new DaoTarjeta();
    private Tarjeta tarjeta = daoTarjeta.buscarTarjeta(235);
    private Pago pago = new Pago();
    private double total;
        /**
     * Método de inicialización, se ejecuta cuando la vista es cargada.
     * Establece los listeners y configuraciones iniciales de los controles.
     */

    public void initialize() {
          // Listener para actualizar el cálculo cuando cambie fechaLlegada
    fechaLlegada.valueProperty().addListener((observable, oldValue, newValue) -> calcularPago());

    // Listener para actualizar el cálculo cuando cambie fechaSalida
    fechaSalida.valueProperty().addListener((observable, oldValue, newValue) -> calcularPago());
        // Crear un grupo para los RadioButtons
        ToggleGroup grupoPago = new ToggleGroup();
        btnEfectivo.setToggleGroup(grupoPago);
        btnTarjeta.setToggleGroup(grupoPago);

        // Configurar comportamiento de los RadioButtons
        btnTarjeta.setOnAction(event -> manejarSeleccionTarjeta());
        btnEfectivo.setOnAction(event -> manejarSeleccionEfectivo());

        // Configurar opciones del ChoiceBox
        choiceTipo.getItems().addAll("SNG", "DBL", "ST");

        // Listener para actualizar información de la habitación seleccionada
        choiceTipo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && fechaLlegada.getValue() != null && fechaSalida.getValue() != null) {
                try {
                    List<Habitacion> habitacionesDisponibles = daoHabitaciones.buscarHabitacionesDisponiblesPorTipoYFechas(
                        newValue, 
                        fechaLlegada.getValue(), 
                        fechaSalida.getValue()
                    );
                    if (habitacionesDisponibles.isEmpty()) {
                        mostrarMensaje("No hay habitaciones disponibles para este tipo y rango de fechas.");
                    } else {
                        Habitacion habitacionSeleccionada = habitacionesDisponibles.get(0); // Selecciona una habitación disponible
                        reserva.setIdHabitacion(habitacionSeleccionada.getIdHabitacion());
                        actualizarInfoHabitacion(habitacionSeleccionada);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    mostrarMensaje("Error al verificar la disponibilidad de habitaciones.");
                }
            }
        });
    }
    /**
     * Maneja la selección de pago mediante tarjeta, deshabilitando el campo de monto.
     */

    private void manejarSeleccionTarjeta() {
        txtMonto.clear();
        txtMonto.setDisable(true);
    }
        /**
     * Maneja la selección de pago en efectivo, habilitando el campo de monto.
     */

    private void manejarSeleccionEfectivo() {
        txtMonto.setDisable(false);
    }
    /**
     * Actualiza los detalles de la habitación seleccionada en la interfaz.
     * 
     * @param habitacion La habitación seleccionada.
     */

    private void actualizarInfoHabitacion(Habitacion habitacion) {
        txtCamas.setText(habitacion.getCamas());
        txtPrecio.setText(String.valueOf(habitacion.getPrecio()));
        txtDisponibilidad.setText(habitacion.isDisponibilidad() ? "Disponible" : "No disponible");
    }
        /**
     * Muestra un mensaje en la consola (se puede mejorar para mostrar en la interfaz).
     * 
     * @param mensaje El mensaje que se desea mostrar.
     */

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
        /**
     * Calcula el total a pagar basado en las fechas seleccionadas y el precio de la habitación.
     */

    @FXML
    private void calcularPago() {
        LocalDate date1 = fechaLlegada.getValue();
        LocalDate date2 = fechaSalida.getValue();

        if (date1 == null || date2 == null || date1.isAfter(date2)) {
            txtNoches.clear();
            txtTotal.clear();
            System.out.println("Fechas inválidas. Por favor, verifica.");
            return;
        }

        if (txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            txtNoches.clear();
            txtTotal.clear();
            System.out.println("Por favor, complete los campos obligatorios.");
            return;
        }

        long noches = ChronoUnit.DAYS.between(date1, date2);
        double precio;
        try {
            precio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido. Por favor, verifica.");
            return;
        }

        total = noches * precio;
        txtNoches.setText(String.valueOf(noches));
        txtTotal.setText(String.format("%.2f", total));
    }
    /**
     * Maneja el evento de clic en el botón de proceder para procesar la reservación.
     * 
     * @param event El evento de clic en el botón.
     */

    @FXML
    void btnProcederClicked(ActionEvent event) {
        try {
            LocalDate date1 = fechaLlegada.getValue();
            LocalDate date2 = fechaSalida.getValue();

            huesped.setNombre(txtNombre.getText());
            huesped.setEmail(txtCorreo.getText());
            huesped.setIdTarjeta(235); // Ajustar según tu implementación
            daoHuesped.agregarHuesped(huesped);
            huesped = daoHuesped.buscarHuesped(txtNombre.getText());
            reserva.setIdHuesped(huesped.getIdHuesped());
            reserva.setFechaLlegada(date1);
            reserva.setFechaSalida(date2);

            if (btnTarjeta.isSelected()) {
                pago.pagoTarjeta(tarjeta, total);
            } else if (btnEfectivo.isSelected() && !txtMonto.getText().isEmpty()) {
                try {
                    double efectivo = Double.parseDouble(txtMonto.getText());
                    pago.pagoEfectivo(total, efectivo);
                } catch (NumberFormatException e) {
                    System.out.println("Monto de efectivo inválido. Por favor, verifica.");
                }
            }

            daoReservas.agregarReservacion(reserva);
        } catch (Exception e) {
            System.out.println("Error al procesar la reserva: " + e.getMessage());
        }
    }
}
