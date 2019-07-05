package MenuFrame.Panel.CadastrarPanel.TextFields;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;

import javax.swing.*;
import java.awt.*;

public class ConfirmacaoSenhaTextField extends JPasswordField {

    public ConfirmacaoSenhaTextField(CadastrarPanel cadastrarPanel) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
        cadastrarPanel.confirmacaoSenhaTextField = this;
    }
}
