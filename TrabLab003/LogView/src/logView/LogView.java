package logView;

import Database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class LogView {

    public static HashMap<Integer, String> mensagens = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String query = "SELECT * FROM Mensagens;";
        PreparedStatement preparedStatement = Database.getInstance().connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next())
            mensagens.put(resultSet.getInt("registro"), resultSet.getString("mensagem"));

        query = "SELECT registro, strftime(data) AS data, arquivo, usuario FROM Registros ORDER BY data;";
        preparedStatement = Database.getInstance().connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            String msg = mensagens.get(resultSet.getInt("registro"));
            String usuario = resultSet.getString("usuario");
            String arquivo = resultSet.getString("arquivo");

            if(usuario != null)
                msg = msg.replace("<login_name>", usuario);

            if(arquivo != null)
                msg = msg.replace("<arq_name>", arquivo);

            System.out.println(resultSet.getString("data") + " | " + msg);
        }
    }
}
