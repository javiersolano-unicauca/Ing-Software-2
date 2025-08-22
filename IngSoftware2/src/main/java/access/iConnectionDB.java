package access;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Contrato que debe cumplir cualquier conector de DB
 * 
 * @author javiersolanop777
 */
public interface iConnectionDB {
    
    /**
     * Metodo para conectarse con la DB
     */
    void connect();
    
    /**
     * Metodo para desconectarse con la DB
     */
    void disconnect();
    
    /**
     * Metodo para obtener el preparador de consultas
     * 
     * @param prmSql Recibe la consulta a preparar
     * 
     * @return La instancia del preparador de consultas
     */
    PreparedStatement getStatement(String prmSql);
    
    /**
     * Metodo para crear una consulta
     * 
     * @return La instancia de la consulta
     */
    Statement createStatement();
}
