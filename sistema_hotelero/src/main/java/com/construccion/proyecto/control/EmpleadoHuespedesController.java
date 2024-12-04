package com.construccion.proyecto.control;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.control.AdminHuespedesController.InfoReservacion;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmpleadoHuespedesController implements SceneAware, Initializable {

    

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnReservar;

     @FXML
    private TableView<InfoReservacion> tblHuesped;
    @FXML
    private TableColumn<InfoReservacion, LocalDate> colEntrada;
    @FXML
    private TableColumn<InfoReservacion, Integer> colReserva;
    @FXML
    private TableColumn<InfoReservacion, Integer> colIdHabitacion;

    @FXML
    private TableColumn<InfoReservacion, Integer> colIdHuesped;

    @FXML
    private TableColumn<InfoReservacion, String> colNombre;

    @FXML
    private TableColumn<InfoReservacion, Integer> colPersonas;

    @FXML
    private TableColumn<InfoReservacion, LocalDate> colSalida;

    @FXML
    private TableColumn<InfoReservacion, String> colTipo;

     @FXML
    private Button btnCuarto;

    @FXML
    private Button btnNombre;

    @FXML
    private Button btnReservacion;

    @FXML
    private TextField txtCuarto;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtReservacion;

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
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoDashboard.fxml");
    }

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/empleado/EmpleadoReservar.fxml");
    }

    public TextField getTxtCuarto() {
        return txtCuarto;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtReservacion() {
        return txtReservacion;
    }
    private final DaoHuesped daoHuesped = new DaoHuesped();
    private final DaoReservas daoReservas = new DaoReservas();
    private final DaoHabitaciones daoHabitaciones = new DaoHabitaciones();
    @FXML
    void btnCuartoClicked(ActionEvent event) {
        int habitacionABuscar = Integer.parseInt(txtCuarto.getText());
        List<InfoReservacion> infoReservaciones = obtenerInfoReservaciones();
        List<InfoReservacion> infoReservacionesFiltradas = new ArrayList<>();
        for (InfoReservacion infoReservacion : infoReservaciones) {
            if (infoReservacion.getIdHabitacion() == habitacionABuscar) {
                infoReservacionesFiltradas.add(infoReservacion);
            }
        }
        cargarDatosEnTabla(infoReservacionesFiltradas);
    }

    @FXML
    void btnNombreClicked(ActionEvent event) {
        String nombreABuscar = txtNombre.getText();
        List<InfoReservacion> infoReservaciones = obtenerInfoReservaciones();
        List<InfoReservacion> infoReservacionesFiltradas = new ArrayList<>();
        for (InfoReservacion infoReservacion : infoReservaciones) {
            if (infoReservacion.getNombreHuesped().contains(nombreABuscar)) {
                infoReservacionesFiltradas.add(infoReservacion);
            }
        }
        cargarDatosEnTabla(infoReservacionesFiltradas);
    }

    @FXML
    void btnReservacionClicked(ActionEvent event) {
        int reservacionABuscar = Integer.parseInt(txtReservacion.getText());
        List<InfoReservacion> infoReservaciones = obtenerInfoReservaciones();
        List<InfoReservacion> infoReservacionesFiltradas = new ArrayList<>();
        for (InfoReservacion infoReservacion : infoReservaciones) {
            if (infoReservacion.getIdReservacion() == reservacionABuscar) {
                infoReservacionesFiltradas.add(infoReservacion);
            }
        }
        cargarDatosEnTabla(infoReservacionesFiltradas);
    }

    private List<Reservacion> obtenerReservaciones() {
        List<Reservacion> reservaciones = null;
        try {
            reservaciones = daoReservas.obtenerReservaciones();
        } catch (Exception e) {
            e.printStackTrace();
            sceneManager.mostrarAlerta("Error", "No se pudieron cargar los huespedes.", null);
        }
        return reservaciones;
    }
    public List<InfoReservacion> obtenerInfoReservaciones(){
        List<Reservacion> reservaciones = obtenerReservaciones();
        List<InfoReservacion> infoReservaciones = new ArrayList<>();
        for (Reservacion reservacion : reservaciones) {
            try {
                Huesped huesped = daoHuesped.buscarHuesped(reservacion.getIdHuesped());
                Habitacion habitacion = daoHabitaciones.buscarHabitacion(reservacion.getIdHabitacion());
                InfoReservacion infoReservacion = new InfoReservacion(huesped, habitacion, reservacion); 
                infoReservaciones.add(infoReservacion);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }
        return infoReservaciones;
    }
    @Override
     public void initialize(URL location, ResourceBundle resources) {
        List<InfoReservacion> infoReservaciones = obtenerInfoReservaciones();
        cargarDatosEnTabla(infoReservaciones);
     }

    private void cargarDatosEnTabla(List<InfoReservacion> infoReservaciones) {
        ObservableList<InfoReservacion> observableInfoReservacion = FXCollections.observableArrayList(infoReservaciones);
        if(observableInfoReservacion.isEmpty()){
            sceneManager.mostrarAlerta("No se encontraron resultados", "No se encontraron reservaciones con los criterios de búsqueda", AlertType.INFORMATION);
        }
        tblHuesped.setItems(observableInfoReservacion);
        colEntrada.setCellValueFactory(new PropertyValueFactory<InfoReservacion, LocalDate>("fechaLlegada"));
        colNombre.setCellValueFactory(new PropertyValueFactory<InfoReservacion, String>("nombreHuesped"));
        colIdHuesped.setCellValueFactory(new PropertyValueFactory<InfoReservacion, Integer>("idHuesped"));
        colIdHabitacion.setCellValueFactory(new PropertyValueFactory<InfoReservacion, Integer>("idHabitacion"));
        colSalida.setCellValueFactory(new PropertyValueFactory<InfoReservacion, LocalDate>("fechaSalida"));
        colTipo.setCellValueFactory(new PropertyValueFactory<InfoReservacion, String>("tipoHabitacion"));
        colReserva.setCellValueFactory(new PropertyValueFactory<InfoReservacion, Integer>("idReservacion"));
    }
    
    
    public class InfoReservacion{
        private int idHuesped;
        private int idHabitacion;
        private LocalDate fechaLlegada;
        private LocalDate fechaSalida;
        private int idReservacion;
        private String tipoHabitacion;
        private String nombreHuesped;
        
        public InfoReservacion(Huesped huesped, Habitacion habitacion, Reservacion reservacion){
            this.nombreHuesped = huesped.getNombre();
            this.idHuesped = huesped.getIdHuesped();
            this.idHabitacion = habitacion.getIdHabitacion();
            this.fechaLlegada = reservacion.getFechaLlegada();
            this.fechaSalida = reservacion.getFechaSalida();
            this.idReservacion = reservacion.getIdReservacion();
            this.tipoHabitacion = habitacion.getTipoHabitacion();
        }

        public int getIdHuesped() {
            return idHuesped;
        }

        public int getIdHabitacion() {
            return idHabitacion;
        }

        public LocalDate getFechaLlegada() {
            return fechaLlegada;
        }

        public LocalDate getFechaSalida() {
            return fechaSalida;
        }

        public int getIdReservacion() {
            return idReservacion;
        }

        public String getTipoHabitacion() {
            return tipoHabitacion;
        }

        public String getNombreHuesped() {
            return nombreHuesped;
        }

        
    }
    

}
