package MenuFrame.Panel.CadastrarPanel.Labels;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;

import javax.swing.*;
import java.awt.*;

public class ConfirmacaoSenhaLabel extends JLabel {

    CadastrarPanel cadastrarPanel;

    public ConfirmacaoSenhaLabel(CadastrarPanel cadastrarPanel) {
        this.cadastrarPanel = cadastrarPanel;

        this.setText("<html>Confirmação senha pessoal:</html>");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
    }
}
