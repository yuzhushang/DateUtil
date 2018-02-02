import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张东斌
 * @Date 2018/2/1 17:59
 */
public class DateUtil {
    /**
     * 返回输入日期所在周的周一日期
     *
     * @param date 格式：yyyy-MM-dd
     * @return
     */
    public static LocalDate getFirstDayOfWeek(String date){
        LocalDate inputDate = LocalDate.parse(date);
        TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()-DayOfWeek.MONDAY.getValue()));
        return inputDate.with(FIRST_OF_WEEK);
    }

    /**
     * 返回输入日期所在周的周末日期
     *
     * @param date 格式：yyyy-MM-dd
     * @return
     */
    public static LocalDate getLastDayOfWeek(String date){
        LocalDate inputDate = LocalDate.parse(date);
        TemporalAdjuster LAST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        return inputDate.with(LAST_OF_WEEK);
    }

    /**
     * 返回输入日期所在周的所有日期
     *
     * @param date 格式：yyyy-MM-dd
     * @return
     */
    public static List<LocalDate> getWeekDayList(String date){
        LocalDate lastWeekDay = getLastDayOfWeek(date);
        List<LocalDate> weekDayList = new ArrayList<>();
        for (int i = 6; i >= 1; i--) {
            weekDayList.add(lastWeekDay.minusDays(i));
        }
        weekDayList.add(lastWeekDay);
        return weekDayList;
    }

    public static List<String> getWeek(String beginDate, String endDate){
        LocalDate beginMonday = getFirstDayOfWeek(beginDate);
        System.out.println(beginMonday);
        LocalDate endSunDay = getLastDayOfWeek(endDate);
        List<String> dayList = new ArrayList<>();
        while (beginMonday.isBefore(endSunDay)){
            LocalDate beginMonday_ = beginMonday.plusDays(6);
            dayList.add(beginMonday.toString() + "|" + beginMonday_.toString());
            beginMonday = beginMonday_;
        }
        return dayList;
    }

    public static void main(String[] args) {
        System.out.println(getWeek("2017-01-10", "2017-01-20"));
//        System.out.println(getFirstDayOfWeek("2017-01-10"));
    }
}
