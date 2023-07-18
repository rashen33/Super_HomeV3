import javax.swing.*;

class SuperHomeController implements Components{  //Observer
    private Components[] compArray = new Components[100];
    private int nextIndex;
    private String status;
    private int selectedIndex;
    private String startHours;
    private String startMinutes;
    private String endHours;
    private String endMinutes;

    public void addComponent(Components ob) {
        compArray[nextIndex++] = ob;
    }
    public void notifyComponents() {
        for (int i = 1; i < nextIndex; i++) {
            compArray[i].update(status);
        }
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
    public Components getComponent(int index){
        return compArray[index];
    }
//    public void sendUpdateComponent(String status,String name){
//        for (int i = 1; i < nextIndex; i++){
//            if (compArray[i].getName().equals(name)){
//                compArray[i].update(status);
//            }
//        }
//    }

    public void sendUpdateComponent(String status,int index){
        compArray[index].update(status);
    }
    public void setSelectedIndex(int selectedIndex){
        if(this.selectedIndex != selectedIndex){
            this.selectedIndex = selectedIndex;
        }
    }
    public  void notifyByIndex(){
            compArray[selectedIndex].setTime(startHours,startMinutes,endHours,endMinutes);
    }
    public void setTime(String startHours,String startMinutes,String endHours,String endMinutes){
        if( this.startHours!=startHours || this.startMinutes!=startMinutes || this.endHours!=endHours || this.endMinutes!=endMinutes){
            this.startHours = startHours;
            this.startMinutes = startMinutes;
            this.endHours = endHours;
            this.endMinutes = endMinutes;
            notifyByIndex();
        }

    }

    @Override
    public void update(String status) {
        this.status = status;
        notifyComponents();
    }
    @Override
    public String getName() {
        return null;
    }
}
