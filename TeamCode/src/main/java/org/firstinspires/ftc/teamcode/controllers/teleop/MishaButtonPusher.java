package org.firstinspires.ftc.teamcode.controllers.teleop;

import org.firstinspires.ftc.teamcode.controllers.Controller;
import org.firstinspires.ftc.teamcode.statics.ControlParser;
import org.firstinspires.ftc.teamcode.statics.Hardware;
import org.firstinspires.ftc.teamcode.tissues.TServo;

/**
 * Created by nhs on 9/29/16.
 */
public class MishaButtonPusher implements Controller {
    private TServo mishapusher = new TServo(Hardware.ServoTop);
    @Override
    public boolean tick() {
        boolean aPressed = ControlParser.button("A1");
        boolean bPressed = ControlParser.button("B1");
        if (aPressed) {
            mishapusher.moveTo(90.0);
        } else if (bPressed){
            mishapusher.moveTo(-90.0);
        } else {
            mishapusher.moveTo(0.0);
        }
    return false;
    }
}