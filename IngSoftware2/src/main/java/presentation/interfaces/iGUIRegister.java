package presentation.interfaces;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Contrato para la vista de registro de usuarios
 * 
 * @author javiersolanop777
 */
public interface iGUIRegister {
    
    JFrame getView();
    
    JTextField getFieldName();
    
    JTextField getFieldSurname();
    
    JTextField getFieldEmail();
    
    JPasswordField getFieldPassword();
    
    JTextField getFieldPhone();
    
    JComboBox<String> getFieldCareer();
    
    JComboBox<String> getFieldRole();
    
    JButton getButtonRegister();
    
    JButton getButtonBackLogin();
    
    /**
     * Metodo para mostrar una ventana de mensaje
     * 
     * @param prmMessage El mensaje a mostrar
     * @param prmMessageType  El tipo de mensaje
     */
    void showMessage(String prmMessage, int prmMessageType);
}
