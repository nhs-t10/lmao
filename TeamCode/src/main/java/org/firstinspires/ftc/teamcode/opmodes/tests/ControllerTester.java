package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.controllers.teleop.DriveRight;
import org.firstinspires.ftc.teamcode.opmodes.T10Opmode;
import org.firstinspires.ftc.teamcode.organs.Instruments;
import org.firstinspires.ftc.teamcode.organs.drivetrains.MecanumDrivetrain;

import java.util.ArrayList;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
/**
 * Created by Admin on 4/19/2016.
 *
 * Tests tick-based controllers.
 */
@TeleOp(name="Controller Tester", group="Testers")
public class ControllerTester extends T10Opmode {
    private ArrayList<Controller> tests = new ArrayList<Controller>();

    public void run()  {
        MecanumDrivetrain driveTrain = new MecanumDrivetrain();
        Instruments instruments = new Instruments();
        instruments.start();
        tests.add(new DriveRight(instruments, driveTrain));

    }

    public void tick() {
        for(int i=0; i<tests.size(); i++) {
            tests.get(i).tick();
        }
    }
}