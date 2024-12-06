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
/**
 * Controlador para la gestión de empleados en la interfaz de administrador.
 * Implementa la interfaz {@link SceneAware} y {@link Initializable}.
 */

public class AdminEmpleadosController implements SceneAware, Initializable{
    
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
    /**
     * Establece el gestor de escenas para este controlador.
     * 
     * @param sceneManager el gestor de escenas a asociar.
     */

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    /**
     * Maneja el evento para cerrar la sesión y redirigir a la pantalla de inicio de sesión.
     */

    @FXML
    void btnCerrarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/Login.fxml");
    }
    /**
     * Maneja el evento para navegar a la vista de verificación de administrador.
     */

    @FXML
    void btnCheckClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminCheck.fxml");
    }
    /**
     * Maneja el evento para navegar a la vista del panel de habitaciones.
     */

    @FXML
    void btnHabitacionesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminDashboard.fxml");
    }
    /**
     * Maneja el evento para navegar a la vista de huéspedes.
     */

    @FXML
    void btnHuespedesClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminHuespedes.fxml");
    }
    /**
     * Maneja el evento para navegar a la vista de reservas.
     */

    @FXML
    void btnReservarClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminReservar.fxml");
    }
    /**
     * Maneja el evento para navegar a la vista de ventas.
     */

    @FXML
    void btnVentasClicked(ActionEvent event) {
        sceneManager.switchScene("/view/admin/AdminVentas.fxml");
    }
        /**
     * Inicializa el controlador cargando los datos en la tabla de empleados.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarDatosEnTabla(obtenerEmpleados());
    }
    DaoEmpleado daoEmpleado = new DaoEmpleado();
        /**
     * Maneja el evento para agregar un nuevo empleado.
     */

    @FXML
    void btnAgregarClicked(ActionEvent event) {
        int rol;
        String contrasena = txtContrasena.getText();
        String nombre = txtNombre.getText();
        String usuario = txtUsuario.getText();
        String rolString = txtRol.getText();
        if( rolString.isEmpty() || nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()){
            sceneManager.mostrarAlerta("Error", "No se pudo agregar el empleado", AlertType.ERROR);
            return;
        }

        try {
            rol = Integer.parseInt(rolString);
            Empleado empleado = new Empleado(nombre, usuario, contrasena, rol);
            if(daoEmpleado.agregarEmpleado(empleado)){
                sceneManager.mostrarAlerta("Accion completada", "Empleado agregado exitosamente", AlertType.INFORMATION);
            }else{
                sceneManager.mostrarAlerta("Error", "No se pudo agregar el empleado", AlertType.ERROR);
            }
        } catch (Exception e) {
            sceneManager.mostrarAlerta("Error", "No se pudo agregar el empleado", AlertType.ERROR);
        }
    }
    /**
     * Maneja el evento para buscar un empleado por su ID.
     */

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
    /**
     * Maneja el evento para modificar los datos de un empleado.
     */

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
    /**
     * Maneja el evento para eliminar un empleado.
     */

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


    /**
     * Obtiene la lista de empleados desde la base de datos.
     * 
     * @return lista de empleados.
     */

    private List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = null;
        try {
            empleados = daoEmpleado.obtenerEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }
    /**
     * Carga los datos de los empleados en la tabla de la interfaz.
     * 
     * @param empleados lista de empleados a mostrar.
     */

    private void cargarDatosEnTabla(List<Empleado> empleados) {
        ObservableList<Empleado> observableEmpleados = FXCollections.observableArrayList(empleados);
        tblEmpleado.setItems(observableEmpleados);
        
        colContrasenia.setCellValueFactory(new PropertyValueFactory<Empleado, String>("contrasenia"));
        colId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("claveEmp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<Empleado, String>("usuario"));
    }

}
