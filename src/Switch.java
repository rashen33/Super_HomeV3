import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JList<TimeComp> timeList;
    private DefaultListModel<TimeComp> timeListModel;
    private TimeComp timeComp;
    private SuperHomeController superHomeController;

    Switch(SuperHomeController superHomeController, String name) {
        this.superHomeController = superHomeController;

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

                JPanel settingsPanel = new JPanel(new BorderLayout());

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

                                int selectedIndex = componentListModel.indexOf(selectedComponent);
                                superHomeController.setSelectedIndex(selectedIndex);
                                System.out.println("Selected component: " + selectedIndex);// index of the selected Component

                                JPanel topPanel = new JPanel(new BorderLayout());

                                timeListModel = new DefaultListModel<>();
                                timeList = new JList<>(timeListModel);

                                topPanel.add(timeList, BorderLayout.CENTER);
                                selectedFrame.add(topPanel, BorderLayout.NORTH);

                                JPanel bottomPanel = new JPanel(new FlowLayout());

                                StartHoursLabel = new JLabel("Start: Hours");
                                StartHoursSpinner = createSpinner(0, 23, 1, 0, 2);

                                StartMinutesLabel = new JLabel("Minutes");
                                StartMinutesSpinner = createSpinner(0, 59, 1, 0, 2);

                                EndHoursLabel = new JLabel("End: Hours");
                                EndHoursSpinner = createSpinner(0, 23, 1, 0, 2);

                                EndMinutesLabel = new JLabel("Minutes");
                                EndMinutesSpinner = createSpinner(0, 59, 1, 0, 2);

                                bottomPanel.add(StartHoursLabel);
                                bottomPanel.add(StartHoursSpinner);
                                bottomPanel.add(StartMinutesLabel);
                                bottomPanel.add(StartMinutesSpinner);
                                bottomPanel.add(EndHoursLabel);
                                bottomPanel.add(EndHoursSpinner);
                                bottomPanel.add(EndMinutesLabel);
                                bottomPanel.add(EndMinutesSpinner);

                                functionButton = new JButton("Set");
                                functionButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                        int startHours = (int) StartHoursSpinner.getValue();
                                        int startMinutes = (int) StartMinutesSpinner.getValue();
                                        int endHours = (int) EndHoursSpinner.getValue();
                                        int endMinutes = (int) EndMinutesSpinner.getValue();
                                        timeListModel.addElement(new TimeComp(startHours, startMinutes, endHours, endMinutes));
                                        superHomeController.setTime(startHours, startMinutes, endHours, endMinutes);
                                    }
                                });
                                functionButton.setFont(new Font("", 1, 20));
                                bottomPanel.add(functionButton);

                                selectedFrame.add(bottomPanel, BorderLayout.SOUTH);
                                selectedFrame.setLocationRelativeTo(null);
                                selectedFrame.setVisible(true);
                            }
                        }
                    }
                });

                JScrollPane componentScrollPane = new JScrollPane(componentList);
                settingsPanel.add(componentScrollPane, BorderLayout.CENTER);

                settingsFrame.add(settingsPanel);
                settingsFrame.setVisible(true);
            }
        });
        settingsButton.setFont(new Font("", 1, 20));
        topPanel.add(settingsButton);
        frame.add("North", topPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        hoursLabel = new JLabel("Hours");
        hoursSpinner = createSpinner(0, 23, 1, 0, 2);
        ChangeListener hoursChangeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentHours = (int) hoursSpinner.getValue();
                int currentMinutes = (int) minutesSpinner.getValue();
                System.out.println("Current Hours: " + currentHours);
                System.out.println("Current Minutes: " + currentMinutes);

                // Access start and end time from the selected component
                int startHours = superHomeController.getStartHours();
                int startMinutes = superHomeController.getStartMinutes();
                int endHours = superHomeController.getEndHours();
                int endMinutes = superHomeController.getEndMinutes();
                System.out.println("Start Time: " + startHours + ":" + startMinutes);
                System.out.println("End Time: " + endHours + ":" + endMinutes);

                // Compare with the selected component's start and end time
                if (currentHours >= startHours && currentHours <= endHours) {
                    status = "OFF";
                    System.out.println("OFF");
                } else {
                    status = "ON";
                    System.out.println("ON");
                }
                superHomeController.updateCompStatus(status);
            }
        };
        hoursSpinner.addChangeListener(hoursChangeListener);

        minutesLabel = new JLabel("Minutes");
        minutesSpinner = createSpinner(0, 59, 1, 0, 2);

        bottomPanel.add(hoursLabel);
        bottomPanel.add(hoursSpinner);
        bottomPanel.add(minutesLabel);
        bottomPanel.add(minutesSpinner);

        frame.add("South", bottomPanel);

        frame.setVisible(true);
    }
    private JSpinner createSpinner(int min, int max, int step, int initialValue, int digits) {
        SpinnerModel spinnerModel = new SpinnerNumberModel(initialValue, min, max, step);
        JSpinner spinner = new JSpinner(spinnerModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "00");
        editor.getTextField().setColumns(digits);
        spinner.setEditor(editor);
        return spinner;
    }

    private String formatTime(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
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

    @Override
    public void setTime(int startHours, int startMinutes, int endHours, int endMinutes) {
    }

    @Override
    public int getStartHours() {
        return 0;
    }

    @Override
    public int getStartMinutes() {
        return 0;
    }

    @Override
    public int getEndHours() {
        return 0;
    }

    @Override
    public int getEndMinutes() {
        return 0;
    }
}