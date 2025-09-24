package test.controllers;

import access.models.CareerEnum;
import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.repositories.implement.ConnectorSQLite;
import access.repositories.implement.UserFactory;
import access.repositories.interfaces.iUserRepository;
import business.controllers.implement.UserController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import support.operation.model_exceptions.ModelException;
import support.operation.model_exceptions.UserException;

/**
 * Test unitario para iUserController
 * 
 * @author javiersolanop777
 */
public class UserControllerTest {
    
    private final iUserRepository atrUserRepository;
    private final UserController atrUserController;
    
    // Constructors:

    public UserControllerTest() 
    {
        ConnectorSQLite.atrUrl = "jdbc:sqlite:IngSoftware2.db";
        atrUserRepository = (iUserRepository) new UserFactory().getRespositoryFactory();
        atrUserController = new UserController();
        atrUserController.setFactory(atrUserRepository);
    }
    
    @Test
    public void saveCorrect()
    {
        // Datos correctos
        try
        {
            assertNotEquals(atrUserController.save(new UserModel(
                "javier",
                "solano",
                "usuariodeprueba@unicauca.edu.co",
                "hol4$OL",
                Long.valueOf("32124567890"),
                    CareerEnum.SYSTEM_ENGINEERING,
                    RoleEnum.STUDENT
            )), null);
            
            this.remove("usuariodeprueba@unicauca.edu.co");
        }
        catch(ModelException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    @Test
    public void saveIncorrect() throws UserException
    {
        // Datos incorrectos
        try
        {
            assertEquals(atrUserController.save(new UserModel(
                "123",
                "123",
                "usuariodeprueba",
                "123",
                Long.valueOf("32124567890"),
                CareerEnum.SYSTEM_ENGINEERING,
                RoleEnum.STUDENT
            )), null);
        }
        catch(ModelException ex)
        {
            System.out.println("\n" + ex.getMessage() + "\n");
        }
    }
    
    @Test
    public void getUserCorrect()
    {
        UserModel objUserModel = atrUserController.getUser("micuenta@unicauca.edu.co");
        
        assertEquals(objUserModel != null, true);
        
        System.out.println(objUserModel);
    }
    
    @Test
    public void getUserIncorrect()
    {
        UserModel objUserModel = atrUserController.getUser("nohay@unicauca.edu.co");
        
        assertEquals(objUserModel, null);
    }
    
    public void remove(String prmEmail)
    {
        UserModel objUser = (UserModel) this.atrUserRepository.getById(prmEmail);
        
        if(objUser != null)
        {
            System.out.println(objUser);
            this.atrUserRepository.remove(prmEmail);
        }
    }
}
