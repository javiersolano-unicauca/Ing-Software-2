package test.repositories;

import access.models.CareerEnum;
import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.repositories.implement.ConnectorSQLite;
import access.repositories.implement.UserFactory;
import access.repositories.interfaces.iUserRepository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Test unitario para iUserRepository
 * 
 * @author javiersolanop777
 */
public class UserRepositoryTest {
    
    private final iUserRepository atrUserRepository;
    
    // Constructors:
    
    public UserRepositoryTest()
    {
        ConnectorSQLite.atrUrl = "jdbc:sqlite:IngSoftware2.db";
        atrUserRepository = (iUserRepository) new UserFactory().getRespositoryFactory();
    }
    
    @Test
    public void saveCorrect()
    {
        // Datos correctos
        assertEquals(atrUserRepository.save(new UserModel(
            "javier",
            "solano",
            "usuariodeprueba@unicauca.edu.co",
            BCrypt.hashpw("123", BCrypt.gensalt()),
            Long.valueOf("32124567890"),
            CareerEnum.SYSTEM_ENGINEERING,
            RoleEnum.STUDENT
        )), true);
        
        this.remove("usuariodeprueba@unicauca.edu.co");
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
