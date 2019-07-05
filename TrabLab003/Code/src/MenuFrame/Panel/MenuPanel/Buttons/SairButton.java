package MenuFrame.Panel.MenuPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import MenuFrame.Panel.SairPanel.SairPanel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SairButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public SairButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setText("4 - Sair do sistema");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(5005, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MenuFrame.getInstance().remove(menuPanel);
        SairPanel consultaPanel = new SairPanel();
        MenuFrame.getInstance().add(consultaPanel);
        consultaPanel.updateUI();
    }
}
