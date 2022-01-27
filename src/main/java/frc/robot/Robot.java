package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;  // For Xbox style controller
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private XboxController joystick = new XboxController(0);  // Controller on port zero

  // Set WheelDrive inputs:
  //   Name of the wheel for debugging
  //   CAN IDs for angle motor and drive motor (back/front, right/left)
  //   PID values (you will need to tune these for each wheel), kP, kI, kD
  //   Encoder ticks (Lamrpey is 1023)
  //   Debug (triggers console output)
  // was 1, .002 0, which was bad
  private WheelDrive bR = new WheelDrive("BR", 8, 4, 15, 0.00, 20, 1023, true);
  private WheelDrive bL = new WheelDrive("BL", 7, 3, 15, 0.00, 2000, 1023, true);
  private WheelDrive fR = new WheelDrive("FR", 6, 2, 15, 0.00, 20, 1023, true);
  private WheelDrive fL = new WheelDrive("FL", 5, 1, 15, 0.00, 2000, 1023, true);

  // Set SwerveDirve inputs: Length between your axles (front to back), Width between your axles (side to side)
  private SwerveDrive swerveDrive = new SwerveDrive(bR, bL, fR, fL, 27.0, 21.0);

  // PID tuning variables
  private String tuneWheel = "BR";
  private double tuneP = 15;
  private double tuneI = 0;
  private double tuneD = 20;

  @Override public void teleopInit() {
    SmartDashboard.putString("Wheel", "BR or BL or FR or FL");
    SmartDashboard.putNumber("P", tuneP);
    SmartDashboard.putNumber("I", tuneI);
    SmartDashboard.putNumber("D", tuneD);
  }

  @Override public void teleopPeriodic() {
    tuneWheel = SmartDashboard.getString("Wheel", tuneWheel);
    tuneP = SmartDashboard.getNumber("P", tuneP);
    tuneI = SmartDashboard.getNumber("I", tuneI);
    tuneD = SmartDashboard.getNumber("D", tuneD);
    swerveDrive.setPID(tuneWheel, tuneP, tuneI, tuneD);

    // Xbox controllers report up as negative and down as positive, so we will flip it here
    swerveDrive.drive(joystick.getLeftX(), joystick.getLeftY(), joystick.getRightX());
  }

  @Override public void robotInit() {}
  @Override public void robotPeriodic() {}
  @Override public void autonomousInit() {}
  @Override public void autonomousPeriodic() {}
  @Override public void testInit() {}
  @Override public void testPeriodic() {}
  @Override public void disabledInit() {}
  @Override public void disabledPeriodic() {}
}