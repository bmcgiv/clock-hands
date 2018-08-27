package net.mcgiverin;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ClockHandsTest {

    private String time;
    private float expectedAngle;

    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public ClockHandsTest(String time, float expectedAngle) {
        this.time = time;
        this.expectedAngle = expectedAngle;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "9:00", 90.0f },
                { "12:00", 0.0f },
                { "6:00", 180.0f },
                { "3:15", 7.5f },
                { "6:30", 15.0f },
                { "11:59", 5.5f },
                { "1:59", 65.5f },
                { "5:59", 174.5f },
                { "6:01", 174.5f },
                { "6:02", 169.0f },
                { "06:03", 163.5f },
                { "11:03", 46.5f },
                { "7:45", 37.5f },
                { "10:10", 115.0f },
                { "2:50", 145.0f },
                { "12:34", 173.0f },
                { "17:58", 169.0f },
                { "17:58:30", 171.75f },
                { "17:59", 174.5f },
                { "17:59:30", 177.25f },
                { "17:59:59", 179.91f }
        });
    }

    @Test
    public void test() throws ParseException
    {
        // given
        if (time.split(":").length == 2) {
            time += ":00";
        }
        ClockHands clockHands = new ClockHands(TIME_FORMAT.parse(time));

        // when
        float angle = clockHands.getAngleOfSeparation();

        // then
        assertEquals(expectedAngle, angle, .01f);
    }


}
