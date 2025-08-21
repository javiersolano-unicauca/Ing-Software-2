package controller.validations;

import exceptions.ChainOfCharacterException;
import exceptions.ChainOfCharacterExceptionEnum;

/**
 * Clase para la implementacion de iChainOfCharacter
 * @see iChainOfCharacter
 * 
 * @author javiersolanop777
 */
public class ChainOfCharacter implements iChainOfCharacter {
    
    // Constructors:

    public ChainOfCharacter() 
    {}

    @Override
    public void containsLettersWithException(String prmChain) throws ChainOfCharacterException 
    {
        if(prmChain == null) return;

        for(int i = 0; i < prmChain.length(); i++){
            
            if(((prmChain.charAt(i) < 'A') || (prmChain.charAt(i) > 'Z')) &&
              ((prmChain.charAt(i) < 'a') || (prmChain.charAt(i) > 'z')) &&
              (prmChain.charAt(i) != ' '))
            {
                ChainOfCharacterException.throwException(true, ChainOfCharacterExceptionEnum.NOT_CONTAINS_LETTERS);
            }
        }
    }
}
