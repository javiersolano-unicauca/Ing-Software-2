package controller.validations;

import exceptions.ChainOfCharacterException;
import exceptions.UserException;
import exceptions.UserExceptionEnum;
import models.UserModel;

/**
 *  Clase para las validaciones de los usuarios
 *  @see UserModel
 * 
 * @author javim
 */
public class UserValidation {
    
    /**
     * Almacena la instancia para validacion de cadenas
     */
    private static iChainOfCharacter atrChainOfCharacter = new ChainOfCharacter();
    
    /**
     *  Metodo para validar todos los campos
     * 
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Cuando los campos no son validos en su totalidad
     */
    public static void validate(UserModel prmUser) throws UserException
    {
        validateNames(prmUser);
        validateSurnames(prmUser);
        validateEmail(prmUser);
        validatePassword(prmUser);
        validateTelephone(prmUser);
    }

    /**
     *  Metodo para validar si un campo esta nulo
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws UserException Si el campo esta vacio
     */
    protected static void validateNull(Object prmField, UserExceptionEnum prmFieldType) throws UserException
    {
        UserException.throwException(
            prmField == null, 
            prmFieldType,
            new Exception("No debe estar nulo")
        );
        
    }
    
    /**
     *  Metodo para validar si un campo esta vacio
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @throws UserException Si el campo esta vacio
     */
    protected static void validateEmpty(String prmField, UserExceptionEnum prmFieldType) throws UserException
    {
        UserException.throwException(
            prmField.isEmpty(), 
            prmFieldType,
            new Exception("No debe estar vacio")
        );
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateNames(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getNames(), UserExceptionEnum.NAMES);
        validateEmpty(prmUser.getNames(), UserExceptionEnum.NAMES);

        try{
            atrChainOfCharacter.containsLettersWithException(prmUser.getNames());
        }
        catch(ChainOfCharacterException e){
            UserException.throwException(UserExceptionEnum.NAMES, e);
        } 
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateSurnames(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getSurnames(), UserExceptionEnum.SURNAMES);
        validateEmpty(prmUser.getSurnames(), UserExceptionEnum.SURNAMES);

        try{
            atrChainOfCharacter.containsLettersWithException(prmUser.getSurnames());
        }
        catch(ChainOfCharacterException e){
            UserException.throwException(UserExceptionEnum.SURNAMES, e);
        } 
    }
    
    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateEmail(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getEmail(), UserExceptionEnum.EMAIL);

        UserException.throwException(    
            !prmUser.getEmail().contains("@unicauca.edu.co"),
            UserExceptionEnum.EMAIL,
            new Exception("No pertenece al dominio de la Universidad del Cauca")
        );
    }

    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validatePassword(UserModel prmUser) throws UserException
    {
        validateNull(prmUser.getPassword(), UserExceptionEnum.PASSWORD);

        UserException.throwException(
            (prmUser.getPassword().length() < 6),
            UserExceptionEnum.PASSWORD,
            new Exception("Debe contener por lo menos seis caracteres")  
        ); 
        
        UserException.throwException(
            !prmUser.getPassword().matches(".*[0-9].*"),
            UserExceptionEnum.PASSWORD,
            new Exception("Debe contener por lo menos un digito")  
        ); 
        
        UserException.throwException(
            !prmUser.getPassword().matches(".*[!@#$%^&*(){}+=-_,./?].*"),
            UserExceptionEnum.PASSWORD,
            new Exception("Debe contener por lo menos un caracter especial")  
        ); 
        
        UserException.throwException(
            !prmUser.getPassword().matches(".*[A-Z].*"),
            UserExceptionEnum.PASSWORD,
            new Exception("Debe contener por lo menos una letra en mayuscula")  
        );
    }
    
    /**
     *  @param prmUser Recibe la referencia del usuario
     * 
     *  @throws UserException Si el campo no es valido
     */
    public static void validateTelephone(UserModel prmUser) throws UserException 
    {
        validateNull(prmUser.getTelephone(), UserExceptionEnum.TELEPHONE);

        UserException.throwException(
            (prmUser.getTelephone() <= 0), 
                UserExceptionEnum.TELEPHONE,
            new Exception("Debe ser positivo")
        );
    }
}
