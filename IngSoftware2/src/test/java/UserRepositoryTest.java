import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CareerEnum;
import models.RoleEnum;
import models.UserModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import repositories.factory.FactoryManager;
import repositories.iModel;
import repositories.iUserRepository;

/**
 * Test unitario para iUserRepository
 * 
 * @author javiersolanop777
 */
public class UserRepositoryTest {
    
    private iUserRepository atrUserRepository;
    
    // Constructors:
    
    public UserRepositoryTest()
    {
        try {
            FactoryManager.setValue(this, this.getClass().getDeclaredField("atrUserRepository"));
        } catch(Exception ex) {
            Logger.getLogger(UserRepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Test
    public void saveCorrect()
    {
        // Datos correctos
        assertEquals(atrUserRepository.save(new UserModel(
            "javier",
            "solano",
            "javiersolano@unicauca.edu.co",
            "123",
            Long.valueOf("32124567890"),
            CareerEnum.SYSTEM_ENGINEERING,
            RoleEnum.ESTUDENT
        )), true);
        
        List<iModel> listUsers = atrUserRepository.list();
        UserModel objUserModel;
        
        for(iModel objUser: listUsers)
        {
            objUserModel = (UserModel) objUser;
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
    }
    
    @Test
    public void saveIncorrect()
    {
        // Datos correctos
        assertEquals(atrUserRepository.save(new UserModel(
            "javier",
            null,
            "javiersolano@unicauca.edu.co",
            null,
            Long.valueOf("32124567890"),
            CareerEnum.SYSTEM_ENGINEERING,
            RoleEnum.ESTUDENT
        )), false);
    }
}
