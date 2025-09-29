package business.controllers.implement;

import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import business.controllers.interfaces.iProjectController;
import business.controllers.interfaces.iTeacherController;
import java.io.File;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import support.operation.builders.ProffesionalProjectBuilder;
import support.operation.builders.ProjectDirector;
import support.operation.builders.ResearchProjectBuilder;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;
import support.operation.model_exceptions.ModelException;
import support.validations.ProjectValidation;

/**
 * Clase controladora para los profesores
 * 
 * @author javiersolanop777
 */
@Controller
public class TeacherController implements iTeacherController {
    
    @ControllerAutowired
    private iProjectController atrProjectController;
    
    /**
     * Almacena el director encargado de dirigir
     * la construccion de los proyectos
     */
    private final ProjectDirector ATR_PROJECT_DIRECTOR;
    
    // Constructors:
    
    public TeacherController()
    {
        ATR_PROJECT_DIRECTOR = new ProjectDirector();
    }

    @Override
    public ProjectModel saveProject(
        ResearchProjectBuilder prmProject, 
        File prmFormat
    ) throws ModelException 
    {
        if((prmProject == null) || (prmFormat == null)) return null;
        
        ATR_PROJECT_DIRECTOR.setProjectBuilder(prmProject);
        ATR_PROJECT_DIRECTOR.buildProjectModel();
        ProjectModel objProject = ATR_PROJECT_DIRECTOR.getProjectModel();
        
        return atrProjectController.save(objProject, prmFormat);
    }

    @Override
    public ProjectModel saveProject(
        ProffesionalProjectBuilder prmProject, 
        File prmFormat, 
        File prmLetterOfAcceptance
    ) throws ModelException 
    {
        if((prmProject == null)
        || (prmFormat == null)
        || (prmLetterOfAcceptance == null)) return null;
        
        ATR_PROJECT_DIRECTOR.setProjectBuilder(prmProject);
        ATR_PROJECT_DIRECTOR.buildProjectModel();
        ProjectModel objProject = ATR_PROJECT_DIRECTOR.getProjectModel();
        
        ProjectValidation objValidator = new ProjectValidation();
        
        objProject.setFileName(prmFormat.getName());
        objValidator.validateFileName(objProject);
        objProject.setFileName(prmLetterOfAcceptance.getName());
        objValidator.validateFileName(objProject);
        objValidator.throwException();
        
        byte[][] mtrBytes = new byte[2][];
        
        try
        {
            mtrBytes[0] = Files.readAllBytes(prmFormat.toPath());
            mtrBytes[1] = Files.readAllBytes(prmLetterOfAcceptance.toPath());  
            return atrProjectController.save(objProject, mtrBytes);
        }
        catch(Exception ex) 
        {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 

    @Override
    public boolean updateFormat(ProjectModelPK prmId, File prmNewFormat) 
    {
        return atrProjectController.updateFormat(prmId, prmNewFormat);
    }
}
