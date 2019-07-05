package MenuFrame;

import Database.Database;
import Security.Validation1;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OnChange implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {
        try {
            Database.log(5001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            Database.log(5002, Validation1.user.getString("email"));
            Database.log(1002, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
