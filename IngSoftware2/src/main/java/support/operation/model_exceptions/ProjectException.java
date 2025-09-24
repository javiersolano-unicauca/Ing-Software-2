package support.operation.model_exceptions;

import java.util.LinkedList;

/**
 *  Clase para el manejo de excepciones de la clase 'ProjectModel'
 *  @see ProjectModel
 * 
 * @author javiersolanop777
 */
public class ProjectException extends ModelException {
    
    // Constructors:
    
    public ProjectException()
    {
        setExceptionMessages(new LinkedList<>());
    }
    
    public ProjectException(ProjectExceptionEnum prmField, String prmMessage)
    {
        setExceptionMessages(new LinkedList<>());
        super.addExceptionMessage(prmField, prmMessage);
    }
    
    public void addExceptionMessage(ProjectExceptionEnum prmField, String prmMessage)
    {
        super.addExceptionMessage(prmField, prmMessage);
    }
    
    public void throwException() throws ProjectException
    {
        if(getMessage() == null) return;
        throw this;
    }
    
    public static void throwException(ProjectExceptionEnum prmField, String prmMessage) throws ProjectException
    {
        throw new ProjectException(prmField, prmMessage);
    }

    public static void throwException(boolean prmCondition, ProjectExceptionEnum prmField, String prmMessage) throws ProjectException
    {
        if(prmCondition) throw new ProjectException(prmField, prmMessage);
    }
}
