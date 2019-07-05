package MenuFrame.Panel.CadastrarPanel.Buttons;

import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaminhoDoCertificadoButton extends JButton implements ActionListener {

    CadastrarPanel cadastrarPanel;

    public CaminhoDoCertificadoButton(CadastrarPanel cadastrarPanel) {
        this.cadastrarPanel = cadastrarPanel;

        this.setText("Caminho do certificado");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            cadastrarPanel.caminhoDoCertificadoLabel.setText(fileChooser.getSelectedFile().getPath());
    }
}
