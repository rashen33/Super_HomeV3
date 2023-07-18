class TimeComp {
    private String startHours;
    private String startMinutes;
    private String endHours;
    private String endMinutes;

    public TimeComp(String startHours, String startMinutes, String endHours, String endMinutes) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
    }

    public void setStartHours(String startHours) {
        this.startHours = startHours;
    }

    public void setStartMinutes(String startMinutes) {
        this.startMinutes = startMinutes;
    }

    public void setEndHours(String endHours) {
        this.endHours = endHours;
    }

    public void setEndMinutes(String endMinutes) {
        this.endMinutes = endMinutes;
    }

    public String getStartHours() {
        return startHours;
    }

    public String getStartMinutes() {
        return startMinutes;
    }

    public String getEndHours() {
        return endHours;
    }

    public String getEndMinutes() {
        return endMinutes;
    }
    public String toString(){
        return  "Starts At : " + startHours + ":" + startMinutes + " Ends At : " + endHours + ":" + endMinutes;
    }

}