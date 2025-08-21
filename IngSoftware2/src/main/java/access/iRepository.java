package access;

import java.util.List;
import repositories.iModel;

/**
 * @author javiersolanop777
 * 
 * @param <ID>Tipo del identificador en las instancias del repositorio
 */
public interface iRepository<ID> {
    
    /**
     * Metodo para guardar en la DB la instancia del modelo 
     * recibido
     * 
     * @param prmModel Instancia del modelo asociado al respositorio
     * 
     * @return 'true' si se guarda. 'false' si no
     */
    boolean save(iModel prmModel);
    
    /**
     * Metodo para listar todas las instancias almacenadas por 
     * el repositorio
     * 
     * @return La lista de instancias si hay. De lo contrario null
     */
    List<iModel> list();
    
    /**
     * Metodo para obtener una instancia almacenada en el repositorio,
     * filtrando por el id
     * 
     * @param prmId Recibe el valor del id 
     * 
     * @return La instancia si existe. De lo contrario null
     */
    iModel getById(ID prmId);
}
