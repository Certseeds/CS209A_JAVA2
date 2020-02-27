package com.nanoseeds.week02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-02-27 14:23:02
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-02-27 14:23:02
 */
public class test_Sorting {
    @Test
    public void test_get_big_numbers() {
        int count = 1;
        for (int i = 0; i <= 10; i++) {
            assertEquals(count, SortRunningTimeSurvey.get_numbers(i));
            count *= 10;
        }
        assertEquals(100000001,SortRunningTimeSurvey.get_numbers(8)+1);
    }

}
