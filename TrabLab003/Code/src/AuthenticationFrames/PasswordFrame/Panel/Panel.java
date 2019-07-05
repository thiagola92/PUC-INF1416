package AuthenticationFrames.PasswordFrame.Panel;

import AuthenticationFrames.PasswordFrame.Panel.LoginButton.LoginButton;
import AuthenticationFrames.PasswordFrame.Panel.NumberButton.NumberButton;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Panel extends JPanel {

    public ArrayList<Integer[]> passwordNumbers = new ArrayList<>();

    public Panel() {
        this.setLayout(new GridBagLayout());

        createButtons(generateRandomSequence());
    }

    public ArrayList<Integer> generateRandomSequence() {
        ArrayList<Integer> normalSequence = new ArrayList<>();

        for(int i = 0; i < 10; i++)
            normalSequence.add(i, i);

        SecureRandom secureRandom = new SecureRandom();
        ArrayList<Integer> randomSequence = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            int positionToRemove = secureRandom.nextInt(normalSequence.size());
            randomSequence.add(randomSequence.size(), normalSequence.remove(positionToRemove));
        }

        return randomSequence;
    }

    public void createButtons(ArrayList<Integer> numbersSequence) {
        this.removeAll();

        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 10, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new NumberButton(numbersSequence.get(0), numbersSequence.get(1)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new NumberButton(numbersSequence.get(2), numbersSequence.get(3)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new NumberButton(numbersSequence.get(4), numbersSequence.get(5)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new NumberButton(numbersSequence.get(6), numbersSequence.get(7)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new NumberButton(numbersSequence.get(8), numbersSequence.get(9)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);

        this.add(new LoginButton(), gridBagConstraints);
    }

    public void addPasswordNumbers(int number1, int number2) {
        passwordNumbers.add(new Integer[]{number1, number2});
        createButtons(generateRandomSequence());
        this.updateUI();
    }
}
