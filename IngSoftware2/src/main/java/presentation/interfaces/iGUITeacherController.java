package presentation.interfaces;

import presentation.GUITeacher;

/**
 * Contrato para los controladores del modulo de profesor
 * @see GUITeacher
 * 
 * @author javiersolanop777
 */
public interface iGUITeacherController {
    
    /**
     * Metodo para la arrancar la vista
     */
    void run();
    
    /**
     * Metodo para la definicion de la accion de cerrar sesion
     * 
     * @param prmGUITeacher Recibe la referencia de la vista
     */
    void logout(GUITeacher prmGUITeacher);
}
