package support.operation.builders;

import access.models.DefaultInformationEnum;
import access.models.ModalityEnum;
import access.models.StatusEnum;
import java.time.LocalDate;

/**
 * Clase para construir un proyecto de investigacion
 * 
 * @author javiersolanop777
 */
public class ResearchProjectBuilder extends ProjectBuilder {
    
    private final String ATR_TITLE;
    private final String ATR_DIRECTOR;
    private final String ATR_CODIRECTOR;
    private final String ATR_STUDENT_1;
    private final String ATR_STUDENT_2;
    private final String ATR_GENERAL_OBJECTIVE;
    private final String ATR_SPECIFIC_OBJECTIVES;
    private final String ATR_FILE_NAME;
    
    // Constructors:
    
    public ResearchProjectBuilder(
       String prmTitle,
       String prmDirector,
       String prmCodirector,
       String prmStudent1,
       String prmStudent2,
       String prmGeneralObjectives,
       String prmSpecificObjectives,
       String prmFileName
    )
    {
        this.ATR_TITLE = prmTitle;
        this.ATR_DIRECTOR = prmDirector;
        this.ATR_CODIRECTOR = prmCodirector;
        this.ATR_STUDENT_1 = prmStudent1;
        this.ATR_STUDENT_2 = prmStudent2;
        this.ATR_GENERAL_OBJECTIVE = prmGeneralObjectives;
        this.ATR_SPECIFIC_OBJECTIVES = prmSpecificObjectives;
        this.ATR_FILE_NAME = prmFileName;
    }

    @Override
    public void buildTitle() 
    {
        this.atrProjectModel.setTitle(this.ATR_TITLE);
    }

    @Override
    public void buildDate() 
    {
       this.atrProjectModel.setDate(LocalDate.now());
    }

    @Override
    public void buildDirector() 
    {
        this.atrProjectModel.setDirector(this.ATR_DIRECTOR);
    }

    @Override
    public void buildCodirector() 
    {
        this.atrProjectModel.setCodirector(this.ATR_CODIRECTOR);
    }

    @Override
    public void buildStudent1() 
    {
        this.atrProjectModel.setStudent1(this.ATR_STUDENT_1);
    }

    @Override
    public void buildStudent2() 
    {
        if(this.ATR_STUDENT_2 != null)
            this.atrProjectModel.setStudent2(this.ATR_STUDENT_2);
        else
            this.atrProjectModel.setStudent2(DefaultInformationEnum.NO.getName());
    }

    @Override
    public void buildModality() 
    {
        this.atrProjectModel.setModality(ModalityEnum.RESEARCH);
    }

    @Override
    public void buildGeneralObjective() 
    {
        this.atrProjectModel.setGeneralObjective(this.ATR_GENERAL_OBJECTIVE);
    }

    @Override
    public void buildSpecificObjectives() 
    {
        this.atrProjectModel.setSpecificObjectives(this.ATR_SPECIFIC_OBJECTIVES);
    }

    @Override
    public void buildStatus() 
    {
        this.atrProjectModel.setStatus(StatusEnum.FIRST);
    }

    @Override
    public void buildNumberOfAttempts() 
    {
       this.atrProjectModel.setNumberOfAttempts(1);
    }
    
    @Override
    public void buildObservations() 
    {
        this.atrProjectModel.setObservations(DefaultInformationEnum.IN_REVISION.getName());
    }

    @Override
    public void buildFileName() 
    {
        this.atrProjectModel.setFileName(this.ATR_FILE_NAME);
    }   
}
