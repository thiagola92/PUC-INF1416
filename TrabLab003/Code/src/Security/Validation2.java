package Security;

import Database.Database;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Validation2 {

    private static ArrayList<Integer[]> passwordPossibilities;

    public static boolean guessPassword(ArrayList<Integer[]> passwordNumbers) throws Exception {
        passwordPossibilities = passwordNumbers;

        if(guessAllSequences("", 0))
            return true;

        return false;
    }

    private static boolean guessAllSequences(String sequence, int index) throws Exception {
        if(passwordPossibilities.size() == index)
            return guess(sequence);

        if(guessAllSequences(sequence + passwordPossibilities.get(index)[0], index + 1))
            return true;

        if(guessAllSequences(sequence + passwordPossibilities.get(index)[1], index + 1))
            return true;

        return false;
    }

    private static boolean guess(String sequenceNumber) throws Exception {
        String salt = Validation1.user.getString("salt");

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update((sequenceNumber + salt).getBytes());

        String digestCurrent = DatatypeConverter.printHexBinary(messageDigest.digest());
        String digest = Validation1.user.getString("digest");

        if(digest.equals(digestCurrent))
            return true;

        return false;
    }
}
