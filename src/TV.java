import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class TV implements Components{
    private JFrame frame;
    private JLabel statusLabel;
    TV(String name){
        frame = new JFrame();
        frame.setSize(250,100);
        frame.setTitle(name);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        statusLabel = new JLabel();
        statusLabel.setText("OFF");
        statusLabel.setFont(new Font("",1,20));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add("Center",statusLabel);

        frame.setVisible(true);

    }
    @Override
    public void update(String status) {
        if(status.equals("ON")){
            statusLabel.setText("ON");
            System.out.println("Update method TV");
        }else{
            statusLabel.setText("OFF");
            System.out.println("Update method TV");
        }
    }

}
