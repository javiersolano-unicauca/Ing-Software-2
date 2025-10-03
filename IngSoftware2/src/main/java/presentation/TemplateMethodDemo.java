/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import support.operation.template_method.ProfessionalPracticeEvaluator;
import support.operation.template_method.ResearchProjectEvaluator;
import support.operation.template_method.ProjectEvaluator;
import java.time.LocalDate;
/**
 *
 * @author yezid
 */

/**
 * Clase para probar el patrón Template Method
 */
public class TemplateMethodDemo {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA PATRON TEMPLATE METHOD ===\n");
        
        // Crear proyecto de práctica profesional
        ProjectModel practiceProject = new ProjectModel(
            "Sistema de Gestion de Inventarios para PYME",
            LocalDate.now(),
            "ing.sistemas@unicauca.edu.co",
            "empresa.consultora@industria.com", 
            "est.practica@unicauca.edu.co",
            "",
            ModalityEnum.PROFESSIONAL,
            "Automatizar el control de inventarios para mejorar eficiencia operativa",
            "1. Desarrollar modulo entradas/salidas, 2. Implementar alertas de stock, 3. Generar reportes automaticos",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Proyecto en colaboracion con empresa del sector comercial",
            "sistema_inventarios.pdf"
        );
        
        // Crear proyecto de investigación
        ProjectModel researchProject = new ProjectModel(
            "Analisis del Impacto de IoT en Agricultura de Precision en el Cauca",
            LocalDate.now(),
            "dr.investigacion@unicauca.edu.co",
            "", 
            "est.investigacion@unicauca.edu.co",
            "colaborador@unicauca.edu.co",
            ModalityEnum.RESEARCH,
            "Evaluar la efectividad de tecnologias IoT en optimizacion de recursos agricolas",
            "1. Revisar estado del arte en agricultura de precision, 2. Diseñar experimento con sensores IoT, " +
            "3. Analizar datos de eficiencia hidrica, 4. Proponer modelo de implementacion escalable",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Investigacion con potencial publicacion en revista indexada",
            "investigacion_iot_agricultura.pdf"
        );
        
        // Probar evaluador de práctica profesional
        System.out.println("EVALUANDO PROYECTO DE PRACTICA PROFESIONAL");
        System.out.println("==================================================");
        ProjectEvaluator practiceEvaluator = new ProfessionalPracticeEvaluator();
        practiceEvaluator.evaluate(practiceProject);
        
        System.out.println("\n" + "=".repeat(80) + "\n");
        
        // Probar evaluador de investigación
        System.out.println("EVALUANDO PROYECTO DE INVESTIGACION");
        System.out.println("============================================");
        ProjectEvaluator researchEvaluator = new ResearchProjectEvaluator();
        researchEvaluator.evaluate(researchProject);
        
        // Demostrar flexibilidad del patrón
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DEMOSTRACION DE FLEXIBILIDAD DEL TEMPLATE METHOD");
        System.out.println("Mismo esqueleto de evaluacion, diferentes implementaciones especificas");
    }
}
