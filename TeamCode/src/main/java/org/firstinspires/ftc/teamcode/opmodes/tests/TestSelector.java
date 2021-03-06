package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.teleop.TestSelect;
import org.firstinspires.ftc.teamcode.debug.Component;
import org.firstinspires.ftc.teamcode.opmodes.T10Opmode;
import org.firstinspires.ftc.teamcode.organs.drivetrains.QuadDrivetrain;

import java.util.ArrayList;

/**
 * Created by max on 5/5/16.
 * Tests organs/tissues sequentially and indicates their success
 */
@TeleOp(name="Test Selector", group="Testers")
@Disabled
public class TestSelector extends T10Opmode {
    private TestSelect testSelect;
    public void run() {
        ArrayList<Component> tests = new ArrayList<Component>();
        tests.add(new QuadDrivetrain());
        testSelect = new TestSelect(tests);
    }
    public void tick() {
        testSelect.tick();
    }
}
