package support.operation.model_exceptions;

import java.util.LinkedList;

/**
 *  Clase para el manejo de excepciones de la clase 'UserModel'
 *  @see UserModel
 * 
 * @author javiersolanop777
 */
public class UserException extends ModelException {
    
    // Constructors:
    
    public UserException()
    {
        setExceptionMessages(new LinkedList<>());
    }
    
    public UserException(UserExceptionEnum prmField, String prmMessage)
    {
        setExceptionMessages(new LinkedList<>());
        super.addExceptionMessage(prmField, prmMessage);
    }
    
    public void addExceptionMessage(UserExceptionEnum prmField, String prmMessage)
    {
        super.addExceptionMessage(prmField, prmMessage);
    }
    
    public void throwException() throws UserException
    {
        if(getMessage() == null) return;
        throw this;
    }
    
    public static void throwException(UserExceptionEnum prmField, String prmMessage) throws UserException
    {
        throw new UserException(prmField, prmMessage);
    }

    public static void throwException(boolean prmCondition, UserExceptionEnum prmField, String prmMessage) throws UserException
    {
        if(prmCondition) throw new UserException(prmField, prmMessage);
    }
}
