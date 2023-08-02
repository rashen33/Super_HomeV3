class TimeComp {
    private int startHours;
    private int startMinutes;
    private int endHours;
    private int endMinutes;

    public TimeComp(int startHours, int startMinutes, int endHours, int endMinutes) {
        this.startHours = startHours;
        this.startMinutes = startMinutes;
        this.endHours = endHours;
        this.endMinutes = endMinutes;
    }

    public void setStartHours(int startHours) {
        this.startHours = startHours;
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

    public int getStartHours() {
        return startHours;
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
    public String toString(){
        return  "Starts At : " + startHours + ":" + startMinutes + " Ends At : " + endHours + ":" + endMinutes;
    }

}