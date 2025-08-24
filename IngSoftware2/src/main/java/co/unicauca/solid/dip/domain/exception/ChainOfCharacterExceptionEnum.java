package co.unicauca.solid.dip.domain.exception;

/**
 * Clase enumeradora para los tipos de excepcion que puede 
 * tener un a impmentacion de iChainOfCharacter
 * @see iChainOfCharacter
 * 
 * @author javiersolanop777
 */
public enum ChainOfCharacterExceptionEnum {
    
    NOT_CONTAINS_LETTERS("Debe contener solamente letras!"),
    NOT_NUMBER_INTEGER("Debe ser un numero entero!"),
    NOT_NUMBER_REAL("Debe ser un numero real!"),
    NOT_PATTERN("No contiene un patron permitido!");
    
    private String atrName;

    private ChainOfCharacterExceptionEnum(String prmName)
    {
        this.atrName = prmName;
    }
    
    /**
     * Metodo para obtener el nombre del campo que lanza la excepcion
     * 
     * @return El nombre
     */
    public String getName()
    {
        return this.atrName;
    }
}
