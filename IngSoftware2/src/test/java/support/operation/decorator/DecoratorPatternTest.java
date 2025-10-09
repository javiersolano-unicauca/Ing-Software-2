/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.decorator;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
/**
 *
 * @author yezid
 */
public class DecoratorPatternTest {
    
    private ProjectModel baseProject;
    
    @BeforeEach
    void setUp() {
        baseProject = new ProjectModel(
            "Proyecto Base",
            LocalDate.now(),
            "dir@test.com",
            "codir@test.com",
            "est@test.com",
            "",
            ModalityEnum.PROFESSIONAL,
            "Objetivo general",
            "Obj1, Obj2",
            StatusEnum.INICIO,
            0,
            "Observaciones base",
            "base.pdf"
        );
    }
    
    @Test
    void testPriorityProjectDecorator() {
        PriorityProject priorityProject = new PriorityProject(baseProject, "Test priority");
        
        assertTrue(priorityProject.getTitle().contains("[ALTA PRIORIDAD]"));
        assertTrue(priorityProject.getObservations().contains("PROYECTO PRIORITARIO"));
        assertEquals("Test priority", priorityProject.getPriorityReason());
        assertTrue(priorityProject.isUrgent());
    }
    
    @Test
    void testInternationalProjectDecorator() {
        InternationalProject internationalProject = new InternationalProject(
            baseProject, "Test University", "Test Country"
        );
        
        assertTrue(internationalProject.getTitle().contains("[INTERNACIONAL]"));
        assertTrue(internationalProject.getObservations().contains("COLABORACION INTERNACIONAL"));
        assertEquals("Test University", internationalProject.getPartnerUniversity());
        assertEquals("Test Country", internationalProject.getCountry());
    }
    
    @Test
    void testFundedProjectDecorator() {
        FundedProject fundedProject = new FundedProject(
            baseProject, "Test Fund", 1000000.0, "TEST-001"
        );
        
        assertTrue(fundedProject.getTitle().contains("[FINANCIADO]"));
        assertTrue(fundedProject.getObservations().contains("FINANCIAMIENTO"));
        assertEquals("Test Fund", fundedProject.getFundingSource());
        assertEquals(1000000.0, fundedProject.getFundingAmount());
        assertEquals("TEST-001", fundedProject.getGrantNumber());
    }
    
    @Test
    void testMultipleDecorators() {
        ProjectDecorator multiDecorated = new PriorityProject(
            new InternationalProject(baseProject, "Uni Test", "Country Test"),
            "Multi test"
        );
        
        String title = multiDecorated.getTitle();
        assertTrue(title.contains("[ALTA PRIORIDAD]"));
        assertTrue(title.contains("[INTERNACIONAL]"));
    }
    
    @Test
    void testDecoratorInheritance() {
        PriorityProject priorityProject = new PriorityProject(baseProject, "Test");
        
        // Verificar que es instancia de ProjectDecorator y ProjectModel
        assertTrue(priorityProject instanceof ProjectDecorator);
        assertTrue(priorityProject instanceof ProjectModel);
        
        // Verificar que delega correctamente
        assertEquals(baseProject.getDirector(), priorityProject.getDirector());
        assertEquals(baseProject.getStudent1(), priorityProject.getStudent1());
    }
    
    @Test
    void testBaseProjectNotModified() {
        String originalTitle = baseProject.getTitle();
        String originalObservations = baseProject.getObservations();
        
        PriorityProject decorated = new PriorityProject(baseProject, "Test");
        
        // El proyecto base no deberia modificarse
        assertEquals(originalTitle, baseProject.getTitle());
        assertEquals(originalObservations, baseProject.getObservations());
        
        // El decorador deberia tener comportamiento diferente
        assertNotEquals(originalTitle, decorated.getTitle());
        assertNotEquals(originalObservations, decorated.getObservations());
    }
}
