package AuthenticationFrames.LoginFrame;

import Database.Database;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OnChange implements WindowListener {
    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {
        try {
            Database.log(2001);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        try {
            Database.log(2002);
            Database.log(1002);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e) {
    }

    @Override
    public void windowIconified(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowDeiconified(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowActivated(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {

    }
}
