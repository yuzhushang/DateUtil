package date;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author 张东斌
 * @Date 2018/3/22 18:13
 */
public class DateUtilsTest {
    @Test
    public void getFirstDayOfWeekTest(){
        Assert.assertEquals(DateUtils.getFirstDayOfWeek("2018-01-03").toString(), "2018-01-01");
    }

    @Test
    public void getLastDayOfWeekTest(){
        Assert.assertEquals(DateUtils.getLastDayOfWeek("2018-01-03").toString(), "2018-01-07");
    }

    @Test
    public void getWeekDayListTest(){
        Assert.assertArrayEquals(DateUtils.getWeekDayList("2018-01-03").stream().map(d -> d.toString()).toArray(String[]::new), new String[]{"2018-01-01","2018-01-02","2018-01-03","2018-01-04","2018-01-05","2018-01-06","2018-01-07"});
    }

    @Test
    public void getWeekListTest() {
        Assert.assertArrayEquals(DateUtils.getWeekList("2018-01-03", "2018-01-08").stream().toArray(String[]::new), new String[]{"2018-01-01|2018-01-07", "2018-01-08|2018-01-14"});
    }

    @Test
    public void betweenWeekNumTest() {
        Assert.assertEquals(DateUtils.betweenWeekNum("2018-01-07", "2018-01-08"), 1);
    }

    @Test
    public void test(){
        String s1 = s(null);
    }

    /**
     * @param s
     * @return
     */
    public String s(@Nullable String s){
        return "";
    }
}
