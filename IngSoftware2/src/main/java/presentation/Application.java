package presentation;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugin.dependency_injection.implement.Injector;
import plugin.dependency_injection.interfaces.iInjector;
import plugin.property_mapping.implement.MapperOfProperties;
import presentation.controllers.GUILoginController;
import support.operation.dependency_injection.ControllerAutowired;
import support.operation.dependency_injection.ControllerScanner;
import support.operation.dependency_injection.FactoryAutowired;
import support.operation.dependency_injection.RepositoryScanner;

/**
 * Clase para configurar el programa y correrlo
 * 
 * @author javiersolanop777
 */
public class Application {
    
    /**
     * Almacena el nombre del archivo de propiedades en el proyecto
     */
    private static final String ATR_PROPERTIES_FILE = "application";
    
    /**
     * Almacena el inyector de fabricas de repositorios
     */
    private final iInjector atrRepositoryInjector;
    
    /**
     * Almacena el inyector de fabricas de controladores
     */
    private final iInjector atrInjectorOfController;
    
    // Constructor:
    
    public Application()
    {
        atrRepositoryInjector = new Injector();
        atrInjectorOfController = new Injector();
    }
    
    /**
     * Metodo para instanciar y cargar todos las fabricas de repositorios
     */
    private void repositoryFactoriesLoader()
    {
        try
        {
            RepositoryScanner objRepositoryScanner = new RepositoryScanner();
            Set<Class<?>> listClasses = objRepositoryScanner.getRepositoryFactoriesClasses();
            
            for(Class<?> objClass: listClasses)
                atrRepositoryInjector.addFactory(objClass.getDeclaredConstructor().newInstance());
        } 
        catch(Exception ex) 
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *  Metodo para asignar fabricas de repositorio
     *  en los atributos de un objeto
     * 
     *  @param prmObject Recibe la referencia al objeto
     * 
     *  @throws IllegalArgumentException
     *  @throws IllegalAccessException 
     */
    private void factoryAssignament(Object prmObject) throws IllegalArgumentException, IllegalAccessException
    {
        atrRepositoryInjector.assignament(prmObject, FactoryAutowired.class);
    }
    
    /**
     * Metodo para asignar controladores que son requeridos por otros
     */
    private void controllersAssignament()
    {
        atrInjectorOfController.forEach((Object prmFactory) -> {
            atrInjectorOfController.assignament(prmFactory, ControllerAutowired.class);
        });
    }
    
    /**
     * Metodo para instanciar y cargar todos los controladores
     */
    private void controllersLoader()
    {
        try
        {
            ControllerScanner objControllerScanner = new ControllerScanner();
            List<Set<Class<?>>> listPackages = objControllerScanner.getControllersClasses();
            Object objController;
            
            for(Set<Class<?>> listClasses: listPackages)
            {
                for(Class<?> objClass: listClasses)
                {
                    objController = objClass.getDeclaredConstructor().newInstance();
                    factoryAssignament(objController);
                    atrInjectorOfController.addFactory(objController);
                } 
            }  
            controllersAssignament();
        } 
        catch(Exception ex) 
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
        MapperOfProperties objMapperOfProperties = new MapperOfProperties(ATR_PROPERTIES_FILE);
        objMapperOfProperties.propertiesAssignament(ClientMain.class);
        
        repositoryFactoriesLoader();
        controllersLoader();
        
        GUILoginController objGUILoginController = (GUILoginController) atrInjectorOfController
                                                    .getFactory(GUILoginController.class);
        
        if(objGUILoginController != null) objGUILoginController.run();
    }  
}
