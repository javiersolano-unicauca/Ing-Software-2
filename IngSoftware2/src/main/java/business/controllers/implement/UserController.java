package business.controllers.implement;

import access.models.implement.UserModel;
import access.repositories.interfaces.iUserRepository;
import business.controllers.interfaces.iUserController;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.FactoryAutowired;
import support.operation.model_exceptions.ModelException;
import support.security.Encryptor;
import support.security.iEncryptor;
import support.validations.UserValidation;
import support.validations.iValidator;

/**
 * Clase controladora para los usuarios
 * 
 * @author javiersolanop777
 */
@Controller
public class UserController implements iUserController {
   
    @FactoryAutowired
    private iUserRepository atrUserRepository;
    
    // Constructors:

    public UserController() 
    {}

    @Override
    public boolean save(UserModel prmUser) throws ModelException
    {
        if(prmUser == null) return false;
        
        iValidator objValidator = new UserValidation();
        objValidator.validate(prmUser);
        
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
