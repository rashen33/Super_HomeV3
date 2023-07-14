import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

class Switch implements Components {
    private JFrame frame;
    private JButton powerButton;
    private JButton settingsButton;
    private JSpinner hoursSpinner;
    private JSpinner minutesSpinner;
    private JLabel hoursLabel;
    private JLabel minutesLabel;
    private String status;
    private JList<String> componentList;
    private DefaultListModel<String> componentListModel;
    private JButton functionButton;
    private JLabel StartHoursLabel;
    private JLabel StartMinutesLabel;
    private JLabel EndHoursLabel;
    private JLabel EndMinutesLabel;
    private JSpinner StartHoursSpinner;
    private JSpinner StartMinutesSpinner;
    private JSpinner EndMinutesSpinner;
    private JSpinner EndHoursSpinner;

    Switch(SuperHomeController superHomeController, String name) {
        frame = new JFrame();
        frame.setSize(300, 150);
        frame.setTitle(name);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

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
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFrame settingsFrame = new JFrame();
                settingsFrame.setSize(300, 300);
                settingsFrame.setTitle("Controller");
                settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                settingsFrame.setLocationRelativeTo(null);

                componentListModel = new DefaultListModel<>();
                componentList = new JList<>(componentListModel);

                String[] listNames = superHomeController.getListComponents();
                for (int i = 0; i < superHomeController.getNextIndex(); i++) {
                    componentListModel.addElement(listNames[i]);
                }

                componentList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            String selectedComponent = componentList.getSelectedValue();
                            if (selectedComponent != null) {
                                JFrame selectedFrame = new JFrame();
                                selectedFrame.setSize(550, 300);
                                selectedFrame.setTitle(selectedComponent);

                                JPanel bottomPanel = new JPanel(new FlowLayout());

                                SpinnerModel valueStartHours = new SpinnerNumberModel(0, 0, 23, 1);
                                StartHoursLabel = new JLabel("Start: Hours");
                                StartHoursSpinner = new JSpinner(valueStartHours);

                                SpinnerModel valueStartMinutes = new SpinnerNumberModel(0, 0, 59, 1);
                                StartMinutesLabel = new JLabel("Minutes");
                                StartMinutesSpinner = new JSpinner(valueStartMinutes);

                                SpinnerModel valueEndHours = new SpinnerNumberModel(0, 0, 23, 1);
                                EndHoursLabel = new JLabel("End: Hours");
                                EndHoursSpinner = new JSpinner(valueEndHours);

                                SpinnerModel valueEndMinutes = new SpinnerNumberModel(0, 0, 59, 1);
                                EndMinutesLabel = new JLabel("Minutes");
                                EndMinutesSpinner = new JSpinner(valueEndMinutes);

                                bottomPanel.add(StartHoursLabel);
                                bottomPanel.add(StartHoursSpinner);
                                bottomPanel.add(StartMinutesLabel);
                                bottomPanel.add(StartMinutesSpinner);

                                bottomPanel.add(EndHoursLabel);
                                bottomPanel.add(EndHoursSpinner);
                                bottomPanel.add(EndMinutesLabel);
                                bottomPanel.add(EndMinutesSpinner);

                                functionButton = new JButton("Set");
                                functionButton.setFont(new Font("", 1, 20));
                                bottomPanel.add(functionButton);

                                selectedFrame.add("South",bottomPanel);


                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setVisible(true);
                            }
                        }
                    }
                });


                settingsFrame.add(componentList);
                settingsFrame.setVisible(true);
            }
        });


        settingsButton.setFont(new Font("", 1, 20));
        topPanel.add(settingsButton);

        frame.add("North", topPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        SpinnerModel valueHours = new SpinnerNumberModel(0, 0, 23, 1);
        hoursLabel = new JLabel("Hours");
        hoursSpinner = new JSpinner(valueHours);

        SpinnerModel valueMinutes = new SpinnerNumberModel(0, 0, 59, 1);
        minutesLabel = new JLabel(" Minutes");
        minutesSpinner = new JSpinner(valueMinutes);

        bottomPanel.add(hoursLabel);
        bottomPanel.add(hoursSpinner);
        bottomPanel.add(minutesLabel);
        bottomPanel.add(minutesSpinner);

        frame.add("South", bottomPanel);

        frame.setVisible(true);
    }

    @Override
    public void update(String status) {
        this.status = status;
        System.out.println("Update method Switch");
    }

    @Override
    public String getName() {
        return frame.getTitle();
    }
}
