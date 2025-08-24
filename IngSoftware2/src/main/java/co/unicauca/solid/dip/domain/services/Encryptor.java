package co.unicauca.solid.dip.domain.services;

import co.unicauca.solid.dip.domain.interfaces.iEncryptor;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Clase para el manejo de encriptaciones de cadenas
 * 
 * @author javiersolnaop777
 */
public class Encryptor implements iEncryptor {
    
    // Constructors:
    
    public Encryptor()
    {}

    @Override
    public String encrypt(String prmChain) 
    {
        return BCrypt.hashpw(prmChain, BCrypt.gensalt());
    }

    @Override
    public boolean checkHash(String prmChain, String prmHash) 
    {
        return BCrypt.checkpw(prmChain, prmHash);
    }
}
