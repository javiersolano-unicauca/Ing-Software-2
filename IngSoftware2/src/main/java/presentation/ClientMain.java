package presentation;

import plugin.property_mapping.annotations.PropertiesScan;
import support.operation.dependency_injection.ControllersScan;
import support.operation.dependency_injection.RepositoriesScan;

/**
 * @author javiersolanop777
 */
@PropertiesScan(packagesNames = {
    "access.repositories.implement",
    "business.controllers.implement"
})
@RepositoriesScan(packageName = "access.repositories.implement")
@ControllersScan(packagesNames = {
    "business.controllers.implement",
    "presentation.controllers"
})
public class ClientMain {
    
    public static void main(String[] args) {
        
        Application objApplication = new Application();
        objApplication.run();
    }
}
