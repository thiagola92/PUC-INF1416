package MenuFrame.Panel.CadastrarPanel.Buttons;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimparCertificadoButton extends JButton implements ActionListener {

    CadastrarPanel cadastrarPanel;

    public LimparCertificadoButton(CadastrarPanel cadastrarPanel) {
        this.cadastrarPanel = cadastrarPanel;

        this.setText("Limpar campo");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        cadastrarPanel.caminhoDoCertificadoLabel.setText("");
    }
}
