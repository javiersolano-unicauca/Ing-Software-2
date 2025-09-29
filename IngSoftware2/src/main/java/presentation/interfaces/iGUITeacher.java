package presentation.interfaces;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Contrato para la vista del modulo de profesor
 * 
 * @author javiersolanop777
 */
public interface iGUITeacher {
    
    JFrame getView();
    
    JTextField getFieldProjectTitle();
    
    JTextField getFieldDirector();
    
    JTextField getFieldCodirector();
    
    JTextField getFieldStudent1();
    
    JTextField getFieldStudent2();
    
    JComboBox<String> getFieldModality();
    
    JTextArea getFieldGeneralObjective();
    
    JTextArea getFieldSpecificObjectives();
    
    JButton getButtonSave();
    
    JButton getButtonFormat();
    
    JButton getButtonLetter();
    
    JButton getButtonBackLogin();
    
    /**
     * Metodo para mostrar una ventana de mensaje
     * 
     * @param prmMessage El mensaje a mostrar
     * @param prmMessageType  El tipo de mensaje
     */
    void showMessage(String prmMessage, int prmMessageType);  
}
