package support.operation.model_exceptions;

/**
 * Contrato que debe cumplir cualquier clase enumeradora
 * de excepciones
 * 
 * @author javiersolanop777
 */
public interface iFieldEnum {
    
    /**
     * Metodo para obtener el nombre del campo que lanza la excepcion
     * 
     * @return El nombre
     */
    public String getFieldName();
}
