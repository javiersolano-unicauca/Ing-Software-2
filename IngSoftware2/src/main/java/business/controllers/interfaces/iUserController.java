package business.controllers.interfaces;

import access.models.implement.UserModel;
import support.operation.model_exceptions.ModelException;

/**
 * Contrato para las clases controladoras de usuarios
 * 
 * @author javiersolanop777
 */
public interface iUserController {
    
    /**
     * Metodo para guardar o actualizar un usuario en la DB
     * 
     * @param prmUser Recibe el usuario
     * 
     * @return La instancia del usuario guardado. De lo contrario null
     * 
     * @throws ModelException Si los campos no son validos
     */
    UserModel save(UserModel prmUser) throws ModelException;
    
    /**
     * Metodo para obtener un usuario por su email
     * 
     * @param prmEmail Recibe el email a buscar
     * 
     * @return El usuario si existe. De lo contrario null
     */
    UserModel getUser(String prmEmail);
}
