package support.operation.model_exceptions;

/**
 * Clase enumeradora para los tipos de excepcion que puede tiene un usuario
 * 
 * @author javiersolanop777
 */
public enum UserExceptionEnum implements iFieldEnum {
    
    NAMES("nombres"),
    SURNAMES("apellidos"),
    EMAIL("email"),
    PASSWORD("contraseña"),
    TELEPHONE("telefono");
    
    private final String ATR_NAME;
    
    UserExceptionEnum(String prmName)
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
