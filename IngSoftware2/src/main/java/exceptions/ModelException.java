package exceptions;

/**
 * Clase para el manejo de excepciones en los modelos
 *  
 * @author javiersolanop777
 */
public abstract class ModelException extends Exception {
    
    // Propiedades de objeto
    
    private String atrField;
    private String atrException;
    
    // Constructors:
    
    public ModelException()
    {}
    
    public ModelException(iExceptionEnum prmField, Exception prmException)
    {
        atrField = getFieldString(prmField);
        atrException = prmException.getMessage();
    }

    @Override
    public String getMessage() 
    {
        return "El campo '" + atrField + "': " + atrException;
    }

    /**
     *  Metodo para obtener la cadena del campo
     * 
     *  @param prmField Recibe el campo
     * 
     *  @return La cadena del campo
     */
    protected abstract String getFieldString(iExceptionEnum prmField);
}
