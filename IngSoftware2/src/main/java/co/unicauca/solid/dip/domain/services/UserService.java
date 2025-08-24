package co.unicauca.solid.dip.domain.services;

//import Utilities.Encryptor;
//import Utilities.iEncryptor;
//import controller.validations.UserValidation;
//import exceptions.UserException;
import co.unicauca.solid.dip.domain.access.FactoryManager;
import co.unicauca.solid.dip.domain.controller.validations.UserValidation;
import co.unicauca.solid.dip.domain.exception.UserException;
import co.unicauca.solid.dip.domain.interfaces.iEncryptor;
import co.unicauca.solid.dip.domain.interfaces.iUserRepository;
import co.unicauca.solid.dip.domain.interfaces.iUserService;
import co.unicauca.solid.dip.domain.models.UserModel;
//import co.unicauca.solid.dip.domain.service.Encryptor;
//import co.unicauca.solid.dip.domain.service.iEncryptor;
import java.util.logging.Level;
import java.util.logging.Logger;
//import repositories.factory.FactoryManager;
//import repositories.iUserRepository;

/**
 * Clase controladora para los usuarios
 * 
 * @author javiersolanop777
 */
public class UserService implements iUserService {
   
    private iUserRepository atrUserRepository;
    
    // Constructors:

    public UserService() 
    {
        try {
            FactoryManager.setValue(this, this.getClass().getDeclaredField("atrUserRepository"));
        } catch(Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
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
