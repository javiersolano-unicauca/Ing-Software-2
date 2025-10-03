/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package support.operation.decorator;

import access.models.implement.ProjectModel;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import access.models.implement.ProjectModelPK;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author yezid
 */
public abstract class ProjectDecorator extends ProjectModel {
    
    protected ProjectModel decoratedProject;
    
    public ProjectDecorator(ProjectModel project) {
        this.decoratedProject = project;
    }
    
    // Delegar todos los metodos al proyecto decorado
    @Override
    public ProjectModelPK getId() {
        return decoratedProject.getId();
    }
    
    @Override
    public void setId(ProjectModelPK prmId) {
        decoratedProject.setId(prmId);
    }
    
    @Override
    public LocalDate getDate() {
        return decoratedProject.getDate();
    }

    @Override
    public void setDate(LocalDate prmDate) {
        decoratedProject.setDate(prmDate);
    }

    @Override
    public String getDirector() {
        return decoratedProject.getDirector();
    }

    @Override
    public void setDirector(String prmDirector) {
        decoratedProject.setDirector(prmDirector);
    }
    
    @Override
    public String getStudent1() {
        return decoratedProject.getStudent1();
    }
    
    @Override
    public void setStudent1(String prmStudent1) {
        decoratedProject.setStudent1(prmStudent1);
    }

    @Override
    public String getTitle() {
        return decoratedProject.getTitle();
    }

    @Override
    public void setTitle(String prmTitle) {
        decoratedProject.setTitle(prmTitle);
    }

    @Override
    public ModalityEnum getModality() {
        return decoratedProject.getModality();
    }

    @Override
    public void setModality(ModalityEnum prmModality) {
        decoratedProject.setModality(prmModality);
    }
    
    @Override
    public String getCodirector() {
        return decoratedProject.getCodirector();
    }

    @Override
    public void setCodirector(String prmCodirector) {
        decoratedProject.setCodirector(prmCodirector);
    }
    
    @Override
    public String getStudent2() {
        return decoratedProject.getStudent2();
    }
    
    @Override
    public void setStudent2(String prmStudent2) {
        decoratedProject.setStudent2(prmStudent2);
    }

    @Override
    public String getGeneralObjective() {
        return decoratedProject.getGeneralObjective();
    }

    @Override
    public void setGeneralObjective(String prmGeneralObjective) {
        decoratedProject.setGeneralObjective(prmGeneralObjective);
    }

    @Override
    public String getSpecificObjectives() {
        return decoratedProject.getSpecificObjectives();
    }

    @Override
    public void setSpecificObjectives(String prmSpecificObjectives) {
        decoratedProject.setSpecificObjectives(prmSpecificObjectives);
    }

    @Override
    public StatusEnum getStatus() {
        return decoratedProject.getStatus();
    }

    @Override
    public void setStatus(StatusEnum prmStatus) {
        decoratedProject.setStatus(prmStatus);
    }

    @Override
    public Integer getNumberOfAttempts() {
        return decoratedProject.getNumberOfAttempts();
    }

    @Override
    public void setNumberOfAttempts(Integer prmNumberOfAttempts) {
        decoratedProject.setNumberOfAttempts(prmNumberOfAttempts);
    }
    
    @Override
    public String getObservations() {
        return decoratedProject.getObservations();
    }
    
    @Override
    public void setObservations(String prmObservations) {
        decoratedProject.setObservations(prmObservations);
    }
    
    @Override
    public String getFileName() {
        return decoratedProject.getFileName();
    }
    
    @Override
    public void setFileName(String prmFileName) {
        decoratedProject.setFileName(prmFileName);
    }
    
    @Override
    public boolean validateFields() {
        return decoratedProject.validateFields();
    }

    @Override
    public Map<String, Object> getFields() {
        return decoratedProject.getFields();
    }

    @Override
    public String toString() {
        return decoratedProject.toString();
    }
    
    // Metodo abstracto para decoradores concretos
    public abstract String getDecoratedDescription();
}
