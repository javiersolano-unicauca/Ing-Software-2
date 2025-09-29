package presentation.controllers;

import access.models.ModalityEnum;
import access.models.RoleEnum;
import access.models.implement.ProjectModel;
import access.models.implement.UserModel;
import access.models.interfaces.iModel;
import business.controllers.interfaces.iTeacherController;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import presentation.GUITeacher;
import presentation.interfaces.iGUILoginController;
import presentation.interfaces.iGUITeacher;
import presentation.interfaces.iGUITeacherController;
import presentation.interfaces.iObserver;
import support.operation.builders.ProffesionalProjectBuilder;
import support.operation.builders.ResearchProjectBuilder;
import support.operation.dependency_injection.Controller;
import support.operation.dependency_injection.ControllerAutowired;
import support.operation.model_exceptions.ModelException;

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
    private iGUILoginController atrGUILoginController;
    
    @ControllerAutowired
    private iTeacherController atrTeacherController;  
    
    /**
     * Almacena el formato del profesor
     */
    private File atrFormat;
    
    /**
     * Almacena la carta de aceptacion del profesor
     */
    private File atrLetter;
    
    // Constructors
    
    public GUITeacherController()
    {
        ATR_GUI_TEACHER = new GUITeacher();
        atrFormat = null;
        atrLetter = null;
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
        UserModel objUserModel = (UserModel) prmModel;
        
        if(objUserModel.getRole().getName().equals(RoleEnum.TEACHER.getName()))
        {
            ((GUITeacher) this.ATR_GUI_TEACHER.getView())
            .getFieldDirector()
            .setText(objUserModel.getEmail());
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

                objView.getFieldModality().addActionListener(event -> this.validateModality(objView));
                objView.getButtonFormat().addActionListener(event -> {
                    atrFormat = requestFile(objView);
                });
                objView.getButtonLetter().addActionListener(event -> {
                    atrLetter = requestFile(objView);
                });
                objView.getButtonBackLogin().addActionListener(event -> this.logout(objView));
                objView.getButtonSave().addActionListener(event -> this.saveProject(objView));
            });
            atrLoadedActions = true;
        }
    }
    
    /**
     * Metodo para validar la modalidad de proyecto y 
     * habilitar o desahibitar campos segun corresponda
     * 
     * @param prmGUITeacher Recibe referencia de la vista
     */
    private void validateModality(GUITeacher prmGUITeacher)
    {
        String varModality = prmGUITeacher.getFieldModality().getSelectedItem().toString();
        
        if(ModalityEnum.RESEARCH.getName().equals(varModality))
        {
            prmGUITeacher.getButtonLetter().setEnabled(false);
            prmGUITeacher.getFieldCodirector().setEnabled(true);
            prmGUITeacher.getFieldStudent2().setEnabled(true);
        }
        else if(ModalityEnum.PROFESSIONAL.getName().equals(varModality))
        {
            prmGUITeacher.getFieldCodirector().setEnabled(false);
            prmGUITeacher.getFieldStudent2().setEnabled(false);
            prmGUITeacher.getButtonLetter().setEnabled(true);
        }
    }
    
    /**
     * Metodo para recibir un archivo desde GUI
     * 
     * @param prmGUITeacher Recibe referencia de la vista
     * 
     * @return El archivo recibido. De lo contrario null
     */
    private File requestFile(GUITeacher prmGUITeacher)
    {
        JFileChooser objFileChooser = new JFileChooser();
        FileNameExtensionFilter objFilter = new FileNameExtensionFilter(
            "Archivos PDF", 
            "pdf"
        );
        objFileChooser.addChoosableFileFilter(objFilter);
        objFileChooser.setFileFilter(objFilter);
        int varOption = objFileChooser.showOpenDialog(prmGUITeacher);
        
        if(varOption == JFileChooser.APPROVE_OPTION)
            return objFileChooser.getSelectedFile();
        return null;
    }
    
    /**
     *  Metodo para guardar un proyecto de investigacion
     * 
     *  @param prmGUITeacher Recibe referencia de la vista
     * 
     *  @return El proyecto guardado. De lo contrario null
     * 
     *  @throws ModelException Si los campos no son validos
     */
    private ProjectModel saveResearchProject(GUITeacher prmGUITeacher) throws ModelException
    {        
        System.out.println(prmGUITeacher.getFieldStudent2().getText());
        return atrTeacherController.saveProject(
            new ResearchProjectBuilder(
                prmGUITeacher.getFieldProjectTitle().getText(), 
                prmGUITeacher.getFieldDirector().getText(), 
                prmGUITeacher.getFieldCodirector().getText(), 
                prmGUITeacher.getFieldStudent1().getText(), 
                prmGUITeacher.getFieldStudent2().getText(), 
                prmGUITeacher.getFieldGeneralObjective().getText(), 
                prmGUITeacher.getFieldSpecificObjectives().getText(), 
                atrFormat.getName()
            ), 
            atrFormat
        );
    }
    
    /**
     *  Metodo para guardar un proyecto de practica profesional
     * 
     *  @param prmGUITeacher Recibe referencia de la vista
     * 
     *  @return El proyecto guardado. De lo contrario null
     * 
     *  @throws ModelException Si los campos no son validos
     */
    private ProjectModel saveProffesionalProject(GUITeacher prmGUITeacher) throws ModelException
    {
        return atrTeacherController.saveProject(
            new ProffesionalProjectBuilder (
                prmGUITeacher.getFieldProjectTitle().getText(), 
                prmGUITeacher.getFieldDirector().getText(), 
                prmGUITeacher.getFieldStudent1().getText(), 
                prmGUITeacher.getFieldGeneralObjective().getText(), 
                prmGUITeacher.getFieldSpecificObjectives().getText(), 
                atrFormat.getName()
            ), 
            atrFormat,
            atrLetter
        );
    }
    
    @Override
    public void saveProject(GUITeacher prmGUITeacher) 
    {
        try
        {
            String varModality = prmGUITeacher.getFieldModality().getSelectedItem().toString();
            ProjectModel objProjectModel = null;
            
            if(ModalityEnum.RESEARCH.getName().equals(varModality))
                objProjectModel = saveResearchProject(prmGUITeacher);
            
            else if(ModalityEnum.PROFESSIONAL.getName().equals(varModality))
                objProjectModel = saveProffesionalProject(prmGUITeacher);
            
            if(objProjectModel != null)
            {
                prmGUITeacher.showMessage(
                    "Proyecto registrado con exito!", 
                    JOptionPane.DEFAULT_OPTION
                );
                this.notifyObserversExcept(GUILoginController.class, objProjectModel);
            }
            else
            {
                prmGUITeacher.showMessage(
                    "Ya existe un proyecto vigente en el sistema", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        catch(ModelException ex)
        {
            prmGUITeacher.showMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void logout(GUITeacher prmGUITeacher) 
    {
        prmGUITeacher.setVisible(false);
        this.notifyOnly(GUILoginController.class, null);
    }
}
