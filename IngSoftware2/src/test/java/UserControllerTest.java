import controller.UserController;
import controller.iUserController;
import exceptions.UserException;
import java.util.List;
import models.CareerEnum;
import models.RoleEnum;
import models.UserModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test unitario para iUserController
 * 
 * @author javiersolanop777
 */
public class UserControllerTest {
    
    private iUserController atrUserController;
    
    // Constructors:

    public UserControllerTest() 
    {
        atrUserController = new UserController();
    }
    
    @Test
    public void saveCorrect()
    {
        // Datos correctos
        try
        {
            assertEquals(atrUserController.save(new UserModel(
                "javier",
                "solano",
                "micuenta@unicauca.edu.co",
                "hol4$OL",
                Long.valueOf("32124567890"),
                CareerEnum.SYSTEM_ENGINEERING,
                RoleEnum.ESTUDENT
            )), true);
        }
        catch(UserException ex)
        {
            System.out.println(ex.getMessage());
        }
        
    }
    
    @Test
    public void saveIncorrect() throws UserException
    {
        // Datos correctos
        assertEquals(atrUserController.save(new UserModel(
            "javier",
            "solano",
            "javiersolano@gmail.edu.co",
            "hol4$OL",
            Long.valueOf("32124567890"),
            CareerEnum.SYSTEM_ENGINEERING,
            RoleEnum.ESTUDENT
        )), true);
    }
    
    @Test
    public void getUserCorrect()
    {
        UserModel objUserModel = atrUserController.getUser("micuenta@unicauca.edu.co");
        
        assertEquals(objUserModel != null, true);
        
        System.out.println(
            "User("
            + objUserModel.getNames() + ","
            + objUserModel.getSurnames() + ","
            + objUserModel.getEmail() + ","
            + objUserModel.getPassword() + ","
            + objUserModel.getTelephone() + ","
            + objUserModel.getCareer().getName() + ","
            + objUserModel.getRole().getName()
            + ")"
        );
    }
    
    @Test
    public void getUserIncorrect()
    {
        UserModel objUserModel = atrUserController.getUser("nohay@unicauca.edu.co");
        
        assertEquals(objUserModel == null, true);
    }
}
