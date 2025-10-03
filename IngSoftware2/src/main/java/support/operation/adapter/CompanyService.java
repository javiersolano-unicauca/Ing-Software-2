/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.adapter;

/**
 *
 * @author yezid
 */

/**
 * Cliente del sistema que utiliza el adaptador para trabajar con el servicio externo
 */
public class CompanyService {
    
    private CompanyDataProvider companyDataProvider;
    
    public CompanyService(CompanyDataProvider companyDataProvider) {
        this.companyDataProvider = companyDataProvider;
    }
    
    public void processCompanyData() {
        System.out.println("Procesando datos de empresa...");
        
        if (companyDataProvider.validateCompanyData()) {
            Company company = companyDataProvider.getCompany();
            System.out.println("Datos de empresa obtenidos exitosamente:");
            System.out.println(company.toFormattedString());
            
            // Simular uso de los datos en el sistema
            registerCompanyForPractices(company);
            
        } else {
            System.out.println("Error: No se pudieron validar los datos de la empresa");
        }
    }
    
    public void displayCompanyInfo() {
        System.out.println("Informacion de empresa:");
        System.out.println(companyDataProvider.getCompanyInfo());
    }
    
    public void registerCompanyForPractices(Company company) {
        System.out.println("Registrando empresa para practicas profesionales...");
        System.out.println("Empresa: " + company.getName());
        System.out.println("Sector: " + company.getSector());
        System.out.println("Contacto: " + company.getContactEmail());
        System.out.println("Registro completado exitosamente");
    }
    
    public void setCompanyDataProvider(CompanyDataProvider provider) {
        this.companyDataProvider = provider;
    }
}
