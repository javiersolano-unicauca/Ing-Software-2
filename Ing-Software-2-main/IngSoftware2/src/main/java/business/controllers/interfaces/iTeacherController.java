package business.controllers.interfaces;

import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;
import java.io.File;
import support.operation.builders.ProffesionalProjectBuilder;
import support.operation.builders.ResearchProjectBuilder;
import support.operation.model_exceptions.ModelException;

/**
 * Contrato para las clases controladoras de 
 * profesores
 * 
 * @author javiersolanop777
 */
public interface iTeacherController {
    
    /**
     * Metodo para guardar un proyecto de tipo investigacion
     * 
     * @param prmProject Recibe el constructor de proyectos de investigacion
     * @param prmFormat Recibe el archivo del formato
     * 
     * @return La instancia del proyecto guardado. De lo contrario null
     * 
     * @throws ModelException Si los campos no son validos
     */
    ProjectModel saveProject(
        ResearchProjectBuilder prmProject,
        File prmFormat
    ) throws ModelException;
    
    /**
     * Metodo para guardar un proyecto de tipo practica profesional
     * 
     * @param prmProject Recibe el constructor de proyectos de practicas 
     *                   profesionales
     * 
     * @param prmFormat Recibe el archivo del formato
     * @param prmLetterOfAcceptance Recibe el archivo de la carta de aceptacion
     * 
     * @return La instancia del proyecto guardado. De lo contrario null
     * 
     * @throws ModelException Si los campos no son validos
     */
    ProjectModel saveProject(
        ProffesionalProjectBuilder prmProject,
        File prmFormat,
        File prmLetterOfAcceptance
    ) throws ModelException;
    
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
}
