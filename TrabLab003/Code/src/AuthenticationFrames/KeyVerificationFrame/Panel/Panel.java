package AuthenticationFrames.KeyVerificationFrame.Panel;

import AuthenticationFrames.KeyVerificationFrame.Panel.LoginButton.LoginButton;
import AuthenticationFrames.KeyVerificationFrame.Panel.SelectFileButton.SelectFileButton;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class Panel extends JPanel {

    public Path cipherPemPath = null;

    public SelectFileButton selectFileButton;
    public JTextField passwordField;

    public Panel() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        selectFileButton = new SelectFileButton();
        this.add(selectFileButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        this.add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        passwordField = new JPasswordField();
        this.add(passwordField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        this.add(new LoginButton(), gridBagConstraints);
    }
}
