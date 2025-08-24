import co.unicauca.solid.dip.domain.exception.UserException;
import co.unicauca.solid.dip.domain.interfaces.iUserService;
import co.unicauca.solid.dip.domain.models.RoleEnum;
import co.unicauca.solid.dip.domain.models.CareerEnum;
import co.unicauca.solid.dip.domain.models.UserModel;
import co.unicauca.solid.dip.domain.services.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test unitario para iUserController
 * 
 * @author javiersolanop777
 */
public class UserServiceTest {
    
    private iUserService  atrUserService;
    
    // Constructors:

    public UserServiceTest() 
    {
        atrUserService = new UserService();
    }
    
    @Test
    public void saveCorrect()
    {
        // Datos correctos
        try
        {
            assertEquals(atrUserService.save(new UserModel(
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
        // Datos incorrectos
        try
        {
            assertEquals(atrUserService.save(new UserModel(
                "javier",
                "solano",
                "javiersolano@gmail.edu.co",
                "hol4$OL",
                Long.valueOf("32124567890"),
                CareerEnum.SYSTEM_ENGINEERING,
                RoleEnum.ESTUDENT
            )), false);
        }
        catch(UserException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    @Test
    public void getUserCorrect()
    {
        UserModel objUserModel = atrUserService.getUser("micuenta@unicauca.edu.co");
        
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
        UserModel objUserModel = atrUserService.getUser("nohay@unicauca.edu.co");
        
        assertEquals(objUserModel == null, true);
    }
}
