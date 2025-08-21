package repositories;

import java.util.Map;

/**
 * Contrato que debe cumplir cualquier modelo
 * 
 * @author javiersolanop777
 */
public interface iModel {
    
    /**
     * Metodo para validar los campos del modelo
     * 
     * @return 'true' si los campos son correctos. De lo contrario 'false'
     */
    boolean validateFields();
    
    /**
     * Metodo para obtener los campos del modelo, con sus respectivos valores
     * 
     * @return Lista con los campos 
     */
    Map<String, Object> getFields();
}
