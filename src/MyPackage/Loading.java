package MyPackage;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class Loading extends JPanel {

    private JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    private JLabel label = new JLabel("", JLabel.CENTER);
    private Timer timer = new Timer(1, new ActionListener() {

        private int counter = 1;

        public void actionPerformed(ActionEvent ae) {
            label.setText(String.valueOf(counter));
            bar.setValue(++counter);
            if (counter > 100) {
                timer.stop();
            }
        }
    });

public Loading() {
        super.setLayout(new GridLayout(0, 1));
        
        bar.setValue(0);
        timer.start();
        bar.setStringPainted(true);
        this.add(bar);
       JOptionPane.showMessageDialog(null, this,"LOADING ...", JOptionPane.INFORMATION_MESSAGE);
        
    }
    }