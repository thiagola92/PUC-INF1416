
package _System_;

import AuthenticationFrames.KeyVerificationFrame.KeyVerificationFrame;
import AuthenticationFrames.LoginFrame.*;
import AuthenticationFrames.PasswordFrame.*;
import Database.Database;

import javax.xml.bind.DatatypeConverter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.PreparedStatement;

public class Main {

    public static LoginFrame loginFrame;
    public static PasswordFrame passwordFrame;
    public static KeyVerificationFrame keyVerificationFrame;

    public static void main(String[] args) throws Exception {
        Database.log(1001);
        loginFrame = new LoginFrame();
    }

}
