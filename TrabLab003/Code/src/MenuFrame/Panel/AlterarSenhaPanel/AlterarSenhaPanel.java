package MenuFrame.Panel.AlterarSenhaPanel;

import MenuFrame.Panel.AlterarSenhaPanel.Buttons.AlterarButton;
import MenuFrame.Panel.AlterarSenhaPanel.Buttons.CaminhoDoCertificadoButton;
import MenuFrame.Panel.AlterarSenhaPanel.Buttons.LimparCertificadoButton;
import MenuFrame.Panel.AlterarSenhaPanel.Buttons.VoltarButton;
import MenuFrame.Panel.AlterarSenhaPanel.Labels.*;
import MenuFrame.Panel.AlterarSenhaPanel.TextFields.ConfirmacaoSenhaTextField;
import MenuFrame.Panel.AlterarSenhaPanel.TextFields.SenhaTextField;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class AlterarSenhaPanel extends JPanel {

    public CaminhoDoCertificadoLabel caminhoDoCertificadoLabel;
    public SenhaTextField senhaTextField;
    public ConfirmacaoSenhaTextField confirmacaoSenhaTextField;

    public AlterarSenhaPanel(){
        try {
            Database.log(7001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new CabecalhoLabel(this);
        new ConsultasDoUsuarioLabel(this);
        new CaminhoDoCertificadoButton(this);
        new CaminhoDoCertificadoLabel(this);
        new LimparCertificadoButton(this);
        new SenhaLabel(this);
        new SenhaTextField(this);
        new ConfirmacaoSenhaLabel(this);
        new ConfirmacaoSenhaTextField(this);
        new AlterarButton(this);
        new VoltarButton(this);
    }
}
