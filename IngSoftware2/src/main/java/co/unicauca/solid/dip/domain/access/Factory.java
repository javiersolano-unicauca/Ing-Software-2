package co.unicauca.solid.dip.domain.access;

//import access.iConnectionDB;

import co.unicauca.solid.dip.domain.interfaces.iConnectionDB;
import co.unicauca.solid.dip.domain.interfaces.iRepository;

//import access.iRepository;
//import repositories.ConnectorSQLite;

/**
 * Clase abstracta para la creacion de fabricas de tipo iRepository
 * @see iRepository
 * 
 * @param <ID>Tipo del identificador en las instancias del repositorio
 * 
 * @author javiersolanop777
 */
public abstract class Factory<ID> {
    
    /**
     * Almacena la instancia para la conexion con la DB para cualquier fabrica
     */
    public static iConnectionDB atrConnection = new ConnectorSQLite();
    
    /**
     * Almacena el nombre de la entidad en la DB asociada al respositorio
     */
    public String atrEntityName;
    
    /**
     * Almacena el id de la entidad en la DB asociada al repositorio
     */
    public ID atrId;
    
    /**
     * Metodo para iniciar la entidad en la DB
     */
    protected abstract void entityInit();
    
    /**
     * Metodo para obtener la implementacion de un repositorio particular
     * 
     * @return La instancia de la implementacion
     */
    public abstract iRepository<ID> getRespositoryFactory();
}
