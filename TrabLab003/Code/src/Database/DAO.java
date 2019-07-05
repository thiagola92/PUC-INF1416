package Database;

import javax.xml.bind.DatatypeConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DAO {

    private static DAO instance = null;

    public DAO(){

    }

    public static DAO getInstance(){
        if(instance == null){
            instance = new DAO();
        }
        return instance;
    }

    public void incrementAcessos(String email) throws Exception{
        String query = "UPDATE Usuarios SET acessos = acessos + 1 WHERE email='" + email + "';";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public String getGrupoNome(int id) throws Exception {
        String query = "SELECT Nome FROM Grupos WHERE GID = ?;";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        String grupoNome = resultSet.getString("Nome");
        resultSet.close();
        preparedStatement.close();

        return grupoNome;
    }

    public ResultSet getUser(String email) throws Exception {
        String query = "SELECT * FROM Usuarios WHERE email = ?;";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getUsersCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Usuarios;";

        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public int getEmailCount(String email) throws Exception {
        String query = "SELECT COUNT(email) FROM Usuarios WHERE email='" +email+ "';";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = resultSet.getInt("COUNT(email)");

        resultSet.close();
        preparedStatement.close();
        return count;
    }

    public void insertUsuario(String email, byte[] digest, byte[] salt, String certificatePath, int grupo) throws Exception{
        String query = "INSERT INTO Usuarios(email,digest,salt,certificate,grupo) VALUES (?,?,?,?,?);";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, DatatypeConverter.printHexBinary(digest));
        preparedStatement.setString(3, new String(salt));
        preparedStatement.setBytes(4, Files.readAllBytes(Paths.get(certificatePath)));
        preparedStatement.setInt(5, grupo);

        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public void updatePassword(String email, byte[] digest, byte[] salt) throws Exception {
        String query = "UPDATE Usuarios SET digest = ?, salt = ? WHERE email = ?";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.setString(1, DatatypeConverter.printHexBinary(digest));
        preparedStatement.setString(2, new String(salt));
        preparedStatement.setString(3, email);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateCertificate(String email, String certificatePath) throws Exception {
        String query = "UPDATE Usuarios SET certificate = ? WHERE email = ?";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        preparedStatement.setBytes(1, Files.readAllBytes(Paths.get(certificatePath)));
        preparedStatement.setString(2, email);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }



}
