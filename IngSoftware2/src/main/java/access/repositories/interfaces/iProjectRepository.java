package access.repositories.interfaces;

import access.models.implement.ProjectModel;
import access.models.implement.ProjectModelPK;

/**
 * Contrato para el acceso a datos de las instancias ProjectModel
 * 
 * @author javiersolanop777
 */
public interface iProjectRepository extends iRepository<ProjectModelPK> {
    
    /**
     * Metodo para obtener un proyecto filtrando por 
     * el primer estudiante
     * 
     * @param prmStudent Recibe el id del estudiante
     * 
     * @return La instancia del proyecto si existe. De lo contrario null
     */
    ProjectModel getByStudent1(String prmStudent);
    
    /**
     * Metodo para obtener un proyecto filtrando por 
     * cualquiera de sus estudiantes asociados
     * 
     * @param prmStudent Recibe el id del estudiante
     * 
     * @return La instancia del proyecto si existe. De lo contrario null
     */
    ProjectModel getByStudent1OrStudent2(String prmStudent);
}
