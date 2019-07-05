package Security;

import MenuFrame.MenuFrame;
import Database.Database;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Signature;

public class SecurityFile {

    public static Key getSecretKey(String filePath) throws Exception {
        Path path = Paths.get(filePath + ".env");
        byte[] fileBytes = Files.readAllBytes(path);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, Validation3.privateKey);
        byte[] seed = cipher.doFinal(fileBytes);

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, secureRandom);

        return keyGenerator.generateKey();
    }

    public static byte[] getFileContent(String filePath, Key secretKey) throws Exception {
        try {
            Path path = Paths.get(filePath + ".enc");
            byte[] fileBytes = Files.readAllBytes(path);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return cipher.doFinal(fileBytes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MenuFrame.getInstance(), "Falha na decriptacao");
            Database.log(8015, filePath, Validation1.user.getString("email"));
            e.printStackTrace();
            throw new Exception("Falha na decriptacao");
        }
    }

    public static boolean isSignatureFine(String filePath, byte[] fileContent) throws Exception {
        Path path = Paths.get(filePath + ".asd");
        byte[] fileSignature = Files.readAllBytes(path);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(Validation3.publicKey);
        signature.update(fileContent);

        return signature.verify(fileSignature);
    }

    public static byte[] readFile(String filePath) throws Exception {
        Key key = getSecretKey(filePath);
        byte[] fileContent = getFileContent(filePath, key);

        Database.log(8013, filePath, Validation1.user.getString("email"));

        if(!isSignatureFine(filePath, fileContent)) {
            JOptionPane.showMessageDialog(MenuFrame.getInstance(), "Falha na verificacao");
            Database.log(8016, filePath, Validation1.user.getString("email"));

            return null; // do something
        }

        Database.log(8014, filePath, Validation1.user.getString("email"));

        return fileContent;
    }

    public static void writeFile(String filePath, byte[] content) throws Exception {
        Path path = Paths.get(filePath);
        Files.write(path, content);
    }

    public static void createSecretFile(String currentPath, String secretPath) {
        try {
            byte[] fileContent = readFile(currentPath);
            writeFile(secretPath, fileContent);

            String currentName = Paths.get(currentPath).getFileName().toString();
            JOptionPane.showMessageDialog(MenuFrame.getInstance(), "Arquivo decriptado com sucesso");
            Database.log(8013, currentName, Validation1.user.getString("email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
