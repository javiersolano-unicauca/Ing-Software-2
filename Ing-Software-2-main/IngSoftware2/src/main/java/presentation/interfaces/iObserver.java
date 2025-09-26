package presentation.interfaces;

import access.models.interfaces.iModel;
import presentation.controllers.Subject;

/**
 * Contrato para los observadores
 * 
 * @author javiersolanop777
 */
public interface iObserver {
    
    /**
     * Metodo para validar que la notificacion recibida
     * requiera cambio de estado en el observador
     * 
     * @param prmSubject Recibe la referencia del observado
     * @param prmModel Recibe la referencia del modelo
     */
    void validateNotification(Subject prmSubject, iModel prmModel);
}
