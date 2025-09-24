package business.controllers.interfaces;

import access.models.implement.ProjectModel;

/**
 * Contrato para las clases controladoras de 
 * estudiantes
 * 
 * @author javiersolanop777
 */
public interface iStudentController {
    
    /**
     * Metodo para obtener el proyecto de un estudiante
     * 
     * @param prmEmail Recibe su correo
     * 
     * @return La instancia del proyecto si existe. De lo contrario null 
     */
    ProjectModel getProject(String prmEmail);
}
