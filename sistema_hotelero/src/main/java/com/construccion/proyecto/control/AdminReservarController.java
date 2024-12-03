package com.construccion.proyecto.control;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class AdminReservarController implements SceneAware{
    private DaoReservas daoReservas = new DaoReservas();
    private Reservacion reserva = new Reservacion(0, 0, 0, null, null);
    private DaoHuesped daoHuesped = new DaoHuesped();
    private Huesped huesped = new Huesped(0, null, null, 0);
    private DaoHabitaciones daoHabitaciones = new DaoHabitaciones();
    private Habitacion habitacion = new Habitacion(0, null, null, 0, false);
    private DaoTarjeta daoTarjeta = new DaoTarjeta();
    private Tarjeta tarjeta = daoTarjeta.buscarTarjeta(235);
    private Pago pago = new Pago();
    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private RadioButton btnEfectivo;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnProceder;

    @FXML
    private RadioButton btnTarjeta;

    @FXML
    private Button btnVentas;

    @FXML
    private ChoiceBox<String> choiceTipo;

    @FXML
    private DatePicker fechaLlegada;

    @FXML
    private DatePicker fechaSalida;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblUsername1;

    @FXML
    private TextField txtCamas;

    @FXML
    private TextField txtCorreo;


    @FXML
    private TextField txtDisponibilidad;

    @FXML
    private TextField txtNoches;

    @FXML
    private TextField txtNombre;


    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtTotal;

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
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }
    @FXML
    void btnHabitacionesClicked(ActionEvent event){
        sceneManager.switchScene("/view/admin/AdminDashboard.fxml");
    }
     public void initialize() {
    // Configurar opciones del ChoiceBox
    choiceTipo.getItems().addAll("SNG", "DBL", "ST");

    // Listener para cuando se seleccione un tipo de habitación
    choiceTipo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            try {
                Habitacion habitacion = daoHabitaciones.buscarHabitacionPorTipo(newValue);
                reserva.setIdHabitacion(habitacion.getIdHabitacion());
                if (habitacion != null) {
                    actualizarInfoHabitacion(habitacion);
                } else {
                    mostrarMensaje("No se encontró una habitación disponible para el tipo seleccionado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarMensaje("Error al buscar habitaciones en la base de datos.");
            }
        }
    });
}

// Método auxiliar para actualizar la interfaz con la información de la habitación
private void actualizarInfoHabitacion(Habitacion habitacion) {
    txtCamas.setText(habitacion.getCamas());
    txtPrecio.setText(String.valueOf(habitacion.getPrecio()));
    txtDisponibilidad.setText(habitacion.isDisponibilidad()? "Disponible" : "No disponible");
}

private void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
}
@FXML
void btnProcederClicked(ActionEvent event) {
    try {
        LocalDate date1 = fechaLlegada.getValue();
        LocalDate date2 = fechaSalida.getValue();
        if (date1 == null || date2 == null || date1.isAfter(date2)) {
            System.out.println("Fechas inválidas. Por favor, verifica.");
            return;
        }

        if (txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            System.out.println("Por favor, complete los campos obligatorios.");
            return;
        }

        // Configurar datos del huésped
        huesped.setNombre(txtNombre.getText());
        huesped.setEmail(txtCorreo.getText());
        huesped.setIdtarjeta(235);
        daoHuesped.agregarHuesped(huesped);
       huesped = daoHuesped.buscarHuesped(txtNombre.getText());
        int idHuesped = huesped.getIdhuesped();
        

        // Configurar datos de la reservación
        reserva.setIdHuesped(idHuesped);
        reserva.setFechaLlegada(date1);
        reserva.setFechaSalida(date2);

        long noches = ChronoUnit.DAYS.between(date1, date2);
        double precio = Double.parseDouble(txtPrecio.getText());
        double total = noches * precio;
        txtNoches.setText(String.valueOf(noches));
        txtTotal.setText(String.valueOf(total));

        if (btnTarjeta.isSelected()) {
            pago.pagoTarjeta(tarjeta, total);
        } else if (btnEfectivo.isSelected()) {
            pago.pagoEfectivo(total, total);
        }

        // Guardar la reservación
        daoReservas.agregarReservacion(reserva);


    } catch (Exception e) {
        System.out.println("Error al procesar la reserva: " + e.getMessage());
    }
}


    }



