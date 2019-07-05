package AuthenticationFrames.KeyVerificationFrame.Panel.SelectFileButton;

import _System_.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class OnClick implements ActionListener {

    public SelectFileButton selectFileButton;

    public OnClick(SelectFileButton selectFileButton) {
        this.selectFileButton = selectFileButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        if(fileChooser.showOpenDialog(selectFileButton) == JFileChooser.APPROVE_OPTION)
            Main.keyVerificationFrame.panel.cipherPemPath = Paths.get(fileChooser.getSelectedFile().getPath());
    }
}
