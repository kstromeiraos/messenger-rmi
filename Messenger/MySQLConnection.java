

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

    private final Connection connection;

    public MySQLConnection() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "1234");
        properties.put("autoReconnect", "true");
        properties.put("characterEncoding", "UTF-8");
        properties.put("useUnicode", "true");
        properties.put("connectionCollation", "utf8_general_ci");
        properties.put("characterSetResults", "utf8");
        String url = "jdbc:mysql://23.94.38.122:3306/messenger";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("driver got");
        this.connection = DriverManager.getConnection(url, properties);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
