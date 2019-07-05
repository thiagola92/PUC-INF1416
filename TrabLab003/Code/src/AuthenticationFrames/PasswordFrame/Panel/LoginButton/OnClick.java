package AuthenticationFrames.PasswordFrame.Panel.LoginButton;

import AuthenticationFrames.KeyVerificationFrame.KeyVerificationFrame;
import Database.Database;
import Security.Blocked;
import Security.Validation1;
import Security.Validation2;
import _System_.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OnClick implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<Integer[]> passwordNumbers = Main.passwordFrame.panel.passwordNumbers;

            // apenas para testar rápido
//            passwordNumbers = new ArrayList<>();
//            passwordNumbers.add(new Integer[]{1,2});
//            passwordNumbers.add(new Integer[]{3,4});
//            passwordNumbers.add(new Integer[]{5,6});
//            passwordNumbers.add(new Integer[]{7,8});
//            passwordNumbers.add(new Integer[]{9,0});
//            passwordNumbers.add(new Integer[]{1,2});
//            passwordNumbers.add(new Integer[]{0,5}); // admin

            if(Blocked.isBlocked()) {
                Main.passwordFrame.warning("Email bloqueado");
                return;
            }

            if(passwordNumbers.size() < 6 || passwordNumbers.size() > 8) {
                Main.passwordFrame.warningIncrease("A senha precisa ter 6/7/8 digitos");
                return;
            }

            if(Validation2.guessPassword(passwordNumbers) == false) {
                Main.passwordFrame.warningIncrease("Senha incorreta");
                return;
            }

            Database.log(3003, Validation1.user.getString("email"));
            Database.log(3002, Validation1.user.getString("email"));

            Main.passwordFrame.dispose();
            Main.keyVerificationFrame = new KeyVerificationFrame();

            Blocked.setFails(0);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
