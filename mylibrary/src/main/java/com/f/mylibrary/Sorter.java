package com.f.mylibrary;

public class Sorter {

    public static void bubbleSort(int[] nums){
        int n = nums.length, bubble;
        for (int i = n - 1; i > -1; i --){
            bubble = nums[0];
            for (int j = 0; j <= i; j ++){
                if (bubble > nums[j]){
                    nums[j - 1] = nums[j];
                    nums[j] = bubble;
                }else {
                    bubble = nums[j];
                }
            }
        }
    }

    public static void insertionSort(int[] nums){
        int n = nums.length, foo, temp;
        for (int i = 1; i < n; i ++){
            foo = i;
            while (foo > 0 && nums[foo] < nums[foo - 1]){
                temp = nums[foo - 1];
                nums[foo - 1] = nums[foo];
                nums[foo --] = temp;
            }
        }
    }

    public static void mergeSort(int[] nums){
        mergeSort(nums, new int[nums.length], 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums,  int[] aux, int start, int end){
        if (end - start < 1)    return;
        else {
            int mid = (start + end) / 2;
            mergeSort(nums, aux, start, mid);
            mergeSort(nums, aux, mid + 1, end);
            int p1 = start, p2 = mid + 1, l = start;
            while (p1 <= mid || p2 <= end){
                if (p1 > mid)                   aux[l++] = nums[p2 ++];
                else if (p2 > end)              aux[l++] = nums[p1 ++];
                else if (nums[p1] > nums[p2])   aux[l++] = nums[p2 ++];
                else                            aux[l++] = nums[p1 ++];
            }
            for (int i = start; i <= end; i ++)
                nums[i] = aux[i];
        }
    }

    public static int[] mergeSortIteratively(int[] nums){
        int n = nums.length, step = 1, aux[] = new int[n], start, mid, end, p1, p2;
        while (step < n){
            for (int i = 0; i < n; i += 2 * step){
                start = i;
                mid = start + step > n ? n : start + step;
                end = mid + step > n ? n : mid + step;
                p1 = start;
                p2 = mid;
                while (p1 < mid || p2 < end){
                    if (p1 >= mid)                   aux[start++] = nums[p2 ++];
                    else if (p2 >= end)              aux[start++] = nums[p1 ++];
                    else if (nums[p1] > nums[p2])   aux[start++] = nums[p2 ++];
                    else                            aux[start++] = nums[p1 ++];
                }
            }
            int[] temp = aux;
            aux = nums;
            nums = temp;
            step *= 2;
        }
        return nums;
    }

}
