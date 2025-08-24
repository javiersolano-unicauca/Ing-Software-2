package co.unicauca.solid.dip.domain.access;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase gestora de las fabricas
 * @see Factory
 * 
 * @author javiersolanop777
 */
public class FactoryManager {
    
    /**
     * Lista para almacenar las fabricas de repositorios existentes
     */
    private static List<Factory<?>> listFactories;
    
    /**
     * Metodo para cargar las fabricas de repositorios
     */
    private static void factoriesLoader()
    {
        listFactories = new LinkedList<>();
        listFactories.add(new UserFactory());
    }
    
    /**
     *  Metodo para asignar la fabrica establecida en la anotacion
     * 
     *  @param prmObject Recibe al objeto al que pertenece el campo
     *  @param prmField Recibe el campo al cual se le asigna la fabrica
     * 
     *  @throws IllegalArgumentException
     *  @throws IllegalAccessException 
     */
    public static void setValue(Object prmObject, Field prmField) throws IllegalArgumentException, IllegalAccessException
    {
        factoriesLoader();
      
        prmField.setAccessible(true);
        
        for(Factory objFactory: listFactories)
        {
            if(prmField.getType().isAssignableFrom(objFactory.getClass()))
            {
                prmField.set(prmObject, (Object) objFactory.getRespositoryFactory());
                return;
            }
        }
    }
}
