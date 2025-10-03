/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.decorator;

import access.models.implement.ProjectModel;
/**
 *
 * @author yezid
 */

/**
 * Decorador concreto que añade funcionalidad de prioridad alta
 */
public class PriorityProject extends ProjectDecorator {
    
    private String priorityReason;
    
    public PriorityProject(ProjectModel project) {
        super(project);
        this.priorityReason = "Prioridad asignada por coordinacion";
    }
    
    public PriorityProject(ProjectModel project, String reason) {
        super(project);
        this.priorityReason = reason;
    }
    
    @Override
    public String getTitle() {
        return "[ALTA PRIORIDAD] " + decoratedProject.getTitle();
    }
    
    @Override
    public String getObservations() {
        String originalObservations = decoratedProject.getObservations();
        String priorityNote = " --- PROYECTO PRIORITARIO: " + priorityReason + " ---";
        
        if (originalObservations == null || originalObservations.isEmpty()) {
            return priorityNote;
        } else {
            return originalObservations + priorityNote;
        }
    }
    
    @Override
    public String getDecoratedDescription() {
        return "Proyecto de alta prioridad: " + decoratedProject.getTitle() + 
               " - Razon: " + priorityReason;
    }
    
    // Metodos adicionales especificos del decorador
    public String getPriorityReason() {
        return priorityReason;
    }
    
    public void setPriorityReason(String reason) {
        this.priorityReason = reason;
    }
    
    public boolean isUrgent() {
        return true;
    }
    
    @Override
    public String toString() {
        String originalString = decoratedProject.toString();
        return originalString.replace(
            "ProjectModel", 
            "ProjectModel [ALTA PRIORIDAD - " + priorityReason + "]"
        );
    }
}
