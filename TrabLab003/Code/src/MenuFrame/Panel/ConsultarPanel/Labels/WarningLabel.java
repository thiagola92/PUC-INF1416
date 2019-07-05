package MenuFrame.Panel.ConsultarPanel.Labels;

import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;

import javax.swing.*;
import java.awt.*;

public class WarningLabel extends JLabel {

    public WarningLabel(ConsultarPanel consultarPanel) {
        this.setText("<html><font color='red'>Sem permissão para abrir</font></html>");
        this.setVerticalAlignment(JLabel.NORTH);
        this.setVisible(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        consultarPanel.add(this, gridBagConstraints);
        consultarPanel.warningLabel = this;
    }
}
