package business.controllers.implement;

import access.models.implement.UserModel;
import business.controllers.interfaces.iSessionController;
import business.controllers.interfaces.iUserController;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;
import support.security.Encryptor;
import support.security.iEncryptor;

/**
 * Clase controladora para la sesion de usuarios
 * 
 * @author javiersolanop777
 */
@Controller
public class SessionController implements iSessionController {
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    public SessionController()
    {}

    @Override
    public UserModel login(String prmEmail, String prmPassword)
    {
        if((prmEmail == null) || (prmPassword == null)) return null;
        
        UserModel objUserModel = atrUserController.getUser(prmEmail);
        
        if(objUserModel == null) return null;
        
        iEncryptor objEncryptor = new Encryptor();
        
        if(objEncryptor.checkHash(prmPassword, objUserModel.getPassword()))
            return objUserModel;
        return null;
    }

    @Override
    public void logout() 
    {
        System.exit(0);
    } 
}
