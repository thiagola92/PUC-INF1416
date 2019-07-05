package AuthenticationFrames.LoginFrame.Panel;

import AuthenticationFrames.LoginFrame.Panel.Button.Button;
import AuthenticationFrames.LoginFrame.Panel.Warning.Warning;

import javax.swing.*;

import java.awt.*;

public class Panel extends JPanel {

    public JTextField textField;
    public Button button;
    public Warning warning;

    public Panel() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(new JLabel("Informe seu e-mail:"), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        textField = new JTextField();
        this.add(textField, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        button = new Button();
        this.add(button, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        warning = new Warning();
        this.add(warning, gridBagConstraints);

    }

}
