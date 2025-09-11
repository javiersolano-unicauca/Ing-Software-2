package business.controllers.interfaces;

import access.models.implement.UserModel;

/**
 * Contrato para las clases controladoras de la sesion
 * de usuarios
 * 
 * @author javiersolanop777
 */
public interface iSessionController {
    
    /**
     * Metodo para el inicio de sesion de un usuario
     * 
     * @param prmEmail Recibe el correo
     * @param prmPassword Recibe la contrasenia
     * 
     * @return Instancia con la informacion del usuario si se inicia la sesion.
     *         De lo contrario null.
     */
    UserModel login(String prmEmail, String prmPassword);
    
    /**
     * Metodo para cerrar la sesion de un usuario
     * finalizando la ejecucion del programa.
     */
    void logout();
}
