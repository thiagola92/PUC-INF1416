package MenuFrame.Panel.SairPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import MenuFrame.Panel.SairPanel.SairPanel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoltarButton extends JButton implements ActionListener {

    SairPanel sairPanel;

    public VoltarButton(SairPanel sairPanel) {
        this.sairPanel = sairPanel;

        this.setText("Voltar para o Menu Principal");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        sairPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(9004, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MenuFrame.getInstance().remove(sairPanel);
        MenuPanel menuPanel = new MenuPanel();
        MenuFrame.getInstance().add(menuPanel);
        menuPanel.updateUI();
    }
}
