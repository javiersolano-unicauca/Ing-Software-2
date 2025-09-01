package presentation;

import access.models.implement.UserModel;
import access.repositories.implement.Factory;
import business.controllers.implement.UserController;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import support.operation.dependency_injection.ControllerScanner;
import support.operation.dependency_injection.FactoryAutowired;
import support.operation.dependency_injection.PropertyMapping;
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
    
    private void repositoryFactoriesLoader()
    {
        try
        {
            RepositoryScanner objRepositoryScanner = new RepositoryScanner();
            Set<Class<?>> listClasses = objRepositoryScanner.getRepsoritoryFactoriesClasses();
            
            for(Class<?> objClass: listClasses)
                atrRespositoryFactories.add(objClass.getDeclaredConstructor().newInstance());
        } 
        catch(Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    private void controllersLoader()
    {
        try
        {
            ControllerScanner objControllerScanner = new ControllerScanner();
            Set<Class<?>> listClasses = objControllerScanner.getControllersClasses();

            for(Class<?> objClass: listClasses)
            {
                Object objController = objClass.getDeclaredConstructor().newInstance();
                factoryAssignament(objController);
                atrControllers.add(objController);
            }   
        } 
        catch(Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
        repositoryFactoriesLoader();
        controllersLoader();
        UserController objUserController = (UserController) atrControllers.get(0);
        
        UserModel objUserModel = objUserController.getUser("javiersolano@unicauca.edu.co");
        
        if(objUserModel != null)
            System.out.println(objUserModel.getSurnames());
        else
            System.out.println("No existe");
    }  
}
