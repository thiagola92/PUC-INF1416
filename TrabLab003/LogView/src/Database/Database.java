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

        String url = "jdbc:sqlite:../Code/database/trab3.db";
//        String url = "jdbc:sqlite:C:/Users/Lucas/Documents/PUC/PUC-INF1416-Trab3/Code/database/trab3.db";
        //Class.forName(url);
        database.connection = DriverManager.getConnection(url);

        return database;
    }
}
