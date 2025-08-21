package controller.validations;

import exceptions.ChainOfCharacterException;

/**
 *
 * @author javim
 */
public interface iChainOfCharacter {
    
    /**
     *  Metodo para validar si la candena contiene solamente letras
     * 
     *  @param prmChain Recibe la cadena
     * 
     *  @throws ChainOfCharacterException Si no contiene solamente letras
     */
    void containsLettersWithException(String prmChain) throws ChainOfCharacterException;
}
