package MenuFrame.Panel.AlterarSenhaPanel.Buttons;

import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaminhoDoCertificadoButton extends JButton implements ActionListener {

    AlterarSenhaPanel alterarSenhaPanel;

    public CaminhoDoCertificadoButton(AlterarSenhaPanel alterarSenhaPanel) {
        this.alterarSenhaPanel = alterarSenhaPanel;

        this.setText("Caminho do certificado");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            alterarSenhaPanel.caminhoDoCertificadoLabel.setText(fileChooser.getSelectedFile().getPath());
    }
}
