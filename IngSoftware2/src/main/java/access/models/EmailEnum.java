package access.models;

/**
 * Enumerador de los dominios de correo permititdos
 * 
 * @author javiersolanop777
 */
public enum EmailEnum {
    
    UNICAUCA("@unicauca.edu.co");
    
    private final String atrName;
    
    EmailEnum(String prmName)
    {
        this.atrName = prmName;
    }
    
    /**
     * Metodo para obtener el nombre del dominio
     * 
     * @return El nombre
     */
    public String getName()
    {
        return this.atrName;
    }
    
    /**
     * Metodo para obtener los dominios a partir de su nombre
     * 
     * @param prmDomain Recibe el nombre
     * 
     * @return El programa si se encuentra. De lo contrario null.
     */
    public static EmailEnum getDomain(String prmDomain)
    {
        for(EmailEnum objObservation: EmailEnum.values())
            if(objObservation.getName().equals(prmDomain))
                return objObservation;
        return null;
    }
}
