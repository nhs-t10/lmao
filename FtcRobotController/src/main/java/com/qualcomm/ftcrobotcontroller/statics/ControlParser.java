package com.qualcomm.ftcrobotcontroller.statics;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by max on 4/17/16.
 */

/*
* ControlParser.range(String controlString) for float-y controls
* ControlParser.button(String controlString) for bool-y controls
* */
public class ControlParser {
    private static Gamepad gamepad1;
    private static Gamepad gamepad2;
    private static String shift;

    public static void init(Gamepad g1, Gamepad g2, String s) {
        gamepad1 = g1;
        gamepad2 = g2;
        shift = s;
    }
    private static ArrayList<Boolean> getButtonResult(Gamepad gamepad, String control) {
        ArrayList<Boolean> results = new ArrayList<Boolean>();
        if(control.equals("A")) {
            results.add(gamepad.a);
        }
        else if(control.equals("B")) {
            results.add(gamepad.b);
        }
        else if(control.equals("X")) {
            results.add(gamepad.x);
        }
        else if(control.equals("Y")) {
            results.add(gamepad.y);
        }
        else if(control.equals("DD")) {
            results.add(gamepad.dpad_down);
        }
        else if(control.equals("DL")) {
            results.add(gamepad.dpad_left);
        }
        else if(control.equals("DR")) {
            results.add(gamepad.dpad_right);
        }
        else if(control.equals("DU")) {
            results.add(gamepad.dpad_up);
        }
        else if(control.equals("LB")) {
            results.add(gamepad.left_bumper);
        }
        else if(control.equals("RB")) {
            results.add(gamepad.right_bumper);
        }
        else if(control.equals("RSB")) {
            results.add(gamepad.right_stick_button);
        }
        else if(control.equals("LSB")) {
            results.add(gamepad.left_stick_button);
        }
        else throw new Error("No valid control specified.");

        return results;
    }
    private static ArrayList<Float> getRangeResult(Gamepad gamepad, String control) {
        ArrayList<Float> results = new ArrayList<Float>();

        if(control.equals("LT")) {
            results.add(gamepad.left_trigger);
        }
        else if(control.equals("RT")) {
            results.add(gamepad.right_trigger);
        }
        else if(control.equals("RS")) {
            results.add(gamepad.right_stick_x);
            results.add(gamepad.right_stick_y);
        }
        else if(control.equals("LS")) {
            results.add(gamepad.left_stick_x);
            results.add(gamepad.left_stick_y);
        }
        else throw new Error("No valid control specified.");

        return results;
    }

    public static ArrayList<Float> range(String controlString) { //"LS1"
        ArrayList<Float> results = new ArrayList<Float>();
        List<String> query = Arrays.asList(controlString.split("")); //["L", "S", "1"]
        String gnum = query.get(query.size() - 1); //"1"
        query.remove(gnum); //["L", "S"]

        Gamepad gamepad = (gnum == "1" ? gamepad1 : gamepad2);
        Boolean shiftCheck = (query.get(0) == "^");
        if(shiftCheck) query.remove(query.get(0)); //["L", "S"]

        String control = "";
        for(int i=0; i<query.size(); i++) { //"LS"
            control += query.get(i);
        }

        if(shiftCheck) results.addAll(getRangeResult(gamepad, shift)); //[true]
        results.addAll(getRangeResult(gamepad, control)); //[true, true]

        return results;
    }

    public static ArrayList<Boolean> button(String controlString) { //"^A1"
        ArrayList<Boolean> results = new ArrayList<Boolean>();
        List<String> query = Arrays.asList(controlString.split("")); //["^", "A", "1"]
        String gnum = query.get(query.size() - 1); //"1"
        query.remove(gnum); //["^", "A"]

        Gamepad gamepad = (gnum == "1" ? gamepad1 : gamepad2);
        Boolean shiftCheck = (query.get(0) == "^");
        if(shiftCheck) query.remove(query.get(0)); //["A"]

        String control = "";
        for(int i=0; i<query.size(); i++) { //"A"
            control += query.get(i);
        }

        results.addAll(getButtonResult(gamepad, control)); //[true, true]

        return results;
    }
}
