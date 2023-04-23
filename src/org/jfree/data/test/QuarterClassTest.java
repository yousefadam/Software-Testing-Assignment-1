package org.jfree.data.test;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Year;
import org.junit.Test;

import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class QuarterClassTest {
    Quarter quarter;

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange() {
        quarter = new Quarter();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Constructor
    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor1() {
        arrange(2, 1800);
        assertEquals(1800, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor2() {
        arrange(5, 1999);
        assertEquals(1999, quarter.getYear().getYear());
        assertEquals(0, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor3() {
        Year year = new Year(2009);
        Quarter quarter = new Quarter(3,year);
        assertEquals(2009, quarter.getYear().getYear());
        assertEquals(3, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor4() {
        Date date = new Date();
        Quarter quarter = new Quarter(date);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtor5() {
        Date date = new Date();
        TimeZone zone = TimeZone.getDefault();
        Quarter quarter = new Quarter(date,zone);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //getQuarter

    @Test
    public void testGetQuarter() {
        arrange();
        assertEquals(2, quarter.getQuarter());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //getYear

    @Test
    public void testGetYear() {
        arrange();
        assertEquals(new Year(2023), quarter.getYear());
    }

    @Test
    public void testGetYear2() {
        arrange(4,9999);
        assertEquals(new Year(9999), quarter.getYear());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //previous

    @Test
    public void testPrevious() {
        arrange();
        assertEquals(new Quarter(1, 2023), quarter.previous());
    }

    @Test
    public void testPrevious2() {
        arrange(1, 1900);
        assertEquals(null, quarter.previous());
    }

    @Test
    public void testPrevious3() {
        arrange(1, 2005);
        assertEquals(new Quarter(4, 2004), quarter.previous());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //next

    @Test
    public void testNext() {
        arrange();
        assertEquals(new Quarter(3, 2023), quarter.next());
    }

    @Test
    public void testNext2() {
        arrange(4, 9999);
        assertEquals(null, quarter.next());
    }

    @Test
    public void testNext3() {
        arrange(4, 2005);
        assertEquals(new Quarter(1, 2006), quarter.next());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //getSerialIndex

    @Test
    public void testGetSerialIndex() {
        arrange(4, 2005);
        assertEquals(8024, quarter.getSerialIndex());
    }

}
