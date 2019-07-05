Compilação de testes.  
Esses códigos podem não ter sido produzidos na hora certa e por isso foram movidos para aqui.  
Por exemplo, eu posso ter escrito o código de como ler uma chave privada cifrada de um binário antes de ter feito todas outras janelas necessárias para o trabalho. Como ainda não está na hora de inserir esse código, ele foi movido para essa área para que depois eu possa relembrar dele.  

# Recuperar chave privada cifrada do arquivo binário

```Java
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.SecureRandom;
import java.security.Key;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        SecureRandom sha1prng = SecureRandom.getInstance("SHA1PRNG");
        sha1prng.setSeed("frase secreta".getBytes());

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, sha1prng);
        Key key = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherText = cipher.doFinal("Isso é um teste, sério... vc pode não acreditar mas é realmente um teste, você não tem ideia... eu até tive que ficar inventando besteira para falar para criar uma string enorme".getBytes("UTF8"));

        Base64.Encoder encoder = Base64.getMimeEncoder();
        byte[] cipherEncoded = encoder.encode(cipherText);

        FileInteraction.writeFile("example", PemPkcs8.addFormat(cipherEncoded));
        byte[] fileText = FileInteraction.readFile("example");
        fileText = PemPkcs8.removeFormat(fileText);

        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] textDecoded = decoder.decode(fileText);

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decipherText = cipher.doFinal(textDecoded);

        System.out.print(new String(decipherText, "UTF8"));
    }
}
```

# Conferir se a chave privada é par da chave pública
```Java
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

public class Main {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(keyPair.getPrivate());
        signature.update("quando pegar a chave pública do usuário e decifrar isso, esse tem que ser o texto".getBytes("UTF8"));
        byte[] sign = signature.sign();

        // troque keyPair.getPublic() pela chave que está no certificado da pessoa
        signature.initVerify(keyPair.getPublic());
        signature.update("quando pegar a chave pública do usuário e decifrar isso, esse tem que ser o texto".getBytes("UTF8"));
        System.out.print(signature.verify(sign));
    }
}
```

# Conectando a um banco de dados
```Java
package main;
import java.sql.*;

public class DatabaseTeste {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Statement statement = null;

        //cria conexao
        // db parameters
        String url = "jdbc:sqlite:memory";
        // create a connection to the database
        conn = DriverManager.getConnection(url);
        conn.setAutoCommit(false);
        System.out.println("Connection to SQLite has been established.");

        //cria tabela
        statement = conn.createStatement();
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
        statement.executeUpdate(sql);

        //faz insert na tabela
        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
        statement.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
        statement.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
        statement.executeUpdate(sql);

        //faz select na tabela

        ResultSet rs = statement.executeQuery( "SELECT * FROM COMPANY;" );

        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  name = rs.getString("name");
            int age  = rs.getInt("age");
            String  address = rs.getString("address");
            float salary = rs.getFloat("salary");

            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println( "AGE = " + age );
            System.out.println( "ADDRESS = " + address );
            System.out.println( "SALARY = " + salary );
            System.out.println();
        }
        rs.close();

        statement.close();
        conn.commit();
        conn.close();
    }
}

```

# Verificando a chave privada do admin/usuário
```Java
package main;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Cryptotestes {

    public static void main(String[] args) throws Exception{

        String fraseSecretaAdmin = "admin";
        String fraseSecretaUser = "user01";

        //gerando a semente
        SecureRandom algoritmo = SecureRandom.getInstance("SHA1PRNG");
        algoritmo.setSeed(fraseSecretaAdmin.getBytes());

        //gerando chave simetrica
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56,algoritmo);
        Key secretKey = keyGenerator.generateKey();

        //decripta chave privada
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        Path adminFilePath = Paths.get("C:\\Users\\Lucas\\Documents\\PUC\\Segurança_da_Informação\\src\\Pacote-T3\\Keys\\admin-pkcs8-des.pem");
        byte[] adminFileBytes = Files.readAllBytes(adminFilePath);
        byte[] adminDataBytes = cipher.doFinal(adminFileBytes);

        String adminInfo = new String(adminDataBytes,"UTF-8");
        adminInfo = adminInfo.replace("-----BEGIN PRIVATE KEY-----\n","");
        adminInfo = adminInfo.replace("-----END PRIVATE KEY-----","");

        //pega private key
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(adminInfo));
        PrivateKey adminPrivateKey = keyFactory.generatePrivate(priPKCS8);

        //pega a public key do certificado
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream certificateInputStream = new FileInputStream("C:\\Users\\Lucas\\Documents\\PUC\\Segurança_da_Informação\\src\\Pacote-T3\\Keys\\admin-x509.crt");
        Certificate certificate = certificateFactory.generateCertificate(certificateInputStream);
        PublicKey adminPublicKey = certificate.getPublicKey();
        certificateInputStream.close();

        //cria assinatura digital
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(adminPrivateKey);
        byte[] message = "qualquer bosta".getBytes();
        signature.update(message);
        byte[] signatureBytes = signature.sign();

        //verifica assinatura
        signature.initVerify(adminPublicKey);
        signature.update(message);
        boolean result = signature.verify(signatureBytes);

        if(result){
            System.out.println("funcionou uhul");
        }
        else{
            System.out.println("penis no seu anus");
        }
    }

}
```

