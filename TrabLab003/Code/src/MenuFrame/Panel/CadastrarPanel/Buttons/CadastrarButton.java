package MenuFrame.Panel.CadastrarPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastrarButton extends JButton implements ActionListener {

    CadastrarPanel cadastrarPanel;

    public CadastrarButton(CadastrarPanel cadastrarPanel) {
        this.cadastrarPanel = cadastrarPanel;

        this.setText("Cadastrar");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        cadastrarPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(6002, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        X509Certificate certificate = getCertificate();

        String email = "";
        String emissor = "";
        String sujeito = "";

        try {
            email = CertificateUtility.getCertificateEMAILADDRESS(certificate);
            sujeito = CertificateUtility.getCertificateCamp(certificate, "CN");

            String regex = ".*CN=([^,]*).*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(certificate.getIssuerX500Principal().toString());
            matcher.matches();
            emissor = matcher.group(1);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String message = "<html>";
        message += "Versao: " + certificate.getVersion();
        message += "<br>Serie: " + certificate.getSerialNumber().toString();
        message += "<br>Validade: " + certificate.getNotAfter().toString();
        message += "<br>Tipo de assinatura: " + certificate.getSigAlgName();
        message += "<br>Emissor: " + emissor;
        message += "<br>Sujeito: " + sujeito;
        message += "<br>Email: " + email;
        message += "<br>Tipo de assinatura: " + certificate.getSigAlgName();
        message += "</html>";

        int answer = JOptionPane.showConfirmDialog(this, message, "Confirmacao", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION) {
            cadastrar(email);

            MenuFrame.getInstance().remove(this.cadastrarPanel);
            CadastrarPanel cadastrarPanel = new CadastrarPanel();
            MenuFrame.getInstance().add(cadastrarPanel);
            cadastrarPanel.updateUI();
        }
    }

    public X509Certificate getCertificate() {
        try {
            Path path = Paths.get(cadastrarPanel.caminhoDoCertificadoLabel.getText());
            byte[] file = Files.readAllBytes(path);

            return CertificateUtility.getCertificate(file);
        } catch (Exception e){
            try {
                Database.log(6004, Validation1.user.getString("email"));
                e.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    public boolean isPasswordValid() {
        String senha = new String(cadastrarPanel.senhaTextField.getPassword());
        String confirmaSenha = new String(cadastrarPanel.confirmacaoSenhaTextField.getPassword());

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

        if(senha.length() < 6 || senha.length() > 8)
            return false;

        //checa se existe sequencia ou repeticao de digitos
        for(int i = 0; i < senha.length() - 1; i++){
            char c = senha.charAt(i);
            int digito = Character.getNumericValue(c);
            char cNext = senha.charAt(i+1);
            int digitoNext = Character.getNumericValue(cNext);

            if((digitoNext == digito + 1) || (digitoNext == digito - 1) ||(digitoNext == digito)){
                return false;
            }
        }

        return true;
    }

    public int getGrupo() {
        String grupo = (String)cadastrarPanel.grupoComboBox.getSelectedItem();

        if(grupo.equals("Administrador"))
            return 0;
        else if(grupo.equals("Usuario"))
            return 1;

        return 999;
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
            String senha = new String(cadastrarPanel.senhaTextField.getPassword());

            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update((senha + new String(salt)).getBytes());

            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void cadastrar(String email) {
        try {
            if(!isPasswordValid()) {
                Database.log(6003, Validation1.user.getString("email"));
                return;
            }

            if(DAO.getInstance().getEmailCount(email) != 0) {
                JOptionPane.showMessageDialog(this, "Esse email está em uso");
                return;
            }

            byte[] salt = getSalt();
            DAO.getInstance().insertUsuario(email, getDigest(salt), salt, cadastrarPanel.caminhoDoCertificadoLabel.getText(), getGrupo());

            Database.log(6005, Validation1.user.getString("email"));
        } catch (Exception e) {
            try {
                Database.log(6006, Validation1.user.getString("email"));
                e.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


}
