package Security;

import Database.*;

import java.sql.ResultSet;

public class Validation1 {

    public static ResultSet user;

    public static boolean isEmailValid(String email) throws Exception {
        Validation1.user = DAO.getInstance().getUser(email);

        if(Validation1.user.isClosed())
            return false;

        return true;
    }
}
