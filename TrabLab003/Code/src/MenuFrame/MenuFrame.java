package MenuFrame;

import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.DAO;
import Security.Validation1;

import javax.swing.*;

public class MenuFrame extends JFrame {

    private final MenuPanel menuPanel;
    private final String NAME = "Tela Principal";
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private static MenuFrame instance = null;

    public MenuFrame(){
        this.addWindowListener(new OnChange());

        menuPanel = new MenuPanel();
        this.add(menuPanel);

        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setTitle(NAME);
        this.setVisible(true);
    }

    private static void count() {
        try {
            String email = Validation1.user.getString("email");

            DAO.getInstance().incrementAcessos(email);
        } catch (Exception e) {

        }
    }

    public static MenuFrame getInstance(){
        if(instance == null){
            count();
            instance = new MenuFrame();
        }
        return instance;
    }
}
