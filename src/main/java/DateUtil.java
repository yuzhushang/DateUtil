import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

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
        TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(localDate.getDayOfWeek().getValue()-DayOfWeek.MONDAY.getValue()));
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

    public static void main(String[] args) {
        System.out.println(getFirstDayOfWeek("2017-01-10"));
    }
}
