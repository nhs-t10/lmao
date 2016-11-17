package org.firstinspires.ftc.teamcode.opmodes.clyde;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.controllers.Team;
import org.firstinspires.ftc.teamcode.controllers.autonomous.DriveFromWall;
import org.firstinspires.ftc.teamcode.controllers.autonomous.DriveToLine;
import org.firstinspires.ftc.teamcode.controllers.autonomous.FlickOnce;
import org.firstinspires.ftc.teamcode.controllers.autonomous.PressBeacon;
import org.firstinspires.ftc.teamcode.controllers.autonomous.TurnX;
import org.firstinspires.ftc.teamcode.controllers.tests.Stall;
import org.firstinspires.ftc.teamcode.opmodes.T10Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.organs.Instruments;
import org.firstinspires.ftc.teamcode.organs.Pusher;
import org.firstinspires.ftc.teamcode.organs.Spinner;
import org.firstinspires.ftc.teamcode.organs.drivetrains.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.tissues.TCamera;

/**
 * Created by nhs on 11/15/16.
 */
public abstract class BeaconAuto extends T10Autonomous {
    private TCamera camera;
    public Team team;
    @Override
    public void registration() {
        setTeam();
        camera = new TCamera();
        MecanumDrivetrain driveTrain = new MecanumDrivetrain();
        Instruments instruments = new Instruments();
        instruments.start();
        Pusher pusher = new Pusher();
        registerController(new DriveFromWall(instruments, driveTrain, 0.1));
        registerController(new TurnX(instruments, driveTrain, (team == Team.RED ? -156 : 175)));
        registerController(new FlickOnce());
        registerController(new Controller() {
            @Override
            public boolean tick() {
                new Spinner().toggle(1);
                return true;
            }
        });
        registerController(new Stall(3000));
        registerController(new FlickOnce());
        registerController(new DriveToLine(instruments, driveTrain, team));
        registerController(new TurnX(instruments, driveTrain, (team == Team.RED ? 180 : 90)));
        registerController(new PressBeacon(team, driveTrain, pusher, camera));
    }
    public abstract void setTeam();
    @Override
    public void stop() {
        camera.stop();
    }
}
