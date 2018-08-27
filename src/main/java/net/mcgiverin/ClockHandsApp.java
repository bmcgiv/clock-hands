package net.mcgiverin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClockHandsApp {

    public static void main(String[] args) throws ParseException {

        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        ClockHands clockHands = new ClockHands(format.parse(args[0]));
        System.out.printf("Angle of separation for %s is %.1f degrees", args[0], clockHands.getAngleOfSeparation());
    }
}
