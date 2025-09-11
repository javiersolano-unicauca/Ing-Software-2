import access.models.CareerEnum;
import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import access.repositories.implement.UserFactory;
import access.repositories.interfaces.iUserRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mindrot.jbcrypt.BCrypt;



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
        atrUserRepository = new UserFactory();
    }
    
    @Test
    public void saveCorrect()
    {
        // Datos correctos
        assertEquals(atrUserRepository.save(new UserModel(
            "javier",
            "solano",
            "cccc@unicauca.edu.co",
            BCrypt.hashpw("123", BCrypt.gensalt()),
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
    
//    @Test
//    public void saveIncorrect()
//    {
//        // Datos correctos
//        assertEquals(atrUserRepository.save(new UserModel(
//            "javier",
//            null,
//            "javiersolano@unicauca.edu.co",
//            null,
//            Long.valueOf("32124567890"),
//            CareerEnum.SYSTEM_ENGINEERING,
//            RoleEnum.ESTUDENT
//        )), false);
//    }
}
