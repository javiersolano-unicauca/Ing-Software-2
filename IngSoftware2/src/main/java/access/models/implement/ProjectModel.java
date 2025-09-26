package access.models.implement;

import access.models.ModalityEnum;
import access.models.StatusEnum;
import access.models.interfaces.iModel;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author javiersolanop777
 */
public class ProjectModel implements iModel {
    
    // Campos de la entidad en la DB
    
    private ProjectModelPK atrId;
    private String atrTitle;
    private ModalityEnum atrModality;
    private String atrCodirector;
    private String atrStudent2;
    private String atrGeneralObjective;
    private String atrSpecificObjectives;
    private StatusEnum atrStatus;
    private Integer atrNumberOfAttempts;
    private String atrObservations;
    private String atrFileName;
    
    // Constructors:
    
    public ProjectModel()
    {
        this.atrId = new ProjectModelPK();
        this.atrTitle = null;
        this.atrModality = null;
        this.atrCodirector = null;
        this.atrStudent2 = null;
        this.atrGeneralObjective = null;
        this.atrSpecificObjectives = null;
        this.atrStatus = null;
        this.atrNumberOfAttempts = null;
        this.atrObservations = null;
        this.atrFileName = null;
    }
    
    public ProjectModel(
        String prmTitle,
        LocalDate prmDate,
        String prmDirector,
        String prmCodirector,
        String prmStudent1,
        String prmStudent2,
        ModalityEnum prmModality,
        String prmGeneralObjective,
        String prmSpecificObjectives,
        StatusEnum prmStatus,
        Integer prmNumberOfAttempts,
        String prmObservations,
        String prmFileName
    )
    {
        this.atrId = new ProjectModelPK(
            prmDate, 
            prmDirector, 
            prmStudent1
        );
        this.atrTitle = prmTitle;
        this.atrModality = prmModality;
        this.atrCodirector = prmCodirector;
        this.atrStudent2 = prmStudent2;
        this.atrGeneralObjective = prmGeneralObjective;
        this.atrSpecificObjectives = prmSpecificObjectives;
        this.atrStatus = prmStatus;
        this.atrNumberOfAttempts = prmNumberOfAttempts;
        this.atrObservations = prmObservations;
        this.atrFileName = prmFileName;
    }
    
    // Metodos 'getter' y 'setter':
    
    public ProjectModelPK getId()
    {
        return this.atrId;
    }
    
    public void setId(ProjectModelPK prmId)
    {
        this.atrId = prmId;
    }
    
     public LocalDate getDate() 
    {
        return this.atrId.getDate();
    }

    public void setDate(LocalDate prmDate) 
    {
        this.atrId.setDate(prmDate);
    }

    public String getDirector() 
    {
        return this.atrId.getDirector();
    }

    public void setDirector(String prmDirector) 
    {
        this.atrId.setDirector(prmDirector);
    }
    
    public String getStudent1()
    {
        return this.atrId.getStudent1();
    }
    
    public void setStudent1(String prmStudent1)
    {
        this.atrId.setStudent1(prmStudent1);
    }

    public String getTitle() 
    {
        return this.atrTitle;
    }

    public void setTitle(String prmTitle) 
    {
        this.atrTitle = prmTitle;
    }

    public ModalityEnum getModality() 
    {
        return this.atrModality;
    }

    public void setModality(ModalityEnum prmModality) 
    {
        this.atrModality = prmModality;
    }
    
    public String getCodirector() 
    {
        return this.atrCodirector;
    }

    public void setCodirector(String prmCodirector) 
    {
        this.atrCodirector = prmCodirector;
    }
    
    public String getStudent2()
    {
        return this.atrStudent2;
    }
    
    public void setStudent2(String prmStudent2)
    {
        this.atrStudent2 = prmStudent2;
    }

    public String getGeneralObjective() 
    {
        return this.atrGeneralObjective;
    }

    public void setGeneralObjective(String prmGeneralObjective) 
    {
        this.atrGeneralObjective = prmGeneralObjective;
    }

    public String getSpecificObjectives() 
    {
        return this.atrSpecificObjectives;
    }

    public void setSpecificObjectives(String prmSpecificObjectives) 
    {
        this.atrSpecificObjectives = prmSpecificObjectives;
    }

    public StatusEnum getStatus() 
    {
        return this.atrStatus;
    }

    public void setStatus(StatusEnum prmStatus) 
    {
        this.atrStatus = prmStatus;
    }

    public Integer getNumberOfAttempts() 
    {
        return atrNumberOfAttempts;
    }

    public void setNumberOfAttempts(Integer prmNumberOfAttempts) 
    {
        this.atrNumberOfAttempts = prmNumberOfAttempts;
    }
    
    public String getObservations()
    {
        return this.atrObservations;
    }
    
    public void setObservations(String prmObservations)
    {
        this.atrObservations = prmObservations;
    }
    
    public String getFileName()
    {
        return this.atrFileName;
    }
    
    public void setFileName(String prmFileName)
    {
        this.atrFileName = prmFileName;
    }
    
    @Override
    public boolean validateFields() 
    {
        if(!this.atrId.validateFields()) return false;
        if((this.atrTitle == null) || this.atrTitle.isEmpty()) return false;
        if(this.atrModality == null) return false;
        if((this.atrCodirector == null) || this.atrCodirector.isEmpty()) return false;
        if((this.atrStudent2 == null) || this.atrStudent2.isEmpty()) return false;
        if((this.atrGeneralObjective == null) || this.atrGeneralObjective.isEmpty()) return false;
        if((this.atrSpecificObjectives == null) || this.atrSpecificObjectives.isEmpty()) return false;
        if(this.atrStatus == null) return false;
        if(this.atrNumberOfAttempts == null) return false;
        if((this.atrObservations == null) || this.atrObservations.isEmpty()) return false;
        return this.atrFileName != null;
    }

    @Override
    public Map<String, Object> getFields() 
    {
        if(!validateFields()) return null;
        
        Map<String, Object> listFields = new HashMap<>();
        
        listFields.putAll(this.atrId.getFields());
        listFields.put("title", this.atrTitle);
        listFields.put("modality", this.atrModality);
        listFields.put("codirector", this.atrCodirector);
        listFields.put("student2", this.atrStudent2);
        listFields.put("general_objective", this.atrGeneralObjective);
        listFields.put("specific_objectives", this.atrSpecificObjectives);
        listFields.put("status", this.atrStatus);
        listFields.put("number_of_attempts", this.atrNumberOfAttempts);
        listFields.put("observations", this.atrObservations);
        listFields.put("file_name", this.atrFileName);
        
        return listFields;
    }

    @Override
    public String toString() 
    {
        return "\nProjectModel(\n"
               + "\n\tTitle: " + this.atrTitle
               + "\n\tDate: " + this.atrId.getDate().toString()
               + "\n\tDirector: " + this.atrId.getDirector()
               + "\n\tCodirector: " + this.atrCodirector
               + "\n\tStudent1: " + this.atrId.getStudent1()
               + "\n\tStudent2: " + this.atrStudent2
               + "\n\tModality: " + this.atrModality.getName()
               + "\n\tGeneral objective: " + this.atrGeneralObjective
               + "\n\tSpecific objectives: " + this.atrSpecificObjectives
               + "\n\tStatus: " + this.atrStatus.getName()
               + "\n\tNumber of attempts: " + this.atrNumberOfAttempts
               + "\n\tObservations: " + this.atrObservations
               + "\n\tFile name: " + this.atrFileName
               + "\n)\n";
    }
}
