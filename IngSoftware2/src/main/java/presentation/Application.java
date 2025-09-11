package presentation;

import access.repositories.implement.Factory;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Almacena las instancias de fabricas de repositorios;
     */
    private final List<Object> atrRespositoryFactories;
    
    /**
     * Almacena las instancias de controladores
     */
    private final List<Object> atrControllers;
    
    // Constructor:
    
    public Application()
    {
        atrRespositoryFactories = new LinkedList<>();
        atrControllers = new LinkedList<>();
    }
    
    /**
     * Metodo para instanciar y cargar todos las fabricas de repositorios
     */
    private void repositoryFactoriesLoader()
    {
        try
        {
            RepositoryScanner objRepositoryScanner = new RepositoryScanner();
            Set<Class<?>> listClasses = objRepositoryScanner.getRepsoritoryFactoriesClasses();
            
            for(Class<?> objClass: listClasses)
                atrRespositoryFactories.add(objClass.getDeclaredConstructor().newInstance());
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
        Field arrFields[] = prmObject.getClass().getDeclaredFields();
        
        for(Field objField: arrFields)
        {
            objField.setAccessible(true);
            
            if(objField.isAnnotationPresent(FactoryAutowired.class))
            {
                for(Object objFactory: atrRespositoryFactories)
                {
                    if(objField.getType().isAssignableFrom(objFactory.getClass()))
                    {
                        objField.set(prmObject, (Object) ((Factory<?>) objFactory).getRespositoryFactory());
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Metodo para asignar controladores que son requeridos por otros
     */
    private void controllersAssignament()
    {
        try
        {
            Field arrFields[];
            
            for(Object objController1: atrControllers)
            {
                arrFields = objController1.getClass().getDeclaredFields();
                
                for(Field objField: arrFields)
                {
                    objField.setAccessible(true);
                    
                    if(objField.isAnnotationPresent(ControllerAutowired.class))
                    {
                        for(Object objController2: atrControllers)
                        {
                            if(objField.getType().isAssignableFrom(objController2.getClass()))
                            {  
                                objField.set(objController1, objController2);
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch(Exception ex) 
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                    atrControllers.add(objController);
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
        repositoryFactoriesLoader();
        controllersLoader();
        
        GUILoginController objGUILoginController = null;
        
        for(Object objController: atrControllers)
        {
            if(objController.getClass().equals(GUILoginController.class))
            {
                objGUILoginController = (GUILoginController) objController;
                break;
            }
        }
        
        if(objGUILoginController != null) objGUILoginController.run();
    }  
}
