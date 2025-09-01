package presentation;

import support.operation.dependency_injection.ControllersScan;
import support.operation.dependency_injection.RepsositoriesScan;

/**
 * @author javiersolanop777
 */
@RepsositoriesScan(packageName = "access.repositories.implement")
@ControllersScan(packageName = "business.controllers.implement")
public class ClientMain {
    
    public static void main(String[] args) {
        
        Application objApplication = new Application();
        objApplication.run();
    }
}
