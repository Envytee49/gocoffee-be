package com.example.gocoffee.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@UtilityClass
public class DateUtil {
    public final int DAY_TIME_IN_SECONDS = 86400;
    public final int HALF_DAY_TIME_IN_SECONDS = 43200;

    public String getTimeOfDay(int timeInSeconds) {
        int timeInSecondsInADay = getTimeInSecondsOfDay(timeInSeconds);
        LocalTime localTime = LocalTime.ofSecondOfDay(getHalfTimeInSecondsOfDay(timeInSecondsInADay));
        String minutes = String.format("%02d", localTime.getMinute());
        String hours = String.format("%02d", localTime.getHour() == 0 ? 12 : localTime.getHour());
        return hours+":"+minutes +" "+getTimePeriodOfDay(timeInSecondsInADay);
    }
    public String getTimePeriodOfDay(int timeInSeconds) {
        int timeInSecondsInADay = getTimeInSecondsOfDay(timeInSeconds);
        return timeInSecondsInADay >= HALF_DAY_TIME_IN_SECONDS ? "PM" : "AM";
    }

    public int getTimeInSecondsOfDay(int timeInSeconds) {
        return timeInSeconds >= DAY_TIME_IN_SECONDS ? timeInSeconds - DAY_TIME_IN_SECONDS : timeInSeconds;
    }
    public int getHalfTimeInSecondsOfDay(int timeInSeconds) {
        return timeInSeconds > HALF_DAY_TIME_IN_SECONDS ? timeInSeconds - HALF_DAY_TIME_IN_SECONDS : timeInSeconds;
    }
    public boolean isTimeNowInTimeRange(int startInSeconds, int endInSeconds) {
        int nowInSeconds = LocalTime.now().toSecondOfDay();
        return nowInSeconds >= startInSeconds && nowInSeconds <= endInSeconds;
    }

}
