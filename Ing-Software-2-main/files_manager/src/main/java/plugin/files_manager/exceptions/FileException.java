package plugin.files_manager.exceptions;

/**
 *  Clase para el manejo de excepciones de la clase 'FileManager'
 *  @see FileManager
 * 
 * @author javiersolanop777
 */
public class FileException extends Exception {
    
    private FileExceptionEnum atrMessage;
    
    // Constructors:
    
    public FileException()
    {};

    public FileException(FileExceptionEnum prmTypeException)
    {
        this.atrMessage = prmTypeException;
    };
    
    @Override
    public String getMessage()
    {
        return "Archivo: " + atrMessage.toString();
    }

    /**
     *  Metodo para lanzar una excepcion;
     * 
     *  @param prmTypeException Recibe el tipo de excepcion a lazar
     * 
     *  @throws FileException Con el tipo de excepcion
     * 
    */
    public static void throwException(FileExceptionEnum prmTypeException) throws FileException
    {
        throw new FileException(prmTypeException);
    }
    
    /**
     *  Metodo para lanzar una excepcion con base en la condicion recibida;
     * 
     *  @param prmCondition Recibe la condicion
     *  @param prmTypeException Recibe el tipo de excepcion a lazar
     * 
     * @throws FileException Con el tipo de excepcion 
    */
    public static void throwException(boolean prmCondition, FileExceptionEnum prmTypeException) throws FileException
    {
        if(prmCondition) throw new FileException(prmTypeException);
    }
}
