package plugin.dependency_injection.implement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugin.dependency_injection.interfaces.iInjector;

/**
 * Clase para la inyeccion de dependencias
 * 
 * @author javiersolanop7777
 */
public final class Injector implements iInjector {
    
    /**
     * Almacena las fabricas de todas los contratos que implementen 
     * las anotaciones almacenadas en esta clase
     */
    private final List<Object> ATR_FACTORIES;
    
    // Constructors:
    
    public Injector()
    {
       ATR_FACTORIES = new LinkedList<Object>();
    }
    
    @Override
    public void addFactory(Object prmFactory)
    {
        try
        {
            ATR_FACTORIES.add(prmFactory);
        }
        catch(Exception ex) 
        {
            Logger.getLogger(Injector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Object getFactory(Class<?> prmClass) 
    {
        for(Object objFactory: ATR_FACTORIES)
        {
            if(objFactory.getClass().equals(prmClass))
                return objFactory;
        }
        return null;
    }
    
    @Override
    public void assignament(Object prmObject, Class<? extends Annotation> prmAnnotation)
    {
        if(prmObject == null) return;
        if(prmAnnotation == null) return;
        
        try
        {
            Field arrFields[] = prmObject.getClass().getDeclaredFields();
        
            for(Field objField: arrFields)
            {
                objField.setAccessible(true);

                if(objField.isAnnotationPresent(prmAnnotation))
                {
                    for(Object objFactory: ATR_FACTORIES)
                    {
                        if(objField.getType().isAssignableFrom(objFactory.getClass()))
                            objField.set(prmObject, objFactory);
                    }
                }
            }
        }
        catch(Exception ex) 
        {
            Logger.getLogger(Injector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void forEach(Consumer<Object> prmLamba) 
    {
        ATR_FACTORIES.forEach(prmLamba);
    }
}
