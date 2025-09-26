package access.models;


/**
 * Enumerador de los estados de un proyecto de grado
 * 
 * @author javiersolanop777
 */
public enum StatusEnum {
    
    FIRST("primera evaluacion formato A"),
    SECOND("segunda evaluacion formato A"),
    THIRD("tercera evaluacion formato A"),
    ACCEPTED("aceptado formato A"),
    REJECTED("rechazado formato A");
    
    private final String atrName;
    
    StatusEnum(String prmName)
    {
        this.atrName = prmName;
    }
    
    /**
     * Metodo para obtener el nombre del programa
     * 
     * @return El nombre
     */
    public String getName()
    {
        return this.atrName;
    }
    
    /**
     * Metodo para obtener la modalidad a partir de su nombre
     * 
     * @param prmModality Recibe el nombre
     * 
     * @return El programa si se encuentra. De lo contrario null.
     */
    public static StatusEnum getStatus(String prmModality)
    {
        for(StatusEnum objStatus: StatusEnum.values())
            if(objStatus.getName().equals(prmModality))
                return objStatus;
        return null;
    }
}
