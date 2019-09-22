package com.f.mylibrary;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testNextP(){
        int[] nums = new int[]{1,2,3,4,5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{5, 7, 7, 6 ,5};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{10, 9, 8, 7, 6};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{ 6};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{2,3,1,3,3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

    }

    private void nextPermutation(int[] nums){
        int max = Integer.MIN_VALUE, n = nums.length, left = 0, right = n - 1, temp;
        for (int i = n - 1; i > -1; i --){
            temp = nums[i];
            if (temp >= max)
                max = temp;
            else {
                //increasing order appeared, find most close value
                int minGap = Integer.MAX_VALUE, pos = i + 1, gap;
                for (int j = i + 1; j <= n - 1; j ++){
                    gap = nums[j] - temp;
                    // find most close bigger value
                    if (gap > 0 && gap <= minGap){
                        minGap = gap;
                        pos = j;
                    }
                }
                nums[i] = nums[pos];
                nums[pos] = temp;
                left = i + 1;
                break;
            }
        }
        while(left < right){
            temp = nums[left];
            nums[left ++] = nums[right];
            nums[right --] = temp;
        }
        System.out.println("hello world");
    }
}