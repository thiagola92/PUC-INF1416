package Security;

import Database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Blocked {

    public static boolean isBlocked() throws Exception {
        String sql = "SELECT * FROM Registros WHERE " +
                "data >= DATETIME(CURRENT_TIMESTAMP, '-2 minutes') " +
                "AND usuario = ? " +
                "AND (registro = 3007 OR registro = 4007);";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(sql);
        preparedStatement.setString(1, Validation1.user.getString("email"));
        ResultSet resultSet = preparedStatement.executeQuery();

        // resultset close == didn't find anything block
        if(resultSet.isClosed()) {
            if(shouldResetCounter())
                setFails(0);

            if(getFails() >= 3)
                setFails(0);

            return false;
        }

        return true;
    }

    public static boolean shouldResetCounter() throws Exception {
        String sql = "SELECT * FROM Registros WHERE " +
                "data >= DATETIME(CURRENT_TIMESTAMP, '-2 minutes') " +
                "AND usuario = ? " +
                "AND (registro = 3004 OR registro = 3005);";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(sql);
        preparedStatement.setString(1, Validation1.user.getString("email"));
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.isClosed();
    }

    public static void setFails(int fails) throws Exception {
        String query = "UPDATE Usuarios SET fails = ? WHERE email = ?;";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.setInt(1, fails);
        preparedStatement.setString(2, Validation1.user.getString("email"));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static int getFails() throws Exception {
        String sql = "SELECT fails FROM Usuarios WHERE email = ?";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(sql);
        preparedStatement.setString(1, Validation1.user.getString("email"));
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.getInt("fails");
    }
}
