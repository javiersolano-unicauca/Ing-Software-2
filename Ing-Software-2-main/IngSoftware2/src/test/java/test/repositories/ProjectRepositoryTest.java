package test.repositories;

import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import access.repositories.implement.ConnectorSQLite;
import access.repositories.implement.ProjectFactory;
import access.repositories.interfaces.iProjectRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import support.operation.builders.ProffesionalProjectBuilder;
import support.operation.builders.ProjectDirector;
import support.operation.builders.ResearchProjectBuilder;

/**
 * Test unitario para iProjectRepository
 * 
 * @author javiersolanop777
 */
public class ProjectRepositoryTest {
    
    private final iProjectRepository atrProjectRepository;
    
    public ProjectRepositoryTest() 
    {
        ConnectorSQLite.atrUrl = "jdbc:sqlite:IngSoftware2.db";
        atrProjectRepository = (ProjectFactory) new ProjectFactory().getRespositoryFactory();
    }
    
    @Test
    public void saveProfessional() 
    {
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ProffesionalProjectBuilder(
            "PROYECTO PRACTICA PROFESIONAL DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            "Archivo prueba"
        ));
        objProjectDirector.buildProjectModel();
        
        // Datos correctos
        assertEquals(atrProjectRepository.save(
            objProjectDirector.getProjectModel()
        ), true);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        ProjectModel objProjectModel = (ProjectModel) atrProjectRepository.getById(objProjectModelPK);
        
        if(objProjectModel != null)
        {
            System.out.println(objProjectModel);
            atrProjectRepository.remove(objProjectModelPK);
        }        
    }
   
    @Test
    public void saveResearch() 
    {      
        ProjectDirector objProjectDirector = new ProjectDirector();
        objProjectDirector.setProjectBuilder(new ResearchProjectBuilder(
            "PROYECTO DE INVESTIGACION DE PRUEBA", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            "cccc@unicauca.edu.co", 
            null, 
            "Objectivo general de prueba",
            "Objetivos especificos de prueba",
            "Archivo prueba"
        ));
        objProjectDirector.buildProjectModel();
        
        // Datos correctos
        assertEquals(atrProjectRepository.save(
            objProjectDirector.getProjectModel()
        ), true);
        
        ProjectModelPK objProjectModelPK = new ProjectModelPK(
            LocalDate.now(),
            "cccc@unicauca.edu.co",
            "cccc@unicauca.edu.co"
        );
        
        ProjectModel objProjectModel = (ProjectModel) atrProjectRepository.getById(objProjectModelPK);
        
        if(objProjectModel != null)
        {
            System.out.println(objProjectModel);
            atrProjectRepository.remove(objProjectModelPK);
        }        
    }
}
