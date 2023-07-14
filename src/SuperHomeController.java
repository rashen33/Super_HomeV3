import javax.swing.*;

class SuperHomeController implements Components{  //Observer
    private Components[] compArray = new Components[100];
    private int nextIndex;
    private String status;

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
