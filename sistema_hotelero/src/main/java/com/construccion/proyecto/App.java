package com.construccion.proyecto;

import com.construccion.proyecto.control.LoginController;
import com.construccion.proyecto.control.SceneManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;



public class App extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);

        // Asegúrate de que la ruta del archivo FXML sea correcta
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));

        // Casting explícito
        Parent root = loader.load();
        Scene mainScene = new Scene(root);

        // Inyectar el SceneManager al controlador
        LoginController loginController = loader.getController();
        loginController.setSceneManager(sceneManager);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("The Continental");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
