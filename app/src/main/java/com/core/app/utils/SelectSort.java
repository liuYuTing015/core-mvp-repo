package com.core.app.utils;

/**
 * Created by Ting on 17/6/13.
 */

public class SelectSort {
    /**
     * 冒泡排序
     */

    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j < i; j--) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     * 以此类推，直到所有元素均排序完毕。
     */

    public void chooseSort(int[] arr) {
        int temp = 0; //中间变量
        for (int i = 0; i < arr.length; i++) {
            int k = i;   //待确定的位置
            //选择出应该在第i个位置的数
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            //交换两个数
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
    }

    /**
     * 插入排序
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     */

    public void insertSort(int[] arr) {
        int tmp, j;
        for (int i = 1; i < arr.length; i++) {
            tmp = arr[i];
            j = i - 1;
            while (j >= 0 && tmp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;
        }
    }

    /**
     * 快速排序
     */

    public void quickSort(int[] arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];

        while (end > start) {
            while (end > start && arr[end] > key) {
                end--;
            }
            if (arr[end] <= key) {
                int tmp = arr[end];
                arr[end] = arr[start];
                arr[start] = tmp;
            }
            while (end > start && arr[start] > key) {
                start++;
            }

            if (arr[start] >= key) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
        }

        if (start > low) quickSort(arr, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) quickSort(arr, end + 1, high);//右边序列。从关键值索引+1到最后一个
    }

    /**
     * 二分法查找
     */

    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (end + start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
