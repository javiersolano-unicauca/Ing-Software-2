package access.models;

/**
 * Enumerador de las informaciones por defecto de un proyecto de grado
 * 
 * @author javiersolanop777
 */
public enum DefaultInformationEnum {
    
    NO("NO TIENE"),
    NOT_APPLICABLE("NO APLICABLE"),
    IN_REVISION("EN REVISION"),
    WITHOUT("SIN OBSERVACIONES");
    
    private final String atrName;
    
    DefaultInformationEnum(String prmName)
    {
        this.atrName = prmName;
    }
    
    /**
     * Metodo para obtener el nombre
     * 
     * @return El nombre
     */
    public String getName()
    {
        return this.atrName;
    }
    
    /**
     * Metodo para obtener la obserevacion a partir de su nombre
     * 
     * @param prmObservation Recibe el nombre
     * 
     * @return El programa si se encuentra. De lo contrario null.
     */
    public static DefaultInformationEnum getObservation(String prmObservation)
    {
        for(DefaultInformationEnum objObservation: DefaultInformationEnum.values())
            if(objObservation.getName().equals(prmObservation))
                return objObservation;
        return null;
    }
}
