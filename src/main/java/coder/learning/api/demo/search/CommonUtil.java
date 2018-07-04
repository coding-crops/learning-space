package coder.learning.api.demo.search;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/4.
 */
public class CommonUtil {
    public static PlanInfo planInfoCreater(ScheduleInfo last, ScheduleInfo next, Integer count) {
        PlanInfo planInfo = new PlanInfo();
        List<FlightInfo> xx = flightInfoCreater(last, next, count);
        xx.sort((a,b) -> b.getDepartureDate().compareTo(a.getDeparture()));
        planInfo.setFlightInfos(xx);
        return planInfo;
    }

    public static List<FlightInfo> flightInfoCreater(ScheduleInfo last, ScheduleInfo next, Integer count) {

        LocalDateTime firstDateTime = combineDateAndTime(last.getDepartureDate(), last.getDepartureTime());
        LocalDateTime lastDateTime = combineDateAndTime(next.getDepartureDate(), next.getDepartureTime());
        Duration devidedDuration = devideDuration(firstDateTime, lastDateTime, count);
        LocalDateTime dateTime1 = addDuration(firstDateTime, devidedDuration);
        String[] dateAndTime1 = getDateAndTime(dateTime1);

        List<FlightInfo> flightInfos = new ArrayList<>();
        String transferDeparture = "BKK";
        String transferArrival = "NRT";
        FlightInfo firstFlightInfo = new FlightInfo();
        firstFlightInfo.setArrival(transferDeparture);
        firstFlightInfo.setArrivalDate(dateAndTime1[0]);
        firstFlightInfo.setArrivalTime(dateAndTime1[1]);
        firstFlightInfo.setDeparture(last.getDepartureCity());
        firstFlightInfo.setDepartureDate(last.getDepartureDate());
        firstFlightInfo.setDepartureTime(last.getDepartureTime());



        LocalDateTime dateTime2 = addDuration(dateTime1, devidedDuration);
        String[] dateAndTime2 = getDateAndTime(dateTime2);

        LocalDateTime dateTime3 = addDuration(dateTime2, devidedDuration);
        String[] dateAndTime3 = getDateAndTime(dateTime3);

        FlightInfo transferFlightInfo = new FlightInfo();
        transferFlightInfo.setArrival(transferArrival);
        transferFlightInfo.setArrivalDate(dateAndTime2[0]);
        transferFlightInfo.setArrivalTime(dateAndTime2[1]);
        transferFlightInfo.setDeparture(transferDeparture);
        transferFlightInfo.setDepartureDate(dateAndTime1[0]);
        transferFlightInfo.setDepartureTime(dateAndTime1[1]);

        FlightInfo lastFlightInfo = new FlightInfo();
        lastFlightInfo.setArrival(next.getArrivalCity());
        lastFlightInfo.setArrivalDate(dateAndTime3[0]);
        lastFlightInfo.setArrivalTime(dateAndTime3[1]);
        lastFlightInfo.setDeparture(transferArrival);
        lastFlightInfo.setDepartureDate(dateAndTime2[0]);
        lastFlightInfo.setDepartureTime(dateAndTime2[1]);

        flightInfos.add(firstFlightInfo);
        flightInfos.add(transferFlightInfo);
        flightInfos.add(lastFlightInfo);

        return flightInfos;
    }

//    public static void main(String[] args) {
//        DateTimeFormatter dater = DateTimeFormatter.ofPattern("yyyyMMdd");
//        DateTimeFormatter timer = DateTimeFormatter.ofPattern("HHmm");
////        LocalDateTime time = LocalDateTime.now();
////
////        LocalDateTime time2 = LocalDateTime.now().plusDays(5).plusHours(6);
//        LocalTime t = LocalTime.parse("2040",timer);
//        LocalDate date = LocalDate.parse("20180704", dater);
//        LocalDateTime time = LocalDateTime.of(date, t);
//        LocalDateTime time2 = LocalDateTime.now().plusDays(5).plusHours(6);
//
//        Duration duration = Duration.between(time, time2);
//
//        Duration halfDuration = duration.dividedBy(2);
//
//        LocalDateTime time3 = time.plus(halfDuration);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
//
//        System.out.println(time3.format(formatter));
//
//        System.out.println(time3.format(formatter).split("\\ ")[0]);
//    }

    public static LocalDateTime combineDateAndTime(String date, String time) {

        DateTimeFormatter dater = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timer = DateTimeFormatter.ofPattern("HHmm");
        LocalTime t = LocalTime.parse(time,timer);
        LocalDate d = LocalDate.parse(date, dater);
        return LocalDateTime.of(d, t);
    }
    public static Duration devideDuration(LocalDateTime oldTime, LocalDateTime newTime, Integer count) {
        Duration duration = Duration.between(oldTime, newTime);
        Duration halfDuration = duration.dividedBy(count);
        return halfDuration;
    }

    public static LocalDateTime addDuration(LocalDateTime oldTime, Duration duration) {
        return oldTime.plus(duration);
    }

    public static String[] getDateAndTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        return time.format(formatter).split("\\ ");
    }
}
