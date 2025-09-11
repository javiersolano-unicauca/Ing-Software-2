package presentation.controllers;

import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iUserController;
import java.awt.EventQueue;
import presentation.GUITeacher;
import presentation.interfaces.iObserver;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;

/**
 * Controlador de la GUI del modulo de profesores
 * @see GUITeacher
 * 
 * @author javiersolanop777
 */
@Controller
public class GUITeacherController implements iObserver {
    
    /**
     * Almacena la vista del modulo de profesores
     */
    private final GUITeacher atrGUITeacher;
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    // Constructors
    
    public GUITeacherController()
    {
        atrGUITeacher = new GUITeacher();
    }

    @Override
    public void validateNotification(Subject prmSubject, iModel prmModel) 
    {
        if(((UserModel) prmModel).getRole().getName().equals(RoleEnum.TEACHER.getName()))
        {
            EventQueue.invokeLater(() -> atrGUITeacher.setVisible(true));
        }
    }
}
