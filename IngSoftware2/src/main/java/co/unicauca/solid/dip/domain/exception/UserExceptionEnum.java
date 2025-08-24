package co.unicauca.solid.dip.domain.exception;

/**
 * Clase enumeradora para los tipos de excepcion que puede tiene un usuario
 * 
 * @author javiersolanop777
 */
public enum UserExceptionEnum implements iExceptionEnum {
    
    NAMES("nombres"),
    SURNAMES("apellidos"),
    EMAIL("email"),
    PASSWORD("contraseña"),
    TELEPHONE("telefono");
    
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
