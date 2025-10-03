/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.adapter;

import org.json.JSONObject;
/**
 *
 * @author yezid
 */

/**
 * Adaptador que convierte la interfaz del servicio externo a la interfaz esperada por el sistema
 * Adapter en el patron Adapter
 */
public class ExternalServiceAdapter implements CompanyDataProvider {
    
    private ExternalService externalService;
    
    public ExternalServiceAdapter(ExternalService externalService) {
        this.externalService = externalService;
    }
    
    @Override
    public Company getCompany() {
        try {
            String jsonData = externalService.getCompanyDataAsJson();
            JSONObject jsonObject = new JSONObject(jsonData);
            
            // Mapear campos JSON a objeto Company
            String name = jsonObject.getString("company_name");
            String sector = jsonObject.getString("industry");
            String email = jsonObject.getString("contact_email");
            String phone = jsonObject.getString("contact_phone");
            
            return new Company(name, sector, email, phone);
            
        } catch (Exception e) {
            System.out.println("Error adaptando datos del servicio externo: " + e.getMessage());
            return new Company("Empresa no disponible");
        }
    }
    
    @Override
    public String getCompanyName() {
        Company company = getCompany();
        return company.getName();
    }
    
    @Override
    public String getCompanyInfo() {
        Company company = getCompany();
        return company.toFormattedString();
    }
    
    @Override
    public boolean validateCompanyData() {
        try {
            Company company = getCompany();
            return company != null && 
                   company.getName() != null && 
                   !company.getName().equals("Empresa no disponible") &&
                   externalService.connectToService();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Metodos adicionales para mayor flexibilidad
    public Company getCompanyFromXml() {
        try {
            String xmlData = externalService.getCompanyDataAsXml();
            // Simulacion simple de parseo XML (en realidad se usaria una libreria XML)
            String name = extractXmlValue(xmlData, "name");
            String sector = extractXmlValue(xmlData, "sector");
            String email = extractXmlValue(xmlData, "email");
            String phone = extractXmlValue(xmlData, "phone");
            
            return new Company(name, sector, email, phone);
            
        } catch (Exception e) {
            System.out.println("Error procesando XML: " + e.getMessage());
            return new Company("Empresa no disponible desde XML");
        }
    }
    
    private String extractXmlValue(String xml, String tag) {
        // Simulacion simple de extraccion de valores XML
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        int start = xml.indexOf(startTag) + startTag.length();
        int end = xml.indexOf(endTag);
        if (start >= 0 && end >= 0 && start < end) {
            return xml.substring(start, end);
        }
        return "No especificado";
    }
    
    public String getServiceInfo() {
        return externalService.getExternalServiceInfo();
    }
}
