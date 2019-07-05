package MenuFrame.Panel.MenuPanel;

import MenuFrame.Panel.MenuPanel.Buttons.AlterarSenhaButton;
import MenuFrame.Panel.MenuPanel.Buttons.CadastrarButton;
import MenuFrame.Panel.MenuPanel.Buttons.ConsultarButton;
import MenuFrame.Panel.MenuPanel.Buttons.SairButton;
import MenuFrame.Panel.MenuPanel.Labels.CabecalhoLabel;
import MenuFrame.Panel.MenuPanel.Labels.ConsultasDoUsuarioLabel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(){
        try {
            Database.log(5001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new CabecalhoLabel(this);
        new ConsultasDoUsuarioLabel(this);
        new CadastrarButton(this);
        new AlterarSenhaButton(this);
        new ConsultarButton(this);
        new SairButton(this);
    }
}
