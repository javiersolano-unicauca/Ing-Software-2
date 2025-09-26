package support.validations;

import access.models.DefaultInformationEnum;
import access.models.implement.ProjectModel;
import access.models.interfaces.iModel;
import support.operation.model_exceptions.ProjectException;
import support.operation.model_exceptions.ProjectExceptionEnum;

/**
 *  Clase para las validaciones de los usuarios
 *  @see ProjectModel
 * 
 * @author javiersolanop777
 */
public class ProjectValidation implements iValidator {
    
    private final ProjectException ATR_EXCEPTION;
    
    // Constructor:
    
    public ProjectValidation()
    {
        ATR_EXCEPTION = new ProjectException();
    }

    @Override
    public void validate(iModel prmModel) throws ProjectException 
    {
        validateTitle((ProjectModel) prmModel);
        validateDirector((ProjectModel) prmModel);
        validateCodirector((ProjectModel) prmModel);
        validateStudent1((ProjectModel) prmModel);
        validateStudent2((ProjectModel) prmModel);
        validateFileName((ProjectModel) prmModel);
        ATR_EXCEPTION.throwException();
    }
    
    @Override
    public void throwException() throws ProjectException 
    {
        ATR_EXCEPTION.throwException();
    }
    
    /**
     *  Metodo para validar si un campo esta nulo,
     *  si el campo esta nulo se agrega el mensaje de
     *  excepcion 
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @return 'true' si el campo esta nulo. 'false' si no
     */
    protected boolean isNull(Object prmField, ProjectExceptionEnum prmFieldType)
    {
        if(prmField == null)
        {
            ATR_EXCEPTION.addExceptionMessage(prmFieldType, "No debe estar nulo");
            return true;
        }
        return false;
    }
    
    /**
     *  Metodo para validar si un campo esta vacio,
     *  si el campo esta vacio se agrega el mensaje de
     *  excepcion 
     * 
     *  @param prmField Recibe la referencia al campo
     *  @param prmFieldType Recibe el tipo de campo
     * 
     *  @return 'true' si el campo esta vacio. 'false' si no
     */
    protected boolean isEmpty(String prmField, ProjectExceptionEnum prmFieldType)
    {
        if(prmField.isEmpty())
        {
            ATR_EXCEPTION.addExceptionMessage(prmFieldType, "No debe estar vacio");
            return true;
        }
        return false;
    }
    
    /**
     *  @param prmProject Recibe la referencia del proyecto
     */
    public void validateTitle(ProjectModel prmProject)
    {
        if(isNull(prmProject.getTitle(), ProjectExceptionEnum.TITLE)
        || isEmpty(prmProject.getTitle(), ProjectExceptionEnum.TITLE)) return;

        if(!prmProject.getTitle().matches("[a-zA-Z ]*"))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.TITLE, 
                "Debe contener solamente letras"
            );
        }
    }
    
    /**
     *  @param prmProject Recibe la referencia del proyecto
     */
    public void validateDirector(ProjectModel prmProject)
    {
        if(isNull(prmProject.getDirector(), ProjectExceptionEnum.DIRECTOR)
        || isEmpty(prmProject.getDirector(), ProjectExceptionEnum.DIRECTOR)) return;

        if(!prmProject.getDirector().contains("@unicauca.edu.co"))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.DIRECTOR, 
                "Debe contener un correo de la universidad del cauca"
            );
        }
    }
    
    /**
     *  @param prmProject Recibe la referencia del proyecto
     */
    public void validateCodirector(ProjectModel prmProject)
    {
        if(isNull(prmProject.getCodirector(), ProjectExceptionEnum.CODIRECTOR)
        || isEmpty(prmProject.getCodirector(), ProjectExceptionEnum.CODIRECTOR)) return;

        if(!prmProject.getCodirector().contains("@unicauca.edu.co")
        && !prmProject.getCodirector().equals(DefaultInformationEnum.NOT_APPLICABLE.getName()))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.CODIRECTOR, 
                "Debe contener un correo de la universidad del cauca"
            );
        }
        else if(prmProject.getDirector().equals(prmProject.getCodirector()))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.CODIRECTOR, 
                "No debe ser igual al director"
            );
        }
    }
    
    /**
     *  @param prmProject Recibe la referencia del proyecto
     */
    public void validateStudent1(ProjectModel prmProject)
    {
        if(isNull(prmProject.getStudent1(), ProjectExceptionEnum.STUDENT_1)
        || isEmpty(prmProject.getStudent1(), ProjectExceptionEnum.STUDENT_1)) return;

        if(!prmProject.getStudent1().contains("@unicauca.edu.co"))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.STUDENT_1, 
                "Debe contener un correo de la universidad del cauca"
            );
        }
    }
    
    /**
     *  @param prmProject Recibe la referencia del proyecto
     */
    public void validateStudent2(ProjectModel prmProject)
    {
        if(isNull(prmProject.getStudent2(), ProjectExceptionEnum.STUDENT_2)
        || isEmpty(prmProject.getStudent2(), ProjectExceptionEnum.STUDENT_2)) return;

        if(!prmProject.getStudent2().contains("@unicauca.edu.co")
        && !prmProject.getStudent2().equals(DefaultInformationEnum.NO.getName())
        && !prmProject.getStudent2().equals(DefaultInformationEnum.NOT_APPLICABLE.getName()))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.STUDENT_2, 
                "Debe contener un correo de la universidad del cauca"
            );
        }
        else if(prmProject.getStudent2().equals(prmProject.getStudent1()))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.STUDENT_2, 
                "No debe ser igual al estudiante 1"
            ); 
        }
    }
    
    /**
     *  @param prmProject Recibe la referencia del proyecto
     */
    public void validateFileName(ProjectModel prmProject)
    {
        if(isNull(prmProject.getFileName(), ProjectExceptionEnum.FILE)) return;
        
        if(!prmProject.getFileName().endsWith(".pdf"))
        {
            ATR_EXCEPTION.addExceptionMessage(
                ProjectExceptionEnum.FILE, 
                "Debe tener formato pdf"
            );
        }
    }
}
