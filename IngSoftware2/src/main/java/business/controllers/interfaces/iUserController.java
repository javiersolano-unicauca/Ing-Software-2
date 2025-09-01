package business.controllers.interfaces;

import access.models.implement.UserModel;
import support.operation.model_exceptions.ModelException;
import support.operation.model_exceptions.UserException;

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
     * @return 'true' si se logra registrar la informacion. 'false' si no
     * 
     * @throws ModelException Si los campos no son validos
     */
    boolean save(UserModel prmUser) throws ModelException;
    
    /**
     * Metodo para obtener un usuario por su email
     * 
     * @param prmEmail Recibe el email a buscar
     * 
     * @return El usuario si existe. De lo contrario null
     */
    UserModel getUser(String prmEmail);
}
