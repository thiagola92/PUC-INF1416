package MenuFrame.Panel.ConsultarPanel.Buttons;

import MenuFrame.Panel.ConsultarPanel.ConsultarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaminhoDaPastaButton extends JButton implements ActionListener {

    ConsultarPanel consultarPanel;

    public CaminhoDaPastaButton(ConsultarPanel consultarPanel) {
        this.consultarPanel = consultarPanel;

        this.setText("Caminho da pasta");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        consultarPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            consultarPanel.caminhoDaPastaLabel.setText(fileChooser.getSelectedFile().getPath());
    }
}
