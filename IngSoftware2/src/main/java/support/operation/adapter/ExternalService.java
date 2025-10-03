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
 * Servicio externo que proporciona datos en formato JSON
 * Adaptee en el patron Adapter - interfaz incompatible
 */
public class ExternalService {
    
    // Simula servicio externo que retorna datos en JSON
    public String getCompanyDataAsJson() {
        // JSON con datos de empresa
        return "{\"company_name\":\"Empaques del Cauca S.A.\"," +
               "\"industry\":\"Manufactura\"," +
               "\"contact_email\":\"contacto@empaquesdelcauca.com.co\"," +
               "\"contact_phone\":\"+57 2823456789\"," +
               "\"address\":\"Calle 5 # 10-45, Popayan, Cauca\"," +
               "\"foundation_year\":\"1995\"}";
    }
    
    public String getCompanyDataAsXml() {
        // Alternativa en XML
        return "<company>" +
               "<name>Industrias Agricolas del Sur</name>" +
               "<sector>Agroindustria</sector>" +
               "<email>info@indagricolas.com.co</email>" +
               "<phone>+57 2823512345</phone>" +
               "</company>";
    }
    
    public String getExternalServiceInfo() {
        return "Servicio externo de datos empresariales v2.1";
    }
    
    // Metodo que simula conexion al servicio externo
    public boolean connectToService() {
        System.out.println("Conectando al servicio externo de empresas...");
        // Simular conexion exitosa
        return Math.random() > 0.1; // 90% de exito
    }
}
