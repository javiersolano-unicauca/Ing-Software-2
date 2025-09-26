package business.controllers.implement;

import access.models.implement.ProjectModel;
import business.controllers.interfaces.iProjectController;
import business.controllers.interfaces.iStudentController;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;

/**
 * Clase controladora para los estudiantes
 * 
 * @author javiersolanop777
 */
@Controller
public class StudentController implements iStudentController {
    
    @ControllerAutowired
    private iProjectController atrProjectController;
    
    // Constructors:
    
    public StudentController()
    {}

    @Override
    public ProjectModel getProject(String prmEmail) 
    {
        return atrProjectController.getProjectOfStudent1OrStudent2(prmEmail);
    }
}
