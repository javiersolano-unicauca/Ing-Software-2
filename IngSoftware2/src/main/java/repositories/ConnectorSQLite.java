package repositories;

import access.iConnectionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author javiersolanop777
 */
public class ConnectorSQLite implements iConnectionDB {
    
    /**
     * Instancia para la conexion con la DB
     */
    private Connection atrConnection;
    
    
    public ConnectorSQLite()
    {
        this.atrConnection = null;
    }
    
    @Override
    public void connect() 
    {
        if(this.atrConnection != null) return;
        
        String url = "jdbc:sqlite:IngSoftware2.db";

        try 
        {
            this.atrConnection = DriverManager.getConnection(url);
        } 
        catch(SQLException ex) 
        {
            Logger.getLogger(ConnectorSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void disconnect() 
    {
        try 
        {
            if(this.atrConnection != null) 
            {
                this.atrConnection.close();
                this.atrConnection = null;
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public PreparedStatement getStatement(String prmSql) 
    {
        try {
            return this.atrConnection.prepareStatement(prmSql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectorSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Statement createStatement() 
    {
        try {
            return this.atrConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectorSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
