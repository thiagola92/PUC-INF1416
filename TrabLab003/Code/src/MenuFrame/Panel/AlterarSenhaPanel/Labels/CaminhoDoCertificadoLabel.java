package MenuFrame.Panel.AlterarSenhaPanel.Labels;

import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;

import javax.swing.*;
import java.awt.*;

public class CaminhoDoCertificadoLabel extends JLabel {

    AlterarSenhaPanel alterarSenhaPanel;

    public CaminhoDoCertificadoLabel(AlterarSenhaPanel alterarSenhaPanel) {
        this.alterarSenhaPanel = alterarSenhaPanel;

        this.setText("");
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
        alterarSenhaPanel.caminhoDoCertificadoLabel = this;
    }
}
