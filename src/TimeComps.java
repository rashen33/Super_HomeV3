class TimeComps {
    private int startHour;
    private int startMinutes;
    private int endHours;
    private int endMinutes;
    private int startHourSwitch;
    private int startMinuteSwitch;
    TimeComps(int startHour, int startMinutes, int endHours, int endMinutes, int startHourSwitch, int startMinuteSwitch){
        this.startHour = startHour;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
        this.startHourSwitch = startHourSwitch;
        this.startMinuteSwitch = startMinuteSwitch;
    }
    public int getStartHour() {
        return startHour;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public int getEndHours() {
        return endHours;
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public int getStartHourSwitch() {
        return startHourSwitch;
    }

    public int getStartMinuteSwitch() {
        return startMinuteSwitch;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
    }

    public void setEndHours(int endHours) {
        this.endHours = endHours;
    }

    public void setEndMinutes(int endMinutes) {
        this.endMinutes = endMinutes;
    }

    public void setStartHourSwitch(int startHourSwitch) {
        this.startHourSwitch = startHourSwitch;
    }

    public void setStartMinuteSwitch(int startMinuteSwitch) {
        this.startMinuteSwitch = startMinuteSwitch;
    }
}

