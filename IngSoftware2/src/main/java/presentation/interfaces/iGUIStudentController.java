package presentation.interfaces;

import presentation.GUIStudent;

/**
 * Contrato para los controladores del modulo de estudiante
 * @see GUIStudent
 * 
 * @author javiersolanop777
 */
public interface iGUIStudentController {
    
    /**
     * Metodo para la arrancar la vista
     */
    void run();
    
    /**
     * Metodo para la definicion de la accion de cerrar sesion
     * 
     * @param prmGUIStudent Recibe la referencia de la vista
     */
    void logout(GUIStudent prmGUIStudent);
}
