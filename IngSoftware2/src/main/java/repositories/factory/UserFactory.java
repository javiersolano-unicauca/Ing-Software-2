package repositories.factory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.CareerEnum;
import models.RoleEnum;
import models.UserModel;
import repositories.iModel;
import repositories.iUserRepository;

/**
 * Clase para la fabrica de de iUserRepository
 * @see iUserRepository
 * 
 * @author javiersolanop777
 */
public class UserFactory extends Factory<String> implements iUserRepository {
    
    // Constructors:
    
    public UserFactory()
    {
        this.atrEntityName = "users";
        this.atrId = "email";
    }

    @Override
    protected void entityInit() 
    {
        String varSql = "CREATE TABLE IF NOT EXISTS " + atrEntityName + " ("
        + "	email TEXT PRIMARY KEY,"
        + "	names TEXT NOT NULL,"
        + "	surnames TEXT NOT NULL,"
        + "	password TEXT NOT NULL,"
        + "	telephone TEXT,"
        + "	career TEXT NOT NULL CHECK(career IN ("       
        + "           'Ingenieria de Sistemas',"
        + "           'Ingenieria Electronica y Telecomunicaciones',"
        + "           'Automatica Industrial',"
        + "           'Tecnologia en Telematica'"
        + "     )),"
        + "     role TEXT NOT NULL CHECK(role IN ('estudiante', 'profesor'))"
        + ");";

        try 
        {
            atrConnection.connect();
            Statement objStmt = atrConnection.createStatement();
            objStmt.execute(varSql);
            atrConnection.disconnect();
        } 
        catch(SQLException ex) 
        {
            atrConnection.disconnect();
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public iUserRepository getRespositoryFactory()
    {
        UserFactory objUserFactory = new UserFactory();
        objUserFactory.entityInit();
        return objUserFactory;
    }

    @Override
    public boolean save(iModel prmModel) 
    {
        atrConnection.connect();
        
        try
        {
            if(prmModel == null || !prmModel.validateFields()) return false;
            
            String varSql = "INSERT OR REPLACE INTO " + this.atrEntityName + "("
                            + "names,"
                            + "surnames,"
                            + "email,"
                            + "password,"
                            + "telephone,"
                            + "career,"
                            + "role"
                            + ")";
                    
            varSql += " VALUES ("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    + ")";
            
            Map<String, Object> listFields = prmModel.getFields();
            
            PreparedStatement objStmt = atrConnection.getStatement(varSql);
            objStmt.setString(1, (String) listFields.get("names"));
            objStmt.setString(2, (String) listFields.get("surnames"));
            objStmt.setString(3, (String) listFields.get("email"));
            objStmt.setString(4, (String) listFields.get("password"));
            objStmt.setString(5, ((Long) listFields.get("telephone")).toString());
            objStmt.setString(6, ((CareerEnum) listFields.get("career")).getName());
            objStmt.setString(7, ((RoleEnum) listFields.get("role")).getName());
            objStmt.executeUpdate();
            
            return true;       
        } 
        catch(SQLException ex) 
        {
            atrConnection.disconnect();
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        atrConnection.disconnect();
        return false;
    }

    @Override
    public List<iModel> list() 
    {
        atrConnection.connect();
        
        List<iModel> listUsers = new ArrayList<>();
        
        try
        {
            String varSql = "SELECT * FROM " + this.atrEntityName;
            
            Statement objStmt = atrConnection.createStatement();
            ResultSet objUsersSet = objStmt.executeQuery(varSql);
            
            while(objUsersSet.next())
            {
                listUsers.add(new UserModel(
                    objUsersSet.getString("names"),
                    objUsersSet.getString("surnames"),
                    objUsersSet.getString("email"),
                    objUsersSet.getString("password"),
                    objUsersSet.getLong("telephone"),
                    CareerEnum.getCareer(objUsersSet.getString("career")),
                    RoleEnum.getRole(objUsersSet.getString("role"))
                ));
            }
        }catch(SQLException ex) 
        {
            atrConnection.disconnect();
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        atrConnection.disconnect();
        return listUsers;
    }

    @Override
    public iModel getById(String prmId) 
    {
        atrConnection.connect();
        
        try
        {
            String varSql = "SELECT * FROM " + this.atrEntityName 
                            + " WHERE "
                            + this.atrId
                            + " = ?";
            
            PreparedStatement objStmt = atrConnection.getStatement(varSql);
            objStmt.setString(1, prmId);
            
            ResultSet objUsersSet = objStmt.executeQuery();
            
            if(!objUsersSet.next()) return null;
            
            return new UserModel(
                objUsersSet.getString("names"),
                objUsersSet.getString("surnames"),
                objUsersSet.getString("email"),
                objUsersSet.getString("password"),
                objUsersSet.getLong("telephone"),
                CareerEnum.getCareer(objUsersSet.getString("career")),
                RoleEnum.getRole(objUsersSet.getString("role"))
            );
        }
        catch(SQLException ex) 
        {
            atrConnection.disconnect();
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        atrConnection.disconnect();
        return null;
    }
}
