package MenuFrame.Panel.CadastrarPanel;

import MenuFrame.Panel.CadastrarPanel.Buttons.CadastrarButton;
import MenuFrame.Panel.CadastrarPanel.Buttons.CaminhoDoCertificadoButton;
import MenuFrame.Panel.CadastrarPanel.Buttons.LimparCertificadoButton;
import MenuFrame.Panel.CadastrarPanel.Buttons.VoltarButton;
import MenuFrame.Panel.CadastrarPanel.ComboBox.GrupoComboBox;
import MenuFrame.Panel.CadastrarPanel.Labels.*;
import MenuFrame.Panel.CadastrarPanel.TextFields.ConfirmacaoSenhaTextField;
import MenuFrame.Panel.CadastrarPanel.TextFields.SenhaTextField;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class CadastrarPanel extends JPanel {

    public CaminhoDoCertificadoLabel caminhoDoCertificadoLabel;
    public SenhaTextField senhaTextField;
    public ConfirmacaoSenhaTextField confirmacaoSenhaTextField;
    public GrupoComboBox grupoComboBox;

    public CadastrarPanel(){
        try {
            Database.log(6001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new CabecalhoLabel(this);
        new TotalUsuariosLabel(this);
        new FormularioLabel(this);
        new CaminhoDoCertificadoButton(this);
        new CaminhoDoCertificadoLabel(this);
        new LimparCertificadoButton(this);
        new SenhaLabel(this);
        new SenhaTextField(this);
        new ConfirmacaoSenhaLabel(this);
        new ConfirmacaoSenhaTextField(this);
        new CadastrarButton(this);
        new GrupoComboBox(this);
        new VoltarButton(this);
    }
}
