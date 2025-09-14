package plugin.dependency_injection.implement;

import plugin.dependency_injection.interfaces.iScanner;
import java.lang.annotation.Annotation;
import java.util.Set;
import org.reflections.Reflections;

/**
 * Clase que implementa el contrato de la interfaz 'iScanner'
 * 
 * @see iScanner
 * 
 * @author javiersolanop777
 */
public class ScannerPackage implements iScanner {
    
    // Constructor:
    
    public ScannerPackage()
    {}

    @Override
    public Set<Class<?>> getClassesByAnnotation(String prmPackageName, Class<? extends Annotation> prmAnnotationType)
    {
        Reflections objScanner = new Reflections(prmPackageName);
        return objScanner.getTypesAnnotatedWith(prmAnnotationType);           
    }
}
