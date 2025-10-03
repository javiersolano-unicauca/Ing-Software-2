/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.decorator;

import access.models.implement.ProjectModel;
/**
 *
 * @author yezid
 */

/**
 * Decorador concreto para proyectos con colaboracion internacional
 */
public class InternationalProject extends ProjectDecorator {
    
    private String partnerUniversity;
    private String country;
    
    public InternationalProject(ProjectModel project, String university, String country) {
        super(project);
        this.partnerUniversity = university;
        this.country = country;
    }
    
    @Override
    public String getTitle() {
        return "[INTERNACIONAL] " + decoratedProject.getTitle() + 
               " (Colaboracion: " + partnerUniversity + ")";
    }
    
    @Override
    public String getObservations() {
        String originalObservations = decoratedProject.getObservations();
        String internationalNote = " --- COLABORACION INTERNACIONAL con " + 
                                 partnerUniversity + " (" + country + ") ---";
        
        if (originalObservations == null || originalObservations.isEmpty()) {
            return internationalNote;
        } else {
            return originalObservations + internationalNote;
        }
    }
    
    @Override
    public String getDecoratedDescription() {
        return "Proyecto internacional: " + decoratedProject.getTitle() + 
               " - Universidad colaboradora: " + partnerUniversity + ", " + country;
    }
    
    // Metodos adicionales especificos del decorador
    public String getPartnerUniversity() {
        return partnerUniversity;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setPartnerUniversity(String university) {
        this.partnerUniversity = university;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        String originalString = decoratedProject.toString();
        return originalString.replace(
            "ProjectModel", 
            "ProjectModel [INTERNACIONAL - " + partnerUniversity + ", " + country + "]"
        );
    }
}
