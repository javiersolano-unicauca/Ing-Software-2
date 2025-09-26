package plugin.property_mapping.implement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugin.dependency_injection.implement.ScannerPackage;
import plugin.dependency_injection.interfaces.iScanner;
import plugin.property_mapping.annotations.PropertiesScan;
import plugin.property_mapping.annotations.Property;
import plugin.property_mapping.annotations.PropertyMapping;

/**
 * Clase para asignar las variables de entorno en atributos
 * de clases que contengan la anotacion 'Property'
 * 
 * @see Property
 * 
 * @author javiersolanop777
 */
public class MapperOfProperties {
    
    /**
     * Almacena la ruta donde se encuentra el archivo de propiedades
     */
    private final String ATR_FILE_PATH;
    
    /**
     * Almacena el flujo de entrada de archivos
     */
    private InputStream atrInputStream;
    
    private final iScanner ATR_SCANNER;
    
    // Constructor
    
    /**
     * @param prmFileName Recibe el nombre del archivo de propiedades
     */
    public MapperOfProperties(String prmFileName)
    {
        ATR_FILE_PATH = System.getProperty("user.dir")
                                + "/src/main/java/resources/"
                                + prmFileName + ".properties";
        
        ATR_SCANNER = new ScannerPackage();
    }
    
    /**
     * Metodo para asignar el valor de variables de entorno a los
     * campos estaticos definidos por la anotacion 'Property' de una
     * clase especifica
     * 
     * @see Property
     * 
     * @param prmClass Recibe la referencia a la clase
     */
    private void assignament(Properties prmProperties, Class<?> prmClass)
    {     
        try
        {
            Field arrFields[] = prmClass.getDeclaredFields();
            int varModifier;

            for(Field objField: arrFields)
            {
                objField.setAccessible(true);
                varModifier = objField.getModifiers();

                if(Modifier.isStatic(varModifier)
                && objField.isAnnotationPresent(Property.class))
                {
                    String varPropertyName = objField.getAnnotation(Property.class)
                                                     .property();

                    objField.set(null, prmProperties.getProperty(varPropertyName));
                }
            }
        }
        catch (Exception ex) 
        {
            Logger.getLogger(MapperOfProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para asignar el valor de variables de entorno a los
     * campos estaticos de clases que se encuentren en los paquetes
     * definidos por la anotacion 'PropertyMapping'
     * 
     * @see PropertyMapping
     * 
     * @param prmClass Recibe la referencia a la clase
     */
    public void propertiesAssignament(Class<?> prmClass)
    {
        if(prmClass.isAnnotationPresent(PropertiesScan.class))
        {
            try
            {
                atrInputStream = new FileInputStream(ATR_FILE_PATH);
                Properties objProperties = new Properties();
                objProperties.load(atrInputStream);

                String arrPackagesNames[] = prmClass.getAnnotation(PropertiesScan.class)
                                                    .packagesNames();

                Set<Class<?>> listClasses;

                for(String varPackageName: arrPackagesNames)
                {
                    listClasses = ATR_SCANNER.getClassesByAnnotation(
                        varPackageName, 
                        PropertyMapping.class
                    );

                    for(Class<?> objClass: listClasses) assignament(objProperties, objClass);
                }
                atrInputStream.close();
            }
            catch(Exception ex)
            {
                try {
                    atrInputStream.close();
                    Logger.getLogger(MapperOfProperties.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex1) {
                    Logger.getLogger(MapperOfProperties.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}
