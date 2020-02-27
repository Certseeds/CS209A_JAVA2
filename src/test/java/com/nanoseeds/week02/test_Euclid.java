package com.nanoseeds.week02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-02-27 13:33:59
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-02-27 13:33:59
 */
public class test_Euclid {
    @Test
    public void Normal_test_rec_Euclid_1() {
        assertEquals(3, Euclid.gcd_rec(12, 9));
        assertEquals(7, Euclid.gcd_rec(14, 21));
        assertEquals(11, Euclid.gcd_rec(121, 132));
        assertEquals(61, Euclid.gcd_rec(122, 61));
        assertEquals(13, Euclid.gcd_rec(169, 13));
    }
    @Test
    public void Normal_test_iter_Euclid(){

        assertEquals(3, Euclid.gcd_iter(12, 9));
        assertEquals(7, Euclid.gcd_iter(14, 21));
        assertEquals(11, Euclid.gcd_iter(121, 132));
        assertEquals(61, Euclid.gcd_iter(122, 61));
        assertEquals(13, Euclid.gcd_iter(169, 13));

    }

}
