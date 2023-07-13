import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Switch implements Components {
    private JFrame frame;
    private JButton powerButton;
    private JButton settingsButton;
    //Insert Code
    private String status;

    Switch(SuperHomeController superHomeController,String name){
        frame = new JFrame();
        frame.setSize(300, 150);
        frame.setTitle(name);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // -----------Top panel with the On/off button and the settings button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        powerButton = new JButton("ON");
        powerButton.setFont(new Font("", 1, 20));
        powerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (powerButton.getText().equals("ON")) {
                    powerButton.setText("OFF");
                    status = "ON";
                    superHomeController.update(status);
                } else {
                    powerButton.setText("ON");
                    status = "OFF";
                    superHomeController.update(status);
                }
            }
        });

        topPanel.add(powerButton);
        frame.add(topPanel);

        settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("", 1, 20));
        topPanel.add(settingsButton);
        frame.add(topPanel);

        frame.add("North", topPanel);

        frame.setVisible(true);
    }

    @Override
    public void update(String status) {
        this.status = status;
        System.out.println("Update method Switch");
    }


//    public static void main(String[] args) {
//        new Switch().frame.setVisible(true);
//    }
}