package support.validations;

import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import support.operation.model_exceptions.UserException;
import support.operation.model_exceptions.UserExceptionEnum;

/**
 *  Clase para las validaciones de los usuarios
 *  @see UserModel
 * 
 * @author javiersolanop777
 */
public class UserValidation implements iValidator {
    
    private UserException atrException;
    
    // Constructor:
    
    public UserValidation()
    {
        atrException = new UserException();
    }

    @Override
    public void validate(iModel prmModel) throws UserException 
    {
        validateNames((UserModel) prmModel);
        validateSurnames((UserModel) prmModel);
        validateEmail((UserModel) prmModel);
        validatePassword((UserModel) prmModel);
        validateTelephone((UserModel) prmModel);
        atrException.throwException();
    }

    /**
     *  Metodo para validar si un campo esta nulo,
     *  si el campo esta nulo se agrega el mensaje de
     *  excepcion 
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @return 'true' si el campo esta nulo. 'false' si no
     */
    protected boolean isNull(Object prmField, UserExceptionEnum prmFieldType)
    {
        if(prmField == null)
        {
            atrException.addExceptionMessage(prmFieldType, "No debe estar nulo");
            return true;
        }
        return false;
    }
    
    /**
     *  Metodo para validar si un campo esta vacio,
     *  si el campo esta vacio se agrega el mensaje de
     *  excepcion 
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @return 'true' si el campo esta vacio. 'false' si no
     */
    protected boolean isEmpty(String prmField, UserExceptionEnum prmFieldType)
    {
        if(prmField.isEmpty())
        {
            atrException.addExceptionMessage(prmFieldType, "No debe estar vacio");
            return true;
        }
        return false;
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     */
    public void validateNames(UserModel prmUser)
    {
        if(isNull(prmUser.getNames(), UserExceptionEnum.NAMES)
        || isEmpty(prmUser.getNames(), UserExceptionEnum.NAMES)) return;
        
        if(prmUser.getNames().matches("[a-zA-Z ]*"))
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.NAMES, 
                "Debe contener solamente letras"
            );
        }
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     */
    public void validateSurnames(UserModel prmUser)
    {
        if(isNull(prmUser.getSurnames(), UserExceptionEnum.SURNAMES)
        || isEmpty(prmUser.getSurnames(), UserExceptionEnum.SURNAMES)) return;
        
        if(prmUser.getSurnames().matches("[a-zA-Z ]*"))
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.SURNAMES, 
                "Debe contener solamente letras"
            );
        }
    }
    
    /**
     *  @param prmUser Recibe la referencia del usuario
     */
    public void validateEmail(UserModel prmUser)
    {
        if(isNull(prmUser.getEmail(), UserExceptionEnum.EMAIL)) return;
        
        if(!prmUser.getEmail().contains("@unicauca.edu.co"))
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.EMAIL, 
                "No pertenece al dominio de la Universidad del Cauca"
            );
        }
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     */
    public void validatePassword(UserModel prmUser)
    {
        if(isNull(prmUser.getPassword(), UserExceptionEnum.PASSWORD)) return;
        
        if(prmUser.getPassword().length() < 6)
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.PASSWORD, 
                "Debe contener por lo menos seis caracteres"
            );
        }
        if(!prmUser.getPassword().matches(".*[0-9].*"))
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.PASSWORD, 
                "Debe contener por lo menos un digito"
            );
        }
        if(!prmUser.getPassword().matches(".*[!@#$%^&*(){}+=-_,./?].*"))
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.PASSWORD, 
                "Debe contener por lo menos un caracter especial"
            );
        }
        if(!prmUser.getPassword().matches(".*[A-Z].*"))
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.PASSWORD, 
                "Debe contener por lo menos una letra en mayuscula"
            );
        }
    }
    
    /**
     *  @param prmUser Recibe la referencia del usuario
     */
    public void validateTelephone(UserModel prmUser)
    {
        if(isNull(prmUser.getTelephone(), UserExceptionEnum.TELEPHONE)) return;
        
        if(prmUser.getTelephone() <= 0)
        {
            atrException.addExceptionMessage(
                UserExceptionEnum.TELEPHONE, 
                "Debe ser positivo"
            );
        }
    }
}
