package AuthenticationFrames.PasswordFrame.Panel.NumberButton;

import javax.swing.*;

public class NumberButton extends JButton {

    public Integer number1 = -1;
    public Integer number2 = -1;

    public NumberButton(Integer number1, Integer number2) {
        this.number1 = number1;
        this.number2 = number2;

        this.setText(number1 + " ou " + number2);
        this.addActionListener(new OnClick(this));
    }
}
