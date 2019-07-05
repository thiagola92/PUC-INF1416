package MenuFrame.Panel.AlterarSenhaPanel.Labels;

import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;

import javax.swing.*;
import java.awt.*;

public class ConfirmacaoSenhaLabel extends JLabel {

    AlterarSenhaPanel alterarSenhaPanel;

    public ConfirmacaoSenhaLabel(AlterarSenhaPanel alterarSenhaPanel) {
        this.alterarSenhaPanel = alterarSenhaPanel;

        this.setText("<html>Confirmação senha pessoal:</html>");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
    }
}
