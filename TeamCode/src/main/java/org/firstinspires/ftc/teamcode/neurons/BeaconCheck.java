package org.firstinspires.ftc.teamcode.neurons;

import org.firstinspires.ftc.teamcode.controllers.Team;
import org.firstinspires.ftc.teamcode.debug.Logger;
import org.lasarobotics.vision.ftc.resq.Beacon;

/**
 * Created by max on 11/3/16.
 */
public class BeaconCheck {
    private Beacon.BeaconAnalysis analysis;
    private Team team;
    public BeaconCheck(Team t) {
        team = t;
    }
    public void update(Beacon.BeaconAnalysis b) {
        Logger.logLine("Analysis Received");
        analysis = b;
    }

    public boolean shouldPressLeft() {
        return ((team == Team.BLUE && analysis.getStateLeft() == Beacon.BeaconColor.BLUE) ||
                (team == Team.RED && analysis.getStateLeft() == Beacon.BeaconColor.RED)) && analysis.getConfidence() > 0.75;
    }
    public boolean shouldPressRight() {
        return ((team == Team.BLUE && analysis.getStateRight() == Beacon.BeaconColor.BLUE) ||
                (team == Team.RED && analysis.getStateRight() == Beacon.BeaconColor.RED)) && analysis.getConfidence() > 0.75;
    }

    public boolean isPressed() {
        return analysis.getStateLeft() == analysis.getStateRight() || analysis.getConfidence() < 0.5;
    }
}
