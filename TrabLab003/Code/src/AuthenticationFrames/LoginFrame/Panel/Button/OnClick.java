package AuthenticationFrames.LoginFrame.Panel.Button;

import AuthenticationFrames.PasswordFrame.PasswordFrame;
import Database.Database;
import Security.Blocked;
import Security.Validation1;
import Security.Validation2;
import _System_.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClick implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String email = Main.loginFrame.panel.textField.getText();
//            email = "user01@inf1416.puc-rio.br"; // para testar rápido
//            email = "admin@inf1416.puc-rio.br";

            if((Validation1.isEmailValid(email) == false)) {
                Database.log(2005, email);
                Main.loginFrame.panel.warning.setText("Email incorreto");

                return;
            }

            if(Blocked.isBlocked()) {
                Database.log(2004, email);
                Main.loginFrame.panel.warning.setText("Email bloqueado");

                return;
            }

            Database.log(2003, email);
            Database.log(2002);

            Main.loginFrame.dispose();
            Main.passwordFrame = new PasswordFrame();

        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
