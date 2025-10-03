/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package support.operation.adapter;

/**
 *
 * @author yezid
 */

/**
 * Interfaz que espera el sistema interno para obtener datos de empresas
 * Target interface en el patron Adapter
 */
public interface CompanyDataProvider {
    Company getCompany();
    String getCompanyName();
    String getCompanyInfo();
    boolean validateCompanyData();
}
