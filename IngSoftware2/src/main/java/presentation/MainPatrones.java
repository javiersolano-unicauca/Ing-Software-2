/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import support.operation.state.ProjectContext;
import support.operation.template_method.ProfessionalPracticeEvaluator;
import support.operation.template_method.ResearchProjectEvaluator;
import support.operation.template_method.ProjectEvaluator;
import support.operation.decorator.PriorityProject;
import support.operation.decorator.InternationalProject;
import support.operation.decorator.FundedProject;
import support.operation.decorator.ProjectDecorator;
import support.operation.adapter.ExternalService;
import support.operation.adapter.ExternalServiceAdapter;
import support.operation.adapter.CompanyService;
import support.operation.facade.ProjectManagementFacade;
import java.time.LocalDate;
/**
 *
 * @author yezid
 */
public class MainPatrones {
    
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("LABORATORIO PATRONES DE DISENO - DEMOSTRACION COMPLETA");
        System.out.println("=================================================");
        System.out.println();
        
        // Crear proyecto base para las pruebas
        ProjectModel baseProject = createSampleProject();
        
        // Probar cada patron de diseño
        testStatePattern(baseProject);  
        testTemplateMethodPattern(baseProject);
        testDecoratorPattern(baseProject);
        testAdapterPattern();
        testFacadePattern(baseProject);
        
