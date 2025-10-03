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
 * Subsistema para notificaciones
 */
public class NotificationService {
    
    public void notifyProjectApproval(ProjectModel project) {
        System.out.println("ENVIANDO NOTIFICACIONES - Proyecto Aprobado");
        System.out.println("Para: " + project.getDirector());
        System.out.println("CC: " + project.getStudent1() + 
                         (project.getStudent2() != null ? ", " + project.getStudent2() : ""));
        System.out.println("Asunto: Proyecto '" + project.getTitle() + "' ha sido APROBADO");
        System.out.println("Mensaje: Su proyecto ha pasado todas las evaluaciones satisfactoriamente.");
    }
    
    public void notifyProjectRejection(ProjectModel project, String reason) {
        System.out.println("ENVIANDO NOTIFICACIONES - Proyecto Rechazado");
        System.out.println("Para: " + project.getDirector());
        System.out.println("CC: " + project.getStudent1());
        System.out.println("Asunto: Proyecto '" + project.getTitle() + "' requiere revision");
        System.out.println("Motivo: " + reason);
    }
    
    public void notifyEvaluatorsAssignment(ProjectModel project) {
        System.out.println("ENVIANDO NOTIFICACIONES - Asignacion de Evaluadores");
        System.out.println("Para: Evaluadores asignados");
        System.out.println("Asunto: Nuevo proyecto para evaluar: '" + project.getTitle() + "'");
        System.out.println("Plazo: 15 dias para entrega de evaluacion");
    }
    
    public void sendReminder(String recipient, String message) {
        System.out.println("RECORDATORIO para: " + recipient);
        System.out.println("Mensaje: " + message);
    }
}
