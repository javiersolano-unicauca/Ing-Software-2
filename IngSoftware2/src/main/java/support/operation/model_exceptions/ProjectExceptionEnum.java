package support.operation.model_exceptions;

/**
 * Clase enumeradora para los tipos de excepcion que puede tiene un proyecto
 * 
 * @author javiersolanop777
 */
public enum ProjectExceptionEnum implements iFieldEnum {
    
    TITLE("titulo"),
    DIRECTOR("director"),
    CODIRECTOR("codirector"),
    STUDENT_1("estudiante 1"),
    STUDENT_2("estudiante 2"),
    FILE("Archivo");
    
    private final String ATR_NAME;
    
    ProjectExceptionEnum(String prmName)
    {
        this.ATR_NAME = prmName;
    }
    
    /**
     * Metodo para obtener el nombre del campo que lanza la excepcion
     * 
     * @return El nombre
     */
    @Override
    public String getFieldName()
    {
        return this.ATR_NAME;
    }
}
