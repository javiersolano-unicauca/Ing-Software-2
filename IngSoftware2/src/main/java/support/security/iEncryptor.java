package support.security;

/**
 * Contrato para clases encriptadoras
 * 
 * @author javiersolanop777
 */
public interface iEncryptor {
    
    /**
     * Metodo para encriptar una cadena
     * 
     * @param prmChain Recibe la cadena
     * 
     * @return El hash asociado a la cadena
     */
    String encrypt(String prmChain);
    
    /**
     * Metodo para chequear si un hash esta asociado a una cadena
     * 
     * @param prmChain Recibe la cadena
     * @param prmHash Recibe el hash
     * 
     * @return 'true' si el hash esta asociado. 'false' si no
     */
    boolean checkHash(String prmChain, String prmHash);
}
