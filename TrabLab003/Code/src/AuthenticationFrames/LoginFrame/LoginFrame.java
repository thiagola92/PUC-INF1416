package AuthenticationFrames.LoginFrame;

import AuthenticationFrames.LoginFrame.Panel.Panel;

import javax.swing.JFrame;

public class LoginFrame extends JFrame {

    public Panel panel;

    private final String NAME = "AuthenticationFrames/LoginFrame";
    private final int WIDTH = 500;
    private final int HEIGHT = 100;

    public LoginFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setTitle(NAME);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
        this.addWindowListener(new OnChange());

        panel = new Panel();
        this.setContentPane(panel);

        this.setVisible(true);
    }
}
