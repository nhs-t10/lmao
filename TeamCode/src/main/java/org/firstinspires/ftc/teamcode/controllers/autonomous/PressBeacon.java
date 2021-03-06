package org.firstinspires.ftc.teamcode.controllers.autonomous;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.controllers.Team;
import org.firstinspires.ftc.teamcode.debug.Logger;
import org.firstinspires.ftc.teamcode.neurons.AngleTurning;
import org.firstinspires.ftc.teamcode.neurons.BeaconCheck;
import org.firstinspires.ftc.teamcode.organs.Instruments;
import org.firstinspires.ftc.teamcode.organs.Pusher;
import org.firstinspires.ftc.teamcode.organs.Spacers;
import org.firstinspires.ftc.teamcode.organs.drivetrains.DriveTrain;
import org.firstinspires.ftc.teamcode.tissues.TCamera;

import java.util.ArrayList;

/**
 * Created by max on 11/3/16.
 *
 * Drives up to the beacon and presses it.
 */
public class PressBeacon implements Controller {
    private DriveTrain driveTrain;
    private Instruments instruments;
    private TCamera camera;
    private Pusher pusher;
    private BeaconCheck beacon;
    private Spacers spacers;
    private AngleTurning angleTurning;
    private boolean Speed = false;
    private Team t;

    // STATE:
    private boolean detectedBeaconStatus = false;
    private boolean isPressingLeft = false;

    private int frames = 0;
    private int isLeft = 0;

    private double pastDistance;

    public PressBeacon(Team t, Instruments i, DriveTrain d, Pusher p, TCamera c, Spacers s) {
        instruments = i;
        driveTrain = d;
        pusher = p;
        camera = c;
        spacers = s;
        beacon = new BeaconCheck(t);
        this.t = t;
        angleTurning = new AngleTurning((t == Team.RED ? 90 : -90));
    }
    //        sw = new Time.Stopwatch();
    private double updateRolling() {
        if (beacon.shouldPressLeft()) isLeft++;
        if (beacon.shouldPressLeft() || beacon.shouldPressRight()) frames++;
        Logger.logLine("Rolling left probability: " + (double) isLeft / frames);
        return (double) isLeft/frames;
    }
    public boolean tick() {
        beacon.update(camera.getAnalysis());
        Logger.logLine(camera.getString());

        if (!detectedBeaconStatus && instruments.IRdistance >= 1.3) {
            double leftProb = updateRolling();
            driveTrain.stop();
            if (frames >= 15) {
                isPressingLeft = (leftProb >= 0.5d);
                detectedBeaconStatus = true;
            }
            Speed = true;
            return false;
        }
//        if (detectedBeaconStatus && instruments.IRdistance >= (t == Team.RED ? 2.1 : 2.1)) {
        if (spacers.isTouchingRight() && spacers.isTouchingLeft()){
            driveTrain.stop();
            if (isPressingLeft) pusher.pushLeft();
            else pusher.pushRight();
            return true;
        }
        ArrayList<Float> powers = angleTurning.getDrivePowers(instruments.yaw, (Speed ? -0.1f : -0.13f), true);
        driveTrain.drive(powers.get(0), powers.get(1));
        pastDistance = instruments.IRdistance;
        return false;
    }
}
