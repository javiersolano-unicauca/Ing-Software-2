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
 * Evaluador específico para proyectos de Práctica Profesional
 * Implementa los pasos abstractos del template method
 */
public class ProfessionalPracticeEvaluator extends ProjectEvaluator {
    
    @Override
    protected void reviewProblem(ProjectModel project) {
        logEvaluationStep("ANALISIS DEL PROBLEMA - PRACTICA PROFESIONAL");
        System.out.println("   - Evaluando relevancia del problema en contexto empresarial");
        System.out.println("   - Verificando alineacion con necesidades del sector productivo");
        System.out.println("   - Analizando impacto potencial en la organizacion");
        
        validateBasicRequirements(project);
        
        // Criterios específicos para práctica profesional
        if (project.getModality() != ModalityEnum.PROFESSIONAL) {
            System.out.println("   Advertencia: El proyecto no esta marcado como practica profesional");
        }
    }
    
    @Override
    protected void reviewGoals(ProjectModel project) {
        logEvaluationStep("EVALUACION DE OBJETIVOS - PRACTICA PROFESIONAL");
        System.out.println("   - Verificando objetivos medibles y alcanzables en entorno laboral");
        System.out.println("   - Evaluando contribucion al desarrollo de competencias profesionales");
        System.out.println("   - Analizando alineacion con perfil de egreso del programa");
        
        if (project.getSpecificObjectives() == null || project.getSpecificObjectives().isEmpty()) {
            System.out.println("    CRITICO: Proyecto carece de objetivos especificos");
        } else {
            System.out.println("    Objetivos especificos definidos: " + 
                project.getSpecificObjectives().split(",").length + " objetivos identificados");
        }
    }
    
    @Override
    protected void reviewScope(ProjectModel project) {
        logEvaluationStep("DELIMITACION DEL ALCANCE - PRACTICA PROFESIONAL");
        System.out.println("   - Evaluando viabilidad en contexto organizacional real");
        System.out.println("   - Verificando que el scope sea realizable en 6 meses");
        System.out.println("   - Analizando recursos disponibles en la empresa");
        
        // Simular evaluación de scope
        double scopeScore = Math.random() * 10;
        System.out.println("   - Puntaje de viabilidad del alcance: " + String.format("%.1f", scopeScore) + "/10");
        
        if (scopeScore < 5.0) {
            System.out.println("RECOMENDACION: Considerar reducir el alcance del proyecto");
        }
    }
    
    @Override
    protected void reviewMethodology(ProjectModel project) {
        logEvaluationStep("REVISION DE METODOLOGIA - PRACTICA PROFESIONAL");
        System.out.println("   - Evaluando adecuacion de metodologias agiles (Scrum, Kanban)");
        System.out.println("   - Verificando plan de trabajo semanal");
        System.out.println("   - Analizando herramientas de gestion propuestas");
        
        System.out.println("   - Metodologia recomendada: Enfoque por sprints con entregables incrementales");
    }
    
    @Override
    protected void reviewResources(ProjectModel project) {
        logEvaluationStep("ANALISIS DE RECURSOS - PRACTICA PROFESIONAL");
        System.out.println("   - Evaluando disponibilidad de equipos en la empresa");
        System.out.println("   - Verificando acceso a software y licencias necesarias");
        System.out.println("   - Analizando soporte tecnico disponible");
        
        System.out.println("   - Recursos criticos: Estacion de trabajo, software especializado, mentor empresarial");
    }
    
    @Override
    protected void reviewTimeline(ProjectModel project) {
        logEvaluationStep("EVALUACION DE CRONOGRAMA - PRACTICA PROFESIONAL");
        System.out.println("   - Verificando factibilidad en 480 horas (6 meses)");
        System.out.println("   - Evaluando hitos principales y entregables");
        System.out.println("   - Analizando dependencias criticas");
        
        System.out.println("   - Cronograma: Fase inicial (1 mes), Desarrollo (4 meses), Cierre (1 mes)");
    }
    
    @Override
    protected void makeDecision(ProjectModel project) {
        logEvaluationStep("DECISION FINAL - PRACTICA PROFESIONAL");
        
        // Simular criterios de decisión específicos para práctica profesional
        boolean hasEnterpriseContext = Math.random() > 0.2; // 80% probabilidad
        boolean hasClearDeliverables = Math.random() > 0.3; // 70% probabilidad
        boolean fitsTimeline = Math.random() > 0.1; // 90% probabilidad
        
        System.out.println("   - Contexto empresarial definido: " + (hasEnterpriseContext ? "[OK]" : "[X]"));
        System.out.println("   - Entregables claros: " + (hasClearDeliverables ? "[OK]" : "[X]"));
        System.out.println("   - Factible en timeline: " + (fitsTimeline ? "[OK]" : "[X]"));
        
        boolean approved = hasEnterpriseContext && hasClearDeliverables && fitsTimeline;
        
        if (approved) {
            System.out.println("    DECISION: PROYECTO APROBADO para practica profesional");
            System.out.println("    RECOMENDACIONES: Establecer reuniones semanales de seguimiento");
        } else {
            System.out.println("    DECISION: PROYECTO NO APROBADO - requiere ajustes");
            System.out.println("    SUGERENCIAS: Mejorar definicion de contexto empresarial");
        }
    }
    
    @Override
    protected void preEvaluationHook(ProjectModel project) {
        System.out.println("EVALUADOR ESPECIALIZADO EN PRACTICA PROFESIONAL");
        System.out.println("   Modalidad: Desarrollo en entorno empresarial real");
    }
}
