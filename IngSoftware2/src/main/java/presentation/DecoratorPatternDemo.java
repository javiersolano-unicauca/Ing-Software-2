/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import support.operation.decorator.PriorityProject;
import support.operation.decorator.InternationalProject;
import support.operation.decorator.FundedProject;
import support.operation.decorator.ProjectDecorator;
import java.time.LocalDate;
/**
 *
 * @author yezid
 */

/**
 * Clase para probar el patron Decorator
 */
public class DecoratorPatternDemo {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA PATRON DECORATOR ===");
        System.out.println();
        
        // Crear proyecto base
        ProjectModel baseProject = new ProjectModel(
            "Sistema de Monitoreo Ambiental",
            LocalDate.now(),
            "dr.ambiental@unicauca.edu.co",
            "co.ambiental@unicauca.edu.co",
            "est.ambiental@unicauca.edu.co",
            "",
            ModalityEnum.RESEARCH,
            "Desarrollar sistema de monitoreo de calidad del aire en tiempo real",
            "1. Implementar red de sensores, 2. Desarrollar plataforma web, 3. Generar alertas automaticas",
            StatusEnum.EN_DESARROLLO,
            0,
            "Proyecto en colaboracion con la secretaria de ambiente",
            "monitoreo_ambiental.pdf"
        );
        
        System.out.println("PROYECTO BASE:");
        System.out.println("Titulo: " + baseProject.getTitle());
        System.out.println("Observaciones: " + baseProject.getObservations());
        System.out.println();
        
        // Aplicar decorador de prioridad
        System.out.println("1. DECORADOR PRIORITY:");
        ProjectDecorator priorityProject = new PriorityProject(baseProject, "Proyecto estrategico institucional");
        System.out.println("Titulo decorado: " + priorityProject.getTitle());
        System.out.println("Observaciones decoradas: " + priorityProject.getObservations());
        System.out.println("Descripcion: " + priorityProject.getDecoratedDescription());
        System.out.println();
        
        // Aplicar decorador internacional
        System.out.println("2. DECORADOR INTERNACIONAL:");
        ProjectDecorator internationalProject = new InternationalProject(
            baseProject, 
            "University of California, Berkeley", 
            "Estados Unidos"
        );
        System.out.println("Titulo decorado: " + internationalProject.getTitle());
        System.out.println("Observaciones decoradas: " + internationalProject.getObservations());
        System.out.println("Descripcion: " + internationalProject.getDecoratedDescription());
        System.out.println();
        
        // Aplicar decorador de financiamiento
        System.out.println("3. DECORADOR FINANCIADO:");
        ProjectDecorator fundedProject = new FundedProject(
            baseProject,
            "MINCIENCIAS - Convocatoria Jovenes Investigadores",
            50000000.0,
            "785-2024"
        );
        System.out.println("Titulo decorado: " + fundedProject.getTitle());
        System.out.println("Observaciones decoradas: " + fundedProject.getObservations());
        System.out.println("Descripcion: " + fundedProject.getDecoratedDescription());
        System.out.println();
        
        // Demostrar composicion multiple de decoradores
        System.out.println("4. COMPOSICION MULTIPLE DE DECORADORES:");
        ProjectDecorator multiDecorated = new PriorityProject(
            new InternationalProject(
                new FundedProject(
                    baseProject,
                    "Programa Erasmus+",
                    80000000.0,
                    "ERASMUS-2024-01"
                ),
                "Technical University of Munich",
                "Alemania"
            ),
            "Proyecto de cooperacion internacional prioritaria"
        );
        
        System.out.println("Titulo multi-decorado: " + multiDecorated.getTitle());
        System.out.println("Observaciones multi-decoradas: " + multiDecorated.getObservations());
        System.out.println("Descripcion: " + multiDecorated.getDecoratedDescription());
        System.out.println();
        
        // Demostrar que el objeto original no se modifica
        System.out.println("5. VERIFICACION DE NO MODIFICACION DEL OBJETO BASE:");
        System.out.println("Proyecto base titulo: " + baseProject.getTitle());
        System.out.println("Proyecto base observaciones: " + baseProject.getObservations());
        System.out.println();
        
        // Probar metodos especificos de decoradores
        System.out.println("6. METODOS ESPECIFICOS DE DECORADORES:");
        if (priorityProject instanceof PriorityProject) {
            PriorityProject pp = (PriorityProject) priorityProject;
            System.out.println("Es urgente: " + pp.isUrgent());
            System.out.println("Razon prioridad: " + pp.getPriorityReason());
        }
        
        if (internationalProject instanceof InternationalProject) {
            InternationalProject ip = (InternationalProject) internationalProject;
            System.out.println("Universidad colaboradora: " + ip.getPartnerUniversity());
            System.out.println("Pais: " + ip.getCountry());
        }
        
        if (fundedProject instanceof FundedProject) {
            FundedProject fp = (FundedProject) fundedProject;
            System.out.println("Fuente financiamiento: " + fp.getFundingSource());
            System.out.println("Monto: $" + String.format("%,.2f", fp.getFundingAmount()));
            System.out.println("Numero convocatoria: " + fp.getGrantNumber());
        }
    }
}
