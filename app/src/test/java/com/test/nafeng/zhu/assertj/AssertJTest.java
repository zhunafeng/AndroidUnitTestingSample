package com.test.nafeng.zhu.assertj;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.DateUtil.*;
import static org.assertj.core.util.Lists.newArrayList;

/**
 * @Description: AssertJ case
 * @Author: Nafeng zhu
 * @Time: 2018/5/15 0015 09:33.
 */
public class AssertJTest {

    @Test
    public void testString(){
        String str = null;

        assertThat(str).isNullOrEmpty();

        assertThat("").isEmpty();

        assertThat("nafeng").isEqualTo("nafeng").isEqualToIgnoringCase("Nafeng");

        assertThat("nafeng").startsWith("n").endsWith("eng").hasSize(6);

        assertThat("nafeng").contains("en").doesNotContain("zhu");

        assertThat("nafeng").containsOnlyOnce("fe");

        assertThat("nafeng").matches("..a.e").doesNotMatch(".*g");

    }

    @Test
    public void testNumber() {
        Integer num = null;

        assertThat(num).isNull();

        assertThat(42).isEqualTo(42);

        assertThat(42).isGreaterThan(38).isGreaterThanOrEqualTo(38);

        assertThat(42).isLessThan(58).isLessThanOrEqualTo(58);

        assertThat(0).isZero();

        assertThat(1).isPositive().isNotNegative();

        assertThat(-1).isNegative().isNotPositive();
    }

    @Test
    public void testDate() {

        assertThat(parse("2018-05-15"))
                .isEqualTo("2018-05-15")
                .isNotEqualTo("2018-05-14")
                .isAfter("2018-04-01")
                .isBefore("2029-03-01");

        assertThat(now()).isBeforeYear(2030).isAfterYear(2017);

        assertThat(parse("2018-05-15"))
                .isBetween("2018-04-01", "2018-06-01")
                .isNotBetween("2019-01-01", "2019-12-31");


        Date d1 = new Date();
        Date d2 = new Date(d1.getTime() + 100);
        assertThat(d1).isCloseTo(d2, 100);

        Date date1 = parseDatetimeWithMs("2018-01-01T01:00:00.000");
        Date date2 = parseDatetimeWithMs("2018-01-01T01:00:00.555");
        Date date3 = parseDatetimeWithMs("2018-01-01T01:00:55.555");
        Date date4 = parseDatetimeWithMs("2018-01-01T01:55:55.555");
        Date date5 = parseDatetimeWithMs("2018-01-01T05:55:55.555");


        assertThat(date1).isEqualToIgnoringMillis(date2);

        assertThat(date1).isInSameSecondAs(date2);

        assertThat(date1).isEqualToIgnoringSeconds(date3);

        assertThat(date1).isInSameMinuteAs(date3);

        assertThat(date1).isEqualToIgnoringMinutes(date4);

        assertThat(date1).isInSameHourAs(date4);

        assertThat(date1).isEqualToIgnoringHours(date5);

        assertThat(date1).isInSameDayAs(date5);
    }

    @Test
    public void testList() {

        assertThat(newArrayList()).isEmpty();

        assertThat(newArrayList(1, 2, 3)).startsWith(1).endsWith(3);

        assertThat(newArrayList(1, 2, 3))
                .contains(1, atIndex(0))
                .contains(2, atIndex(1))
                .contains(3)
                .isSorted();

        assertThat(newArrayList(3, 1, 2)).isSubsetOf(newArrayList(1, 2, 3, 4));

        assertThat(Lists.newArrayList("a", "b", "c")).containsOnlyOnce("a");
    }

    @Test
    public void testMap() {
        Map<String, Integer> foo = Maps.newHashMap("A", 1);
        foo.put("B", 2);
        foo.put("C", 3);


        assertThat(foo).isNotEmpty().hasSize(3);

        assertThat(foo).contains(entry("A", 1), entry("B", 2));

        assertThat(foo).containsKeys("A", "B", "C");

        assertThat(foo).containsValue(3);
    }
    
}
