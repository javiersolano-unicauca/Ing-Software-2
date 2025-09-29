package access.repositories.implement;

import access.models.ModalityEnum;
import access.models.StatusEnum;
import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import access.models.interfaces.iModel;
import access.repositories.interfaces.iProjectRepository;
import access.repositories.interfaces.iRepository;
import static business.controllers.implement.ProjectController.atrStorePath;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugin.property_mapping.annotations.Property;
import plugin.property_mapping.annotations.PropertyMapping;
import support.operation.dependency_injection.RepositoryFactory;

/**
 * Clase para la fabrica de de iProjectRepository
 * @see iProjectRepository
 * 
 * @author javiersolanop777
 */
@RepositoryFactory
@PropertyMapping
public class ProjectFactory extends Factory<ProjectModelPK> implements iProjectRepository {
    
    @Property(property = "store.path")
    public static String atrStorePath;
    
    // Constructors:
    
    public ProjectFactory()
    {
        connectorInit();
        this.atrEntityName = "projects";
        this.atrId = new ProjectModelPK();
    }

    @Override
    protected void entityInit() 
    {
        String varSql = "CREATE TABLE IF NOT EXISTS " + atrEntityName + " ("
        + "	title TEXT NOT NULL,"
        + "	modality TEXT NOT NULL CHECK(modality IN ("
        + "           'investigacion',"
        + "           'practica profesional'"
        + "     )),"
        + "	date TEXT NOT NULL,"
        + "	director TEXT NOT NULL,"
        + "	codirector TEXT NOT NULL,"
        + "	student1 TEXT NOT NULL,"
        + "	student2 TEXT NOT NULL,"
        + "	general_objective TEXT NOT NULL,"
        + "	specific_objectives TEXT NOT NULL,"
        + "	status TEXT NOT NULL CHECK(status IN ("
        + "           'primera evaluacion formato A',"
        + "           'segunda evaluacion formato A',"
        + "           'tercera evaluacion formato A',"
        + "           'aceptado formato A',"
        + "           'rechazado formato A'"
        + "     )),"
        + "	number_of_attempts INTEGER NOT NULL,"
        + "     observations TEXT NOT NULL,"
        + "	file_name TEXT NOT NULL,"
        + "     PRIMARY KEY (date, director, student1),"
        + "     FOREIGN KEY (director) REFERENCES users(email) ON DELETE CASCADE,"
        + "     FOREIGN KEY (codirector) REFERENCES users(email) ON DELETE CASCADE,"
        + "     FOREIGN KEY (student1) REFERENCES users(email) ON DELETE CASCADE,"
        + "     FOREIGN KEY (student2) REFERENCES users(email) ON DELETE CASCADE"
        + ");";
        
        atrConnection.connect();

        try(Statement objStmt = atrConnection.createStatement())
        {
            objStmt.execute(varSql);
        } 
        catch(SQLException ex) 
        {
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
    }

    @Override
    public iRepository<ProjectModelPK> getRespositoryFactory() 
    {
        ProjectFactory objProjectFactory = new ProjectFactory();
        objProjectFactory.entityInit();
        return objProjectFactory;
    }

    @Override
    public boolean save(iModel prmModel) 
    {
        if((prmModel == null) || !prmModel.validateFields()) return false;
        
        String varSql = "INSERT OR REPLACE INTO " + this.atrEntityName + "("
                            + "title,"
                            + "modality,"
                            + "date,"
                            + "director,"
                            + "codirector,"
                            + "student1,"
                            + "student2,"
                            + "general_objective,"
                            + "specific_objectives,"
                            + "status,"
                            + "number_of_attempts,"
                            + "observations,"
                            + "file_name"
                            + ")";
                    
            varSql += " VALUES ("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
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
            objStmt.setString(1, (String) listFields.get("title"));
            objStmt.setString(2, ((ModalityEnum) listFields.get("modality")).getName());
            objStmt.setString(3, (String) listFields.get("date"));
            objStmt.setString(4, (String) listFields.get("director"));
            objStmt.setString(5, (String) listFields.get("codirector"));
            objStmt.setString(6, (String) listFields.get("student1"));
            objStmt.setString(7, (String) listFields.get("student2"));
            objStmt.setString(8, (String) listFields.get("general_objective"));
            objStmt.setString(9, (String) listFields.get("specific_objectives"));
            objStmt.setString(10, ((StatusEnum) listFields.get("status")).getName());
            objStmt.setInt(11, (Integer) listFields.get("number_of_attempts"));
            objStmt.setString(12, (String) listFields.get("observations"));
            objStmt.setString(13, (String) listFields.get("file_name"));
            objStmt.executeUpdate();
            
            return true;       
        } 
        catch(SQLException ex) 
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return false;
    }

