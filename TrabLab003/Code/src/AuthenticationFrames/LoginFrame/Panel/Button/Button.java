package AuthenticationFrames.LoginFrame.Panel.Button;

import javax.swing.JButton;

public class Button extends JButton {

    public Button() {
        this.setText("Login");
        this.addActionListener(new OnClick());
    }
}
