package AuthenticationFrames.KeyVerificationFrame.Panel.LoginButton;

import MenuFrame.MenuFrame;
import Database.Database;
import Security.*;
import _System_.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClick implements ActionListener {
    private static MenuFrame menuframe = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
//            Main.keyVerificationFrame.panel.passwordField.setText("user01"); // para teste rápido
//            Main.keyVerificationFrame.panel.passwordField.setText("admin"); // para teste rápido

            if(Validation3.isPrivateKeyValid(Main.keyVerificationFrame.panel.passwordField.getText(), Main.keyVerificationFrame.panel.cipherPemPath)) {

                Database.log(4003, Validation1.user.getString("email"));
                Database.log(4002, Validation1.user.getString("email"));

                Main.keyVerificationFrame.dispose();
                menuframe = MenuFrame.getInstance();
            } else {
                Main.keyVerificationFrame.warningIncrease("");
            }
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
