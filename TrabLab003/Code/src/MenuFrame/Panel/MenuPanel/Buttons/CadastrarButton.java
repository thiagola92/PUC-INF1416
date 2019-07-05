package MenuFrame.Panel.MenuPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.Database;
import Database.DAO;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public CadastrarButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        try {
            String grupo = DAO.getInstance().getGrupoNome(Validation1.user.getInt("grupo"));
            if (grupo.equals("Administrador") == false)
                return;
        } catch (Exception e) {
            e.printStackTrace();

            return;
        }

        this.setText("1 - Cadastrar novo usuario");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(5002, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MenuFrame.getInstance().remove(menuPanel);
        CadastrarPanel consultarPanel = new CadastrarPanel();
        MenuFrame.getInstance().add(consultarPanel);
        consultarPanel.updateUI();
    }
}
