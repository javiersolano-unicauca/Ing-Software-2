/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentation.interfaces;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author laura
 */
public interface iGUICoordinator {
    JFrame getView();
    
    JButton getButtonBackLogin();
    
    /**
     * Metodo para mostrar una ventana de mensaje
     * 
     * @param prmMessage El mensaje a mostrar
     * @param prmMessageType  El tipo de mensaje
     */
    void showMessage(String prmMessage, int prmMessageType);  
}
