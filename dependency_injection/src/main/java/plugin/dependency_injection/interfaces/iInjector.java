package plugin.dependency_injection.interfaces;

import java.lang.annotation.Annotation;
import java.util.function.Consumer;

/**
 * Contrato para los inyectores de dependencias
 * 
 * @author javiersolanop777
 */
public interface iInjector {
    
    /**
     * Metodo para adicionar una fabrica en la lista
     * de fabricas del inyector
     * 
     * @param prmFactory Recibe la referencia de la fabrica
     */
    void addFactory(Object prmFactory);
    
    /**
     * Metodo para obtener una fabrica almacenada
     * en el inyector
     * 
     * @param prmClass Recibe la referencia a la clase de la fabrica
     * 
     * @return La fabrica si se encuentra. De lo contrario null
     */
    Object getFactory(Class<?> prmClass);
    
    /**
     * Metodo para asignar una fabrica en los campo que implementen
     * una anotacion especifica
     * 
     * @param prmObject Recibe la referencia del objeto al que se inyecta
     * @param prmAnnotation Recibe la anotacion a buscar en los campos 
     */
    void assignament(Object prmObject, Class<? extends Annotation> prmAnnotation);
    
    /**
     * Metodo para recorrer y manipular las fabricas almacenadas
     * en el inyector
     * 
     * @param prmLambda Recibe la lambda que manipula la fabrica
     */
    void forEach(Consumer<Object> prmLambda);
}
