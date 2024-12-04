package com.construccion.proyecto.control;

import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import com.construccion.proyecto.dao.DaoEmpleado;
import com.construccion.proyecto.model.Empleado;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;

public class AdminEmpleadosController implements SceneAware, Initializable{
    DaoEmpleado daoEmpleado = new DaoEmpleado();

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnHuespedes;

    @FXML
    private Button btnReservar;

    @FXML
    private Button btnVentas;

    @FXML
    private TableColumn<Empleado, String> colContrasenia;

    @FXML
    private TableColumn<Empleado, Integer> colId;

    @FXML
    private TableColumn<Empleado, String> colNombre;

    @FXML
    private TableColumn<Empleado, String> colUsuario;

    @FXML
    private TableView<Empleado> tblEmpleado;

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
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarDatosEnTabla(obtenerEmpleados());
    }

    private List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = null;
        try {
            empleados = daoEmpleado.obtenerEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    private void cargarDatosEnTabla(List<Empleado> empleados) {
        ObservableList<Empleado> observableEmpleados = FXCollections.observableArrayList(empleados);
        tblEmpleado.setItems(observableEmpleados);
        
        colContrasenia.setCellValueFactory(new PropertyValueFactory<Empleado, String>("contrasenia"));
        colId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("claveEmp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<Empleado, String>("usuario"));
    }

}
