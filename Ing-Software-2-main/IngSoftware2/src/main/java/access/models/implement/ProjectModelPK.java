package access.models.implement;

import access.models.interfaces.iModel;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Clave compuesta del modelo ProjectModel
 * @see ProjectModel
 * 
 * @author javiersolanop777
 */
public class ProjectModelPK implements iModel {
    
    // Campos de que componen la clave de la entidad en la DB
    
    private LocalDate atrDate;
    private String atrDirector;
    private String atrStudent1;
    
    // Constructors:
    
    public ProjectModelPK()
    {
        atrDate = null;
        atrDirector = null;
        atrStudent1 = null;
    }
    
    public ProjectModelPK(
        LocalDate prmDate,
        String prmDirector,
        String prmStudent1
    )
    {
        atrDate = prmDate;
        atrDirector = prmDirector;
        atrStudent1 = prmStudent1;
    }
    
    public LocalDate getDate() 
    {
        return atrDate;
    }

    public void setDate(LocalDate prmDate) 
    {
        this.atrDate = prmDate;
    }

    public String getDirector() 
    {
        return atrDirector;
    }

    public void setDirector(String prmDirector) 
    {
        this.atrDirector = prmDirector;
    }
    
    public String getStudent1()
    {
        return this.atrStudent1;
    }
    
    public void setStudent1(String prmStudent1)
    {
        this.atrStudent1 = prmStudent1;
    }
    
    @Override
    public boolean validateFields() 
    {
        if(this.atrDate == null) return false;
        if((this.atrDirector == null) || this.atrDirector.isEmpty()) return false;
        return ((this.atrStudent1 != null) && !this.atrStudent1.isEmpty());
    }

    @Override
    public Map<String, Object> getFields() 
    {
        if(!validateFields()) return null;
        
        Map<String, Object> listFields = new HashMap<>();
        
        listFields.put("date", this.atrDate.toString());
        listFields.put("director", this.atrDirector);
        listFields.put("student1", this.atrStudent1);
        
        return listFields;
    }
}
