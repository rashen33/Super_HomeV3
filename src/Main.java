class Main {
    public static void main(String[] args) {
        SuperHomeController superHomeController = new SuperHomeController();
        superHomeController.addComponent(new Switch(superHomeController,"Switch"));
        superHomeController.addComponent(new TV("TV"));
        superHomeController.addComponent(new Window("Window"));
        superHomeController.addComponent(new Speaker("Speaker"));
    }
}

