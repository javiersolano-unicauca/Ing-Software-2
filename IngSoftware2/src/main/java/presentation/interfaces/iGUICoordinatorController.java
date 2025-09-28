/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentation.interfaces;

import presentation.GUICoordinator;

/**
 *
 * @author laura
 */
public interface iGUICoordinatorController {
    void run();
    
    /**
     * Metodo para la definicion de la accion de cerrar sesion
     * 
     * @param prmGUICoordinator Recibe la referencia de la vista
     */
    void logout(GUICoordinator prmGUICoordinator);
}
