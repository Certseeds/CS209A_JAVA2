package com.nanoseeds.week02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * @Github: https://github.com/Certseeds
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-02-27 14:23:02
 * @LastEditors : nanoseeds
 * @LastEditTime : 2020-03-02 16:40:27
 */

public class test_Sorting {
    int[] data = {3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6};
    int[] nums = new int[data.length];
    int[] zeros = new int[data.length];
    //int[] results = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] results = new int[data.length];

    @BeforeEach
    public void before_operas() {
        nums = new int[data.length];
        zeros = new int[data.length];
        results = new int[data.length];
        System.arraycopy(data, 0, nums, 0, data.length);
        System.arraycopy(data, 0, results, 0, nums.length);
        Arrays.sort(results);
    }

    @Test
    public void tests() {

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
