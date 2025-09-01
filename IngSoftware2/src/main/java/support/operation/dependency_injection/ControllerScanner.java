package support.operation.dependency_injection;

import java.util.Set;
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
    public Set<Class<?>> getControllersClasses()
    {
        if(ClientMain.class.isAnnotationPresent(ControllersScan.class))
        {
            String varPackageName = ClientMain.class.getAnnotation(ControllersScan.class)
                                                    .packageName();
            
            return ATR_SCANNER.getClassesByAnnotation(
                                                    varPackageName, 
                                                    Controller.class
                                                    );
        }
        return null;
    }
}
