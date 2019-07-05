package MenuFrame.Panel.ConsultarPanel.Buttons;

import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;
import MenuFrame.Panel.ConsultarPanel.Table.Table;
import Database.Database;
import Security.Index;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListarButton extends JButton implements ActionListener {

    ConsultarPanel consultarPanel;

    public ListarButton(ConsultarPanel consultarPanel) {
        this.consultarPanel = consultarPanel;

        this.setText("Listar");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        consultarPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(8003, Validation1.user.getString("email"));

            String[][] fileTable = Index.getIndex(consultarPanel.caminhoDaPastaLabel.getText());
            String[] fileColumns = new String[]{"Nome Código", "Nome Secreto", "Dono", "Grupo"};

            new Table(consultarPanel, fileTable, fileColumns);

            Database.log(8009, Validation1.user.getString("email"));
        } catch(Exception e1) {
            try {
                Database.log(8004, Validation1.user.getString("email"));
                JOptionPane.showMessageDialog(this, "Não foi possível listar esse diretório");
                e1.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
