/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import support.operation.state.ProjectContext;
import java.time.LocalDate;

/**
 *
 * @author yezid
 */

/**
 * Clase específica para probar el patrón State
 */
public class StatePatternDemo {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA PATRON STATE ===\n");
        
        // Crear proyecto de prueba
        ProjectModel project = new ProjectModel(
            "Sistema IoT para Agricultura",
            LocalDate.now(),
            "julio.hurtado@unicauca.edu.co",
            "maria.garcia@unicauca.edu.co", 
            "carlos.rodriguez@unicauca.edu.co",
            "ana.martinez@unicauca.edu.co",
            ModalityEnum.PROFESSIONAL,  // Usar la constante existente
            "Desarrollar sistema IoT para optimizar riego",
            "1. Sensores temperatura, 2. Control riego automatico, 3. App movil",
            StatusEnum.INICIO,  // Estado inicial
            0,
            "Proyecto innovador para agricultura de precision",
            "proyecto_iot.pdf"
        );
        
        // Crear contexto con el proyecto
        ProjectContext context = new ProjectContext(project);
        
        // Probar transiciones de estado
        System.out.println("PROYECTO: " + project.getTitle());
        System.out.println("ESTADO INICIAL: " + context.getCurrentState().getEstadoNombre());
        System.out.println();
        
        // Simular flujo completo del proyecto
        simularFlujoCompleto(context);
        
        // Probar funcionalidad de correcciones
        System.out.println("\n--- Probando sistema de correcciones ---");
        probarCorrecciones();
    }
    
    private static void simularFlujoCompleto(ProjectContext context) {
        System.out.println("SIMULANDO FLUJO DEL PROYECTO:");
        
        // Flujo normal hasta evaluación de comité
        avanzarEstado(context, "1. De INICIO a FORMULARIO A");
        avanzarEstado(context, "2. De FORMULARIO A a COORDINADOR");
        avanzarEstado(context, "3. De COORDINADOR a EVALUACION COMITE");
        
        // Simular diferentes escenarios
        System.out.println("\n--- Escenario 1: Proyecto aceptado por comite ---");
        simularAceptacionComite(context);
        
        System.out.println("\n--- Escenario 2: Proyecto con correcciones ---");
        simularCorreccionesComite();  // ← Ahora este método existe
    }
    
    private static void avanzarEstado(ProjectContext context, String descripcion) {
        System.out.println(descripcion);
        System.out.println("   Antes: " + context.getCurrentState().getEstadoNombre());
        context.siguienteEstado();
        System.out.println("   Despues: " + context.getCurrentState().getEstadoNombre());
    }
    
    private static void simularAceptacionComite(ProjectContext context) {
        // Forzar estado de evaluación de comité
        context.setCurrentState(new support.operation.state.EstadoEnEvaluacionComite());
        System.out.println("Estado actual: " + context.getCurrentState().getEstadoNombre());
        
        // Avanzar (simula decisión aleatoria del comité)
        context.siguienteEstado();
        System.out.println("Decision del comite: " + context.getCurrentState().getEstadoNombre());
        
        // Continuar flujo si fue aceptado
        if (context.getCurrentState().getStatusEnum() == StatusEnum.ACEPTADO_COMITE) {
            avanzarEstado(context, "Continuando con anteproyecto...");
            avanzarEstado(context, "Presentando a jefatura...");
        }
    }
    
    // 🔥 MÉTODO FALTANTE - AGREGAR ESTO:
    private static void simularCorreccionesComite() {
        System.out.println("SIMULANDO ESCENARIO DE CORRECCIONES:");
        
        // Crear proyecto específico para correcciones
        ProjectModel proyectoCorrecciones = new ProjectModel(
            "App para Gestion de Bibliotecas",
            LocalDate.now(),
            "profesor.biblioteca@unicauca.edu.co",
            "", 
            "est.biblioteca@unicauca.edu.co",
            "",
            ModalityEnum.PROFESSIONAL,
            "Desarrollar sistema de gestion para bibliotecas universitarias",
            "1. Catalogo digital, 2. Reservas en linea, 3. Gestion de prestamos",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Primera version con funcionalidades basicas",
            "app_biblioteca.pdf"
        );
        
        ProjectContext contextCorrecciones = new ProjectContext(proyectoCorrecciones);
        
        System.out.println("Proyecto: " + proyectoCorrecciones.getTitle());
        System.out.println("Estado inicial: " + contextCorrecciones.getCurrentState().getEstadoNombre());
        
        // Simular que el comité pide correcciones
        contextCorrecciones.setCurrentState(new support.operation.state.EstadoCorreccionesComite());
        System.out.println("Comite solicita correcciones. Estado: " + 
            contextCorrecciones.getCurrentState().getEstadoNombre());
        
        // Probar el límite de correcciones
        System.out.println("\n PROBANDO LIMITE DE CORRECCIONES:");
        for (int i = 1; i <= 4; i++) {
            System.out.println("--- Correccion #" + i + " ---");
            contextCorrecciones.corregir();
            System.out.println("Estado: " + contextCorrecciones.getCurrentState().getEstadoNombre());
            System.out.println("Intentos totales: " + contextCorrecciones.getIntentosCorreccion());
            
            if (contextCorrecciones.getIntentosCorreccion() >= 3) {
                System.out.println("¡ALERTA! Máximo de correcciones alcanzado");
                if (contextCorrecciones.getCurrentState().getStatusEnum() == StatusEnum.RECHAZADO_COMITE) {
                    System.out.println("PROYECTO RECHAZADO DEFINITIVAMENTE");
                }
            }
        }
    }
    
    private static void probarCorrecciones() {
        // Crear nuevo proyecto para probar correcciones
        ProjectModel proyectoCorrecciones = new ProjectModel(
            "App Movil para Turismo",
            LocalDate.now(),
            "laura.sanchez@unicauca.edu.co",
            "", 
            "pedro.gomez@unicauca.edu.co",
            "",
            ModalityEnum.PROFESSIONAL,
            "Desarrollar app turistica para Popayan",
            "1. Mapa interactivo, 2. Rutas turisticas, 3. Informacion sitios",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Primera version",
            "app_turismo.pdf"
        );
        
        ProjectContext contextCorrecciones = new ProjectContext(proyectoCorrecciones);
        
        System.out.println("Proyecto: " + proyectoCorrecciones.getTitle());
        System.out.println("Estado inicial: " + contextCorrecciones.getCurrentState().getEstadoNombre());
        
        // Probar correcciones
        System.out.println("\nProbando correcciones:");
        for (int i = 1; i <= 4; i++) {
            System.out.println("Intento de correccion #" + i);
            contextCorrecciones.corregir();
            System.out.println("Estado despues de correccion: " + 
                contextCorrecciones.getCurrentState().getEstadoNombre());
            System.out.println("Intentos totales: " + contextCorrecciones.getIntentosCorreccion());
            
            if (contextCorrecciones.getIntentosCorreccion() >= 3) {
                System.out.println("¡Maximo de correcciones alcanzado!");
            }
        }
    }
}
