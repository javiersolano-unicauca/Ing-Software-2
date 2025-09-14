package access.repositories.implement;

import access.repositories.interfaces.iConnectionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugin.property_mapping.annotations.Property;
import plugin.property_mapping.annotations.PropertyMapping;

/**
 * @author javiersolanop777
 */
@PropertyMapping
public class ConnectorSQLite implements iConnectionDB {
    
    /**
     * Instancia para la conexion con la DB
     */
    private Connection atrConnection;
    
    @Property(property = "sqlite.url")
    private static String atrUrl;
    
    public ConnectorSQLite()
    {
        this.atrConnection = null;
    }
    
    @Override
    public void connect() 
    {
        if(this.atrConnection != null) return;

        try 
        {
            this.atrConnection = DriverManager.getConnection(atrUrl);
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
