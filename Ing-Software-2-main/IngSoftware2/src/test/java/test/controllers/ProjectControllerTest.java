package test.controllers;

import access.models.DefaultInformationEnum;
import access.models.StatusEnum;
import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import access.repositories.implement.ConnectorSQLite;
import access.repositories.implement.ProjectFactory;
import access.repositories.interfaces.iProjectRepository;
import business.controllers.implement.ProjectController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import support.operation.builders.ProffesionalProjectBuilder;
import support.operation.builders.ProjectDirector;
import support.operation.model_exceptions.ModelException;

/**
 * Test unitario para iProjectController
 * 
 * @author javiersolanop777
 */
public class ProjectControllerTest {
    
    private final iProjectRepository atrProjectRepository;
    private final ProjectController atrProjectController;
    
    // Constructors:
    
    public ProjectControllerTest()
    {
        ConnectorSQLite.atrUrl = "jdbc:sqlite:IngSoftware2.db";
        atrProjectRepository = (ProjectFactory) new ProjectFactory().getRespositoryFactory();
        ProjectController.atrStorePath = "resources\\store";
        atrProjectController = new ProjectController();
        atrProjectController.setFactory(atrProjectRepository);
    }
    
    @Test
    public void save1() throws ModelException 
    {
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ProffesionalProjectBuilder(
            "PROYECTO DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            null
        ));
        objProjectDirector.buildProjectModel();
        
        File objFormatTest = new File(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\"  
            + ProjectController.atrStorePath
            + "\\test1.pdf"
        );
        
        // Datos correctos
        assertNotEquals(atrProjectController.save(
            objProjectDirector.getProjectModel(), objFormatTest
        ), null);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        this.delete(objProjectModelPK);
    }
    
    @Test
    public void save2() throws ModelException, IOException 
    {
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ProffesionalProjectBuilder(
            "PROYECTO DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            null
        ));
        objProjectDirector.buildProjectModel();
        
        File objFormatTest1 = new File(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\"  
            + ProjectController.atrStorePath
            + "\\test1.pdf"
        );
        
        File objFormatTest2 = new File(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\"  
            + ProjectController.atrStorePath
            + "\\test2.pdf"
        );
        
        byte[][] mtrFiles = new byte[2][];
        mtrFiles[0] = Files.readAllBytes(objFormatTest1.toPath());
        mtrFiles[1] = Files.readAllBytes(objFormatTest2.toPath());
        
        ProjectModel objProject = objProjectDirector.getProjectModel();
        
        objProject.setFileName(objFormatTest2.getName());
        
        // Datos correctos
        assertNotEquals(atrProjectController.save(
            objProject, mtrFiles
        ), null);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        this.delete(objProjectModelPK);
    }
    
    @Test
    public void evaluateAccept() throws ModelException
    {
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ProffesionalProjectBuilder(
            "PROYECTO DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            null
        ));
        objProjectDirector.buildProjectModel();
        
        File objFormatTest = new File(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\"  
            + ProjectController.atrStorePath
            + "\\test1.pdf"
        );
        
        atrProjectController.save(objProjectDirector.getProjectModel(), objFormatTest);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        // Datos correctos
        assertNotEquals(atrProjectController.evaluateProject(
            objProjectModelPK, true, DefaultInformationEnum.WITHOUT.getName()
        ), null);
        
        this.delete(objProjectModelPK);
    }
    
    @Test
    public void evaluateRejected() throws ModelException
    {
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ProffesionalProjectBuilder(
            "PROYECTO DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            null
        ));
        objProjectDirector.buildProjectModel();
        
        File objFormatTest = new File(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\"  
            + ProjectController.atrStorePath
            + "\\test1.pdf"
        );
        
        atrProjectController.save(objProjectDirector.getProjectModel(), objFormatTest);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        // Datos correctos
        assertNotEquals(atrProjectController.evaluateProject(
            objProjectModelPK, false, "No cumple con los requisitos minimos."
        ), null);
        
        this.delete(objProjectModelPK);
    }
    
    @Test
    public void updateFormat() throws ModelException
    {
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ProffesionalProjectBuilder(
            "PROYECTO DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            null
        ));
        objProjectDirector.buildProjectModel();
        
        File objFormatTest = new File(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\"  
            + ProjectController.atrStorePath
            + "\\test1.pdf"
        );
        
        ProjectModel objProject = objProjectDirector.getProjectModel();
        objProject.setStatus(StatusEnum.REJECTED);
        atrProjectController.save(objProject, objFormatTest);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        // Datos correctos
        assertEquals(atrProjectController.updateFormat(
            objProjectModelPK, objFormatTest
        ), true);
        
        this.delete(objProjectModelPK);    
    }

    public void delete(ProjectModelPK prmId)
    {  
        ProjectModel objProjectModel = (ProjectModel) atrProjectRepository.getById(prmId);

        if(objProjectModel != null)
        {
            System.out.println(objProjectModel);
            assertEquals(atrProjectController.delete(prmId), true);
        }
    }
}
