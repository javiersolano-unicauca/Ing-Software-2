package support.validations;

import access.models.interfaces.iModel;
import support.operation.model_exceptions.ModelException;

/**
 * Contrato que debe cumplir cualquier validador de campos
 * 
 * @author javiersolanop777
 */
public interface iValidator {
    
    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmModel Recibe la referencia del modelo
     * 
     *  @throws ModelException Cuando los campos no son validos en su totalidad
     */
    public void validate(iModel prmModel) throws ModelException;
}
