package ua.hillelit.lms;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ua.hillelit.lms.deviceCalcWorkHours.entity.Device;
import ua.hillelit.lms.deviceCalcWorkHours.entity.PowerBank;
import ua.hillelit.lms.deviceCalcWorkHours.service.PowerCalc;

public class PowerCalcTest {

  Device laptop = new Device("Lenovo", 20, false, 2.25, 1.1, 0.1);
  Device phone = new Device("Xiaomi", 5, false, 0.7, 1, 1);

  PowerBank powerBank = new PowerBank("Laptop PowerBank", 20, 10000);
  PowerBank powerBank2 = new PowerBank("Phone Power Bank", 5, 1000);

  @Test(expected = IllegalArgumentException.class)
  public void testVoltageInconsistencies() {
    PowerCalc powerCalc = new PowerCalc(100);
    powerCalc.hoursOfWork(laptop, powerBank2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadInconsistenciesMax() {
    PowerCalc powerCalc = new PowerCalc(150);
    powerCalc.hoursOfWork(laptop, powerBank);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadInconsistenciesMin() {
    PowerCalc powerCalc = new PowerCalc(-10);
    powerCalc.hoursOfWork(laptop, powerBank);
  }

  @Test()
  public void testLaptopCalculation() {
    PowerCalc powerCalc = new PowerCalc(50);
    double hours = powerCalc.hoursOfWork(laptop, powerBank);
    System.out.printf("Laptop will work: %.2f h.\n", hours);
    assertTrue(hours > 0);
  }

  @Test()
  public void testPhoneCalculation() {
    PowerCalc powerCalc = new PowerCalc(50);
    double hours = powerCalc.hoursOfWork(phone, powerBank2);
    System.out.printf("Phone will work: %.2f h.\n", hours);
    assertTrue(hours > 0);
  }

}
