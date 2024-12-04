package com.construccion.proyecto.control;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.dao.DaoHabitaciones;
import com.construccion.proyecto.dao.DaoHuesped;
import com.construccion.proyecto.dao.DaoReservas;
import com.construccion.proyecto.model.Habitacion;
import com.construccion.proyecto.model.Huesped;
import com.construccion.proyecto.model.Reservacion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminVentasController implements SceneAware{
    DaoReservas daoReservas = new DaoReservas();
    DaoHabitaciones daoHabitaciones = new DaoHabitaciones();
    DaoHuesped daoHuesped = new DaoHuesped();
    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnReservar;

    @FXML
    private ChoiceBox<String> choiceTipo;

    @FXML
    private TableView<InfoVenta> tblVentas;

    @FXML
    private TableColumn<InfoVenta, LocalDate> colFechaLlegada;

    @FXML
    private TableColumn<InfoVenta, LocalDate> colFechaSalida;

    @FXML
    private TableColumn<InfoVenta, String> colHuesped;

    @FXML
    private TableColumn<InfoVenta, Long> colNoches;

    @FXML
    private TableColumn<InfoVenta, Integer> colNum;

    @FXML
    private TableColumn<InfoVenta, Integer> colReservacion;

    @FXML
    private TableColumn<InfoVenta, String> colTipo;

    @FXML
    private TableColumn<InfoVenta, Double> colTotal;


    @FXML
    private DatePicker fechaFin;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblUsername1;



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
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminDashboard.fxml");
    }

    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminHuespedes.fxml");
    }

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminReservar.fxml");
    }
    @FXML
        public void initialize() {
        
        choiceTipo.getItems().addAll("","SNG", "DBL", "ST");
        choiceTipo.setValue(""); 
    }

    
    
    @FXML
void btnGenerarReporteClicked(ActionEvent event) {
    List<InfoVenta> infoVentas = new ArrayList<>();

    try {
        // Obtener el tipo de habitación seleccionado
        String tipoSeleccionado = (String) choiceTipo.getValue();

        List<Reservacion> reservaciones = daoReservas.obtenerReservaciones();

        for (Reservacion reservacion : reservaciones) {
            // Consideramos reservas activas aquellas que aún no han salido
            if (reservacion.getFechaSalida().isAfter(LocalDate.now()) || reservacion.getFechaSalida().isEqual(LocalDate.now())) {

                Huesped huesped = daoHuesped.buscarHuesped(reservacion.getIdHuesped());
                Habitacion habitacion = daoHabitaciones.buscarHabitacion(reservacion.getIdHabitacion());

                // Filtrar por tipo de habitación (si se seleccionó alguno)
                if (tipoSeleccionado == null || tipoSeleccionado.isEmpty() || habitacion.getTipoHabitacion().equals(tipoSeleccionado)) {
                    long noches = ChronoUnit.DAYS.between(reservacion.getFechaLlegada(), reservacion.getFechaSalida());
                    double total = noches * habitacion.getPrecio();

                    InfoVenta infoVenta = new InfoVenta(
                        reservacion.getIdReservacion(),
                        huesped.getNombre(),
                        reservacion.getFechaLlegada(),
                        reservacion.getFechaSalida(),
                        noches,
                        habitacion.getTipoHabitacion(),
                        total,
                        habitacion.getIdHabitacion()// Agregar número de habitación
                    );

                    infoVentas.add(infoVenta);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    cargarDatosEnTabla(infoVentas); // Mostrar las reservas activas filtradas
}


    

    
@FXML
private TableColumn<InfoVenta, Integer> colNumeroHabitacion;

private void cargarDatosEnTabla(List<InfoVenta> infoVentas) {
    ObservableList<InfoVenta> observableVentas = FXCollections.observableArrayList(infoVentas);

    if (observableVentas.isEmpty()) {
        sceneManager.mostrarAlerta("Sin resultados", "No hay ventas en el rango de fechas seleccionado.", AlertType.INFORMATION);
    }

    colFechaLlegada.setCellValueFactory(new PropertyValueFactory<>("fechaLlegada"));
    colFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
    colHuesped.setCellValueFactory(new PropertyValueFactory<>("huesped"));
    colNoches.setCellValueFactory(new PropertyValueFactory<>("noches"));
    colReservacion.setCellValueFactory(new PropertyValueFactory<>("idReservacion"));
    colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoHabitacion"));
    colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    colNum.setCellValueFactory(new PropertyValueFactory<>("numeroHabitacion")); // Nuevo enlace

    tblVentas.setItems(observableVentas); // Mostrar los datos en la tabla
}




    public class InfoVenta {
        private int idReservacion;
        private String huesped;
        private LocalDate fechaLlegada;
        private LocalDate fechaSalida;
        private long noches;
        private String tipoHabitacion;
        private double total;
        private int numeroHabitacion; // Nuevo campo
    
        public InfoVenta(int idReservacion, String huesped, LocalDate fechaLlegada, LocalDate fechaSalida, long noches, String tipoHabitacion, double total, int numeroHabitacion) {
            this.idReservacion = idReservacion;
            this.huesped = huesped;
            this.fechaLlegada = fechaLlegada;
            this.fechaSalida = fechaSalida;
            this.noches = noches;
            this.tipoHabitacion = tipoHabitacion;
            this.total = total;
            this.numeroHabitacion = numeroHabitacion;
        }
    
        // Getters
        public int getIdReservacion() { return idReservacion; }
        public String getHuesped() { return huesped; }
        public LocalDate getFechaLlegada() { return fechaLlegada; }
        public LocalDate getFechaSalida() { return fechaSalida; }
        public long getNoches() { return noches; }
        public String getTipoHabitacion() { return tipoHabitacion; }
        public double getTotal() { return total; }
        public int getNumeroHabitacion() { return numeroHabitacion; } // Nuevo getter
    }
    

}
