package MenuFrame.Panel.MenuPanel.Labels;

import MenuFrame.Panel.MenuPanel.MenuPanel;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class ConsultasDoUsuarioLabel extends JLabel {

    public ConsultasDoUsuarioLabel(MenuPanel menuPanel) {
        String totalDeAcessos = "Total de acessos do usuário: ";

        try {
            totalDeAcessos += Validation1.user.getInt("acessos") + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(totalDeAcessos);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }
}
