package org.firstinspires.ftc.teamcode.organs.drivetrains;

import org.firstinspires.ftc.teamcode.debug.Component;
import org.firstinspires.ftc.teamcode.lib.Sleep;

/**
 * Created by Admin on 4/17/2016.
 */
public abstract class DriveTrain implements Component {
    public String getName(){return "Drive Train";}
    public abstract void doDrive(float left, float right);
    public void drive(float left, float right) {
        doDrive(left, right);
    }

    public void goForward(float pwr) {
        drive(pwr, pwr);
    }
    public void goBackward(float pwr) {
        drive(-pwr, -pwr);
    }

    /**
     * Turns robot based on power value between -1.0 and 1.0. Negative turns left, positive right.
     * @param pwr power used for turn
     */
    public void turn(float pwr) {
        drive(pwr, -pwr);
    }

    public void stop() {
        drive(0.0f, 0.0f);
    }
    public boolean test() {
        this.goForward(0.5f);
        Sleep.secs(2);
        this.stop();
        this.turn(-0.5f);
        Sleep.secs(2);
        this.stop();
        return true;
    }
}
