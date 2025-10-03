/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.facade;

import access.models.implement.ProjectModel;
/**
 *
 * @author yezid
 */

/**
 * Subsistema para gestion de documentos
 */
public class DocumentManagementService {
    
    public boolean validateProjectDocuments(ProjectModel project) {
        System.out.println("Validando documentos del proyecto: " + project.getTitle());
        
        boolean formatAValid = validateFormatA(project);
        boolean proposalValid = validateProposalDocument(project);
        boolean ethicalValid = validateEthicalConsiderations(project);
        
        boolean allValid = formatAValid && proposalValid && ethicalValid;
        
        System.out.println("Validacion documentos: " + (allValid ? "COMPLETADA" : "INCOMPLETA"));
        return allValid;
    }
    
    private boolean validateFormatA(ProjectModel project) {
        // Simular validacion del Formato A
        boolean valid = project.getFileName() != null && 
                       project.getFileName().toLowerCase().endsWith(".pdf");
        System.out.println("Formato A: " + (valid ? "VALIDO" : "INVALIDO"));
        return valid;
    }
    
    private boolean validateProposalDocument(ProjectModel project) {
        // Simular validacion del documento de propuesta
        boolean hasTitle = project.getTitle() != null && !project.getTitle().isEmpty();
        boolean hasObjectives = project.getGeneralObjective() != null && 
                               !project.getGeneralObjective().isEmpty();
        boolean valid = hasTitle && hasObjectives;
        System.out.println("Documento propuesta: " + (valid ? "VALIDO" : "INVALIDO"));
        return valid;
    }
    
    private boolean validateEthicalConsiderations(ProjectModel project) {
        // Simular validacion de consideraciones eticas
        boolean valid = Math.random() > 0.2; // 80% de proyectos tienen consideraciones eticas validas
        System.out.println("Consideraciones eticas: " + (valid ? "VALIDAS" : "INVALIDAS"));
        return valid;
    }
    
    public String generateDocumentChecklist(ProjectModel project) {
        return "CHECKLIST DOCUMENTOS - " + project.getTitle() + "\n" +
               "1. Formato A: " + (validateFormatA(project) ? "PRESENTE" : "FALTANTE") + "\n" +
               "2. Propuesta: " + (validateProposalDocument(project) ? "COMPLETA" : "INCOMPLETA") + "\n" +
               "3. Etica: " + (validateEthicalConsiderations(project) ? "APROBADA" : "PENDIENTE") + "\n" +
               "Estado general: " + (validateProjectDocuments(project) ? "COMPLETO" : "INCOMPLETO");
    }
    
    public void archiveProjectDocuments(ProjectModel project) {
        System.out.println("Archivando documentos del proyecto: " + project.getTitle());
        System.out.println("Documentos archivados en: /archive/projects/" + 
                         project.getFileName().replace(".pdf", "") + "/");
    }
}
