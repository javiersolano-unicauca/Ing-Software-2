package presentation.interfaces;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Contrato para la vista del modulo de estudiante
 * 
 * @author javiersolanop777
 */
public interface iGUIStudent {
    
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
