class SuperHomeController implements Components{  //Observer
    private Components[] compArray = new Components[100];
    private int nextIndex;
    private String status;
    private int selectedIndex;
    private int startHours;
    private int startMinutes;
    private int endHours;
    private int endMinutes;

    public void addComponent(Components ob) {
        compArray[nextIndex++] = ob;
    }

    public int getNextIndex() {
        return nextIndex;
    }
    public String[] getListComponents() {
        String[] names = new String[nextIndex];
        for (int i = 0; i < nextIndex; i++) {
            names[i] = compArray[i].getName();
        }
        return names;
    }
    public void setSelectedIndex(int selectedIndex){
        if(this.selectedIndex != selectedIndex){
            this.selectedIndex = selectedIndex;
        }
    }
    public void updateCompStatus(String status){
        compArray[selectedIndex].update(status);
    }
    public  void notifyByIndex(){
        compArray[selectedIndex].setTime(startHours,startMinutes,endHours,endMinutes);
    }
    public void setTime(int startHours,int startMinutes,int endHours,int endMinutes){
        if( this.startHours!=startHours || this.startMinutes!=startMinutes || this.endHours!=endHours || this.endMinutes!=endMinutes){
            this.startHours = startHours;
            this.startMinutes = startMinutes;
            this.endHours = endHours;
            this.endMinutes = endMinutes;
            notifyByIndex();
        }

    }
    public void notifyComponents() {
        for (int i = 1; i < nextIndex; i++) {
            compArray[i].update(status);
        }
    }
    @Override
    public void update(String status) {
        if(this.status!=status){
            this.status = status;
            notifyComponents();
        }
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getStartHours() {
        return startHours;
    }

    @Override
    public int getStartMinutes() {
        return startMinutes;
    }

    @Override
    public int getEndHours() {
        return endHours;
    }

    @Override
    public int getEndMinutes() {
        return endMinutes;
    }
}
