package AuthenticationFrames.KeyVerificationFrame.Panel.SelectFileButton;

import javax.swing.*;

public class SelectFileButton extends JButton {

    public SelectFileButton() {
        this.setText("Select File");
        this.addActionListener(new OnClick(this));
    }
}
