package hu.neuron.webapp.database_connection;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
 * Létrehozzuk az adatbázissal a kapcsolatot.
 */
public class DatabaseConnect {
    public static final String URL = "jdbc:postgresql://localhost:5432/warehouse";
    public static final String USER = "postgres";
    public static final String PASS = "postgres";
    private static Connection connection = null;

    public static Connection getConnection()
    {
        try {
            /*
            * Ha a connection értéke null, akkor regisztrálunk egy drivert és létrehozzuk
            * a kapcsolatot a megadott paraméterek alapján
            */
            if(connection == null){
                Class.forName("org.postgresql.Driver");
                DriverManager.registerDriver(new Driver());
                connection = DriverManager.getConnection(URL, USER, PASS);
                return connection;
            }
            /*
             *Abban az esetben ha a connection nem null csak visszatérünk vele.
             */
            else
            {
                return connection;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

}
