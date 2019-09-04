package com.f.mylibrary;

import org.junit.Test;

import java.util.Arrays;

public class SortTester {

    @Test
    public void bubbleSortTest(){
        int[] nums = new int[]{0, 1,9, 10,6, 2, 7, 3, 5, 5};
        System.out.println(Arrays.toString(nums));
        Sorter.bubbleSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,5, 5, 0, 0, 0, 5, 5, 5, 5};
        System.out.println(Arrays.toString(nums));
        Sorter.bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void insertionSortTest(){
        int[] nums = new int[]{0, 1,9, 10,6, 2, 7, 3, 5, 5};
        System.out.println(Arrays.toString(nums));
        Sorter.insertionSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,5, 5, 0, 0, 0, 5, 5, 5, 5};
        System.out.println(Arrays.toString(nums));
        Sorter.insertionSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 3,8, 9, 10, 7, 7};
        System.out.println(Arrays.toString(nums));
        Sorter.insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    @Test
    public void mergeSortTest(){
        int[] nums = new int[]{0, 1,9, 10,6, 2, 7, 3, 5, 5};
        System.out.println(Arrays.toString(nums));
        Sorter.mergeSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,5, 5, 0, 0, 0, 5, 5, 5, 5};
        System.out.println(Arrays.toString(nums));
        Sorter.mergeSort(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 3,8, 9, 10, 7, 7};
        System.out.println(Arrays.toString(nums));
        Sorter.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    @Test
    public void mergeSortIterativelyTest(){
        int[] nums = new int[]{0, 1,9, 10,6, 2, 7, 3, 5, 5};
        System.out.println(Arrays.toString(nums));
        nums = Sorter.mergeSortIteratively(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,5, 5, 0, 0, 0, 5, 5, 5, 5};
        System.out.println(Arrays.toString(nums));
        nums = Sorter.mergeSortIteratively(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 3,8, 9, 10, 7, 7};
        System.out.println(Arrays.toString(nums));
        nums = Sorter.mergeSortIteratively(nums);
        System.out.println(Arrays.toString(nums));
    }
}
