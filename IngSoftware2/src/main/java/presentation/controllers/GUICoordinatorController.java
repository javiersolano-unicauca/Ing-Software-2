/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation.controllers;

import access.models.RoleEnum;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iUserController;
import java.awt.EventQueue;
import presentation.GUICoordinator;
import presentation.interfaces.iGUICoordinator;
import presentation.interfaces.iGUICoordinatorController;
import presentation.interfaces.iGUILoginController;
import presentation.interfaces.iObserver;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;

/**
 *
 * @author laura
 */
@Controller
public class GUICoordinatorController extends Subject implements iObserver, iGUICoordinatorController {
    /**
     * Almacena la vista del modulo de coordinador
     */
    private final iGUICoordinator ATR_GUI_COORDINATOR;
    
    /**
     * Almacena la bandera para la carga de las acciones de eventos
     */
    private boolean atrLoadedActions;
    
    @ControllerAutowired
    private iUserController atrUserController;
    
    @ControllerAutowired
    private iGUILoginController atrGUILoginController;
    
    // Constructors
    
    public GUICoordinatorController()
    {
        ATR_GUI_COORDINATOR = new GUICoordinator();
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
        if(((UserModel) prmModel).getRole().getName().equals(RoleEnum.COORDINATOR.getName()))
        {
            this.run();
        }
    }

    @Override
    public void run() 
    {
        observersLoader();
        
        GUICoordinator objView = (GUICoordinator) this.ATR_GUI_COORDINATOR.getView();
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
    public void logout(GUICoordinator prmGUICoordinator) 
    {
        prmGUICoordinator.setVisible(false);
        this.notifyOnly(GUILoginController.class, null);
    }
}
