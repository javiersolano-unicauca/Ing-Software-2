package access.repositories.implement;

import access.models.CareerEnum;
import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import access.repositories.interfaces.iUserRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import support.operation.dependency_injection.RepositoryFactory;

/**
 * Clase para la fabrica de de iUserRepository
 * @see iUserRepository
 * 
 * @author javiersolanop777
 */
@RepositoryFactory
public class UserFactory extends Factory<String> implements iUserRepository {
    
    // Constructors:
    
    public UserFactory()
    {
        connectorInit();
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
        + "     role TEXT NOT NULL CHECK(role IN ("
        + "           'estudiante',"
        + "           'profesor',"
        + "           'coordinador'"
        + "     ))"
        + ");";
        
        atrConnection.connect();

        try(Statement objStmt = atrConnection.createStatement())
        {
            objStmt.execute(varSql);
        } 
        catch(SQLException ex) 
        {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
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
        atrConnection.connect();
        
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {          
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
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return false;
    }

    @Override
    public List<iModel> list() 
    {
        atrConnection.connect();
        
        List<iModel> listUsers = new ArrayList<>();
        String varSql = "SELECT * FROM " + this.atrEntityName;
        
        try(
            Statement objStmt = atrConnection.createStatement();
            ResultSet objUsersSet = objStmt.executeQuery(varSql); 
        )
        { 
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
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return (!listUsers.isEmpty()) ? listUsers : null;
    }

    @Override
    public iModel getById(String prmId) 
    {
        if(prmId == null) return null;
        atrConnection.connect();
        
        String varSql = "SELECT * FROM " + this.atrEntityName 
                + " WHERE "
                + this.atrId
                + " = ?";
        
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            objStmt.setString(1, prmId);
            
            try(ResultSet objUsersSet = objStmt.executeQuery())
            {
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
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return null;
    }

    @Override
    public boolean remove(String prmId) 
    {
        if(prmId == null) return false;
        atrConnection.connect();
        
        String varSql = "DELETE FROM " + this.atrEntityName 
                            + " WHERE "
                            + this.atrId
                            + " = ?";
        
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            objStmt.setString(1, prmId);
            
            return (objStmt.executeUpdate() > 0);
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return false;
    }

    @Override
    public UserModel getByIdAndRole(String prmId, RoleEnum prmRole) 
    {
        if(prmId == null) return null;
        atrConnection.connect();
        
        String varSql = "SELECT * FROM " + this.atrEntityName 
                + " WHERE "
                + this.atrId
                + " = ?"
                + " AND "
                + "role"
                + " = ?";
        
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            objStmt.setString(1, prmId);
            objStmt.setString(2, prmRole.getName());
            
            try(ResultSet objUsersSet = objStmt.executeQuery())
            {
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
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return null;
    }
}
