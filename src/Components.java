interface Components {
    void update(String status);
    String getName();
    void setTime(int startHours, int startMinutes, int endHours, int endMinutes);
    int getStartHours();
    int getStartMinutes();
    int getEndHours();
    int getEndMinutes();
}