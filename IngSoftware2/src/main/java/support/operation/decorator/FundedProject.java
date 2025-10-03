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
 * Decorador concreto para proyectos con financiamiento externo
 */
public class FundedProject extends ProjectDecorator {
    
    private String fundingSource;
    private double fundingAmount;
    private String grantNumber;
    
    public FundedProject(ProjectModel project, String source, double amount, String grant) {
        super(project);
        this.fundingSource = source;
        this.fundingAmount = amount;
        this.grantNumber = grant;
    }
    
    @Override
    public String getTitle() {
        return "[FINANCIADO] " + decoratedProject.getTitle() + 
               " (Financiamiento: " + fundingSource + ")";
    }
    
    @Override
    public String getObservations() {
        String originalObservations = decoratedProject.getObservations();
        String fundingNote = " --- FINANCIAMIENTO: " + fundingSource + 
                           " - Monto: $" + String.format("%,.2f", fundingAmount) + 
                           " - Convocatoria: " + grantNumber + " ---";
        
        if (originalObservations == null || originalObservations.isEmpty()) {
            return fundingNote;
        } else {
            return originalObservations + fundingNote;
        }
    }
    
    @Override
    public String getDecoratedDescription() {
        return "Proyecto financiado: " + decoratedProject.getTitle() + 
               " - Fuente: " + fundingSource + 
               " - Monto: $" + String.format("%,.2f", fundingAmount);
    }
    
    // Metodos adicionales especificos del decorador
    public String getFundingSource() {
        return fundingSource;
    }
    
    public double getFundingAmount() {
        return fundingAmount;
    }
    
    public String getGrantNumber() {
        return grantNumber;
    }
    
    public void setFundingSource(String source) {
        this.fundingSource = source;
    }
    
    public void setFundingAmount(double amount) {
        this.fundingAmount = amount;
    }
    
    public void setGrantNumber(String grant) {
        this.grantNumber = grant;
    }
    
    @Override
    public String toString() {
        String originalString = decoratedProject.toString();
        return originalString.replace(
            "ProjectModel", 
            "ProjectModel [FINANCIADO - " + fundingSource + " - $" + 
            String.format("%,.2f", fundingAmount) + "]"
        );
    }
}
