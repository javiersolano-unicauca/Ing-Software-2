/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author yezid
 */
public class AdapterPatternTest {
    
    @Test
    void testExternalService() {
        ExternalService externalService = new ExternalService();
        
        assertNotNull(externalService.getCompanyDataAsJson());
        assertNotNull(externalService.getCompanyDataAsXml());
        assertNotNull(externalService.getExternalServiceInfo());
    }
    
    @Test
    void testAdapterImplementsTargetInterface() {
        ExternalService externalService = new ExternalService();
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        
        assertTrue(adapter instanceof CompanyDataProvider);
    }
    
    @Test
    void testAdapterGetCompany() {
        ExternalService externalService = new ExternalService();
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        
        Company company = adapter.getCompany();
        
        assertNotNull(company);
        assertNotNull(company.getName());
        assertNotEquals("Empresa no disponible", company.getName());
    }
    
    @Test
    void testAdapterGetCompanyName() {
        ExternalService externalService = new ExternalService();
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        
        String companyName = adapter.getCompanyName();
        
        assertNotNull(companyName);
        assertFalse(companyName.isEmpty());
    }
    
    @Test
    void testAdapterGetCompanyInfo() {
        ExternalService externalService = new ExternalService();
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        
        String companyInfo = adapter.getCompanyInfo();
        
        assertNotNull(companyInfo);
        assertTrue(companyInfo.contains("Empresa:"));
    }
    
    @Test
    void testAdapterValidateCompanyData() {
        ExternalService externalService = new ExternalService();
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        
        boolean isValid = adapter.validateCompanyData();
        
        // Depende de la conexion simulada
        assertTrue(isValid);
    }
    
    @Test
    void testCompanyServiceWithAdapter() {
        ExternalService externalService = new ExternalService();
        ExternalServiceAdapter adapter = new ExternalServiceAdapter(externalService);
        CompanyService companyService = new CompanyService(adapter);
        
        assertNotNull(companyService);
        // No deberia lanzar excepciones
        assertDoesNotThrow(() -> companyService.displayCompanyInfo());
    }
    
    @Test
    void testCompanyCreation() {
        Company company = new Company("Test Company", "Test Sector", "test@test.com", "123456789");
        
        assertEquals("Test Company", company.getName());
        assertEquals("Test Sector", company.getSector());
        assertEquals("test@test.com", company.getContactEmail());
        assertEquals("123456789", company.getPhone());
        
        assertNotNull(company.toString());
        assertNotNull(company.toFormattedString());
    }
}
