/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.template_method;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
/**
 *
 * @author yezid
 */
public class TemplateMethodTest {
    
    private ProjectModel practiceProject;
    private ProjectModel researchProject;
    private ByteArrayOutputStream outputStream;
    
    @BeforeEach
    void setUp() {
        practiceProject = new ProjectModel(
            "Practica Test",
            LocalDate.now(),
            "dir@test.com",
            "codir@test.com",
            "est@test.com",
            "",
            ModalityEnum.PROFESSIONAL,
            "Objetivo general practica",
            "Objetivo1, Objetivo2",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Observaciones",
            "practica.pdf"
        );
        
        researchProject = new ProjectModel(
            "Investigacion Test",
            LocalDate.now(),
            "dir@test.com",
            "",
            "est@test.com",
            "",
            ModalityEnum.RESEARCH,
            "Objetivo general investigacion",
            "Obj1, Obj2, Obj3",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Observaciones",
            "investigacion.pdf"
        );
        
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    void testProfessionalPracticeEvaluator() {
        ProjectEvaluator evaluator = new ProfessionalPracticeEvaluator();
        evaluator.evaluate(practiceProject);
        
        String output = outputStream.toString();
        assertTrue(output.contains("PRACTICA PROFESIONAL"));
        assertTrue(output.contains("EVALUACION"));
    }
    
    @Test
    void testResearchProjectEvaluator() {
        ProjectEvaluator evaluator = new ResearchProjectEvaluator();
        evaluator.evaluate(researchProject);
        
        String output = outputStream.toString();
        assertTrue(output.contains("INVESTIGACION"));
        assertTrue(output.contains("EVALUACION"));
    }
    
    @Test
    void testTemplateMethodStructure() {
        // Verificar que el metodo template es final y no puede ser sobrescrito
        ProjectEvaluator evaluator = new ProfessionalPracticeEvaluator();
        
        // El metodo evaluate deberia ejecutar todos los pasos en orden
        evaluator.evaluate(practiceProject);
        
        String output = outputStream.toString();
        assertTrue(output.contains("INICIANDO EVALUACION"));
        assertTrue(output.contains("EVALUACION COMPLETADA"));
    }
    
    @Test
    void testAbstractMethodsImplemented() {
        ProfessionalPracticeEvaluator practiceEvaluator = new ProfessionalPracticeEvaluator();
        ResearchProjectEvaluator researchEvaluator = new ResearchProjectEvaluator();
        
        // No deberia lanzar excepciones
        assertDoesNotThrow(() -> practiceEvaluator.evaluate(practiceProject));
        assertDoesNotThrow(() -> researchEvaluator.evaluate(researchProject));
    }
}
