package plugin.files_manager.exceptions;

/**
 * Clase enumeradora para los tipos de excepcion que puede tiene un archivo
 * 
 * @author javiersolanop777
 */
public enum FileExceptionEnum {
    
    IMPORT("No se puede importar!"),
    EXPORT("No se puede exportar!"),
    DELETE("No se puede eliminar!");
    
    private final String atrName;
    
    FileExceptionEnum(String prmName)
    {
        this.atrName = prmName;
    }

    @Override
    public String toString() 
    {
        return atrName;
    }
}
