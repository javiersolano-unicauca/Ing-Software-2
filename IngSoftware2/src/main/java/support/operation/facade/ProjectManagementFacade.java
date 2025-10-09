/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.facade;

import access.models.implement.ProjectModel;
import access.models.StatusEnum;
/**
 *
 * @author yezid
 */

/**
 * Fachada que simplifica la interaccion con los subsistemas de gestion de proyectos
 * Facade en el patron Facade
 */
public class ProjectManagementFacade {
    
    private ProjectEvaluationService evaluationService;
    private EvaluatorAssignmentService assignmentService;
    private DocumentManagementService documentService;
    private NotificationService notificationService;
    
    public ProjectManagementFacade() {
        this.evaluationService = new ProjectEvaluationService();
        this.assignmentService = new EvaluatorAssignmentService();
        this.documentService = new DocumentManagementService();
        this.notificationService = new NotificationService();
    }
    
    /**
     * Metodo principal que orquesta todo el proceso de revision de proyectos
     */
    public boolean processProjectSubmission(ProjectModel project) {
        System.out.println("=========================================");
        System.out.println("PROCESANDO SUBMISION DE PROYECTO: " + project.getTitle());
        System.out.println("=========================================");
        
        // 1. Validar documentos
        System.out.println("\n--- FASE 1: VALIDACION DOCUMENTAL ---");
        boolean documentsValid = documentService.validateProjectDocuments(project);
        if (!documentsValid) {
            System.out.println("ERROR: Documentos incompletos o invalidos");
            notificationService.notifyProjectRejection(project, "Documentacion incompleta");
            return false;
        }
        
        // 2. Evaluar proyecto
        System.out.println("\n--- FASE 2: EVALUACION ACADEMICA ---");
        boolean evaluationPassed = evaluationService.evaluateProject(project);
        if (!evaluationPassed) {
            System.out.println("ERROR: Proyecto no cumple criterios academicos");
            notificationService.notifyProjectRejection(project, "No cumple criterios academicos");
            return false;
        }
        
        // 3. Asignar evaluadores
        System.out.println("\n--- FASE 3: ASIGNACION DE EVALUADORES ---");
        boolean evaluatorsAssigned = assignmentService.assignEvaluators(project);
        if (!evaluatorsAssigned) {
            System.out.println("ERROR: No se pudieron asignar evaluadores");
            notificationService.notifyProjectRejection(project, "No hay evaluadores disponibles");
            return false;
        }
        
        // 4. Notificar aprobacion
        System.out.println("\n--- FASE 4: NOTIFICACION ---");
        notificationService.notifyProjectApproval(project);
        notificationService.notifyEvaluatorsAssignment(project);
        
        // 5. Actualizar estado del proyecto
        project.setStatus(StatusEnum.EN_EVALUACION_COMITE);
        
        System.out.println("\n PROCESO COMPLETADO EXITOSAMENTE");
        return true;
    }
    
    /**
     * Metodo simplificado para revision rapida
     */
    public boolean quickProjectReview(ProjectModel project) {
        System.out.println("REVISION RAPIDA DE PROYECTO: " + project.getTitle());
        
        boolean documentsValid = documentService.validateProjectDocuments(project);
        boolean evaluationPassed = evaluationService.evaluateProject(project);
        
        return documentsValid && evaluationPassed;
    }
    
    /**
     * Metodo para generar reporte completo
     */
    public void generateProjectReport(ProjectModel project) {
        System.out.println("=========================================");
        System.out.println("REPORTE COMPLETO DE PROYECTO");
        System.out.println("=========================================");
        
        System.out.println("\n" + evaluationService.generateEvaluationReport(project));
        System.out.println("\n" + documentService.generateDocumentChecklist(project));
        
        System.out.println("\nEVALUADORES DISPONIBLES: " + 
                         assignmentService.getAvailableEvaluatorsCount());
    }
    
    /**
     * Metodo para archivar proyecto completado
     */
    public void archiveCompletedProject(ProjectModel project) {
        System.out.println("ARCHIVANDO PROYECTO COMPLETADO: " + project.getTitle());
        documentService.archiveProjectDocuments(project);
        System.out.println("Proyecto archivado exitosamente");
    }
    
    /**
     * Metodo para gestion de evaluadores
     */
    public void manageEvaluators() {
        System.out.println("GESTION DE EVALUADORES");
        System.out.println("Evaluadores disponibles: " + 
                         assignmentService.getAvailableEvaluatorsCount());
        
        for (String evaluator : assignmentService.getAvailableEvaluators()) {
            System.out.println(" - " + evaluator);
        }
    }
    
    // Getters para acceso directo a subsistemas (si es necesario)
    public ProjectEvaluationService getEvaluationService() {
        return evaluationService;
    }
    
    public EvaluatorAssignmentService getAssignmentService() {
        return assignmentService;
    }
    
    public DocumentManagementService getDocumentService() {
        return documentService;
    }
    
    public NotificationService getNotificationService() {
        return notificationService;
    }
}
