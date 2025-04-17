package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private static String url;
    private final String username;
    private final String password;

    private DaoFactory(String url, String username, String password)
    {
        DaoFactory.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance(String database, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trouvé !");
        }

        url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&serverTimezone=UTC";
        return new DaoFactory(url, username, password);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOImpl(this);
    }
}
