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
 * Subsistema para evaluacion de proyectos
 */
public class ProjectEvaluationService {
    
    public boolean evaluateProject(ProjectModel project) {
        System.out.println("Iniciando evaluacion del proyecto: " + project.getTitle());
        
        // Simular criterios de evaluacion
        boolean titleValid = evaluateTitle(project.getTitle());
        boolean objectivesValid = evaluateObjectives(project.getGeneralObjective(), 
                                                   project.getSpecificObjectives());
        boolean methodologyValid = evaluateMethodology(project.getModality());
        boolean scopeValid = evaluateScope(project);
        
        boolean approved = titleValid && objectivesValid && methodologyValid && scopeValid;
        
        System.out.println("Resultado evaluacion: " + (approved ? "APROBADO" : "RECHAZADO"));
        return approved;
    }
    
    private boolean evaluateTitle(String title) {
        boolean valid = title != null && title.length() >= 10 && title.length() <= 200;
        System.out.println("Evaluacion titulo: " + (valid ? "VALIDA" : "INVALIDA"));
        return valid;
    }
    
    private boolean evaluateObjectives(String generalObjective, String specificObjectives) {
        boolean generalValid = generalObjective != null && generalObjective.length() >= 20;
        boolean specificValid = specificObjectives != null && specificObjectives.length() >= 30;
        boolean valid = generalValid && specificValid;
        System.out.println("Evaluacion objetivos: " + (valid ? "VALIDOS" : "INVALIDOS"));
        return valid;
    }
    
    private boolean evaluateMethodology(Object modality) {
        boolean valid = modality != null;
        System.out.println("Evaluacion metodologia: " + (valid ? "VALIDA" : "INVALIDA"));
        return valid;
    }
    
    private boolean evaluateScope(ProjectModel project) {
        // Simular evaluacion de alcance
        double scopeScore = Math.random() * 10;
        boolean valid = scopeScore >= 5.0;
        System.out.println("Evaluacion alcance: " + (valid ? "VIABLE" : "NO VIABLE") + 
                         " (Puntaje: " + String.format("%.1f", scopeScore) + "/10)");
        return valid;
    }
    
    public String generateEvaluationReport(ProjectModel project) {
        return "REPORTE DE EVALUACION - " + project.getTitle() + "\n" +
               "Fecha: " + java.time.LocalDate.now() + "\n" +
               "Estado: " + (evaluateProject(project) ? "RECOMENDADO" : "NO RECOMENDADO") + "\n" +
               "Evaluador: Sistema Automatico de Evaluacion";
    }
}
