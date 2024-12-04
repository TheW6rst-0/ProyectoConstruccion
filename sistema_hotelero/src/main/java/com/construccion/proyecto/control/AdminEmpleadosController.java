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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AdminEmpleadosController implements SceneAware, Initializable{
    DaoEmpleado daoEmpleado = new DaoEmpleado();
    @FXML
    private Button btnBorrar;


    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnId;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;
    
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
    @FXML
    private TextField txtRol;
    

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

    @FXML
    void btnAgregarClicked(ActionEvent event) {
        String contrasena = txtContrasena.getText();
        String nombre = txtNombre.getText();
        String usuario = txtUsuario.getText();
        int rol = Integer.parseInt(txtRol.getText());
        int id = Integer.parseInt(txtId.getText());
        Empleado empleado = new Empleado(id, nombre, usuario, contrasena, rol);
        try {
            if(daoEmpleado.agregarEmpleado(empleado)){
                sceneManager.mostrarAlerta("Accion completada", "Empleado agregado exitosamente", AlertType.INFORMATION);
            }else{
                sceneManager.mostrarAlerta("Error", "No se pudo agregar el empleado", AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnIdClicked(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        try {
            Empleado empleado = daoEmpleado.buscarEmpleado(id);
            if(empleado != null){
                txtContrasena.setText(empleado.getContrasenia());
                txtNombre.setText(empleado.getNombre());
                txtUsuario.setText(empleado.getUsuario());
                txtRol.setText(String.valueOf(empleado.getRol()));
            }else{
                sceneManager.mostrarAlerta("Error", "No se encontró el empleado", AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnModificarClicked(ActionEvent event) {
        String contrasenaMoficada = txtContrasena.getText();
        String nombreModificado = txtNombre.getText();
        String usuarioModificado = txtUsuario.getText();
        int rolModificado = Integer.parseInt(txtRol.getText());
        int id = Integer.parseInt(txtId.getText());

        Empleado empleado = new Empleado(id, nombreModificado, usuarioModificado, contrasenaMoficada, rolModificado);
        try {
            if(daoEmpleado.modificarEmpleado(empleado)){
                sceneManager.mostrarAlerta("Accion completada", "Empleado modificado exitosamente", AlertType.INFORMATION);
            }else{
                sceneManager.mostrarAlerta("Error", "No se pudo modificar el empleado", AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnBorrarClicked(ActionEvent event){
        String contrasenaMoficada = txtContrasena.getText();
        String nombreModificado = txtNombre.getText();
        String usuarioModificado = txtUsuario.getText();
        int rolModificado = Integer.parseInt(txtRol.getText());
        int id = Integer.parseInt(txtId.getText());
        Empleado empleado = new Empleado(id, nombreModificado, usuarioModificado, contrasenaMoficada, rolModificado);
        try {
            if(daoEmpleado.eliminarEmpleado(empleado)){
                sceneManager.mostrarAlerta("Accion completada", "Empleado eliminado exitosamente", AlertType.INFORMATION);
            }else{
                sceneManager.mostrarAlerta("Error", "No se pudo eliminr el empleado", AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
