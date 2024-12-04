package com.construccion.proyecto.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private final Stage mainStage;

    public SceneManager(Stage stage) {
        this.mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }

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

    // Método para mostrar alertas
    public void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}


