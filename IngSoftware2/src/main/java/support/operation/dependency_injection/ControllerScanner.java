package support.operation.dependency_injection;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import plugin.dependency_injection.implement.ScannerPackage;
import plugin.dependency_injection.interfaces.iScanner;
import presentation.ClientMain;

/**
 * Clase para escanear las clases controladoras existentes
 * en un paquete
 * 
 * @author javiersolanop777
 */
public final class ControllerScanner {
    
    /**
     * Almacena el escaneador de clases en paquetes
     */
    private final iScanner ATR_SCANNER;
    
    // Constructor:
    
    public ControllerScanner()
    {
        ATR_SCANNER = new ScannerPackage();
    }
    
    /**
     * Metodo para obtener las clases controladoras que se encuentren 
 en el paquete establecido por la anotacion 'ControllersScan' 
     * 
     * @see ControllersScan
     * 
     * @return La lista de clases si las hay. De lo contrario null
     */
    public List<Set<Class<?>>> getControllersClasses()
    {
        if(ClientMain.class.isAnnotationPresent(ControllersScan.class))
        {
            String[] arrPackagesNames = ClientMain.class.getAnnotation(ControllersScan.class)
                                                    .packagesNames();
            
            List<Set<Class<?>>> listPackages = new LinkedList<>();
            
            for(String objPackageName: arrPackagesNames)
            {
                listPackages.add(ATR_SCANNER.getClassesByAnnotation(
                objPackageName, 
                Controller.class
                ));
            }
            return listPackages;
        }
        return null;
    }
}
