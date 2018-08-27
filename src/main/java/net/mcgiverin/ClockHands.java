package net.mcgiverin;


import java.util.Calendar;
import java.util.Date;

/**
 * Constructed using a Date or Calendar object that indicates a time of day, removing all of the
 * headache around parsing time expressions to those purpose-built classes.
 * <p>
 * Able to then compute the angle of separation between the hour and minute hands on a clock face.
 * It is assumed that the all hands "sweep" continuously across the face, as opposed to some fixed
 * interval.  For example, the minute hand should be halfway between two hashes when the second hand reaches 30.
 */
public class ClockHands {

    private static final float DEGREES = 360f;

    private final int hour;
    private final int minute;
    private final int second;

    private ClockHands(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Creates a new instance given the time specified within the date parameter.
     * @param date used to extract the time for the local calendar
     */
    public ClockHands(Date date) {
        this(calendarFromDate(date));
    }

    public ClockHands(Calendar calendar) {
        // insures the hour is always between 0 and 11, inclusive
        this(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }

    private static Calendar calendarFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public float getAngleOfSeparation() {

        // determine angle of separation
        float rawAngle = Math.abs(getHourAngle() - getMinuteAngle());

        // adjust to always be the smallest angle of separation
        if (rawAngle > DEGREES / 2) {
            rawAngle = DEGREES - rawAngle;
        }
        return rawAngle;
    }

    private float getHourAngle() {
        // include minutes and seconds to account for additional movement of hour hand
        return (hour + (minute / 60f) + (second / (60f * 60f))) / 12f * DEGREES;
    }

    private float getMinuteAngle() {
        // include seconds to account for additional movement of minute hand
        return (minute + (second / 60f)) / 60f * DEGREES;
    }

}
