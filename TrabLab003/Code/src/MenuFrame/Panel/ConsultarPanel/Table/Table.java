package MenuFrame.Panel.ConsultarPanel.Table;

import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;
import Database.DAO;
import Database.Database;
import Security.SecurityFile;
import Security.Validation1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.File;

public class Table extends JTable {

    ConsultarPanel consultarPanel;
    public int selectedRow = 0;
    public int doubleClick = 0;

    public Table(ConsultarPanel consultarPanel, String[][] data, String[] column) {
        super(data, column);

        this.consultarPanel = consultarPanel;
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);

        consultarPanel.add(this, gridBagConstraints);
        consultarPanel.updateUI();
    }

    public boolean isCellEditable(int row, int column) {
        if(getSelectedRow() == selectedRow && getSelectedRow() != -1 && doubleClick == 0) {
            tryDecipherRow();
            selectedRow = getSelectedRow();
            doubleClick = 1;
            return false;
        }

        selectedRow = getSelectedRow();
        doubleClick = 0;

        return false;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(getSelectedRow() != -1)
            consultarPanel.warningLabel.setVisible(!isAccessible());

        doubleClick = 0;

        super.valueChanged(e);
    }

    public void tryDecipherRow() {
        try {
            decipherRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decipherRow() throws Exception {
        String currentName = (String) this.getValueAt(getSelectedRow(), 0);
        String secretName = (String) this.getValueAt(getSelectedRow(), 1);

        String currentPath = consultarPanel.caminhoDaPastaLabel.getText() + File.separator + currentName;
        String secretPath = consultarPanel.caminhoDaPastaLabel.getText() + File.separator + secretName;

        Database.log(8010, currentName, Validation1.user.getString("email"));

        if(isAccessible()) {
            Database.log(8011, currentName, Validation1.user.getString("email"));
            SecurityFile.createSecretFile(currentPath, secretPath);

            return;
        }
        JOptionPane.showMessageDialog(this, "Acesso negado");
        Database.log(8012, currentName, Validation1.user.getString("email"));
    }

    public boolean isAccessible() {
        String fileOwner = (String) this.getValueAt(getSelectedRow(), 2);
        String fileGroup = (String) this.getValueAt(getSelectedRow(), 3);

        try {
            if(isFromGroup(fileGroup) || isFromOwner(fileOwner))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isFromGroup(String fileGroup) throws Exception {
        int userGroupID = Validation1.user.getInt("grupo");
        String userGroup = DAO.getInstance().getGrupoNome(userGroupID);

        if(userGroup.equalsIgnoreCase(fileGroup))
            return true;

        return false;
    }

    public boolean isFromOwner(String fileOwner) throws Exception {
        String ownerEmail = Validation1.user.getString("email");

        if(ownerEmail.equalsIgnoreCase(fileOwner))
            return true;

        return false;
    }
}
