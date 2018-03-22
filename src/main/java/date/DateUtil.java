package date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 张东斌
 * @Date 2018/2/1 17:59
 *
 * 日期工具类
 */
public class DateUtil {
    /**
     * 返回输入日期所在周的周一日期
     *
     * @param date 格式：yyyy-MM-dd, not null
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
     * @param date 格式：yyyy-MM-dd, not null
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

    /**
     * 返回输入日期的之间的自然周日期
     *
     * @param beginDate 开始时间，格式：yyyy-MM-dd
     * @param endDate 结束时间，格式：yyyy-MM-dd
     * @return ["weekBegin|weekEnd"]
     */
    public static List<String> getWeekList(String beginDate, String endDate){
        LocalDate beginMonday = getFirstDayOfWeek(beginDate);
        LocalDate endSunDay = getLastDayOfWeek(endDate);
        List<String> dayList = new ArrayList<>();
        while (beginMonday.isBefore(endSunDay)){
            LocalDate sunday_ = beginMonday.plusDays(6);
            dayList.add(beginMonday.toString() + "|" + sunday_.toString());
            beginMonday = beginMonday.plusDays(7);
        }
        return dayList;
    }

    /**
     * 计算beginDate 与 endDate之间相差自然周数量
     *
     * @param beginDate 开始时间，格式：yyyy-MM-dd
     * @param endDate 结束时间，格式：yyyy-MM-dd，not null
     * @return 输入时间相差的自然周数量，not null
     */
    public static long betweenWeekNum(String beginDate, String endDate){
//        Objects.requireNonNull(beginDate);
//        Objects.requireNonNull(endDate);
        LocalDate beginMonday = getFirstDayOfWeek(beginDate);
        LocalDate endMonday = getFirstDayOfWeek(endDate);
        return ChronoUnit.WEEKS.between(beginMonday, endMonday);
    }
}
