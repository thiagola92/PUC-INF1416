package MenuFrame.Panel.ConsultarPanel;

import MenuFrame.Panel.ConsultarPanel.Buttons.CaminhoDaPastaButton;
import MenuFrame.Panel.ConsultarPanel.Buttons.ListarButton;
import MenuFrame.Panel.ConsultarPanel.Buttons.VoltarButton;
import MenuFrame.Panel.ConsultarPanel.Labels.CabecalhoLabel;
import MenuFrame.Panel.ConsultarPanel.Labels.CaminhoDaPastaLabel;
import MenuFrame.Panel.ConsultarPanel.Labels.ConsultasDoUsuarioLabel;
import MenuFrame.Panel.ConsultarPanel.Labels.WarningLabel;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class ConsultarPanel extends JPanel {

    public CaminhoDaPastaLabel caminhoDaPastaLabel;
    public WarningLabel warningLabel;

    public ConsultarPanel(){
        try {
            Database.log(8001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new CabecalhoLabel(this);
        new CaminhoDaPastaButton(this);
        new CaminhoDaPastaLabel(this);
        new ConsultasDoUsuarioLabel(this);
        new WarningLabel(this);
        new ListarButton(this);
        new VoltarButton(this);
    }
}
