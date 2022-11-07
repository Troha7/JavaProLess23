package ua.hillelit.lms.deviceCalcWorkHours.entity;

public class Device {

  private final String name;
  private final double volt;
  private final boolean currentAC;
  private final double ampere;
  private final double startCurrentCoeff;

  private final double startedTime;

  public Device(String name, double volt, boolean currentAC, double ampere,
      double startCurrentCoeff,
      double startedTime) {
    this.name = name;
    this.volt = volt;
    this.currentAC = currentAC;
    this.ampere = ampere;
    this.startCurrentCoeff = startCurrentCoeff;
    this.startedTime = startedTime;
  }

  public String getName() {
    return name;
  }

  public double getVolt() {
    return volt;
  }

  public boolean isCurrentAC() {
    return currentAC;
  }

  public double getAmpere() {
    return ampere;
  }

  public double getStartCurrentCoeff() {
    return startCurrentCoeff;
  }

  public double getStartedTime() {
    return startedTime;
  }

}
