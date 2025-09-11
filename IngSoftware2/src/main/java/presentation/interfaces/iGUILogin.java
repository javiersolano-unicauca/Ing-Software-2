package presentation.interfaces;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Contrato para la vista de inicio de sesion
 * 
 * @author javiersolanop777
 */
public interface iGUILogin {
    
    JFrame getView();
    
    JTextField getFieldEmail();
    
    JPasswordField getFieldPassword();
    
    JButton getButtonRegister();
    
    JButton getButtonLogin();
    
    /**
     * Metodo para mostrar una ventana de mensaje
     * 
     * @param prmMessage El mensaje a mostrar
     * @param prmMessageType  El tipo de mensaje
     */
    void showMessage(String prmMessage, int prmMessageType);
}
