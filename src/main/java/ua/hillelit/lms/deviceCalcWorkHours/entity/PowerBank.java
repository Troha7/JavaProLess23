package ua.hillelit.lms.deviceCalcWorkHours.entity;

public class PowerBank {

  private final String name;
  private final double volt;
  private final double capacity;

  public PowerBank(String name, double volt, double capacity) {
    this.name = name;
    this.volt = volt;
    this.capacity = capacity;
  }

  public String getName() {
    return name;
  }

  public double getVolt() {
    return volt;
  }

  public double getCapacity() {
    return capacity;
  }
}
