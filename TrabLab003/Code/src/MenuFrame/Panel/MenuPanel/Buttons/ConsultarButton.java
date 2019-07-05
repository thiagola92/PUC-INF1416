package MenuFrame.Panel.MenuPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public ConsultarButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setText("3 - Consultar pasta de arquivos secretos do usuario");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(5004, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MenuFrame.getInstance().remove(menuPanel);
        ConsultarPanel consultarPanel = new ConsultarPanel();
        MenuFrame.getInstance().add(consultarPanel);
        consultarPanel.updateUI();
    }
}
