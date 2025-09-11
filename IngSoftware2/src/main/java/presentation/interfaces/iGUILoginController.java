package presentation.interfaces;

import presentation.GUILogin;

/**
 * Contrato para los controladores del inicio de sesion
 * @see GUIRegister
 * 
 * @author javiersolanop777
 */
public interface iGUILoginController {
    
    /**
     * Metodo para la arrancar la vista
     */
    void run();
    
    /**
     * Metodo para la definicion de la accion de iniciar sesion
     * 
     * @param prmGUILogin Recibe la referencia de la vista
     */
    void login(GUILogin prmGUILogin);
}
