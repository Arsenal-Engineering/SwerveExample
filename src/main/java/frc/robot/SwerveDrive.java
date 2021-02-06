// Based on  https://jacobmisirian.gitbooks.io/frc-swerve-drive-programming/content/
// Jacob Misirian of FIRST Robotics Team 2506 of Franklin, WI.
package frc.robot;

public final class SwerveDrive {
    private double L_over_R;
    private double W_over_R;

    private WheelDrive bR;
    private WheelDrive bL;
    private WheelDrive fR;
    private WheelDrive fL;

    public SwerveDrive(WheelDrive bR, WheelDrive bL, WheelDrive fR, WheelDrive fL, double length, double width) {
        this.bR = bR;
        this.bL = bL;
        this.fR = fR;
        this.fL = fL;

        double r = Math.sqrt((length * length) + (width + width));
        L_over_R = length / r;
        W_over_R = width / r;
    }

    public void drive(double x1, double y1, double x2) {
        // If no joystick input, prevent from turning randomly and ensure motors are stopped
        if (Math.abs(x1) < 0.05 && Math.abs (y1) < 0.05 && Math.abs(x2) < 0.05) {
            bR.stop();
            bL.stop();
            fR.stop();
            fL.stop();
        }
        else {
            double a = x1 - x2 * L_over_R;
            double b = x1 + x2 * L_over_R;
            double c = y1 - x2 * W_over_R;
            double d = y1 + x2 * W_over_R;

            // Arg1 = Speed = Range of 0 to 1
            // Arg2 = Angle = Range of -1 to 1 (or multiply by 180 for angle)
            bR.drive(Math.sqrt((a * a) + (d * d)), Math.atan2(a, d) / Math.PI);
            bL.drive(Math.sqrt((a * a) + (c * c)), Math.atan2(a, c) / Math.PI);
            fR.drive(Math.sqrt((b * b) + (d * d)), Math.atan2(b, d) / Math.PI);
            fL.drive(Math.sqrt((b * b) + (c * c)), Math.atan2(b, c) / Math.PI);
        }
    }

    public void setPID(String wheel, double kP, double kI, double kD){
        if (wheel.equals("BR")) {
            bR.setPID(kP, kI, kD);
        }
        else if (wheel.equals("BL")) {
            bL.setPID(kP, kI, kD);
        }
        else if (wheel.equals("FR")) {
            fR.setPID(kP, kI, kD);
        }
        else if (wheel.equals("FL")) {
            fL.setPID(kP, kI, kD);
        }
        else {
            System.out.println("Unrecognized wheel: " + wheel);
        }
    }
}