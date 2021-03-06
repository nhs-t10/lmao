package org.firstinspires.ftc.teamcode.controllers.autonomous;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.neurons.AngleTurning;
import org.firstinspires.ftc.teamcode.organs.Instruments;
import org.firstinspires.ftc.teamcode.organs.drivetrains.DriveTrain;

import java.util.ArrayList;

/**
 * Created by jacob_000 on 11/15/2016.
 */

public class TurnXDegrees implements Controller {
    private DriveTrain driveTrain;
    private Instruments instruments;
    private AngleTurning angleTurning;
    public TurnXDegrees (Instruments i, DriveTrain d, int addend) {
        instruments = i;
        driveTrain = d;
        angleTurning = new AngleTurning(Modulo(i.yaw + addend));
    }
    public double Modulo (double angle){
        if (180 < angle){
            return 360 - angle;
        }
        else if (-180 > angle){
            return 360 + angle;
        }
        return angle;
    }
    public boolean tick (){
        ArrayList<Float> values = angleTurning.getPivotPowers(instruments.yaw);
        if (values.get(0) == 0) return true;
        driveTrain.drive(values.get(0), values.get(1));
        return false;
    }
}
