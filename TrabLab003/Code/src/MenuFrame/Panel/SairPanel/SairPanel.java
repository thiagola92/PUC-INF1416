package MenuFrame.Panel.SairPanel;

import MenuFrame.Panel.SairPanel.Buttons.SairButton;
import MenuFrame.Panel.SairPanel.Buttons.VoltarButton;
import MenuFrame.Panel.SairPanel.Labels.CabecalhoLabel;
import MenuFrame.Panel.SairPanel.Labels.ConsultasDoUsuarioLabel;
import MenuFrame.Panel.SairPanel.Labels.SairLabel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class SairPanel extends JPanel {

    public SairPanel(){
        try {
            Database.log(9001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new CabecalhoLabel(this);
        new ConsultasDoUsuarioLabel(this);
        new SairLabel(this);
        new SairButton(this);
        new VoltarButton(this);
    }
}
