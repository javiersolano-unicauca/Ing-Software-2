package presentation.interfaces;

import presentation.GUIRegister;

/**
 * Contrato para los controladores del registro de usuarios
 * @see GUIRegister
 * 
 * @author javiersolanop777
 */
public interface iGUIRegisterController {
    
    /**
     * Metodo para la arrancar la vista
     */
    void run();
    
    /**
     * Metodo para la definicion de la accion de registrar
     * 
     * @param prmGUIRegister Recibe la referencia de la vista
     */
    void register(GUIRegister prmGUIRegister);
    
    /**
     * Metodo para la definicion de la accion de regresar 
     * a la vista de inicio de sesion
     * 
     * @param prmGUIRegister Recibe la referencia de la vista
     */
    void backLogin(GUIRegister prmGUIRegister);
}
