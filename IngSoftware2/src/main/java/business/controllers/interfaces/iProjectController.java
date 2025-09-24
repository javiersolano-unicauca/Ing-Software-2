package business.controllers.interfaces;

import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import java.io.File;
import java.util.List;
import support.operation.model_exceptions.ModelException;

/**
 * Contrato para las clases controladoras de 
 * proyectos
 * 
 * @author javiersolanop777
 */
public interface iProjectController {
    
    /**
     * Metodo para obtener todos los proyectos
     * 
     * @return La lista de proyectos si existen. De lo contrario null 
     */
    List<ProjectModel> getProjects();
    
    /**
     * Metodo para obtener un proyecto
     * 
     * @param prmId Recibe el id
     * 
     * @return La instancia del proyecto si existe. De lo contrario null 
     */
    ProjectModel getProject(ProjectModelPK prmId);
    
    /**
     * Metodo para obtener el proyecto asociado al primer estudiante
     * 
     * @param prmStudent Recibe su id
     * 
     * @return La instancia del proyecto si existe. De lo contrario null 
     */
    ProjectModel getProjectOfStudent1(String prmStudent);
    
    /**
     * Metodo para obtener el proyecto buscando por cualquiera
     * de los estudiantes asociados
     * 
     * @param prmStudent Recibe su id
     * 
     * @return La instancia del proyecto si existe. De lo contrario null 
     */
    ProjectModel getProjectOfStudent1OrStudent2(String prmStudent);
    
    /**
     * Metodo para guardar en la DB
     * 
     * @param prmProject Recibe el proyecto
     * @param prmFiles Recibe los archivos a guardar
     * 
     * @return La instancia del proyecto guardado. De lo contrario null
     * 
     * @throws ModelException Si los campos no son validos
     */
    ProjectModel save(ProjectModel prmProject, byte[][] prmFiles) throws ModelException;
    
    /**
     * Metodo para guardar en la DB
     * 
     * @param prmProject Recibe el proyecto
     * @param prmFormat Recibe el archivo del formato
     * 
     * @return La instancia del proyecto guardado. De lo contrario null
     * 
     * @throws ModelException Si los campos no son validos
     */
    ProjectModel save(ProjectModel prmProject, File prmFormat) throws ModelException;
    
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
    
    /**
     * Metodo para actualizar el archivo de un formato, siguiendo
     * las reglas del negocio
     * 
     * @param prmId Recibe su id
     * @param prmNewFormat Recibe el nuevo archivo del formato
     * 
     * @return 'true' si se actualiza. 'false' si no
     */
    boolean updateFormat(ProjectModelPK prmId, File prmNewFormat);
    
    /**
     * Metodo para eliminar un proyecto 
     * 
     * @param prmId Recibe su id
     * 
     * @return 'true' si se elimina. 'false' si no 
     */
    boolean delete(ProjectModelPK prmId);
}
