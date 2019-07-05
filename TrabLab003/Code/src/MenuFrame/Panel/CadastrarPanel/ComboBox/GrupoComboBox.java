package MenuFrame.Panel.CadastrarPanel.ComboBox;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GrupoComboBox extends JComboBox<String> implements ActionListener {

    CadastrarPanel cadastrarPanel;

    public GrupoComboBox(CadastrarPanel cadastrarPanel) {
        this.addItem("Usuario");
        this.addItem("Administrador");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
        cadastrarPanel.grupoComboBox = this;
    }
}
