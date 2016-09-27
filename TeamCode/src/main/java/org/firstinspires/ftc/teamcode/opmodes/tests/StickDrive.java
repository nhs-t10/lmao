package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.teleop.OneStickDrive;
import org.firstinspires.ftc.teamcode.opmodes.T10Opmode;
import org.firstinspires.ftc.teamcode.organs.drivetrains.TreadDrivetrain;

/**
 * Created by Admin on 4/19/2016.
 */
@TeleOp(name="Stick Drive", group="Testers")
@Disabled
public class StickDrive extends T10Opmode {
    private OneStickDrive osd;

    public void run()  {
        TreadDrivetrain tdt = new TreadDrivetrain();
        osd = new OneStickDrive(tdt);
    }

    public void tick() {
        osd.tick();
    }
}