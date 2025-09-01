package access.models;

/**
 * Enumerador de los programas permitidos para un usuario
 * 
 * @author javiersolanop777
 */
public enum CareerEnum {
    
    SYSTEM_ENGINEERING("Ingenieria de Sistemas"),
    ELECTRONIC_ENGINEERING_AND_TELECOM("Ingenieria Electronica y Telecomunicaciones"),
    INDUSTRIAL_AUTOMATIC("Automatica Industrial"),
    TELEMATICS_TECHNOLOGY("Tecnologia en Telematica");
    
    private String atrName;
    
    CareerEnum(String prmName)
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
     * Metodo para obtener el programa a partir de su nombre
     * 
     * @param prmCareer Recibe el nombre
     * 
     * @return El programa si se encuentra. De lo contrario null.
     */
    public static CareerEnum getCareer(String prmCareer)
    {
        for(CareerEnum objCareer: CareerEnum.values())
            if(objCareer.getName().equals(prmCareer))
                return objCareer;
        return null;
    }
}
