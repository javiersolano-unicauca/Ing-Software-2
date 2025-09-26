package support.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que implenta el contrato de la interfaz 'iEmailManager'
 * @see iEmailManager
 * 
 * @author javiersolanop777
 */
public class EmailManager implements iEmailManager {
    
    /**
     * Almacena el simulador de envios de correos
     */
    private final Logger ATR_LOGGER; 
    
    // Constructors:
    
    public EmailManager()
    {
        ATR_LOGGER = LoggerFactory.getLogger(EmailManager.class);
    }
    
    private String buildEmail(
        String prmRecipient, 
        String prmSubject, 
        String prmBody
    )
    {
        return "\n\nPARA: " + prmRecipient
                + "\nASUNTO: " + prmSubject
                + "\nCUERPO: " + prmBody;
    }

    @Override
    public void send(
        String prmRecipient, 
        String prmSubject, 
        String prmBody
    ) 
    {
        ATR_LOGGER.info(
            buildEmail(prmRecipient, prmSubject, prmBody)
        );
    }
}
