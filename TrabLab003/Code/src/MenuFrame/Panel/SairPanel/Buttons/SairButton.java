package MenuFrame.Panel.SairPanel.Buttons;

import MenuFrame.Panel.SairPanel.SairPanel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SairButton extends JButton implements ActionListener {

    SairPanel sairPanel;

    public SairButton(SairPanel sairPanel) {
        this.sairPanel = sairPanel;

        this.setText("Sair");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        sairPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(9003, Validation1.user.getString("email"));
            Database.log(1002, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        System.exit(0);
    }
}
