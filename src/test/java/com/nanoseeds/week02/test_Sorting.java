package com.nanoseeds.week02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-02-27 14:23:02
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-02-27 14:23:02
 */
public class test_Sorting {
    //  int[] nums = {10, 4, 0, 8, 2, 5, 1, 6, 3, 7, 9};
    int[] nums = {3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6};
    int[] zeros = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //int[] results = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] results = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9};

    @Before
    public void before_operas() {
        nums = new int[]{3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6};
        zeros = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    @Test
    public void test_get_big_numbers() {
        int count = 1;
        for (int i = 0; i <= 10; i++) {
            assertEquals(count, SortRunningTimeSurvey.get_numbers(i));
            count *= 10;
        }
        assertEquals(100000001, SortRunningTimeSurvey.get_numbers(8) + 1);
    }

    @Test
    public void test_insertsort() {
        SortRunningTimeSurvey.insertionSort(nums.length - 1, nums);
        System.out.println("test insert Sort");
        assertArrayEquals(results, nums);
    }

    @Test
    public void test_bubblesort() {
        SortRunningTimeSurvey.bubbleSort(nums.length - 1, nums);
        System.out.println("test bubble Sort");
        assertArrayEquals(results, nums);
    }

    @Test
    public void test_selectionsort() {
        SortRunningTimeSurvey.selectionSort(nums.length - 1, nums);
        System.out.println("test selection Sort");
        assertArrayEquals(results, nums);
    }

    @Test
    public void test_quicksort_rec() {
        SortRunningTimeSurvey.quickSort_rec(nums, 0, nums.length - 1);
        System.out.println("test quicksort Sort");
        assertArrayEquals(results, nums);
    }

    @Test
    public void test_mergesort_rec() {
        SortRunningTimeSurvey.mergesort_rec(nums, zeros, 0, nums.length - 1);
        System.out.println("test merge Sort");
        assertArrayEquals(results, nums);
    }

    @Test
    public void test_heapsort_rec() {
        SortRunningTimeSurvey.heapSort(nums.length - 1, nums);
        System.out.println("test merge Sort");
        assertArrayEquals(results, nums);
    }

}
