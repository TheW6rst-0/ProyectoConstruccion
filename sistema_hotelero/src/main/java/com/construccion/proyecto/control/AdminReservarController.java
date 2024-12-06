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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AdminReservarController implements SceneAware {
 
    @FXML
    private Button btnCerrar, btnCheck, btnEmpleados, btnHabitaciones, btnHuespedes, btnProceder, btnVentas;
    @FXML
    private RadioButton btnEfectivo, btnTarjeta;
    @FXML
    private ChoiceBox<String> choiceTipo;
    @FXML
    private DatePicker fechaLlegada, fechaSalida;
    @FXML
    private Label lblUsername, lblUsername1;
    @FXML
    private TextField txtCamas, txtCorreo, txtDisponibilidad, txtNoches, txtNombre, txtMonto, txtPrecio, txtTotal;
   /**
 * Controlador para gestionar las reservaciones de habitaciones.
 * Permite realizar la selección de fechas, tipo de habitación, y el pago de la reserva.
 * Utiliza DAO para acceder a la base de datos y realizar operaciones relacionadas con las habitaciones,
 * los huéspedes, las reservas y los pagos.
 */
    private SceneManager sceneManager;
 

    /**
     * Establece el manejador de la escena.
     * 
     * @param sceneManager El administrador de escenas.
     */

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    /**
     * Método que maneja el evento de cierre de la sesión.
     * Cambia la vista a la pantalla de inicio de sesión.
     * 
     * @param event El evento de clic.
     */

    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/Login.fxml");
    }
    /**
     * Método que maneja el evento de clic en el botón de "Check".
     * Cambia la vista a la pantalla de administración de verificaciones.
     * 
     * @param event El evento de clic.
     */

    @FXML
    void btnCheckClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminCheck.fxml");
    }
    /**
     * Método que maneja el evento de clic en el botón de "Empleados".
     * Cambia la vista a la pantalla de administración de empleados.
     * 
     * @param event El evento de clic.
     */

    @FXML
    void btnEmpleadosClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminEmpleados.fxml");
    }
    /**
     * Método que maneja el evento de clic en el botón de "Huespedes".
     * Cambia la vista a la pantalla de administración de huéspedes.
     * 
     * @param event El evento de clic.
     */

    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminHuespedes.fxml");
    }
    /**
     * Método que maneja el evento de clic en el botón de "Ventas".
     * Cambia la vista a la pantalla de administración de ventas.
     * 
     * @param event El evento de clic.
     */

    @FXML
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }
/**
     * Método que maneja el evento de clic en el botón de "Habitaciones".
     * Cambia la vista a la pantalla de administración de habitaciones.
     * 
     * @param event El evento de clic.
     */

    @FXML
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminDashboard.fxml");
    }
    private final  DaoReservas daoReservas = new DaoReservas();
    private final Reservacion reserva = new Reservacion(0, 0, 0, null, null);
    private DaoHuesped daoHuesped = new DaoHuesped();
    private Huesped huesped = new Huesped(0, null, null, 0);
    private DaoHabitaciones daoHabitaciones = new DaoHabitaciones();
    private DaoTarjeta daoTarjeta = new DaoTarjeta();
    private Tarjeta tarjeta = daoTarjeta.buscarTarjeta(235);
    private Pago pago = new Pago();
    private double total;
    /**
     * Método de inicialización que configura los listeners y el comportamiento inicial.
     * Se configura la actualización del cálculo de pago cuando cambian las fechas de llegada o salida,
     * así como la selección de tipo de habitación y método de pago.
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
     * Método que maneja la selección de pago con tarjeta.
     * Desactiva el campo de monto cuando se selecciona tarjeta.
     */

    private void manejarSeleccionTarjeta() {
        txtMonto.clear();
        txtMonto.setDisable(true);
    }
    /**
     * Método que maneja la selección de pago en efectivo.
     * Activa el campo de monto cuando se selecciona efectivo.
     */

    private void manejarSeleccionEfectivo() {
        txtMonto.setDisable(false);
    }
    /**
     * Actualiza la información de la habitación seleccionada en los campos correspondientes.
     * 
     * @param habitacion La habitación seleccionada.
     */

    private void actualizarInfoHabitacion(Habitacion habitacion) {
        txtCamas.setText(habitacion.getCamas());
        txtPrecio.setText(String.valueOf(habitacion.getPrecio()));
        txtDisponibilidad.setText(habitacion.isDisponibilidad() ? "Disponible" : "No disponible");
    }
/**
     * Muestra un mensaje en la consola.
     * 
     * @param mensaje El mensaje a mostrar.
     */

    private void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    /**
     * Calcula el pago total de la reserva en base a las fechas de llegada y salida,
     * el precio de la habitación y otros parámetros ingresados por el usuario.
     */

    @FXML
    private void calcularPago() {
        LocalDate date1 = fechaLlegada.getValue();
        LocalDate date2 = fechaSalida.getValue();

        if (date1 == null || date2 == null || date1.isAfter(date2) ) {
            txtNoches.clear();
            txtTotal.clear();
          
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
     * Método que maneja el evento de clic en el botón de "Proceder".
     * Realiza la creación de la reserva, el pago y la actualización de la base de datos.
     * 
     * @param event El evento de clic.
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

            Boolean estado = daoReservas.agregarReservacion(reserva);
            if(estado){
                sceneManager.mostrarAlerta("Acción exitosa", "No se logró agregar una reservación.", AlertType.INFORMATION);
            }
        } catch (Exception e) {
            sceneManager.mostrarAlerta("Error", "No se logró agregar una reservación", AlertType.ERROR);
        }
    }
}
