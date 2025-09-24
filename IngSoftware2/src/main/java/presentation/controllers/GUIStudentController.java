package presentation.controllers;

import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iUserController;
import java.awt.EventQueue;
import presentation.GUIStudent;
import presentation.interfaces.iGUILoginController;
import presentation.interfaces.iGUIStudent;
import presentation.interfaces.iGUIStudentController;
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
public class GUIStudentController extends Subject implements iObserver, iGUIStudentController {
    
    /**
     * Almacena la vista del modulo de profesores
     */
    private final iGUIStudent ATR_GUI_STUDENT;
    
    /**
     * Almacena la bandera para la carga de las acciones de eventos
     */
    private boolean atrLoadedActions;
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    @ControllerAutowired
    private iGUILoginController atrGUILoginController;
    
    // Constructors
    
    public GUIStudentController()
    {
        ATR_GUI_STUDENT = new GUIStudent();
    }
    
    @Override
    public void observersLoader()
    {
        if(!this.noneObserver()) return;
        
        this.addObserver((iObserver) atrGUILoginController);
    }

    @Override
    public void validateNotification(Subject prmSubject, iModel prmModel) 
    {
        if(((UserModel) prmModel).getRole().getName().equals(RoleEnum.STUDENT.getName()))
        {
            this.run();
        }
    }

    @Override
    public void run() 
    {
        observersLoader();
        
        GUIStudent objView = (GUIStudent) this.ATR_GUI_STUDENT.getView();
        objView.setVisible(true);
        
        if(!atrLoadedActions)
        {
            EventQueue.invokeLater(() -> {

                objView.getButtonBackLogin().addActionListener(event -> this.logout(objView));
            });
            atrLoadedActions = true;
        }
    }

    @Override
    public void logout(GUIStudent prmGUIStudent) 
    {
        prmGUIStudent.setVisible(false);
        this.notifyOnly(GUILoginController.class, null);
    }
}
