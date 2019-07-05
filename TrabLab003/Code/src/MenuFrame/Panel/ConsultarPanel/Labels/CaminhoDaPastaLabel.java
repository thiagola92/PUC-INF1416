package MenuFrame.Panel.ConsultarPanel.Labels;

import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;

import javax.swing.*;
import java.awt.*;

public class CaminhoDaPastaLabel extends JLabel {

    ConsultarPanel consultarPanel;

    public CaminhoDaPastaLabel(ConsultarPanel consultarPanel) {
        this.consultarPanel = consultarPanel;

        this.setText("");
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        consultarPanel.add(this, gridBagConstraints);
        consultarPanel.caminhoDaPastaLabel = this;
    }
}
