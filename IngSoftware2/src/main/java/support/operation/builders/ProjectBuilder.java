package support.operation.builders;

import access.models.implement.ProjectModel;

/**
 * Clase constructora de proyectos 
 * 
 * @author javiersolanop777
 */
public abstract class ProjectBuilder {
    
    /**
     * Almacena la instancia del proyecto construido
     */
    protected ProjectModel atrProjectModel;
    
    /**
     * Metodo para retornar el proyecto construido
     * 
     * @return La instancia dle proyecto
     */
    public ProjectModel getProjectModel()
    {
        return this.atrProjectModel;
    }
    
    /**
     * Metodo para construir el proyecto
     */
    public void buildProjectModel()
    {
        this.atrProjectModel = new ProjectModel();
    }
    
    // Metodos para construir los campos del modelo:
    
    public abstract void buildTitle();
    public abstract void buildDate();
    public abstract void buildDirector();
    public abstract void buildCodirector();
    public abstract void buildStudent1();
    public abstract void buildStudent2();
    public abstract void buildModality();
    public abstract void buildGeneralObjective();
    public abstract void buildSpecificObjectives();
    public abstract void buildStatus();
    public abstract void buildNumberOfAttempts();
    public abstract void buildObservations();
    public abstract void buildFileName();
}
