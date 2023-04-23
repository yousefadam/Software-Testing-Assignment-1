package org.jfree.data.test.org.jfree.data.test;

import org.jfree.data.time.Quarter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuarterClassTest {
    Quarter quarter;

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange() {
        quarter = new Quarter();
    }

    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor1() {
        Quarter quarter = new Quarter(2, 1800);
        assertEquals(1800, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }


}
