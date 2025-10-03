/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.template_method;

import access.models.implement.ProjectModel;
/**
 *
 * @author yezid
 */

/**
 * Clase template que define el flujo común de evaluación de proyectos
 * Patrón Template Method
 */
public abstract class ProjectEvaluator {
    
    /**
     * Método template final que define el esqueleto del algoritmo de evaluación
     * No puede ser sobrescrito por las subclases
     */
    public final void evaluate(ProjectModel project) {
        System.out.println("INICIANDO EVALUACION FORMAL DEL PROYECTO: " + project.getTitle());
        System.out.println("================================================");
        
        // Flujo fijo de evaluación - no puede ser modificado por subclases
        reviewProblem(project);
        reviewGoals(project);
        reviewScope(project);
        reviewMethodology(project);
        reviewResources(project);
        reviewTimeline(project);
        makeDecision(project);
        
        System.out.println("================================================");
        System.out.println("✅ EVALUACION COMPLETADA");
    }
    
    // Métodos abstractos que deben ser implementados por las subclases
    protected abstract void reviewProblem(ProjectModel project);
    protected abstract void reviewGoals(ProjectModel project);
    protected abstract void reviewScope(ProjectModel project);
    protected abstract void reviewMethodology(ProjectModel project);
    protected abstract void reviewResources(ProjectModel project);
    protected abstract void reviewTimeline(ProjectModel project);
    protected abstract void makeDecision(ProjectModel project);
    
    // Métodos hook (opcionales) que las subclases pueden sobrescribir
    protected void preEvaluationHook(ProjectModel project) {
        // Por defecto no hace nada, las subclases pueden extender
    }
    
    protected void postEvaluationHook(ProjectModel project) {
        // Por defecto no hace nada, las subclases pueden extender
    }
    
    // Métodos concretos comunes a todas las evaluaciones
    protected final void logEvaluationStep(String stepName) {
        System.out.println("[" + stepName + "]");
    }
    
    protected final void validateBasicRequirements(ProjectModel project) {
        System.out.println("   Validando requisitos basicos...");
        if (project.getTitle() == null || project.getTitle().isEmpty()) {
            throw new IllegalArgumentException("El proyecto debe tener un titulo");
        }
        if (project.getGeneralObjective() == null || project.getGeneralObjective().isEmpty()) {
            throw new IllegalArgumentException("El proyecto debe tener un objetivo general");
        }
        System.out.println("   Requisitos basicos validados correctamente");
    }
}