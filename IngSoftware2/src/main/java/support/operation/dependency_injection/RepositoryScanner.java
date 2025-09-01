package support.operation.dependency_injection;

import java.util.Set;
import presentation.ClientMain;

/**
 * Clase para escanear las clases de repositorios existentes
 * en un paquete
 * 
 * @author javiersolanop777
 */
public final class RepositoryScanner {
    
    /**
     * Almacena el escaneador de clases en paquetes
     */
    private final iScanner ATR_SCANNER;
    
    // Constructor:
    
    public RepositoryScanner()
    {
        ATR_SCANNER = new ScannerPackage();
    }
    
    /**
     * Metodo para obtener las clases de repositorios que se encuentren 
     * en el paquete establecido por la anotacion 'ScanRespositories' 
     * 
     * @see ScanRespositories
     * 
     * @return La lista de clases si las hay. De lo contrario null
     */
    public Set<Class<?>> getRepsoritoryFactoriesClasses()
    {
        if(ClientMain.class.isAnnotationPresent(RepsositoriesScan.class))
        {
            String varPackageName = ClientMain.class.getAnnotation(RepsositoriesScan.class)
                                                    .packageName();
            
            return ATR_SCANNER.getClassesByAnnotation(
                                                    varPackageName, 
                                                    RepsositoryFactory.class
                                                    );
        }
        return null;
    }
}
