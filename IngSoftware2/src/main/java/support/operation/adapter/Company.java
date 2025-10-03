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
 * Clase que representa una empresa en el sistema interno
 */
public class Company {
    private String name;
    private String sector;
    private String contactEmail;
    private String phone;
    
    public Company(String name) {
        this.name = name;
        this.sector = "No especificado";
        this.contactEmail = "No especificado";
        this.phone = "No especificado";
    }
    
    public Company(String name, String sector, String contactEmail, String phone) {
        this.name = name;
        this.sector = sector;
        this.contactEmail = contactEmail;
        this.phone = phone;
    }
    
    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    
    public String toFormattedString() {
        return "Empresa: " + name + 
               "\nSector: " + sector + 
               "\nEmail de contacto: " + contactEmail + 
               "\nTelefono: " + phone;
    }
}
