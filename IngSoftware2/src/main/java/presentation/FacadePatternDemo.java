/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import support.operation.facade.ProjectManagementFacade;
import java.time.LocalDate;
/**
 *
 * @author yezid
 */

/**
 * Clase para probar el patron Facade
 */
public class FacadePatternDemo {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA PATRON FACADE ===");
        System.out.println();
        
        // Crear proyecto de prueba
        ProjectModel project = new ProjectModel(
            "Sistema de Gestion Academica Integral",
            LocalDate.now(),
            "director.sistema@unicauca.edu.co",
            "co.director@unicauca.edu.co",
            "estudiante.principal@unicauca.edu.co",
            "estudiante.secundario@unicauca.edu.co",
            ModalityEnum.PROFESSIONAL,
            "Desarrollar una plataforma web integral para la gestion de procesos academicos",
            "1. Modulo de registro estudiantil, 2. Modulo de calificaciones, 3. Modulo de reportes, 4. Modulo de notificaciones",
            StatusEnum.FORMATO_A_DILIGENCIADO,
            0,
            "Proyecto en colaboracion con el departamento de sistemas",
            "sistema_gestion_academica.pdf"
        );
        
        // Crear la fachada
        ProjectManagementFacade projectFacade = new ProjectManagementFacade();
        
        // Probar el proceso completo simplificado
        System.out.println("1. PROCESO COMPLETO DE SUBMISION:");
        boolean success = projectFacade.processProjectSubmission(project);
        System.out.println("Resultado final: " + (success ? "EXITOSO" : "FALLIDO"));
        System.out.println();
        
        // Probar revision rapida
        System.out.println("2. REVISION RAPIDA:");
        boolean quickReview = projectFacade.quickProjectReview(project);
        System.out.println("Revision rapida: " + (quickReview ? "APROBADA" : "RECHAZADA"));
        System.out.println();
        
        // Generar reporte completo
        System.out.println("3. REPORTE COMPLETO:");
        projectFacade.generateProjectReport(project);
        System.out.println();
        
        // Gestion de evaluadores
        System.out.println("4. GESTION DE EVALUADORES:");
        projectFacade.manageEvaluators();
        System.out.println();
        
        // Probar archivo de proyecto
        System.out.println("5. ARCHIVADO DE PROYECTO:");
        projectFacade.archiveCompletedProject(project);
        System.out.println();
        
        // Demostrar uso individual de subsistemas (si es necesario)
        System.out.println("6. USO INDIVIDUAL DE SUBSISTEMAS:");
        String report = projectFacade.getEvaluationService().generateEvaluationReport(project);
        System.out.println("Reporte de evaluacion individual:");
        System.out.println(report);
    }
}
