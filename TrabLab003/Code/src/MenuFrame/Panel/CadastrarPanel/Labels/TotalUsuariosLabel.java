package MenuFrame.Panel.CadastrarPanel.Labels;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;
import Database.DAO;

import javax.swing.*;
import java.awt.*;

public class TotalUsuariosLabel extends JLabel {

    public TotalUsuariosLabel(CadastrarPanel cadastrarPanel) {
        String totalDeUsuarios = "Total de usuários no sistema: ";

        try {
            totalDeUsuarios += DAO.getInstance().getUsersCount().getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(totalDeUsuarios);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
    }
}
