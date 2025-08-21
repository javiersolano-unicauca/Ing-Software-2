package models;

/**
 * Enumerador de los roles para un usuario
 * 
 * @author javiersolanop777
 */
public enum RoleEnum {
    
    ESTUDENT("estudiante"),
    TEACHER("profesor");
    
    private String atrName;
    
    RoleEnum(String prmName)
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
     * Metodo para obtener el rol a partir de su nombre
     * 
     * @param prmRole Recibe el nombre
     * 
     * @return El rol si se encuentra. De lo contrario null.
     */
    public static RoleEnum getRole(String prmRole)
    {
        for(RoleEnum objRole: RoleEnum.values())
            if(objRole.getName().equals(prmRole))
                return objRole;
        return null;
    }
}
