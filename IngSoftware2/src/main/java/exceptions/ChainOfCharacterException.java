package exceptions;

import controller.validations.iChainOfCharacter;

/**
 *  Clase para el manejo de excepciones de iChainOfCharacter
 *  @see iChainOfCharacter
 * 
 * @author javiersolanop777
 */
public class ChainOfCharacterException extends Exception {
    
    private String atrMessage;
    
    // Constructors:
    
    public ChainOfCharacterException()
    {};

    public ChainOfCharacterException(ChainOfCharacterExceptionEnum prmTypeException)
    {
        generateMessage(prmTypeException);
    };
    
    /**
     *  Metodo para generar la descripcion del error, 
     *  dependiendo del tipo de excepcion
     * 
     *  @param prmTypeException Recibe el tipo de excepcion establecida en la clase
     */
    private void generateMessage(ChainOfCharacterExceptionEnum prmTypeException)
    {
        atrMessage = prmTypeException.getName();
    }
    
    @Override
    public String getMessage()
    {
        return atrMessage;
    }
    
    public static void throwException(ChainOfCharacterExceptionEnum prmField) throws ChainOfCharacterException
    {
        throw new ChainOfCharacterException(prmField);
    }
    
    public static void throwException(boolean prmCondition, ChainOfCharacterExceptionEnum prmTypeException) throws ChainOfCharacterException
    {
        if(prmCondition) throw new ChainOfCharacterException(prmTypeException);
    } 
}
