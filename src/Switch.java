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
    private JSpinner hoursSpinnerSwitch;
    private JSpinner minutesSpinnerSwitch;

    private JList<String> timeList;
    private DefaultListModel<String> timeListModel;

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

                String[] listNames = superHomeController.getListComponents(); //Getting all the names from the components that has been created in the Main class.
                for (int i = 0; i < superHomeController.getNextIndex(); i++) {
                    componentListModel.addElement(listNames[i]); //Adding the names to the componentListModel
                }

                componentList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            String selectedComponent = componentList.getSelectedValue(); //Taking the name of the selected component from the JList (Controller JLISt)
                            if (selectedComponent != null) {
                                //----------Creating a JFrame (controller window) to the selected component
                                JFrame selectedFrame = new JFrame();
                                selectedFrame.setSize(550, 300);
                                selectedFrame.setTitle(selectedComponent);

                                //--------------------Top panel has the JList----------------------
                                JPanel topPanel = new JPanel(new BorderLayout());

                                timeListModel = new DefaultListModel<>();
                                timeList = new JList<>(timeListModel);

                                topPanel.add("Center",timeList);
                                selectedFrame.add("North",topPanel);

                                //------------Bottom panel with the start time end time JSpinners and Set JButton--------------
                                JPanel bottomPanel = new JPanel(new FlowLayout());

                                StartHoursLabel = new JLabel("Start: Hours");
                                StartHoursSpinner = createSpinner(0, 23, 1, 0, 2); //Passing the values to create the JSpinner to createSpinner method


                                StartMinutesLabel = new JLabel("Minutes");
                                StartMinutesSpinner = createSpinner(0, 59, 1, 0, 2);


                                EndHoursLabel = new JLabel("End: Hours");
                                EndHoursSpinner = createSpinner(0, 23, 1, 0, 2);


                                EndMinutesLabel = new JLabel("Minutes");
                                EndMinutesSpinner = createSpinner(0, 59, 1, 0, 2);


                                //Adding all the JSpinners and JLabels to the bottom panel
                                bottomPanel.add(StartHoursLabel);
                                bottomPanel.add(StartHoursSpinner);
                                bottomPanel.add(StartMinutesLabel);
                                bottomPanel.add(StartMinutesSpinner);

                                bottomPanel.add(EndHoursLabel);
                                bottomPanel.add(EndHoursSpinner);
                                bottomPanel.add(EndMinutesLabel);
                                bottomPanel.add(EndMinutesSpinner);

                                //----The "Set" button as function button-----
                                functionButton = new JButton("Set");
//                                functionButton.addActionListener(new ActionListener() {
//                                    public void actionPerformed(ActionEvent evt) {
//                                        int selectedIndex = componentListModel.indexOf(selectedComponent); //index of the selected Component
//                                        System.out.println("index of the element " + selectedIndex);
//                                        int startHour = (int) StartHoursSpinner.getValue();
//                                        int startMinutes = (int) StartMinutesSpinner.getValue();
//                                        int endHour = (int) EndHoursSpinner.getValue();
//                                        int endMinutes = (int) EndMinutesSpinner.getValue();
//
//                                        //---------------Time from the Switch timer------------------
//                                        int startHourSwitch = (int) hoursSpinnerSwitch.getValue();
//                                        int startMinutesSwitch = (int) minutesSpinnerSwitch.getValue();
//
//                                        String setTime = "Starts at : " + startHour + ":" + startMinutes + "  Ends at : " + endHour + ":" + endMinutes;
//                                        timeListModel.addElement(setTime);
//                                        TimeComps timeComps = new TimeComps(startHour,startMinutes,endHour,endMinutes,startHourSwitch,startMinutesSwitch);
//
//
//                                        System.out.println(startHourSwitch + " " + startMinutesSwitch);
//                                    }
//                                });
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

        //--------------Bottom Panel of the Switch main window with the JSpinners-----------------
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        hoursLabel = new JLabel("Hours");
        hoursSpinner = createSpinner(0, 23, 1, 0, 2);

        minutesLabel = new JLabel("Minutes");
        minutesSpinner = createSpinner(0, 59, 1, 0, 2);

        bottomPanel.add(hoursLabel);
        bottomPanel.add(hoursSpinner);
        bottomPanel.add(minutesLabel);
        bottomPanel.add(minutesSpinner);
        //topPanel.add(bottomPanelSwitch);

        frame.add("North",topPanel); //Consisting the power button and the settings button
        frame.add("South", bottomPanel);//Consisting the two JSpinners

        frame.setVisible(true);
    }
    private JSpinner createSpinner(int min, int max, int step, int initialValue, int digits) {
        SpinnerModel spinnerModel = new SpinnerNumberModel(initialValue, min, max, step);
        JSpinner spinner = new JSpinner(spinnerModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "00"); //To format the number
        editor.getTextField().setColumns(digits);
        spinner.setEditor(editor);
        return spinner;
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
