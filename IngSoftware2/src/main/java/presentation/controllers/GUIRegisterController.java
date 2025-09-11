package presentation.controllers;

import access.models.CareerEnum;
import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iUserController;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import presentation.GUIRegister;
import presentation.interfaces.iGUILoginController;
import presentation.interfaces.iGUIRegisterController;
import presentation.interfaces.iObserver;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;
import support.operation.model_exceptions.ModelException;

/**
 * Controlador de la GUI de registro de usuarios
 * @see GUIRegister
 * 
 * @author javiersolanop777
 */
@Controller
public class GUIRegisterController extends Subject implements iGUIRegisterController, iObserver {
    
    /**
     * Almacena la vista para el registro de usuarios
     */
    private final GUIRegister atrGUIRegister;
    
    /**
     * Almacena la bandera para la carga de las acciones de eventos
     */
    private boolean atrLoadedActions;
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    @ControllerAutowired
    private iGUILoginController atrGUILoginController;
    
    // Constructors:
    
    public GUIRegisterController()
    {
        atrGUIRegister = new GUIRegister();
        atrLoadedActions = false;
    }
    
    @Override
    public void observersLoader()
    {
        if(!this.noneObserver()) return;
        
        this.addObserver((iObserver) atrGUILoginController);
    }
    
    @Override
    public void run()
    {
        observersLoader();
        
        GUIRegister objView = (GUIRegister) this.atrGUIRegister.getView();
        objView.setVisible(true);
        
        if(!atrLoadedActions)
        {
            EventQueue.invokeLater(() -> {

                objView.getButtonRegister().addActionListener(event -> this.register(objView));
                objView.getButtonBackLogin().addActionListener(event -> this.backLogin(objView));
            });
            atrLoadedActions = true;
        }
    }
    
    @Override
    public void register(GUIRegister prmGUIRegister)
    {
        try
        {
            UserModel objUserModel = atrUserController.save(new UserModel(
                prmGUIRegister.getFieldName().getText(),
                prmGUIRegister.getFieldSurname().getText(),
                prmGUIRegister.getFieldEmail().getText(),
                new String(prmGUIRegister.getFieldPassword().getPassword()),
                Long.valueOf(prmGUIRegister.getFieldPhone().getText()),
                CareerEnum.getCareer(
                    prmGUIRegister.getFieldCareer().getSelectedItem().toString()
                ),
                RoleEnum.getRole(
                    prmGUIRegister.getFieldRole().getSelectedItem().toString()
                )
            ));

            if(objUserModel != null)
            {
                prmGUIRegister.showMessage(
                    "Usuario registrado con exito!", 
                    JOptionPane.DEFAULT_OPTION
                );
                prmGUIRegister.setVisible(false);
                this.notifyOnly(GUILoginController.class, objUserModel);
            }
            else
            {
                prmGUIRegister.showMessage(
                    "Ya existe ese usuario en el sistema", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        catch(ModelException ex)
        {
            prmGUIRegister.showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void backLogin(GUIRegister prmGUIRegister)
    {
        prmGUIRegister.setVisible(false);
        this.notifyObservers(null);
    }

    @Override
    public void validateNotification(Subject prmSubject, iModel prmModel) 
    {
        this.run();
    }
}
