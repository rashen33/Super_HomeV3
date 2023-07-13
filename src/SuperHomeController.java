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
    @Override
    public void update(String status) {
        this.status = status;
        notifyComponents();
    }


}
