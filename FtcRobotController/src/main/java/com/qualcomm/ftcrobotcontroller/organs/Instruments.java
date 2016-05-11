package com.qualcomm.ftcrobotcontroller.organs;

import com.qualcomm.ftcrobotcontroller.debug.Component;
import com.qualcomm.ftcrobotcontroller.statics.Hardware;
import com.qualcomm.ftcrobotcontroller.tissues.TColor;
import com.qualcomm.ftcrobotcontroller.tissues.TIMU;
import com.qualcomm.ftcrobotcontroller.tissues.TUltra;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by max on 4/18/16.
 *
 * Represents the sensor array on the robot. Acts as an asynchronous state container for sensor values to prevent over-polling.
 * Could also manipulate motor mounted sensors.
 */
public class Instruments extends Thread {
    private static final long NANOS_PER_MILLI = 1000000L;

    //Sensors
    private TColor colorsensor;
    private TUltra ultrasensor;
    private TIMU imusensor;

    //data
    private class Color {
        public double RED = -1;
        public double GREEN = -1;
        public double BLUE = -1;
        public double ALPHA = -1;
    }
    public final Color color = new Color();

    public double distance = -1;
    public double yaw = -1;

    private boolean gatherData;
    private long tickInterval;

    public Instruments() {
        /*colorsensor = new TColor(Hardware.Color);
        ultrasensor = new TUltra(Hardware.Ultra);*/
        imusensor = new TIMU(Hardware.IMU);

        gatherData = false;
        tickInterval = 100 * NANOS_PER_MILLI;
    }

    public void setTickInterval(int millis) {
        tickInterval = millis* NANOS_PER_MILLI;
    }


    @Override
    public void run() {
        gatherData = true;

        long initialTime = System.nanoTime();

        while(gatherData) {
            if(System.nanoTime() - initialTime > tickInterval) {
                tick();
                initialTime = System.nanoTime();
            }
        }
    }

    public void kill() {
        gatherData = false;
    }

    //TODO: make a cycle
    private void tick() {
        /*color.RED = colorsensor.red();
        color.BLUE = colorsensor.red();
        color.GREEN = colorsensor.red();
        color.ALPHA = colorsensor.alpha();

        distance = ultrasensor.distance();*/
        yaw = imusensor.getYaw();
    }
}