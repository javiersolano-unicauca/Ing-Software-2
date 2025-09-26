package access.repositories.interfaces;

import access.models.RoleEnum;
import access.models.implement.UserModel;

/**
 * Contrato para el acceso a datos de las instancias UserModel
 * 
 * @author javiersolanop777
 */
public interface iUserRepository extends iRepository<String> {
    
    /**
     * Metodo para obtener un usuario por el id y el role
     * 
     * @param prmId Recibe su id
     * @param prmRole Recibe su role
     * 
     * @return La instancia del usuario si existe. De lo contrario null
     */
    UserModel getByIdAndRole(String prmId, RoleEnum prmRole);
}
