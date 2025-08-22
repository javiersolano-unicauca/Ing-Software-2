package exceptions;

import models.UserModel;

/**
 *  Clase para el manejo de excepciones de la clase 'UserModel'
 *  @see UserModel
 * 
 * @author javiersolanop777
 */
public class UserException extends ModelException {
    
    // Constructors:
    
    public UserException()
    {}
    
    public UserException(UserExceptionEnum prmField, Exception prmException)
    {
        super(prmField, prmException);
    }

    @Override
    protected String getFieldString(iExceptionEnum prmField) 
    {
        return prmField.getFieldName();
    }
    
    public static void throwException(UserExceptionEnum prmField, Exception prmException) throws UserException
    {
        throw new UserException(prmField, prmException);
    }

    public static void throwException(boolean prmCondition, UserExceptionEnum prmField, Exception prmException) throws UserException
    {
        if(prmCondition) throw new UserException(prmField, prmException);
    }
}
