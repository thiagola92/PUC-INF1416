package MenuFrame.Panel.SairPanel.Labels;

import MenuFrame.Panel.SairPanel.SairPanel;

import javax.swing.*;
import java.awt.*;

public class SairLabel extends JLabel {

    public SairLabel(SairPanel sairPanel) {
        String cabecalho = "<html>Saída do sistema: <br> Pressione o botão Sair para sair do sistema</html>";

        this.setText(cabecalho);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        sairPanel.add(this, gridBagConstraints);
    }
}
