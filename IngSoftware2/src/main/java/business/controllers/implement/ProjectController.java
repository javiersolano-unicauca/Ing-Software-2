package business.controllers.implement;

import access.models.DefaultInformationEnum;
import access.models.EmailEnum;
import access.models.StatusEnum;
import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import access.models.interfaces.iModel;
import access.repositories.interfaces.iProjectRepository;
import business.controllers.interfaces.iProjectController;
import java.io.File;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugin.files_manager.exceptions.FileException;
import plugin.files_manager.implement.FileManager;
import plugin.property_mapping.annotations.Property;
import plugin.property_mapping.annotations.PropertyMapping;

import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.FactoryAutowired;
import support.operation.model_exceptions.ModelException;
import support.validations.ProjectValidation;
import support.validations.iValidator;

/**
 * Clase controladora para los proyectos
 * 
 * @author javiersolanop777
 */
@Controller
@PropertyMapping
public class ProjectController implements iProjectController {
    
    @FactoryAutowired
    private iProjectRepository atrProjectRepository;
    
    @Property(property = "store.path")
    public static String atrStorePath;
    
    /**
     * Almacena el manejador de archivos
     */
    private final FileManager ATR_FILE_MANAGER;
    
    // Constructors:
    
    public ProjectController()
    {
        ATR_FILE_MANAGER = new FileManager(
            System.getProperty("user.dir")
            + "\\src\\main\\java\\" 
            + atrStorePath
        );
    }
    
    // Metodos 'setter':
    
    public void setFactory(iProjectRepository prmFactory)
    {
        this.atrProjectRepository = prmFactory;
    }  
    
    // Metodos del contrato:
    
    @Override
    public List<ProjectModel> getProjects() 
    {
        List<iModel> listModels = atrProjectRepository.list();
        
        if(listModels == null) return null;
        
        List<ProjectModel> listProjects = new LinkedList<>();
        
        for(iModel objModel: listModels)
            listProjects.add((ProjectModel) objModel);
        return listProjects;
    }

    @Override
    public ProjectModel getProject(ProjectModelPK prmId) 
    {
        return (ProjectModel) atrProjectRepository.getById(prmId);
    }
    
    @Override
    public ProjectModel getProjectOfStudent1(String prmStudent) 
    {
        return atrProjectRepository.getByStudent1(prmStudent);
    }

    @Override
    public ProjectModel getProjectOfStudent1OrStudent2(String prmStudent) 
    {
        return atrProjectRepository.getByStudent1OrStudent2(prmStudent);
    }
    
    @Override
    public ProjectModel save(ProjectModel prmProject, byte[][] prmFiles) throws ModelException 
    {
        if(prmProject == null) return null;
        
        iValidator objValidator = new ProjectValidation();
        objValidator.validate(prmProject);
        
        if(this.getProject(prmProject.getId()) != null) return null;
        
        prmProject.setFileName(
            prmProject.getDirector().replace(EmailEnum.UNICAUCA.getName(),"") 
            + "-" + 
            prmProject.getStudent1().replace(EmailEnum.UNICAUCA.getName(),"") 
        );
        
        if(atrProjectRepository.save(prmProject))
        {
            try 
            {
                ATR_FILE_MANAGER.exportPdf(
                    prmProject.getFileName(),
                    prmFiles
                );
                return prmProject;
            } 
            catch(Exception ex) 
            {
                Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public ProjectModel save(ProjectModel prmProject, File prmFormat) throws ModelException 
    {
        if((prmProject == null) || (prmFormat == null)) return null;
        
        prmProject.setFileName(prmFormat.getName());
        
        iValidator objValidator = new ProjectValidation();
        objValidator.validate(prmProject);
        
        if(this.getProject(prmProject.getId()) != null) return null;
        
        prmProject.setFileName(
            prmProject.getDirector().replace(EmailEnum.UNICAUCA.getName(),"") 
            + "-" + 
            prmProject.getStudent1().replace(EmailEnum.UNICAUCA.getName(),"") 
        );
        
        if(atrProjectRepository.save(prmProject))
        {
            try 
            {
                ATR_FILE_MANAGER.exportPdf(
                    prmProject.getFileName(),
                    Files.readAllBytes(prmFormat.toPath())
                );
                return prmProject;
            } 
            catch(Exception ex) 
            {
                Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public ProjectModel evaluateProject(
        ProjectModelPK prmId, 
        boolean prmStatus, 
        String prmObservations) 
    {
        if(prmId == null) return null;
        
        ProjectModel objProject = this.getProject(prmId);
        
        if(objProject == null) return null;
        
        if(prmStatus)
        {
            objProject.setStatus(StatusEnum.ACCEPTED);
            objProject.setObservations(
                (prmObservations != null) ? prmObservations : DefaultInformationEnum.WITHOUT.getName()
            );
        }
        else if(objProject.getNumberOfAttempts() == 3)
        {
            this.delete(prmId);
            return null;
        }
        else 
        {
            if(prmObservations == null) return null;
            
            objProject.setStatus(StatusEnum.REJECTED);
            objProject.setObservations(prmObservations);
        }   
        return (atrProjectRepository.save(objProject)) ? objProject : null;
    }
    
    /**
     * Metodo para cambiar el archivo de un formato
     * 
     * @param prmFortmat Recibe el nombre del archivo
     * @param prmNewFile Recibe el nuevo archivo del formato
     */
    private void changeFormat(String prmFormat, File prmNewFile)
    {
        try 
        {
            ATR_FILE_MANAGER.removePdf(prmFormat);
            ATR_FILE_MANAGER.exportPdf(
                prmFormat, 
                Files.readAllBytes(prmNewFile.toPath())
            );
        } 
        catch(Exception ex) 
        {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean updateFormat(ProjectModelPK prmId, File prmNewFormat) 
    {
        if((prmId == null) || (prmNewFormat == null)) return false;
        
        ProjectModel objProject = this.getProject(prmId);
        
        if(objProject == null) return false;
        
        if(objProject.getStatus().equals(StatusEnum.REJECTED)
        && (objProject.getNumberOfAttempts() < 3))
        {    
            objProject.setStatus(StatusEnum.values()[objProject.getNumberOfAttempts()]);   
            objProject.setNumberOfAttempts(objProject.getNumberOfAttempts() + 1);
            objProject.setObservations(DefaultInformationEnum.IN_REVISION.getName());
            atrProjectRepository.save(objProject);
            changeFormat(objProject.getFileName(), prmNewFormat);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(ProjectModelPK prmId) 
    {
        if(prmId == null) return false;
        
        ProjectModel objProject = this.getProject(prmId);
        
        if(objProject == null) return false;
        
        if(atrProjectRepository.remove(prmId))
        {
            try 
            {
                ATR_FILE_MANAGER.removePdf(objProject.getFileName());
            } 
            catch(FileException ex) 
            {
                Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }   
}
