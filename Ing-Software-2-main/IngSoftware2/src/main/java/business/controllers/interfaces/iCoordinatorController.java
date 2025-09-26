package business.controllers.interfaces;

import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import java.util.List;

/**
 * Contrato para las clases controladoras de 
 * coordinadores
 * 
 * @author javiersolanop777
 */
public interface iCoordinatorController {
    
    /**
     * Metodo para obtener todos los proyectos
     * 
     * @return La lista de proyectos si existen. De lo contrario null 
     */
    List<ProjectModel> getProjects();
    
    /**
     * Metodo para evaluar un proyecto, siguiendo
     * las reglas del negocio
     * 
     * @param prmId Recibe su id
     * @param prmStatus Recibe el estado de la evaluacion
     * @param prmObservations Recibe las observaciones
     * 
     * @return El proyecto actualizado
     */
    ProjectModel evaluateProject(
        ProjectModelPK prmId, 
        boolean prmStatus,   
        String prmObservations
    );
}
