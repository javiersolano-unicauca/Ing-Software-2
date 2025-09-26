package support.operation.builders;

import access.models.implement.ProjectModel;

/**
 * Clase para dirigir la construccion de un proyecto concreto
 * 
 * @author javiersolanop777
 */
public class ProjectDirector {
    
    /**
     * Almacena el constructor especifico
     */
    private ProjectBuilder atrProjectBuilder;
    
    // Constructors:
    
    public ProjectDirector()
    {
        this.atrProjectBuilder = null;
    }
    
    /**
     * Metodo para establecer el constructor especifico
     * 
     * @param prmProjectBuilder Reibe la referencia al constructor
     */
    public void setProjectBuilder(ProjectBuilder prmProjectBuilder)
    {
        this.atrProjectBuilder = prmProjectBuilder;
    }
    
    /**
     * Metodo para obtener el proyecto construido
     * 
     * @return La instancia del proyecto
     */
    public ProjectModel getProjectModel()
    {
        return this.atrProjectBuilder.getProjectModel();
    }
    
    /**
     * Metodo para construir un proyecto a partir del constructor
     * establecido
     */
    public void buildProjectModel()
    {
        this.atrProjectBuilder.buildProjectModel();
        this.atrProjectBuilder.buildTitle();
        this.atrProjectBuilder.buildDate();
        this.atrProjectBuilder.buildDirector();
        this.atrProjectBuilder.buildCodirector();
        this.atrProjectBuilder.buildStudent1();
        this.atrProjectBuilder.buildStudent2();
        this.atrProjectBuilder.buildModality();
        this.atrProjectBuilder.buildGeneralObjective();
        this.atrProjectBuilder.buildSpecificObjectives();
        this.atrProjectBuilder.buildStatus();
        this.atrProjectBuilder.buildNumberOfAttempts();
        this.atrProjectBuilder.buildObservations();
        this.atrProjectBuilder.buildFileName();
    }
}
