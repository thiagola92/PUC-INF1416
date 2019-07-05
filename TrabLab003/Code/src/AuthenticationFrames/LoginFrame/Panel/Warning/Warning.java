package AuthenticationFrames.LoginFrame.Panel.Warning;

import javax.swing.*;

public class Warning extends JLabel {

    public Warning() {
        this.setHorizontalAlignment(JLabel.CENTER);

        setText("");
    }

    public void setText(String text) {
        super.setText("<html><font color=red>" + text + "</font></html>");
    }
}
