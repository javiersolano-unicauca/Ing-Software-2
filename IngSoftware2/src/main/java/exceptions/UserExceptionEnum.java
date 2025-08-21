package exceptions;

/**
 * Clase enumeradora para los tipos de excepcion que puede tiene un usuario
 * 
 * @author javiersolanop777
 */
public enum UserExceptionEnum implements iExceptionEnum {
    
    NAMES("names"),
    SURNAMES("surnames"),
    EMAIL("email"),
    PASSWORD("password"),
    TELEPHONE("telephone");
    
    private String atrName;
    
    UserExceptionEnum(String prmName)
    {
        this.atrName = prmName;
    }
    
    /**
     * Metodo para obtener el nombre del campo que lanza la excepcion
     * 
     * @return El nombre
     */
    @Override
    public String getFieldName()
    {
        return this.atrName;
    }
}
