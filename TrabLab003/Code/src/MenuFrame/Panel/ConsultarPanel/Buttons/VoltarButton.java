package MenuFrame.Panel.ConsultarPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VoltarButton extends JButton implements ActionListener {

    ConsultarPanel consultarPanel;

    public VoltarButton(ConsultarPanel consultarPanel) {
        this.consultarPanel = consultarPanel;

        this.setText("Voltar para o Menu Principal");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        consultarPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(8002, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MenuFrame.getInstance().remove(consultarPanel);
        MenuPanel menuPanel = new MenuPanel();
        MenuFrame.getInstance().add(menuPanel);
        menuPanel.updateUI();
    }
}
