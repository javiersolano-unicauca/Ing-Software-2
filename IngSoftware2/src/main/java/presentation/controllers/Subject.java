package presentation.controllers;

import access.models.interfaces.iModel;
import java.util.LinkedList;
import java.util.List;
import presentation.interfaces.iObserver;

/**
 * Clase abstracta para el observado
 * 
 * @author javiersolanop777
 */
public abstract class Subject {
    
    /**
     * Almacena la lista de observadores
     */
    private List<iObserver> atrObservers;
    
    /**
     * Metodo para validar si no hay observadores
     * 
     * @return 'true' si no hay. De lo contrario 'false'
     */
    public boolean noneObserver()
    {
        return ((atrObservers == null) || atrObservers.isEmpty());
    }
    
    /**
     * Metodo para adicionar observadores 
     * 
     * @param prmObserver Recibe la referencia del observador
     */
    public void addObserver(iObserver prmObserver)
    {
        if(atrObservers == null) 
            atrObservers = new LinkedList<>();
        atrObservers.add(prmObserver);
    }
    
    /**
     * Metodo para notificar a un unico observador
     * 
     * @param prmObserver Recibe la referencia del ob
     * @param prmModel 
     */
    public void notifyOnly(Class<? extends iObserver> prmObserver, iModel prmModel)
    {
        if(atrObservers == null) return;
        
        for(iObserver objObserver: atrObservers)
        {
            if(objObserver.getClass().equals(prmObserver))
                objObserver.validateNotification(this, prmModel);
        }
    }
    
    /**
     * Metodo para notificar a todos los obervadores
     * 
     * @param prmModel Recibe la referencia del modelo
     */
    public void notifyObservers(iModel prmModel)
    {
        if(atrObservers == null) return;
        
        for(iObserver objObserver: atrObservers)
            objObserver.validateNotification(this, prmModel);
    }
    
    /**
     * Metodo para cargar los observadores
     */
    public abstract void observersLoader();
}
