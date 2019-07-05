package MenuFrame.Panel.AlterarSenhaPanel.Buttons;

import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;
import Database.Database;
import Database.DAO;
import Security.CertificateUtility;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class AlterarButton extends JButton implements ActionListener {

    AlterarSenhaPanel alterarSenhaPanel;

    public AlterarButton(AlterarSenhaPanel alterarSenhaPanel) {
        this.alterarSenhaPanel = alterarSenhaPanel;

        this.setText("Alterar");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
    }

    public boolean isPaasswordValid(String senha, String confirmaSenha) {
        if(!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "Senhas diferentes");
            return false;
        }

        try {
            Integer.parseInt(senha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Senha só pode ter números");
            e.printStackTrace();

            return false;
        }

        if(senha.length() < 6 || senha.length() > 8) {
            JOptionPane.showMessageDialog(this, "Senha precisa ter 6/7/8 digitos");
            return false;
        }

        //checa se existe sequencia ou repeticao de digitos
        for(int i = 0; i < senha.length() - 1; i++){
            char c = senha.charAt(i);
            int digito = Character.getNumericValue(c);
            char cNext = senha.charAt(i+1);
            int digitoNext = Character.getNumericValue(cNext);

            if((digitoNext == digito + 1) || (digitoNext == digito - 1) ||(digitoNext == digito)){
                JOptionPane.showMessageDialog(this, "Senha fraca");
                return false;
            }
        }

        return true;
    }

    public X509Certificate getCertificate() {
        try {
            Path path = Paths.get(alterarSenhaPanel.caminhoDoCertificadoLabel.getText());
            byte[] file = Files.readAllBytes(path);

            return CertificateUtility.getCertificate(file);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public byte[] getSalt() {
        byte[] salt = new byte[10];

        String validChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = new SecureRandom();

        for(int i = 0; i < salt.length; i++)
            salt[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));

        return salt;
    }

    public byte[] getDigest(byte[] salt) {
        try {
            String senha = new String(alterarSenhaPanel.senhaTextField.getPassword());

            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update((senha + new String(salt)).getBytes());

            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void alterarSenha(String senha, String confirmaSenha) {
        try {
            if(isPaasswordValid(senha, confirmaSenha) == false) {
                Database.log(7002, Validation1.user.getString("email"));
                return;
            }

            byte[] salt = getSalt();
            byte[] digest = getDigest(salt);

            DAO.getInstance().updatePassword(Validation1.user.getString("email"), digest, salt);
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso");
        } catch (Exception e) {
            try {
                Database.log(7005, Validation1.user.getString("email"));
                e.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public void alterarCertificado() {
        try {
            if(CertificateUtility.getCertificateEMAILADDRESS(getCertificate()).equals(Validation1.user.getString("email")) == false) {
                Database.log(7005, Validation1.user.getString("email"));
                JOptionPane.showMessageDialog(this, "O email do usuario e do certicado não são iguais");
                return;
            }

            DAO.getInstance().updateCertificate(Validation1.user.getString("email"), alterarSenhaPanel.caminhoDoCertificadoLabel.getText());
            JOptionPane.showMessageDialog(this, "Certificado alterado com sucesso");
        } catch (Exception e) {
            try {
                Database.log(7003, Validation1.user.getString("email"));
                Database.log(7005, Validation1.user.getString("email"));
                e.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(8002, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String senha = new String(alterarSenhaPanel.senhaTextField.getPassword());
        String confirmaSenha = new String(alterarSenhaPanel.confirmacaoSenhaTextField.getPassword());


        if(senha.length() != 0 || confirmaSenha.length() != 0)
            alterarSenha(senha, confirmaSenha);

        if(alterarSenhaPanel.caminhoDoCertificadoLabel.getText().equals("") == false)
            alterarCertificado();
    }
}