    @Override
    public List<iModel> list() 
    {
        atrConnection.connect();
        
        List<iModel> listProjects = new ArrayList<>();
        String varSql = "SELECT * FROM " + this.atrEntityName;
        
        try(
            Statement objStmt = atrConnection.createStatement();
            ResultSet objProjectsSet = objStmt.executeQuery(varSql); 
        )
        { 
            while(objProjectsSet.next())
            {
                listProjects.add(new ProjectModel(
                    objProjectsSet.getString("title"),
                    LocalDate.parse(objProjectsSet.getString("date"),DateTimeFormatter.ISO_DATE),
                    objProjectsSet.getString("director"),
                    objProjectsSet.getString("codirector"),
                    objProjectsSet.getString("student1"),
                    objProjectsSet.getString("student2"),
                    ModalityEnum.getModality(objProjectsSet.getString("modality")),
                    objProjectsSet.getString("general_objective"),
                    objProjectsSet.getString("specific_objectives"),
                    StatusEnum.getStatus(objProjectsSet.getString("status")),
                    objProjectsSet.getInt("number_of_attempts"),
                    objProjectsSet.getString("observations"),
                    this.setFilePath(objProjectsSet.getString("file_name"))
                ));
            }
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return (!listProjects.isEmpty()) ? listProjects : null;
    }

    @Override
    public iModel getById(ProjectModelPK prmId) 
    {
        if(prmId == null) return null;
        atrConnection.connect();
        
        String varSql = "SELECT * FROM " + this.atrEntityName 
                + " WHERE ";
        
        Object[] arrEntry = prmId.getFields().entrySet().toArray();
        int varLimit = arrEntry.length - 1;
        
        for(int i = 0; i < varLimit; i++)
            varSql += ((Map.Entry<String, Object>) arrEntry[i]).getKey() + " = ? AND ";
        varSql += ((Map.Entry<String, Object>) arrEntry[varLimit]).getKey() + " = ?";
 
        
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            for(int i = 0; i <= varLimit; i++)
                objStmt.setString((i + 1), (String) ((Map.Entry<String, Object>) arrEntry[i]).getValue());
            
            try(ResultSet objProjectsSet = objStmt.executeQuery())
            {
                if(!objProjectsSet.next()) return null;
            
                return new ProjectModel(
                    objProjectsSet.getString("title"),
                    LocalDate.parse(objProjectsSet.getString("date"),DateTimeFormatter.ISO_DATE),
                    objProjectsSet.getString("director"),
                    objProjectsSet.getString("codirector"),
                    objProjectsSet.getString("student1"),
                    objProjectsSet.getString("student2"),
                    ModalityEnum.getModality(objProjectsSet.getString("modality")),
                    objProjectsSet.getString("general_objective"),
                    objProjectsSet.getString("specific_objectives"),
                    StatusEnum.getStatus(objProjectsSet.getString("status")),
                    objProjectsSet.getInt("number_of_attempts"),
                    objProjectsSet.getString("observations"),
                    objProjectsSet.getString("file_name")
                );
            }
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return null;
    }

    @Override
    public boolean remove(ProjectModelPK prmId) 
    {
        if(prmId == null) return false;
        atrConnection.connect();
        
        String varSql = "DELETE FROM " + this.atrEntityName 
                            + " WHERE ";
        
        Object[] arrEntry = prmId.getFields().entrySet().toArray();
        int varLimit = arrEntry.length - 1;
        
        for(int i = 0; i < varLimit; i++)
            varSql += ((Map.Entry<String, Object>) arrEntry[i]).getKey() + " = ? AND ";
        varSql += ((Map.Entry<String, Object>) arrEntry[varLimit]).getKey() + " = ?";
        
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            for(int i = 0; i <= varLimit; i++)
                objStmt.setString((i + 1), (String) ((Map.Entry<String, Object>) arrEntry[i]).getValue());
            
            return (objStmt.executeUpdate() > 0);
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return false;
    }  
    
    @Override
    public ProjectModel getByStudent1(String prmStudent) 
    {
       if(prmStudent == null) return null;
        atrConnection.connect();
        
        String varSql = "SELECT * FROM " + this.atrEntityName 
                + " WHERE "
                + "student1 = ? ";
 
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            objStmt.setString(1, prmStudent);
            
            try(ResultSet objProjectsSet = objStmt.executeQuery())
            {
                if(!objProjectsSet.next()) return null;
            
                return new ProjectModel(
                    objProjectsSet.getString("title"),
                    LocalDate.parse(objProjectsSet.getString("date"),DateTimeFormatter.ISO_DATE),
                    objProjectsSet.getString("director"),
                    objProjectsSet.getString("codirector"),
                    objProjectsSet.getString("student1"),
                    objProjectsSet.getString("student2"),
                    ModalityEnum.getModality(objProjectsSet.getString("modality")),
                    objProjectsSet.getString("general_objective"),
                    objProjectsSet.getString("specific_objectives"),
                    StatusEnum.getStatus(objProjectsSet.getString("status")),
                    objProjectsSet.getInt("number_of_attempts"),
                    objProjectsSet.getString("observations"),
                    objProjectsSet.getString("file_name")
                );
            }
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return null;
    }

    @Override
    public ProjectModel getByStudent1OrStudent2(String prmStudent) 
    {
        if(prmStudent == null) return null;
        atrConnection.connect();
        
        String varSql = "SELECT * FROM " + this.atrEntityName 
                + " WHERE "
                + "student1 = ? "
                + "OR "
                + "student2 = ?";
 
        try(PreparedStatement objStmt = atrConnection.getStatement(varSql))
        {
            objStmt.setString(1, prmStudent);
            objStmt.setString(2, prmStudent);
            
            try(ResultSet objProjectsSet = objStmt.executeQuery())
            {
                if(!objProjectsSet.next()) return null;
            
                return new ProjectModel(
                    objProjectsSet.getString("title"),
                    LocalDate.parse(objProjectsSet.getString("date"),DateTimeFormatter.ISO_DATE),
                    objProjectsSet.getString("director"),
                    objProjectsSet.getString("codirector"),
                    objProjectsSet.getString("student1"),
                    objProjectsSet.getString("student2"),
                    ModalityEnum.getModality(objProjectsSet.getString("modality")),
                    objProjectsSet.getString("general_objective"),
                    objProjectsSet.getString("specific_objectives"),
                    StatusEnum.getStatus(objProjectsSet.getString("status")),
                    objProjectsSet.getInt("number_of_attempts"),
                    objProjectsSet.getString("observations"),
                    objProjectsSet.getString("file_name")
                );
            }
        }
        catch(SQLException ex) 
        {
            Logger.getLogger(ProjectFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { atrConnection.disconnect(); }
        
        return null;
    }
    
    private String setFilePath(String prmFileName)
    {
        atrStorePath = atrStorePath.replace("/","\\");
        
        return System.getProperty("user.dir")
                + "\\src\\main\\java\\" 
                + atrStorePath
                + "\\"
                + prmFileName
                + ".pdf";
    }
}
