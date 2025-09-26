package support.operation.model_exceptions;

import java.util.List;

/**
 * Clase para el manejo de excepciones en los modelos
 *  
 * @author javiersolanop777
 */
public abstract class ModelException extends Exception {
    
    // Propiedades de objeto
   
    private List<String> atrExceptionMessages;
    
    protected void setExceptionMessages(List<String> prmList)
    {
        atrExceptionMessages = prmList;
    }

    @Override
    public String getMessage() 
    {
        if((atrExceptionMessages == null) || atrExceptionMessages.isEmpty()) return null;
        String varMessage = "";
        
        for(String atrExceptionMessage: atrExceptionMessages)
            varMessage += atrExceptionMessage + "\n";
        
        return varMessage;
    }
    
    /**
     *  Metodo para agregar un mensaje de excepcion de un campo
     * 
     *  @param prmField Recibe el campo 
     *  @param prmMessage Recibe el mensaje de excepcion
     */
    public void addExceptionMessage(iFieldEnum prmField, String prmMessage)
    {
        if(atrExceptionMessages == null) return;
        atrExceptionMessages.add("El campo " + prmField.getFieldName() + ": " + prmMessage);
    }
}
