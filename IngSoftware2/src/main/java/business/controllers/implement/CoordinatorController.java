package business.controllers.implement;

import access.models.DefaultInformationEnum;
import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import business.controllers.interfaces.iCoordinatorController;
import business.controllers.interfaces.iProjectController;
import java.util.List;
import support.communication.EmailManager;
import support.communication.iEmailManager;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;

/**
 * Clase controladora para los coordinadores
 * 
 * @author javiersolanop777
 */
@Controller
public class CoordinatorController implements iCoordinatorController {
    
    @ControllerAutowired
    private iProjectController atrProjectController;
    
    /**
     * Almacena el manejador de correos electronicos
     */
    private final iEmailManager ATR_EMAIL_MANAGER;
    
    // Constructors:
    
    public CoordinatorController()
    {
        ATR_EMAIL_MANAGER = new EmailManager();
    }

    @Override
    public List<ProjectModel> getProjects() 
    {
        return atrProjectController.getProjects();
    }
    
    /**
     * Metodo para notificar por correo la evaluacion de un 
     * proyecto a los docentes y estudiantes asociados
     * 
     * @param prmProject Recibe el proyecto
     */
    private void sendEmail(ProjectModel prmProject)
    {
        if(prmProject == null) return;
        
        String varSubject = "Evaluacion de proyecto";
        String varBody = "Se acaba de realizar una evaluacion de proyecto.";
        
        ATR_EMAIL_MANAGER.send(prmProject.getDirector(), varSubject, varBody);
        ATR_EMAIL_MANAGER.send(prmProject.getStudent1(), varSubject, varBody);
        
        if(!prmProject.getCodirector().equals(DefaultInformationEnum.NOT_APPLICABLE.getName()))
        {
            ATR_EMAIL_MANAGER.send(prmProject.getCodirector(), varSubject, varBody);
        }
        if(!prmProject.getStudent2().equals(DefaultInformationEnum.NO.getName())
        || !prmProject.getStudent2().equals(DefaultInformationEnum.NOT_APPLICABLE.getName()))
        {
            ATR_EMAIL_MANAGER.send(prmProject.getStudent2(), varSubject, varBody);
        }
    }

    @Override
    public ProjectModel evaluateProject(ProjectModelPK prmId, boolean prmStatus, String prmObservations) 
    {
        if(prmId == null) return null;
        
        ProjectModel objProject = atrProjectController.evaluateProject(prmId, prmStatus, prmObservations);
        
        if(objProject != null)
        {
            sendEmail(objProject);
            return objProject;
        }
        return null;
    }
}
