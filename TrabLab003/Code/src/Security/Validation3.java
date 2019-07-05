package Security;

import Database.Database;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Validation3 {

    public static PrivateKey privateKey;
    public static PublicKey publicKey;

    public static PrivateKey getPrivateKey(String password, Path cipherPemPath) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, secureRandom);
        Key key = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherPemBytes = Files.readAllBytes(cipherPemPath);
        byte[] pemBytes = cipher.doFinal(cipherPemBytes);

        String pemString = new String(pemBytes);
        pemString = pemString.replace("-----BEGIN PRIVATE KEY-----\n","");
        pemString = pemString.replace("-----END PRIVATE KEY-----\n","");

        byte[] privateKeyBytes = Base64.getMimeDecoder().decode(pemString);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public static PublicKey getPublicKey() throws Exception {
        byte[] certificateBytes = Validation1.user.getBytes("certificate");

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream certificateInputStream = new ByteArrayInputStream(certificateBytes);
        X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);

        return x509Certificate.getPublicKey();
    }

    public static boolean isPrivateKeyValid(String password, Path cipherPemPath) throws Exception {
        try {
            privateKey = getPrivateKey(password, cipherPemPath);
        } catch (Exception e) {
            Database.log(4005, Validation1.user.getString("email"));
            return false;
        }

        publicKey = getPublicKey();

        byte[] message = new byte[2048];
        (new SecureRandom()).nextBytes(message);

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        byte[] cipherMessage = signature.sign();

        signature.initVerify(publicKey);
        signature.update(message);

        if(signature.verify(cipherMessage))
            return true;

        Database.log(4006, Validation1.user.getString("email"));

        return false;
    }
}
