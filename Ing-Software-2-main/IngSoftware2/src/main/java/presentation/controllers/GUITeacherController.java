package presentation.controllers;

import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iUserController;
import java.awt.EventQueue;
import presentation.GUITeacher;
import presentation.interfaces.iGUILoginController;
import presentation.interfaces.iGUITeacher;
import presentation.interfaces.iGUITeacherController;
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
public class GUITeacherController extends Subject implements iObserver, iGUITeacherController {
    
    /**
     * Almacena la vista del modulo de profesores
     */
    private final iGUITeacher ATR_GUI_TEACHER;
    
    /**
     * Almacena la bandera para la carga de las acciones de eventos
     */
    private boolean atrLoadedActions;
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    @ControllerAutowired
    private iGUILoginController atrGUILoginController;
    
    // Constructors
    
    public GUITeacherController()
    {
        ATR_GUI_TEACHER = new GUITeacher();
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
        if(((UserModel) prmModel).getRole().getName().equals(RoleEnum.TEACHER.getName()))
        {
            this.run();
        }
    }

    @Override
    public void run() 
    {
        observersLoader();
        
        GUITeacher objView = (GUITeacher) this.ATR_GUI_TEACHER.getView();
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
    public void logout(GUITeacher prmGUITeacher) 
    {
        prmGUITeacher.setVisible(false);
        this.notifyOnly(GUILoginController.class, null);
    }
}