# Tabelas necessárias no banco de dados
```SQL
CREATE TABLE Usuarios (
    email           TEXT,
    digest          TEXT,
    salt            TEXT,
    certificate     BLOB,
    
    grupo           INTEGER,
    acessos         INTEGER DEFAULT 0,
    fails           INTEGER DEFAULT 0,

    PRIMARY KEY (email),
    FOREIGN KEY (grupo) REFERENCES Grupos(GID)
);

CREATE TABLE Grupos (
    GID             INTEGER,
    Nome            TEXT
);

CREATE TABLE Mensagens (
    registro        INTEGER,
    mensagem        TEXT,

    PRIMARY KEY (registro)
);

CREATE TABLE Registros (
    registro        INTEGER,
    data            INTEGER DEFAULT CURRENT_TIMESTAMP,
    arquivo         TEXT DEFAULT NULL,
    usuario         INTEGER DEFAULT NULL,

    FOREIGN KEY (registro) REFERENCES Mensagens(id)
);
```

```SQL
INSERT INTO Grupos(GID, Nome) VALUES
(0, 'Administrador'),
(1, 'Usuario');
```

# Tabela de Mensagens de Registro
```SQL
INSERT INTO Mensagens(registro, mensagem) VALUES
(1001, "Sistema iniciado."),
(1002, "Sistema encerrado."),
(2001, "Autenticação etapa 1 iniciada."),
(2002, "Autenticação etapa 1 encerrada."),
(2003, "Login name <login_name> identificado com acesso liberado."),
(2004, "Login name <login_name> identificado com acesso bloqueado."),
(2005, "Login name <login_name> não identificado."),
(3001, "Autenticação etapa 2 iniciada para <login_name>."),
(3002, "Autenticação etapa 2 encerrada para <login_name>."),
(3003, "Senha pessoal verificada positivamente para <login_name>."),
(3004, "Primeiro erro da senha pessoal contabilizado para <login_name>."),
(3005, "Segundo erro da senha pessoal contabilizado para <login_name>."),
(3006, "Terceiro erro da senha pessoal contabilizado para <login_name>."),
(3007, "Acesso do usuario <login_name> bloqueado pela autenticação etapa 2."),
(4001, "Autenticação etapa 3 iniciada para <login_name>."),
(4002, "Autenticação etapa 3 encerrada para <login_name>."),
(4003, "Chave privada verificada positivamente para <login_name>."),
(4004, "Chave privada verificada negativamente para <login_name> (caminho inválido)."),
(4005, "Chave privada verificada negativamente para <login_name> (frase secreta inválida)."),
(4006, "Chave privada verificada negativamente para <login_name> (assinatura digital inválida)."),
(4007, "Acesso do usuario <login_name> bloqueado pela autenticação etapa 3."),
(5001, "Tela principal apresentada para <login_name>."),
(5002, "Opção 1 do menu principal selecionada por <login_name>."),
(5003, "Opção 2 do menu principal selecionada por <login_name>."),
(5004, "Opção 3 do menu principal selecionada por <login_name>."),
(5005, "Opção 4 do menu principal selecionada por <login_name>."),
(6001, "Tela de cadastro apresentada para <login_name>."),
(6002, "Botão cadastrar pressionado por <login_name>."),
(6003, "Senha pessoal inválida fornecida por <login_name>."),
(6004, "Caminho do certificado digital inválido fornecido por <login_name>."),
(6005, "Confirmação de dados aceita por <login_name>."),
(6006, "Confirmação de dados rejeitada por <login_name>."),
(6007, "Botão voltar de cadastro para o menu principal pressionado por <login_name>."),
(7001, "Tela de alteração da senha pessoal e certificado apresentada para <login_name>."),
(7002, "Senha pessoal inválida fornecida por <login_name>."),
(7003, "Caminho do certificado digital inválido fornecido por <login_name>."),
(7004, "Confirmação de dados aceita por <login_name>."),
(7005, "Confirmação de dados rejeitada por <login_name>."),
(7006, "Botão voltar de carregamento para o menu principal pressionado por <login_name>."),
(8001, "Tela de consulta de arquivos secretos apresentada para <login_name>."),
(8002, "Botão voltar de consulta para o menu principal pressionado por <login_name>."),
(8003, "Botão Listar de consulta pressionado por <login_name>."),
(8004, "Caminho de pasta inválido fornecido por <login_name>."),
(8005, "Arquivo de índice decriptado com sucesso para <login_name>."),
(8006, "Arquivo de índice verificado (integridade e autenticidade) com sucesso para <login_name>."),
(8007, "Falha na decriptação do arquivo de índice para <login_name>."),
(8008, "Falha na verificação (integridade e autenticidade) do arquivo de índice para <login_name>."),
(8009, "Lista de arquivos presentes no índice apresentada para <login_name>."),
(8010, "Arquivo <arq_name> selecionado por <login_name> para decriptação."),
(8011, "Acesso permitido ao arquivo <arq_name> para <login_name>."),
(8012, "Acesso negado ao arquivo <arq_name> para <login_name>."),
(8013, "Arquivo <arq_name> decriptado com sucesso para <login_name>."),
(8014, "Arquivo <arq_name> verificado (integridade e autenticidade) com sucesso para <login_name>."),
(8015, "Falha na decriptação do arquivo <arq_name> para <login_name>."),
(8016, "Falha na verificação (integridade e autenticidade) do arquivo <arq_name> para <login_name>."),
(9001, "Tela de saída apresentada para <login_name>."),
(9002, "Saída não liberada por falta de one-time password para <login_name>."),
(9003, "Botão sair pressionado por <login_name>."),
(9004, "Botão voltar de sair para o menu principal pressionado por <login_name>.");
```

