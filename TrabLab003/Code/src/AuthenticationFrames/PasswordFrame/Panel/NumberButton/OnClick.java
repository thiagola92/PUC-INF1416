package AuthenticationFrames.PasswordFrame.Panel.NumberButton;

import _System_.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClick implements ActionListener {

    public NumberButton numberButton;

    public OnClick(NumberButton numberButton) {
        this.numberButton = numberButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.passwordFrame.panel.addPasswordNumbers(numberButton.number1, numberButton.number2);
    }
}
