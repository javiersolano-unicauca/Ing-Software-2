package presentation.controllers;

import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iSessionController;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import presentation.GUILogin;
import presentation.interfaces.iGUILogin;
import presentation.interfaces.iGUILoginController;
import presentation.interfaces.iGUIRegisterController;
import presentation.interfaces.iObserver;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;

/**
 * Controlador de la GUI de inicio de sesion
 * @see GUILogin
 * 
 * @author javiersolanop777
 */
@Controller
public class GUILoginController extends Subject implements iGUILoginController, iObserver {
    
    /**
     * Almacena la vista para el inicio de sesion
     */
    private final iGUILogin atrGUILogin;
    
    /**
     * Almacena la bandera para la carga de las acciones de eventos
     */
    private boolean atrLoadedActions;
    
    @ControllerAutowired
    private iSessionController atrSessionController;
    
    @ControllerAutowired
    private iGUIRegisterController atrGUIRegisterController;
    
    // Contructors:
    
    public GUILoginController()
    {
        this.atrGUILogin = new GUILogin();
    }
    
    @Override
    public void observersLoader()
    {
        if(!this.noneObserver()) return;
        
        this.addObserver((iObserver) atrGUIRegisterController);
        this.addObserver(new GUIStudentController());
        this.addObserver(new GUITeacherController());
    }
    
    @Override
    public void run()
    {   
        observersLoader();
        
        GUILogin objView = (GUILogin) this.atrGUILogin.getView();
        objView.setVisible(true);
        
        if(!atrLoadedActions)
        {
            EventQueue.invokeLater(() -> {

                objView.getButtonLogin().addActionListener(event -> this.login(objView));

                objView.getButtonRegister().addActionListener(event -> {
                    objView.setVisible(false);
                    this.notifyOnly(GUIRegisterController.class, null);
                });
            });
            atrLoadedActions = true;
        }
    }
    
    @Override
    public void login(GUILogin prmGUILogin)
    {        
        UserModel objUserModel = this.atrSessionController.login(
            prmGUILogin.getFieldEmail().getText(),
            new String(prmGUILogin.getFieldPassword().getPassword())
        );

        if(objUserModel != null)
        {
            prmGUILogin.setVisible(false);
            this.notifyObservers(objUserModel);
        }
        else
        {
            prmGUILogin.showMessage(
                "No existe ese usuario en el sistema", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void validateNotification(Subject prmSubject, iModel prmModel) 
    {
        this.run();
        GUILogin objView = (GUILogin) this.atrGUILogin.getView();
        
        if(prmModel != null)
        {
            UserModel objUserModel = (UserModel) prmModel;
            objView.getFieldEmail().setText(objUserModel.getEmail()); 
        }
    }
}
