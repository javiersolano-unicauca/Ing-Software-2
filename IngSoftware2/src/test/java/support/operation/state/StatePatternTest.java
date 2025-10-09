/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.state;

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
public class StatePatternTest {
    
    private ProjectModel project;
    private ProjectContext context;
    
    @BeforeEach
    void setUp() {
        project = new ProjectModel(
            "Proyecto Test",
            LocalDate.now(),
            "director@test.com",
            "codirector@test.com",
            "est1@test.com",
            "est2@test.com",
            ModalityEnum.PROFESSIONAL,
            "Objetivo general test",
            "Obj1, Obj2, Obj3",
            StatusEnum.INICIO,
            0,
            "Observaciones test",
            "test.pdf"
        );
        context = new ProjectContext(project);
    }
    
    @Test
    void testEstadoInicial() {
        assertEquals("Inicio", context.getCurrentState().getEstadoNombre());
        assertEquals(StatusEnum.INICIO, context.getCurrentState().getStatusEnum());
    }
    
    @Test
    void testTransicionEstados() {
        context.siguienteEstado();
        assertEquals(StatusEnum.FORMATO_A_DILIGENCIADO, context.getCurrentState().getStatusEnum());
        
        context.siguienteEstado();
        assertEquals(StatusEnum.PRESENTADO_A_COORDINADOR, context.getCurrentState().getStatusEnum());
    }
    
    @Test
    void testEstadoAnterior() {
        context.siguienteEstado(); // Va a Formato A
        context.estadoAnterior(); // Regresa a Inicio
        
        assertEquals(StatusEnum.INICIO, context.getCurrentState().getStatusEnum());
    }
    
    @Test
    void testCorrecciones() {
        context.setIntentosCorreccion(2);
        context.corregir();
        
        assertEquals(3, context.getIntentosCorreccion());
        assertEquals(StatusEnum.CORRECCIONES_COMITE, context.getCurrentState().getStatusEnum());
    }
    
    @Test
    void testRechazoPorMaximasCorrecciones() {
        context.setIntentosCorreccion(3);
        context.corregir();
        
        assertEquals(4, context.getIntentosCorreccion());
        assertEquals(StatusEnum.RECHAZADO_COMITE, context.getCurrentState().getStatusEnum());
    }
    
    @Test
    void testStateFactory() {
        ProjectState state = StateFactory.createState(StatusEnum.ACEPTADO_COMITE);
        assertTrue(state instanceof EstadoAceptadoComite);
        
        state = StateFactory.createState(StatusEnum.EN_EVALUACION_COMITE);
        assertTrue(state instanceof EstadoEnEvaluacionComite);
    }
}
