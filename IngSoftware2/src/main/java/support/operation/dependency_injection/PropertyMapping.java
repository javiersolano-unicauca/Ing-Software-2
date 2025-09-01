package support.operation.dependency_injection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para asignar las variables de entorno en atributos
 * de clases que contengan la anotacion 'Property'
 * 
 * @see Property
 * 
 * @author javiersolanop777
 */
public class PropertyMapping {
    
    /**
     * Almacena la ruta donde se encuentra el archivo de propiedades
     */
    private final String ATR_FILE_PATH;
    
    /**
     * Almacena el nombre del archivo de propiedades
     */
    private final String ATR_FILE_NAME = "application";
    
    /**
     * Almacena el flujo de entrada de archivos
     */
    private InputStream atrInputStream;
    
    // Constructor
    
    public PropertyMapping()
    {
        ATR_FILE_PATH = System.getProperty("user.dir")
                                + "/src/main/java/resources/"
                                + ATR_FILE_NAME + ".properties";
    }
    
    /**
     * Metodo que asigna el valor de variables de entorno a un 
     * atributo de objeto
     * 
     * @param prmObject Recibe la referencia del objeto
     */
    public void propertiesAssignament(Object prmObject)
    {
        try
        {
            Field arrFields[] = prmObject.getClass().getDeclaredFields();
        
            for(Field objField: arrFields)
            {
                objField.setAccessible(true);

                if(objField.isAnnotationPresent(Property.class))
                {
                    String varPropertyName = objField.getAnnotation(Property.class)
                                                 .property();

                    atrInputStream = new FileInputStream(ATR_FILE_PATH);
                    Properties objProperties = new Properties();

                    objProperties.load(atrInputStream);
                    objField.set(prmObject, objProperties.getProperty(varPropertyName));
                    atrInputStream.close();
                    return;
                }
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(PropertyMapping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
