package MenuFrame.Panel.AlterarSenhaPanel.TextFields;

import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;

import javax.swing.*;
import java.awt.*;

public class ConfirmacaoSenhaTextField extends JPasswordField {

    public ConfirmacaoSenhaTextField(AlterarSenhaPanel alterarSenhaPanel) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
        alterarSenhaPanel.confirmacaoSenhaTextField = this;
    }
}
