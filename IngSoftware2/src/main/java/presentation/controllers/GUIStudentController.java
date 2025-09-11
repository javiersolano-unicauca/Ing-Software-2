package presentation.controllers;

import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iUserController;
import java.awt.EventQueue;
import presentation.GUIStudent;
import presentation.interfaces.iObserver;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;

/**
 * Controlador de la GUI del modulo de estudiantes
 * @see GUIStudent
 * 
 * @author javiersolanop777
 */
@Controller
public class GUIStudentController implements iObserver {
    
    /**
     * Almacena la vista del modulo de estudiantes
     */
    private final GUIStudent atrGUIStudent;
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    // Constructors
    
    public GUIStudentController()
    {
        atrGUIStudent = new GUIStudent();
    }

    @Override
    public void validateNotification(Subject prmSubject, iModel prmModel) 
    {
        if(((UserModel) prmModel).getRole().getName().equals(RoleEnum.ESTUDENT.getName()))
        {
            EventQueue.invokeLater(() -> atrGUIStudent.setVisible(true));
        }
    }
}
