package plugin.dependency_injection.interfaces;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Contrato para los escaneadores de clases en paquetes
 * 
 * @author javiersolanop777
 */
public interface iScanner {
    
    /**
     * Metodo para obtener las clases que implementen una anotacion especifica
     * en un paquete
     * 
     * @param prmPackageName Recibe el nombre del paquete
     * @param prmAnnotationType Recibe la referencia a la anotacion
     * 
     * @return La lista de clases si las hay. De lo contrario null
     */
    Set<Class<?>> getClassesByAnnotation(String prmPackageName, Class<? extends Annotation> prmAnnotationType);
}
