package ua.hillelit.lms.deviceCalcWorkHours.service;

import ua.hillel.electric.Electric;
import ua.hillelit.lms.logger.api.Loggable;
import ua.hillelit.lms.deviceCalcWorkHours.entity.Device;
import ua.hillelit.lms.deviceCalcWorkHours.entity.PowerBank;
import ua.hillelit.lms.logger.loggers.FileLogger;
import ua.hillelit.lms.logger.loggers.FileLoggerConfiguration;

/**
 * The class {@code PowerCalc} contain method {@code hoursOfWork}
 * <p>for calculation working hours of the device from the power bank,
 * depending on the {@code load} of the device.
 *
 * @author  Dmytro Trotsenko
 **/
public class PowerCalc {

  private final double load; // Load %

  public PowerCalc(double load) {
    this.load = load;
  }

  public double hoursOfWork(Device device, PowerBank powerBank) {

    Loggable fileLogger = new FileLogger(new FileLoggerConfiguration());
    //Loggable stdoutLogger = new StdoutLogger(new StdoutLoggerConfiguration());

    fileLogger.info("Start calculation " + device.getName() + " and " + powerBank.getName());

    if (device.getVolt() != powerBank.getVolt()) {
      throw new IllegalArgumentException("Devise voltage does not match the power bank voltage!");
    }

    double deviceWatt = Electric.watt(device.getVolt(), device.getAmpere(), device.isCurrentAC());
    fileLogger.info("Device consumers: " + deviceWatt + " Watt");

    double powerBankWattHour = Electric.mAmpHourToWattHour(powerBank.getCapacity());
    fileLogger.info("Convert capacity of PowerBank to mW/h: " + powerBankWattHour + " W/h");

    double deviceStartWatt = Electric.startWatt(deviceWatt, device.getStartCurrentCoeff());
    fileLogger.info("Device start consumers: " + deviceStartWatt + " Watt");

    double runPowerWatt = deviceStartWatt * device.getStartedTime();
    fileLogger.info("Device run power consumers: " + runPowerWatt + " Watt");

    if (load <= 0 || load > 100) {
      throw new IllegalArgumentException("Load must be > 0 and < 100");
    }

    double totalDeviceWatt = (deviceWatt + runPowerWatt) * (load / 100);
    fileLogger.info("Device total consumers at " + load + "% load: " + totalDeviceWatt + " Watt");

    double hoursOfWork = Electric.hourWork(powerBankWattHour, totalDeviceWatt);
    fileLogger.info("Device will work: " + hoursOfWork + " h.");

    return hoursOfWork;
  }


}
