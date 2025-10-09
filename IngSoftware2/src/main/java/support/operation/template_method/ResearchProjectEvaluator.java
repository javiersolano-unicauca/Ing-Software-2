/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.template_method;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
/**
 *
 * @author yezid
 */

/**
 * Evaluador específico para proyectos de Trabajo de Investigación
 * Implementa los pasos abstractos del template method
 */
public class ResearchProjectEvaluator extends ProjectEvaluator {
    
    @Override
    protected void reviewProblem(ProjectModel project) {
        logEvaluationStep("ANALISIS DEL PROBLEMA - INVESTIGACION");
        System.out.println("   - Evaluando originalidad y contribucion al conocimiento");
        System.out.println("   - Verificando brecha de investigacion identificada");
        System.out.println("   - Analizando relevancia cientifica y academica");
        
        validateBasicRequirements(project);
        
        // Criterios específicos para investigación
        if (project.getModality() != ModalityEnum.RESEARCH) {
            System.out.println("   Advertencia: El proyecto no está marcado como trabajo de investigacion");
        }
    }
    
    @Override
    protected void reviewGoals(ProjectModel project) {
        logEvaluationStep("EVALUACION DE OBJETIVOS - INVESTIGACION");
        System.out.println("   - Verificando objetivos de investigacion claros y medibles");
        System.out.println("   - Evaluando preguntas de investigacion bien formuladas");
        System.out.println("   - Analizando hipotesis (si aplica)");
        
        if (project.getSpecificObjectives() == null || project.getSpecificObjectives().isEmpty()) {
            System.out.println("   CRITICO: Proyecto de investigacion carece de objetivos especificos");
        } else {
            String[] objetivos = project.getSpecificObjectives().split(",");
            System.out.println("   Objetivos de investigacion definidos: " + objetivos.length + " objetivos");
            for (int i = 0; i < objetivos.length; i++) {
                System.out.println("     " + (i + 1) + ". " + objetivos[i].trim());
            }
        }
    }
    
    @Override
    protected void reviewScope(ProjectModel project) {
        logEvaluationStep("DELIMITACION DEL ALCANCE - INVESTIGACION");
        System.out.println("   - Evaluando factibilidad metodologica");
        System.out.println("   - Verificando que el scope sea investigable en el tiempo disponible");
        System.out.println("   - Analizando poblacion/universo de estudio");
        
        // Simular evaluación de scope para investigación
        double scopeScore = Math.random() * 10;
        System.out.println("   - Puntaje de viabilidad investigativa: " + String.format("%.1f", scopeScore) + "/10");
        
        if (scopeScore < 6.0) {
            System.out.println("   RECOMENDACION: Delimitar mss especificamente el problema de investigacion");
        }
    }
    
    @Override
    protected void reviewMethodology(ProjectModel project) {
        logEvaluationStep("REVISION DE METODOLOGIA - INVESTIGACION");
        System.out.println("   - Evaluando adecuacion del disenio investigativo");
        System.out.println("   - Verificando metodos de recoleccion de datos");
        System.out.println("   - Analizando tecnicas de analisis propuestas");
        
        System.out.println("   - Metodologia recomendada: Enfoque mixto con triangulacion metodologica");
    }
    
    @Override
    protected void reviewResources(ProjectModel project) {
        logEvaluationStep("ANALISIS DE RECURSOS - INVESTIGACION");
        System.out.println("   - Evaluando acceso a fuentes bibliograficas especializadas");
        System.out.println("   - Verificando disponibilidad de instrumentos de medicion");
        System.out.println("   - Analizando requerimientos eticos y permisos");
        
        System.out.println("   - Recursos criticos: Acceso a bases de datos, software estadistico, comite de etica");
    }
    
    @Override
    protected void reviewTimeline(ProjectModel project) {
        logEvaluationStep("EVALUACION DE CRONOGRAMA - INVESTIGACION");
        System.out.println("   - Verificando factibilidad en 9 meses de desarrollo");
        System.out.println("   - Evaluando fases: Marco teorico, trabajo de campo, analisis, redaccion");
        System.out.println("   - Analizando tiempo para revisiones eticas");
        
        System.out.println("   - Cronograma: Revision literaria (2 meses), Metodologia (1 mes), " +
                         "Trabajo campo (3 meses), Analisis (2 meses), Redaccion (1 mes)");
    }
    
    @Override
    protected void makeDecision(ProjectModel project) {
        logEvaluationStep("DECISION FINAL - INVESTIGACION");
        
        // Simular criterios de decisión específicos para investigación
        boolean hasResearchGap = Math.random() > 0.25; // 75% probabilidad
        boolean hasSolidMethodology = Math.random() > 0.3; // 70% probabilidad
        boolean hasAcademicContribution = Math.random() > 0.15; // 85% probabilidad
        boolean ethicalConsiderations = Math.random() > 0.1; // 90% probabilidad
        
        System.out.println("   - Brecha de investigacion identificada: " + (hasResearchGap ? "[OK]" : "[X]"));
        System.out.println("   - Metodologia solida: " + (hasSolidMethodology ? "[OK]" : "[X]"));
        System.out.println("   - Contribucion academica: " + (hasAcademicContribution ? "[OK]" : "[X]"));
        System.out.println("   - Consideraciones eticas: " + (ethicalConsiderations ? "[OK]" : "[X]"));
        
        boolean approved = hasResearchGap && hasSolidMethodology && 
                          hasAcademicContribution && ethicalConsiderations;
        
        if (approved) {
            System.out.println("    DECISION: PROYECTO APROBADO para trabajo de investigacion");
            System.out.println("    RECOMENDACIONES: Presentar ante comite de etica, elaborar protocolo");
        } else {
            System.out.println("    DECISION: PROYECTO NO APROBADO - requiere ajustes metodologicos");
            System.out.println("    SUGERENCIAS: Fortalecer marco teorico, clarificar metodologia");
        }
    }
    
    @Override
    protected void preEvaluationHook(ProjectModel project) {
        System.out.println("EVALUADOR ESPECIALIZADO EN INVESTIGACION");
        System.out.println("   Modalidad: Contribucion al conocimiento cientifico");
    }
}
