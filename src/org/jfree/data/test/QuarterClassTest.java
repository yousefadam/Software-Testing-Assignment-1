package org.jfree.data.test;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Year;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

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

    @Test
    public void testGetSerialIndex2() {
        arrange();
        Quarter quarter2 = new Quarter(4, 2019);

        Long serial1 = quarter.getSerialIndex();
        Long serial2 = quarter2.getSerialIndex();

        assertNotEquals(serial1, serial2);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //equals

    @Test
    public void testEquals() {
        arrange();
        Quarter quarter2 = new Quarter(2,2023);
        assertTrue(quarter.equals(quarter2));
    }

    @Test
    public void testEquals2() {
        arrange();
        Quarter quarter2 = new Quarter(4,2021);
        assertFalse(quarter.equals(quarter2));
    }

    @Test
    public void testEquals3() {
        arrange();
        Quarter quarter2 = new Quarter(4,2023);
        assertFalse(quarter.equals(quarter2));
    }

    @Test
    public void testEquals4() {
        arrange();
        Quarter quarter2 = new Quarter(2,2020);
        assertFalse(quarter.equals(quarter2));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //hashCode

    @Test
    public void testHashCode() {
        arrange();
        assertNotNull(quarter.hashCode());
    }

    @Test
    public void testHashCode2() {
        arrange();
        Quarter quarter2 = new Quarter(2,2020);
        assertFalse(quarter.hashCode() == quarter2.hashCode());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //compareTo

    // -/+ the differences in the year first, then the quarters

    @Test
    public void testCompareTo() {
        arrange();
        //the same year
        Quarter quarter2 = new Quarter(2,2023);
        assertEquals(0, quarter.compareTo(quarter2));
    }

    @Test
    public void testCompareTo2() {
        arrange();
        //a qurater after
        Quarter quarter2 = new Quarter(3,2023);
        assertEquals(-1, quarter.compareTo(quarter2));
    }

    @Test
    public void testCompareTo3() {
        arrange();
        //a year after
        Quarter quarter2 = new Quarter(2,2024);
        assertEquals(-1, quarter.compareTo(quarter2));
    }

    @Test
    public void testCompareTo4() {
        arrange();
        //14 years ago
        Quarter quarter2 = new Quarter(2,2009);
        assertEquals(14, quarter.compareTo(quarter2));
    }

    @Test
    public void testCompareTo5() {
        arrange();
        //a quarter ago
        Quarter quarter2 = new Quarter(1,2023);
        assertEquals(1, quarter.compareTo(quarter2));
    }

    @Test
    public void testCompareTo6() {
        arrange();
        //3 years and 2 quarters ahead
        Quarter quarter2 = new Quarter(4,2026);
        assertEquals(-3, quarter.compareTo(quarter2));
    }

    @Test
    public void testCompareTo7() {
        arrange();
        Quarter quarter2 = new Quarter(1,2009);
        assertEquals(14, quarter.compareTo(quarter2));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //toString

    @Test
    public void testToString() {
        arrange();

        // Current quarter, current year
        Quarter quarter2 = new Quarter();
        assertEquals("Q2/2023", quarter2.toString());

        // prev quarter, current year
        quarter2 = new Quarter(1, 2023);
        assertEquals("Q1/2023", quarter2.toString());

        // Next quarter, current year
        quarter2 = new Quarter(3,2023);
        assertEquals("Q3/2023", quarter2.toString());

        // Past
        quarter2 = new Quarter(4,1994);
        assertEquals("Q4/1994", quarter2.toString());

        // Upcoming
        quarter2 = new Quarter(2,2025);
        assertEquals("Q2/2025", quarter2.toString());
    }

    /*@Test
    public void testToString2() {
        arrange();
        // prev quarter, current year
        Quarter quarter2 = new Quarter(1, 2023);
        assertEquals("Q1/2023", quarter2.toString());
    }
    
    @Test
    public void testToString3() {
        arrange();
        // Next quarter, current year
        Quarter quarter2 = new Quarter(3,2023);
        assertEquals("Q3/2023", quarter2.toString());
    }

    @Test
    public void testToString4() {
        arrange();
        // Past
        Quarter quarter2 = new Quarter(4,1994);
        assertEquals("Q4/1994", quarter2.toString());
    }

    @Test
    public void testToString5() {
        arrange();
        // Upcoming
        Quarter quarter2 = new Quarter(2,2025);
        assertEquals("Q2/2025", quarter2.toString());
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //getFirstMillisecond

    @Test public void testGetFirstMillisecond() {
        arrange();

        // Current quarter
        Quarter quarter2 = new Quarter();
        assertEquals(1680300000000L, quarter2.getFirstMillisecond());
        assertEquals(1680332400000L, quarter2.getFirstMillisecond(TimeZone.getTimeZone("America/Los_Angeles")));
        assertEquals(1680300000000L, quarter2.getFirstMillisecond(TimeZone.getTimeZone("Africa/Cairo")));

        // Different Quarters, same time zone
        assertNotEquals(
            quarter2.getFirstMillisecond(TimeZone.getTimeZone("Africa/Cairo")), 
            new Quarter(1,2023).getFirstMillisecond(TimeZone.getTimeZone("Africa/Cairo"))
            );

        // Next quarter
        quarter2 = new Quarter(3,2023);
        assertEquals(1688162400000L, quarter2.getFirstMillisecond());

        // Prev Quarter
        quarter2 = new Quarter(1,2023);
        assertEquals(1672524000000L, quarter2.getFirstMillisecond());

        // Upcoming
        quarter2 = new Quarter(4,2029);
        assertEquals(1885500000000L, quarter2.getFirstMillisecond());

        // Past
        quarter2 = new Quarter(2,2000);
        assertEquals(954540000000L, quarter2.getFirstMillisecond());

    }

    /*@Test public void testgetFirstMillisecond2() {
        arrange();
        Quarter quarter2 = new Quarter(3,2023);
        assertEquals(1688162400000L, quarter2.getFirstMillisecond());
    }

    @Test public void testgetFirstMillisecond3() {
        arrange();
        Quarter quarter2 = new Quarter(1,2023);
        assertEquals(1672524000000L, quarter2.getFirstMillisecond());
    }

    @Test public void testgetFirstMillisecond4() {
        arrange();
        Quarter quarter2 = new Quarter(4,2029);
        assertEquals(1885500000000L, quarter2.getFirstMillisecond());
    }

    @Test public void testgetFirstMillisecond5() {
        arrange();
        Quarter quarter2 = new Quarter(2,2000);
        assertEquals(954540000000L, quarter2.getFirstMillisecond());
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //getLastMillisecond

    @Test public void testGetLastMillisecond() {
        arrange();

        // Current quarter
        Quarter quarter2 = new Quarter();
        assertEquals(1688162399999L, quarter2.getLastMillisecond());
        assertEquals(1688194799999L, quarter2.getLastMillisecond(TimeZone.getTimeZone("America/Los_Angeles")));
        assertEquals(1688162399999L, quarter2.getLastMillisecond(TimeZone.getTimeZone("Africa/Cairo")));

        // Different Quarters, same time zone
        assertNotEquals(
            quarter2.getLastMillisecond(TimeZone.getTimeZone("Africa/Cairo")), 
            new Quarter(1,2023).getLastMillisecond(TimeZone.getTimeZone("Africa/Cairo"))
            );

        // Next quarter
        quarter2 = new Quarter(3,2023);
        assertEquals(1696111199999L, quarter2.getLastMillisecond());

        // Prev Quarter
        quarter2 = new Quarter(1,2023);
        assertEquals(1680299999999L, quarter2.getLastMillisecond());

        // Upcoming
        quarter2 = new Quarter(4,2029);
        assertEquals(1893448799999L, quarter2.getLastMillisecond());

        // Past
        quarter2 = new Quarter(2,2000);
        assertEquals(962398799999L, quarter2.getLastMillisecond());
    }

    /*@Test public void getLastMillisecond2() {
        arrange();
        Quarter quarter2 = new Quarter(3,2023);
        assertEquals(1696111199999L, quarter2.getLastMillisecond());
    }

    @Test public void getLastMillisecond3() {
        arrange();
        Quarter quarter2 = new Quarter(1,2023);
        assertEquals(1680299999999L, quarter2.getLastMillisecond());
    }

    @Test public void getLastMillisecon4() {
        arrange();
        Quarter quarter2 = new Quarter(4,2029);
        assertEquals(1893448799999L, quarter2.getLastMillisecond());
    }

    @Test public void getLastMillisecond5() {
        arrange();
        Quarter quarter2 = new Quarter(2,2000);
        assertEquals(962398799999L, quarter2.getLastMillisecond());
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //parseQuarter

    @Test public void testParseQuarter() {
        arrange();

        // Current quarter
        Quarter quarter2 = new Quarter();
        assertEquals(quarter2, Quarter.parseQuarter("Q2-2023"));
        assertEquals(quarter2, Quarter.parseQuarter("2023-Q2")); // Year (-) quarter
        assertEquals(quarter2, Quarter.parseQuarter("2023 Q2")); // Year ( ) quarter
        assertEquals(quarter2, Quarter.parseQuarter("2023/Q2")); // Year (/) quarter
        assertEquals(quarter2, Quarter.parseQuarter("2023,Q2")); // Year (,) quarter

        // Next quarter
        quarter2 = new Quarter(3,2023);
        assertEquals(quarter2, Quarter.parseQuarter("Q3-2023")); // Quarter (-) year

        // Prev Quarter
        quarter2 = new Quarter(1,2023);
        assertEquals(quarter2, Quarter.parseQuarter("Q1 2023")); // Quarter ( ) year

        // Upcoming
        quarter2 = new Quarter(4,2050);
        assertEquals(quarter2, Quarter.parseQuarter("2050/Q4")); // Quarter (/) year

        // Past
        quarter2 = new Quarter(2,1998);
        assertEquals(quarter2, Quarter.parseQuarter("1998,Q2")); // Quarter (,) year

    }

}
