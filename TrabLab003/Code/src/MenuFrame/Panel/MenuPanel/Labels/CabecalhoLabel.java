package MenuFrame.Panel.MenuPanel.Labels;

import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.DAO;
import Security.CertificateUtility;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class CabecalhoLabel extends JLabel {

    public CabecalhoLabel(MenuPanel menuPanel) {
        String cabecalho = "<html>";

        try{
            String email = Validation1.user.getString("email");
            String grupo_nome = DAO.getInstance().getGrupoNome(Validation1.user.getInt("grupo"));
            String nome = CertificateUtility.getCertificateCN(Validation1.user.getBytes("certificate"));

            cabecalho = cabecalho + "Login: " + email + "<br>";
            cabecalho = cabecalho + "Grupo: " + grupo_nome + "<br>";
            cabecalho = cabecalho + "Nome: " + nome + "<br>";
        } catch (Exception e){
            e.printStackTrace();
        }

        cabecalho = cabecalho + "</html>";
        this.setText(cabecalho);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }
}
