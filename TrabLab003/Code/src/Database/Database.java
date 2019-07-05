package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    public Connection connection = null;

    private static Database database = null;

    private Database() {

    }

    public static Database getInstance() throws Exception {
        if(database != null)
            return database;

        database = new Database();

        String url = "jdbc:sqlite:database/trab3.db";
  //      String url = "jdbc:sqlite:C:/Users/Lucas/Documents/PUC/PUC-INF1416-Trab3/Code/database/trab3.db";
//        String url = "jdbc:sqlite:C:/Users/Lucas/Documents/PUC/PUC-INF1416-Trab3/Code/database/backup.db";

        database.connection = DriverManager.getConnection(url);

        return database;
    }

    public static void log(int registro) throws Exception {
        String sql = "INSERT INTO Registros(registro) VALUES (?)";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(sql);
        preparedStatement.setInt(1, registro);
        preparedStatement.executeUpdate();
    }

    public static void log(int registro, String usuario) throws Exception {
        String sql = "INSERT INTO Registros(registro, usuario) VALUES (?, ?)";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(sql);
        preparedStatement.setInt(1, registro);
        preparedStatement.setString(2, usuario);
        preparedStatement.executeUpdate();
    }

    public static void log(int registro, String arquivo, String usuario) throws Exception {
        String sql = "INSERT INTO Registros(registro, arquivo, usuario) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(sql);
        preparedStatement.setInt(1, registro);
        preparedStatement.setString(2, arquivo);
        preparedStatement.setString(3, usuario);
        preparedStatement.executeUpdate();
    }
}