        System.out.println("=================================================");
        System.out.println("DEMOSTRACION COMPLETADA EXITOSAMENTE");
        System.out.println("=================================================");
    }
    
    private static ProjectModel createSampleProject() {
        return new ProjectModel(
            "Sistema Inteligente para Gestion Agricola",
            LocalDate.now(),
            "dr.agricola@unicauca.edu.co",
            "co.agricola@unicauca.edu.co",
            "est.agricola1@unicauca.edu.co",
            "est.agricola2@unicauca.edu.co",
            ModalityEnum.PROFESSIONAL,
            "Implementar solucion IoT para optimizacion de recursos en agricultura",
            "1. Desarrollo de sensores de suelo, 2. Plataforma de monitoreo, 3. Sistema de riego automatico",
            StatusEnum.INICIO,
            0,
            "Proyecto enfocado en agricultura de precision",
            "sistema_agricola_inteligente.pdf"
        );
    }
    
    private static void testStatePattern(ProjectModel project) {
        System.out.println("1. PATRON STATE - MAQUINA DE ESTADOS");
        System.out.println("=====================================");
        
        ProjectContext context = new ProjectContext(project);
        System.out.println("Estado inicial: " + context.getCurrentState().getEstadoNombre());
        
        // Simular avance de estados
        System.out.println("Avanzando estados...");
        for (int i = 0; i < 5; i++) {
            context.siguienteEstado();
            System.out.println("Estado " + (i + 1) + ": " + context.getCurrentState().getEstadoNombre());
        }
        
        // Probar correcciones
        System.out.println("Probando sistema de correcciones...");
        context.setIntentosCorreccion(2);
        context.corregir();
        System.out.println("Intentos de correccion: " + context.getIntentosCorreccion());
        System.out.println("Estado despues de correccion: " + context.getCurrentState().getEstadoNombre());
        
        System.out.println();
    }
    
    private static void testTemplateMethodPattern(ProjectModel project) {
        System.out.println("2. PATRON TEMPLATE METHOD - EVALUADORES");
        System.out.println("========================================");
        
        // Probar evaluador de practica profesional
        System.out.println("Evaluador Practica Profesional:");
        ProjectEvaluator practiceEvaluator = new ProfessionalPracticeEvaluator();
        practiceEvaluator.evaluate(project);
        System.out.println();
        
        // Crear proyecto de investigacion para el segundo evaluador
        ProjectModel researchProject = new ProjectModel(
            "Analisis de Algoritmos de Machine Learning",
            LocalDate.now(),
            "dr.investigacion@unicauca.edu.co",
            "",
            "est.investigacion@unicauca.edu.co",
            "",
            ModalityEnum.RESEARCH,
            "Investigar y comparar algoritmos de ML para clasificacion",
            "1. Revision bibliografica, 2. Implementacion de algoritmos, 3. Analisis comparativo",
            StatusEnum.EN_EVALUACION_COMITE,
            0,
            "Proyecto de investigacion pura",
            "investigacion_ml.pdf"
        );
        
        // Probar evaluador de investigacion
        System.out.println("Evaluador Investigacion:");
        ProjectEvaluator researchEvaluator = new ResearchProjectEvaluator();
        researchEvaluator.evaluate(researchProject);
        
        System.out.println();
    }
    
    private static void testDecoratorPattern(ProjectModel project) {
        System.out.println("3. PATRON DECORATOR - PROYECTOS ENRIQUECIDOS");
        System.out.println("=============================================");
        
        System.out.println("Proyecto base: " + project.getTitle());
        
        // Aplicar decorador de prioridad
        ProjectDecorator priorityProject = new PriorityProject(project, "Proyecto estrategico");
        System.out.println("Con prioridad: " + priorityProject.getTitle());
        
        // Aplicar decorador internacional
        ProjectDecorator internationalProject = new InternationalProject(
            project, 
            "University of Cambridge", 
            "Reino Unido"
        );
        System.out.println("Internacional: " + internationalProject.getTitle());
        
        // Aplicar decorador de financiamiento
        ProjectDecorator fundedProject = new FundedProject(
            project,
            "Fondo de Ciencia y Tecnologia",
            75000000.0,
            "FCT-2024-003"
        );
        System.out.println("Financiado: " + fundedProject.getTitle());
        
        // Composicion multiple de decoradores
        ProjectDecorator multiDecorated = new PriorityProject(
            new InternationalProject(
                project,
                "MIT",
                "Estados Unidos"
            ),
            "Proyecto de cooperacion internacional prioritaria"
        );
        System.out.println("Multi-decorado: " + multiDecorated.getTitle());
        
        System.out.println();
    }
    
    private static void testAdapterPattern() {
        System.out.println("4. PATRON ADAPTER - SERVICIO EXTERNO");
        System.out.println("====================================");
        
        // Servicio externo con interfaz incompatible
        ExternalService externalService = new ExternalService();
        System.out.println("Servicio externo JSON: " + externalService.getCompanyDataAsJson());
        
        // Crear adaptador
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        
        // Usar adaptador como interfaz esperada
        CompanyService companyService = new CompanyService(adapter);
        System.out.println("Validacion datos: " + adapter.validateCompanyData());
        System.out.println("Nombre empresa: " + adapter.getCompanyName());
        
        // Procesar datos a traves del adaptador
        companyService.processCompanyData();
        
        System.out.println();
    }
    
    private static void testFacadePattern(ProjectModel project) {
        System.out.println("5. PATRON FACADE - GESTION UNIFICADA");
        System.out.println("=====================================");
        
        // Crear fachada
        ProjectManagementFacade projectFacade = new ProjectManagementFacade();
        
        // Probar proceso completo simplificado
        System.out.println("Iniciando proceso de revision de proyecto...");
        boolean success = projectFacade.processProjectSubmission(project);
        System.out.println("Proceso completado: " + (success ? "EXITOSO" : "FALLIDO"));
        
        // Probar revision rapida
        System.out.println("Revision rapida: " + 
            (projectFacade.quickProjectReview(project) ? "APROBADA" : "RECHAZADA"));
        
        // Gestion de evaluadores
        System.out.println("Evaluadores disponibles: " + 
            projectFacade.getAssignmentService().getAvailableEvaluatorsCount());
        
        System.out.println();
    }
    
    private static void testIntegration() {
        System.out.println("6. INTEGRACION DE PATRONES");
        System.out.println("==========================");
        
        // Crear proyecto integrado
        ProjectModel integratedProject = new ProjectModel(
            "Plataforma Integral de E-learning",
            LocalDate.now(),
            "dr.educacion@unicauca.edu.co",
            "co.tecnologia@unicauca.edu.co", 
            "est.plataforma@unicauca.edu.co",
            "",
            ModalityEnum.PROFESSIONAL,
            "Desarrollar plataforma de educacion virtual con IA",
            "1. Modulo de cursos, 2. Sistema de recomendacion, 3. Analiticas de aprendizaje",
            StatusEnum.INICIO,
            0,
            "Proyecto con potencial de escalabilidad",
            "plataforma_elearning.pdf"
        );
        
        // Aplicar State Pattern
        ProjectContext stateContext = new ProjectContext(integratedProject);
        System.out.println("Estado inicial: " + stateContext.getCurrentState().getEstadoNombre());
        
        // Aplicar Decorator Pattern
        ProjectDecorator decoratedProject = new FundedProject(
            new PriorityProject(integratedProject, "Proyecto innovacion educativa"),
            "Ministerio de Educacion",
            120000000.0,
            "MINEDU-2024-001"
        );
        System.out.println("Proyecto decorado: " + decoratedProject.getTitle());
        
        // Aplicar Template Method Pattern
        ProjectEvaluator evaluator = new ProfessionalPracticeEvaluator();
        System.out.println("Iniciando evaluacion template...");
        evaluator.evaluate(integratedProject);
        
        // Aplicar Facade Pattern
        ProjectManagementFacade facade = new ProjectManagementFacade();
        boolean processed = facade.processProjectSubmission(integratedProject);
        System.out.println("Proceso facade: " + (processed ? "COMPLETADO" : "FALLIDO"));
        
        System.out.println();
    }
}
