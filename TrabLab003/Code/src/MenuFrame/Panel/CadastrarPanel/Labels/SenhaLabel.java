package MenuFrame.Panel.CadastrarPanel.Labels;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;

import javax.swing.*;
import java.awt.*;

public class SenhaLabel extends JLabel {

    CadastrarPanel cadastrarPanel;

    public SenhaLabel(CadastrarPanel cadastrarPanel) {
        this.cadastrarPanel = cadastrarPanel;

        this.setText("<html>Senha pessoal:</html>");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
    }
}
