package controller;

import Utilities.Encryptor;
import Utilities.iEncryptor;
import controller.validations.UserValidation;
import exceptions.UserException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UserModel;
import repositories.factory.FactoryManager;
import repositories.iUserRepository;

/**
 * Clase controladora para los usuarios
 * 
 * @author javiersolanop777
 */
public class UserController implements iUserController {
   
    private iUserRepository atrUserRepository;
    
    // Constructors:

    public UserController() 
    {
        try {
            FactoryManager.setValue(this, this.getClass().getDeclaredField("atrUserRepository"));
        } catch(Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public boolean save(UserModel prmUser) throws UserException
    {
        if(prmUser == null) return false;
        
        UserValidation.validate(prmUser);
        
        UserModel objUser = (UserModel) atrUserRepository.getById(prmUser.getEmail());
        
        if(objUser != null) return false;
        
        iEncryptor objEncryptor = new Encryptor();
        
        prmUser.setPassword(objEncryptor.encrypt(prmUser.getPassword()));
        
        return atrUserRepository.save(prmUser);
    }

    @Override
    public UserModel getUser(String prmEmail) 
    {
        if(prmEmail.isEmpty()) return null;
        
        return (UserModel) atrUserRepository.getById(prmEmail);
    } 
}
