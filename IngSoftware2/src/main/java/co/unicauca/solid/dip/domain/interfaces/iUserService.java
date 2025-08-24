package co.unicauca.solid.dip.domain.interfaces;

//import exceptions.UserException;
import co.unicauca.solid.dip.domain.exception.UserException;
import co.unicauca.solid.dip.domain.models.UserModel;


/**
 * Contrato para las clases controladoras de usuarios
 * 
 * @author javiersolanop777
 */
public interface iUserService {
    
    /**
     * Metodo para guardar o actualizar un usuario en la DB
     * 
     * @param prmUser Recibe el usuario
     * 
     * @return 'true' si se logra registrar la informacion. 'false' si no
     * 
     * @throws UserException Si los campos no son validos
     */
    boolean save(UserModel prmUser) throws UserException;
    
    /**
     * Metodo para obtener un usuario por su email
     * 
     * @param prmEmail Recibe el email a buscar
     * 
     * @return El usuario si existe. De lo contrario null
     */
    UserModel getUser(String prmEmail);
}