# Adicionando um blob (certificado)
```Java
package _System_;

import Database.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = Database.getInstance();

        String sql = "INSERT INTO Usuarios(email, digest, salt, certificate) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = database.connection.prepareStatement(sql);
        preparedStatement.setString(1, "user01@inf1416.puc-rio.br");
        preparedStatement.setString(2, "asdf");
        preparedStatement.setString(3, "zxcv");
        preparedStatement.setBytes(4, Files.readAllBytes(Paths.get("keys/user01-x509.crt")));
        System.out.println(preparedStatement.executeUpdate());
        database.connection.close();

    }

}
```

# Inserindo digest e salt do usuario
```Java
package _System_;

import Authentication.KeyVerificationFrame.KeyVerificationFrame;
import Authentication.LoginFrame.*;
import Authentication.PasswordFrame.*;
import Database.Database;

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

        String validChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[10];

        for(int i = 0; i < salt.length; i++)
            salt[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update(("135791" + new String(salt)).getBytes());
        byte[] digest = messageDigest.digest();

        Database database = Database.getInstance();

        String sql = "INSERT INTO Usuarios(email, digest, salt, certificate, grupo) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = database.connection.prepareStatement(sql);
        preparedStatement.setString(1, "user01@inf1416.puc-rio.br");
        preparedStatement.setString(2, DatatypeConverter.printHexBinary(digest));
        preparedStatement.setString(3, new String(salt));
        preparedStatement.setBytes(4, Files.readAllBytes(Paths.get("keys/user01-x509.crt")));
        preparedStatement.setInt(5, 1);
        System.out.println(preparedStatement.executeUpdate());
        database.connection.close();
    }

}
```

# Inserindo digest e salt do admin
```Java
package _System_;

import Authentication.KeyVerificationFrame.KeyVerificationFrame;
import Authentication.LoginFrame.*;
import Authentication.PasswordFrame.*;
import Database.Database;

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

        String validChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[10];

        for(int i = 0; i < salt.length; i++)
            salt[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update(("2468025" + new String(salt)).getBytes());
        byte[] digest = messageDigest.digest();

        Database database = Database.getInstance();

        String sql = "INSERT INTO Usuarios(email, digest, salt, certificate, grupo) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = database.connection.prepareStatement(sql);
        preparedStatement.setString(1, "admin@inf1416.puc-rio.br");
        preparedStatement.setString(2, DatatypeConverter.printHexBinary(digest));
        preparedStatement.setString(3, new String(salt));
        preparedStatement.setBytes(4, Files.readAllBytes(Paths.get("keys/admin-x509.crt")));
        preparedStatement.setInt(5, 0);
        System.out.println(preparedStatement.executeUpdate());
        database.connection.close();
    }

}
```

# Query 3 minutos atrás

```SQL
SELECT * FROM Registros WHERE data>=DATETIME(CURRENT_TIMESTAMP, '-3 minutes');
```