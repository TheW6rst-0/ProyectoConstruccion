package com.construccion.proyecto.control;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.util.concurrent.TimeUnit.DAYS;

import com.construccion.proyecto.dao.DaoHabitaciones;
import com.construccion.proyecto.dao.DaoHuesped;
import com.construccion.proyecto.dao.DaoReservas;
import com.construccion.proyecto.model.Habitacion;
import com.construccion.proyecto.model.Huesped;
import com.construccion.proyecto.model.Reservacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class AdminReservarController implements SceneAware{
    private DaoReservas daoReservas = new DaoReservas();
    private Reservacion reserva = new Reservacion(0, 0, 0, null, null);
    private DaoHuesped daoHuesped = new DaoHuesped();
    private Huesped huesped = new Huesped(0, null, null, 0);
    private DaoHabitaciones daoHabitaciones = new DaoHabitaciones();
    private Habitacion habitacion = new Habitacion(0, null, null, 0, false);
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
    private ChoiceBox<?> choiceTipo;

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
    private TextField txtDireccion;

    @FXML
    private TextField txtDisponibilidad;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtNoches;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPersonas;

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
    void btnProcederClicked(ActionEvent event) {
       
        LocalDate date1 = fechaLlegada.getValue();
        reserva.setFechaLlegada(date1);
        LocalDate date2 = fechaSalida.getValue();
        reserva.setFechaSalida(date2);

        huesped.setNombre(txtNombre.getText());
        huesped.setEmail(txtCorreo.getText());
        long noches = ChronoUnit.DAYS.between(date1,date2);  
        txtNoches.setText(String.valueOf(noches));
        double precio = noches * habitacion.getPrecio();
        txtPrecio.setText(String.valueOf(precio));
        daoHuesped.agregarHuesped(huesped);
        daoReservas.agregarReservacion(reserva);
    }
    @FXML
    void btnHabitacionesClicked(ActionEvent event) {

    }

}

