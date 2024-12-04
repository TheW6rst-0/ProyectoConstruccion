package com.construccion.proyecto.control;
/**
 * Interfaz que debe ser implementada por las clases que necesiten acceder al SceneManager
 * para cambiar entre escenas en la aplicación.
 */

public interface SceneAware {
        /**
     * Establece el SceneManager que manejará la transición de escenas.
     * 
     * @param sceneManager El SceneManager que manejará la navegación entre vistas.
     */

    void setSceneManager(SceneManager sceneManager);
}


