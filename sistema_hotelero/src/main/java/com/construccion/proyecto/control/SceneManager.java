package com.construccion.proyecto.control;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 * Clase encargada de gestionar las transiciones entre escenas en la aplicación.
 * Maneja la carga de archivos FXML y la inyección de controladores con el SceneManager.
 */

public class SceneManager {
    private final Stage mainStage;
    /**
     * Constructor que inicializa el SceneManager con la etapa principal de la aplicación.
     * 
     * @param stage La etapa principal donde se mostrarán las escenas.
     */

    public SceneManager(Stage stage) {
        this.mainStage = stage;
    }
    /**
     * Obtiene la etapa principal de la aplicación.
     * 
     * @return La etapa principal donde se están mostrando las escenas.
     */

    public Stage getMainStage() {
        return mainStage;
    }
/**
     * Cambia la escena actual cargando un nuevo archivo FXML y configurando el controlador.
     * 
     * @param fxmlFile El archivo FXML que representa la nueva escena.
     */

    // Método para cargar la escena y el controlador de forma automática
    public void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Inyectar el SceneManager al controlador
            Object controller = loader.getController();
            if (controller instanceof SceneAware) {
                ((SceneAware) controller).setSceneManager(this);
            }

            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la escena: " + fxmlFile);
        }
    }
    /**
     * Muestra una alerta con el título y contenido especificados.
     * 
     * @param titulo El título de la alerta.
     * @param contenido El contenido de la alerta.
     * @param tipo El tipo de alerta (informativa, error, advertencia, etc.).
     */

    // Método para mostrar alertas
    public void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}


