/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import support.operation.adapter.ExternalService;
import support.operation.adapter.ExternalServiceAdapter;
import support.operation.adapter.CompanyService;
import support.operation.adapter.CompanyDataProvider;
import support.operation.adapter.Company;
/**
 *
 * @author yezid
 */

/**
 * Clase para probar el patron Adapter
 */
public class AdapterPatternDemo {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA PATRON ADAPTER ===");
        System.out.println();
        
        // Demostrar el problema: servicio externo con interfaz incompatible
        System.out.println("1. SERVICIO EXTERNO (INTERFAZ INCOMPATIBLE):");
        ExternalService externalService = new ExternalService();
        System.out.println("Datos en JSON: " + externalService.getCompanyDataAsJson());
        System.out.println("Info del servicio: " + externalService.getExternalServiceInfo());
        System.out.println();
        
        // Crear el adaptador
        System.out.println("2. CREANDO ADAPTADOR:");
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        System.out.println("Adaptador creado: " + adapter.getClass().getSimpleName());
        System.out.println("Info del servicio via adaptador: " + adapter.getServiceInfo());
        System.out.println();
        
        // Usar el adaptador como CompanyDataProvider
        System.out.println("3. USANDO ADAPTADOR COMO CompanyDataProvider:");
        CompanyDataProvider dataProvider = adapter;
        
        System.out.println("Validando datos: " + dataProvider.validateCompanyData());
        System.out.println("Nombre empresa: " + dataProvider.getCompanyName());
        System.out.println();
        
        System.out.println("Informacion completa:");
        System.out.println(dataProvider.getCompanyInfo());
        System.out.println();
        
        // Usar el cliente del sistema con el adaptador
        System.out.println("4. CLIENTE DEL SISTEMA USANDO EL ADAPTADOR:");
        CompanyService companyService = new CompanyService(adapter);
        companyService.processCompanyData();
        System.out.println();
        
        // Demostrar obtencion de empresa
        System.out.println("5. OBTENCION DIRECTA DE OBJETO COMPANY:");
        Company company = adapter.getCompany();
        System.out.println("Objeto Company obtenido:");
        System.out.println(company.toString());
        System.out.println();
        
        // Probar metodo alternativo XML
        System.out.println("6. PROBANDO ADAPTACION DESDE XML:");
        Company companyFromXml = adapter.getCompanyFromXml();
        System.out.println("Empresa desde XML:");
        System.out.println(companyFromXml.toFormattedString());
        System.out.println();
        
        // Demostrar flexibilidad del adaptador
        System.out.println("7. FLEXIBILIDAD DEL ADAPTADOR:");
        System.out.println("El mismo adaptador puede manejar multiples formatos:");
        System.out.println("- JSON: " + (adapter.getCompany() != null ? "OK" : "ERROR"));
        System.out.println("- XML: " + (adapter.getCompanyFromXml() != null ? "OK" : "ERROR"));
        System.out.println();
        
        // Simular escenario de error
        System.out.println("8. MANEJO DE ERRORES:");
        ExternalService faultyService = new ExternalService() {
            @Override
            public String getCompanyDataAsJson() {
                return "invalid_json{";
            }
            
            @Override
            public boolean connectToService() {
                return false;
            }
        };
        
        ExternalServiceAdapter errorAdapter = new ExternalServiceAdapter(faultyService);
        CompanyService errorService = new CompanyService(errorAdapter);
        errorService.processCompanyData();
    }
}
