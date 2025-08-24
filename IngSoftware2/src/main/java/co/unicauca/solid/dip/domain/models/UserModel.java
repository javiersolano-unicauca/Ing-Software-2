package co.unicauca.solid.dip.domain.models;

import co.unicauca.solid.dip.domain.interfaces.iModel;
import java.util.HashMap;
import java.util.Map;
import models.CareerEnum;
import models.RoleEnum;
//import repositories.iModel;

/**
 * @author javiersolanop777
 */
public class UserModel implements iModel {
    
    // Campos de la entidad en la DB
    
    private String atrNames;
    private String atrSurnames;
    private String atrEmail;
    private String atrPassword;
    private Long atrTelephone;
    private CareerEnum atrCareer;
    private RoleEnum atrRole;
    
    // Constructors:
    
    public UserModel()
    {
        atrNames = null;
        atrSurnames = null;
        atrEmail = null;
        atrPassword = null;
        atrTelephone =  null;
        atrCareer = null;
        atrRole = null;
    }
    
    public UserModel(
       String prmNames,
       String prmSurnames,
       String prmEmail,
       String prmPassword,
       Long prmTelephone,
       CareerEnum prmCareer,
       RoleEnum prmRole
    )
    {
        this.atrNames = prmNames;
        this.atrSurnames = prmSurnames;
        this.atrEmail = prmEmail;
        this.atrPassword = prmPassword;
        this.atrTelephone = prmTelephone;
        this.atrCareer = prmCareer;
        this.atrRole = prmRole;
    }
    
    // Metodos 'getter' y 'setter':
    
    public String getNames()
    {
        return this.atrNames;
    }
    
    public void setNames(String prmNames)
    {
        this.atrNames = prmNames;
    }
    
    public String getSurnames()
    {
        return this.atrSurnames;
    }
    
    public void setSurnames(String prmSurnames)
    {
        this.atrSurnames = prmSurnames;
    }
    
    public String getEmail()
    {
        return this.atrEmail;
    }
    
    public void setEmail(String prmEmail)
    {
        this.atrEmail = prmEmail;
    }
    
    public String getPassword()
    {
        return this.atrPassword;
    }
    
    public void setPassword(String prmPassword)
    {
        this.atrPassword = prmPassword;
    }
    
    public Long getTelephone()
    {
        return this.atrTelephone;
    }
    
    public void setTelephone(Long prmTelephone)
    {
        this.atrTelephone = prmTelephone;
    }
    
    public CareerEnum getCareer()
    {
        return this.atrCareer;
    }
    
    public void setCareer(CareerEnum prmCareer)
    {
        this.atrCareer = prmCareer;
    }
    
    public RoleEnum getRole()
    {
        return this.atrRole;
    }
    
    public void setRole(RoleEnum prmRole)
    {
        this.atrRole = prmRole;
    }

    @Override
    public boolean validateFields() 
    {
        if((atrNames == null) || atrNames.isEmpty()) return false;
        if((atrSurnames == null) || atrSurnames.isEmpty()) return false;
        if((atrEmail == null) || atrEmail.isEmpty()) return false;
        if((atrPassword == null) || atrPassword.isEmpty()) return false;
        if(atrTelephone == null) return false;
        if(atrCareer == null) return false;
        return atrRole != null;
    }

    @Override
    public Map<String, Object> getFields() 
    {
        if(!validateFields()) return null;
        
        Map<String, Object> listFields = new HashMap<>();
        
        listFields.put("names", this.atrNames);
        listFields.put("surnames", this.atrSurnames);
        listFields.put("email", this.atrEmail);
        listFields.put("password", this.atrPassword);
        listFields.put("telephone", this.atrTelephone);
        listFields.put("career", this.atrCareer);
        listFields.put("role", this.atrRole);
        
        return listFields;
    }
}
