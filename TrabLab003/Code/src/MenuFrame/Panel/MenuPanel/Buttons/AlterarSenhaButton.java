package MenuFrame.Panel.MenuPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarSenhaButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public AlterarSenhaButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setText("2 - Alterar a senha pessoal e certificado do usuario");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(5003, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MenuFrame.getInstance().remove(menuPanel);
        AlterarSenhaPanel alterarSenhaPanel = new AlterarSenhaPanel();
        MenuFrame.getInstance().add(alterarSenhaPanel);
        alterarSenhaPanel.updateUI();
    }
}
