package AuthenticationFrames.KeyVerificationFrame.Panel.LoginButton;

import javax.swing.*;

public class LoginButton extends JButton {

    public LoginButton() {
        this.setText("Login");
        this.addActionListener(new OnClick());
    }
}
