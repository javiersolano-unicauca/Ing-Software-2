package support.communication;

/**
 * Contrato que debe cumplir cualquier clase manejadora
 * de correos electronicos
 * 
 * @author javiersolanop777
 */
public interface iEmailManager {
    
    /**
     * Metodo para enviar correos
     * 
     * @param prmRecipient Recibe el destinatario
     * @param prmSubject Recibe el asunto
     * @param prmBody Recibe el cuerpo de correo
     */
    void send(
        String prmRecipient, 
        String prmSubject, 
        String prmBody
    );
}
